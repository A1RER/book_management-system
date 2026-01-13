package com.java.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 用于密码加密存储
 * @author refactored
 * @version 1.0
 */
public class MD5Util {

    /**
     * MD5加密
     * @param password 原始密码
     * @return 加密后的32位小写MD5字符串
     */
    public static String encrypt(String password) {
        if (StringNull.isEmpty(password)) {
            return null;
        }

        try {
            // 创建MD5加密对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对密码进行加密
            byte[] bytes = md.digest(password.getBytes());

            // 将字节数组转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                // 将每个字节转换为2位16进制数
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证密码是否匹配
     * @param inputPassword 用户输入的原始密码
     * @param dbPassword 数据库中存储的MD5密码
     * @return true-匹配 false-不匹配
     */
    public static boolean verify(String inputPassword, String dbPassword) {
        if (StringNull.isEmpty(inputPassword) || StringNull.isEmpty(dbPassword)) {
            return false;
        }
        // 将输入密码加密后与数据库密码比较
        String encryptedInput = encrypt(inputPassword);
        return dbPassword.equals(encryptedInput);
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        String password = "123456";
        String encrypted = encrypt(password);
        System.out.println("原始密码: " + password);
        System.out.println("加密后: " + encrypted);
        System.out.println("验证结果: " + verify(password, encrypted));
    }
}
