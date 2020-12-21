package com.meals.security.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 加密工具类 加盐不可逆加密算法
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-07
 */
public class EncryptionUtils {
    private static String DEFAULT_ENCODING = "UTF-8";
    private static String SHA_256 = "SHA-256";

    /**
     * 获取16位随机盐值
     * @return
     */
    public static String getSalt()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 通过MD5加密
     * @param str 传入密码+盐值
     * @return
     */
    public static String encode(String str) {
        try {
            byte[] data = str.getBytes(DEFAULT_ENCODING);
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            return toHex(md.digest(data));
        } catch (Exception var5) {
            throw new RuntimeException("digest fail!", var5);
        }
    }

    /**
     * 将字节数组转为十六进制 并且截取前64位
     * @param input
     * @return
     */
    public static String toHex(byte[] input)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            //以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            //如果参数为负，那么无符号整数值为参数加上 2^32；否则等于该参数。将该值转换为十六进制（基数 16）的无前导 0 的 ASCII 数字字符串。
            //如果无符号数的大小值为零，则用一个零字符 '0' (’\u0030’) 表示它；否则，无符号数大小的表示形式中的第一个字符将不是零字符。
            //用以下字符作为十六进制数字【0123456789abcdef】。这些字符的范围是从【'\u0030' 到 '\u0039'】和从【'\u0061' 到 '\u0066'】。
            String hex = Integer.toHexString(input[i] & 0xFF);//其实核心也就这一样代码
            if (hex.length() == 1) hex = '0' + hex;
            sb.append(hex.toUpperCase());
        }
        String hex = sb.toString();
        return hex.length() > 64 ? hex.substring(0,63) : hex;
    }
}
