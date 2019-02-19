package com.lee.demo.zk;




import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.wd.encrypt.AESUtil;
import com.wd.encrypt.EncryptUtil;
import com.wd.zk.util.ConfigLoader;
import com.wd.zk.util.NetUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.AbstractResource;

public class ZookeeperResource extends AbstractResource implements ApplicationContextAware, DisposableBean {
    private static Logger log = LoggerFactory.getLogger(ZookeeperResource.class);
    public static final String URL_HEADER = "zk://";
    public static String rootPath;
    public static String START_PATH = "/%s/start-configs";
    public static String DEVICE_PLATFORM_PATH = "/%s/deviceplatform";
    private static String path = "";
    ConcurrentMap<String, Object> recoverDataCache = Maps.newConcurrentMap();
    AbstractApplicationContext ctx;

    public ZookeeperResource() {
    }

    public boolean exists() {
        try {
            return null != ZKClient.getClient().checkExists().forPath("");
        } catch (Exception var2) {
            log.error("Falied to detect the config in zoo keeper.", var2);
            return false;
        }
    }

    public boolean isOpen() {
        return false;
    }

    public URL getURL() throws IOException {
        return new URL("zk://" + path);
    }

    public String getFilename() throws IllegalStateException {
        return path;
    }

    public String getDescription() {
        return "Zookeeper resouce at :'" + path;
    }

    public InputStream getInputStream() throws IOException {
        byte[] data = null;
        byte[] tmp1 = new byte[0];
        String rStr = System.getProperty("line.separator");

        try {
            if (ZKClient.getClient().checkExists().forPath(path) != null) {
                List<String> pathList = (List)ZKClient.getClient().getChildren().forPath(path);
                Iterator var6 = pathList.iterator();

                while(var6.hasNext()) {
                    String cPath = (String)var6.next();
                    String childNodeName = cPath.substring(cPath.lastIndexOf("\\") + 1, cPath.length());
                    String childNodeValue = new String((byte[])ZKClient.getClient().getData().forPath(path + "/" + cPath));
                    String nameAndValue = childNodeName + "=" + childNodeValue + rStr;
                    byte[] tmpByte1 = nameAndValue.getBytes();
                    data = new byte[tmpByte1.length + tmp1.length];
                    System.arraycopy(tmpByte1, 0, data, 0, tmpByte1.length);
                    if (tmp1.length > 0) {
                        System.arraycopy(tmp1, 0, data, tmpByte1.length, tmp1.length);
                    }

                    tmp1 = new byte[data.length];
                    System.arraycopy(data, 0, tmp1, 0, data.length);
                }
            } else {
                log.error("{} and {} none exists", "", path);
                System.exit(-1);
            }
        } catch (Exception var13) {
            log.error("zk server error", var13);

            try {
                data = ZKRecoverUtil.loadRecoverData(path);
            } catch (Exception var12) {
                log.error("zk server cloud_path error", var13);
                data = ZKRecoverUtil.loadRecoverData(path);
            }
        }

        ZKRecoverUtil.doRecover(data, path, this.recoverDataCache);
        log.debug("init get startconfig data {}", new String(data));
        if (EncryptUtil.isEncrypt(data)) {
            byte[] pureData = new byte[data.length - 2];
            System.arraycopy(data, 2, pureData, 0, data.length - 2);
            String originStr = null;

            try {
                originStr = AESUtil.aesDecrypt(new String(pureData), "i love thinkoy, and i love change");
            } catch (Exception var11) {
                log.error("decrypt error", var11);
                System.exit(-1);
            }

            return new ByteArrayInputStream(originStr.getBytes());
        } else {
            return new ByteArrayInputStream(data);
        }
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = (AbstractApplicationContext)ctx;
    }

    public void destroy() throws Exception {
        log.info("Destory Zookeeper Resouce.");
    }

    static {
        String projectName = ConfigLoader.getInstance().getProperty("project.name");
        String rootNode;
        if (Strings.isNullOrEmpty(projectName)) {
            rootNode = System.getProperty("user.dir");
            projectName = rootNode.substring(rootNode.lastIndexOf("\\") + 1, rootNode.length());
        }

        try {
            rootNode = ConfigLoader.getInstance().getProperty("localhost.root");
            if (!Strings.isNullOrEmpty(rootNode)) {
                String rootStr = rootNode.substring(1, rootNode.indexOf("/", 1));
                rootPath = rootStr;
                path = String.format(rootNode, projectName);
                START_PATH = String.format(START_PATH, rootStr);
                DEVICE_PLATFORM_PATH = String.format(DEVICE_PLATFORM_PATH, rootStr);
            } else {
                List<String> localIps = NetUtil.getLocalIps();
                Iterator var3 = localIps.iterator();

                while(var3.hasNext()) {
                    String ip = (String)var3.next();
                    rootNode = ConfigLoader.getInstance().getProperty(ip + ".root");
                    if (!Strings.isNullOrEmpty(rootNode)) {
                        String rootStr = rootNode.substring(1, rootNode.indexOf("/", 1));
                        rootPath = rootStr;
                        path = String.format(rootNode, projectName);
                        START_PATH = String.format(START_PATH, rootStr);
                        DEVICE_PLATFORM_PATH = String.format(DEVICE_PLATFORM_PATH, rootStr);
                        break;
                    }
                }
            }

            if ("".equals(path)) {
                throw new RuntimeException("zookeeper config not specified");
            }
        } catch (SocketException var6) {
            log.error("getlocalip error ", var6);
        }

    }
}
