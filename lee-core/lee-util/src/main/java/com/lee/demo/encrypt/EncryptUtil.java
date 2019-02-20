package com.lee.demo.encrypt;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptUtil {
    private static final byte[] encryptTag = new byte[]{83, 1};
    private static final char oneChar = '\u0001';
    public static final String encryptStr = "S\u0001";
    public static final String encryptKey = "i love thinkoy, and i love change";
    private static final int ENCODE_ID_INSERT_INDEX = 5;

    public EncryptUtil() {
    }

    public static boolean isEncrypt(byte[] datas) {
        return datas[0] == encryptTag[0] && datas[1] == encryptTag[1];
    }

    public static String encrypt(String cryptStr) throws Exception {
        return "S\u0001" + AESUtil.aesEncrypt(cryptStr, "i love thinkoy, and i love change");
    }

    public static String decrypt(String encryptStr) throws Exception {
        String realEncryptStr = encryptStr.substring(2);
        return AESUtil.aesDecrypt(realEncryptStr, "i love thinkoy, and i love change");
    }

    public static final String MD5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            return byteArrayToHex(mdTemp.digest());
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int j = byteArray.length;
        char[] str = new char[j * 2];
        int k = 0;

        for(int i = 0; i < j; ++i) {
            byte byte0 = byteArray[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        return new String(str);
    }

    public static String fileMd5(File file) {
        FileInputStream in = null;

        String var5;
        try {
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(MapMode.READ_ONLY, 0L, file.length());
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(byteBuffer);
            var5 = byteArrayToHex(messagedigest.digest());
        } catch (NoSuchAlgorithmException var15) {
            var15.printStackTrace();
            throw new RuntimeException(var15);
        } catch (Exception var16) {
            throw new RuntimeException(var16);
        } finally {
            try {
                in.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

        return var5;
    }

    public static final String encodeId(int id) {
        String _id = String.valueOf(id);
        if (id >= 0 && _id.length() <= 9) {
            StringBuilder buf = new StringBuilder();
            buf.append(System.currentTimeMillis());

            for(int i = 0; i < _id.length(); ++i) {
                buf.insert(5, _id.charAt(i));
            }

            buf.append(_id.length());
            return buf.toString();
        } else {
            return "";
        }
    }

    public static final int unencodeId(String str) {
        String times = System.currentTimeMillis() + "";
        if (str != null && str.length() >= times.length()) {
            if (!str.matches("\\d{13,}")) {
                return 0;
            } else {
                int len = Integer.parseInt(str.substring(str.length() - 1));
                return str.length() != times.length() + len + 1 ? 0 : Integer.parseInt((new StringBuilder(str.substring(5, 5 + len))).reverse().toString());
            }
        } else {
            return 0;
        }
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    public static String getSha1(String text) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("sha-1");
            byte[] byteText = text.getBytes();
            md.update(byteText);
            byte[] sha1 = md.digest();
            return byte2hex(sha1);
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static byte[] des3Encrypt(byte[] mykey, String str) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(mykey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(1, securekey);
        byte[] b = cipher.doFinal(str.getBytes());
        return b;
    }
}

