package com.lee.demo.zk;


import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.lee.demo.encrypt.AESUtil;
import com.lee.demo.encrypt.EncryptUtil;
import com.lee.demo.zk.util.ConfigLoader;
import com.lee.demo.zk.util.NetUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.lee.sys.constant.Constants;
import com.lee.sys.exception.ConfigException;
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

    public static String rootPath;

    public static String START_PATH = "/%s/app-configs";

    public static String DEVICE_PLATFORM_PATH = "/%s/deviceplatform";

    private static String path = "";

    ConcurrentMap<String, Object> recoverDataCache = Maps.newConcurrentMap();

    AbstractApplicationContext ctx;

    public ZookeeperResource() {

    }

    public boolean exists() {

        try {

            return null != ZKClient.getClient().checkExists().forPath("");

        } catch (Exception ex) {

            log.error("Falied to detect the config in zookeeper", ex);

            return false;

        }
    }

    public boolean isOpen() {
        return false;
    }

    public URL getURL() throws IOException {

        return new URL(Constants.ZK_URL_HEADER + path);

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

                List<String> pathList = (List) ZKClient.getClient().getChildren().forPath(path);

                Iterator var6 = pathList.iterator();

                while (var6.hasNext()) {

                    String cPath = (String) var6.next();

                    String childNodeName = cPath.substring(cPath.lastIndexOf("\\") + 1);

                    String childNodeValue = new String((byte[]) ZKClient.getClient().getData().forPath(path + "/" + cPath));

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

        this.ctx = (AbstractApplicationContext) ctx;

    }

    public void destroy() throws Exception {

        log.info("Destory Zookeeper Resouce.");

    }

    static {

        String projectName = ConfigLoader.getInstance().getProperty("project.name");

        if (Strings.isNullOrEmpty(projectName)) {

            String projectNode = System.getProperty("user.dir");  //D:\workSpace\root

            projectName = projectNode.substring(projectNode.lastIndexOf("\\") + 1);//root
        }
        try {
            String configRootNode;

            configRootNode = ConfigLoader.getInstance().getProperty("localhost.root");

            if (!Strings.isNullOrEmpty(configRootNode)) {

                 rootPath = configRootNode.substring(1, configRootNode.indexOf("/", 1));//dev-environment

                path = String.format(configRootNode, projectName);//dev.../root

                START_PATH = String.format(START_PATH, rootPath);

                DEVICE_PLATFORM_PATH = String.format(DEVICE_PLATFORM_PATH, rootPath);

            } else {

                List<String> localIps = NetUtil.getLocalIps();

                Iterator ips = localIps.iterator();

                while (ips.hasNext()) {

                    String ip = (String) ips.next();

                    configRootNode = ConfigLoader.getInstance().getProperty(ip + ".root");

                    if (!Strings.isNullOrEmpty(configRootNode)) {

                        rootPath = configRootNode.substring(1, configRootNode.indexOf("/", 1));

                        path = String.format(configRootNode, projectName);

                        START_PATH = String.format(START_PATH, rootPath);

                        DEVICE_PLATFORM_PATH = String.format(DEVICE_PLATFORM_PATH, rootPath);

                        break;
                    }
                }
            }

            if (Constants.EMPTY_STRING.equals(path)) {

                throw new ConfigException("zookeeper config not specified");
            }
        } catch (SocketException se) {

            log.error("getlocalip error ", se);
        }

    }

}
