package com.lee.demo.zk;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.google.common.io.Files;
import com.lee.demo.zk.util.NamedThreadFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKRecoverUtil {
    public static final Logger logger = LoggerFactory.getLogger(ZKRecoverUtil.class);

    public ZKRecoverUtil() {
    }

    public static void doRecover(final byte[] content, final String path, final ConcurrentMap<String, Object> recoverDataCache) {
        Executors.newSingleThreadExecutor(new NamedThreadFactory("DYN-CONFIG-RECOVER")).execute(new Runnable() {
            public void run() {
                try {
                    Object oldCacheData = recoverDataCache.putIfAbsent(path, content);
                    String parentDir;
                    File recoveryFile;
                    if (oldCacheData == null) {
                        parentDir = System.getProperty("java.io.tmpdir");
                        recoveryFile = new File(parentDir + path);
                        Files.createParentDirs(recoveryFile);
                        Files.write(content, recoveryFile);
                    }

                    if (oldCacheData != null && content != oldCacheData) {
                        parentDir = System.getProperty("java.io.tmpdir");
                        recoveryFile = new File(parentDir + path);
                        Files.createParentDirs(recoveryFile);
                        Files.write(content, recoveryFile);
                    }

                    ZKRecoverUtil.logger.warn("path {} recover data", path);
                } catch (Exception var4) {
                    ZKRecoverUtil.logger.error("DYN-CONFIG-RECOVER error", var4);
                }

            }
        });
    }

    public static byte[] loadRecoverData(String path) throws IOException {
        String parentDir = System.getProperty("java.io.tmpdir");
        File recoveryFile = new File(parentDir + path);
        if (!recoveryFile.exists()) {
            Files.createParentDirs(recoveryFile);
        }

        return Files.toByteArray(recoveryFile);
    }
}
