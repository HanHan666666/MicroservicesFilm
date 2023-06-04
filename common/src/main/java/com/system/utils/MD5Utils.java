package com.system.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    //调用MD5加密
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    //加密字符 基数
    private static final String privateKey = "1xc2d34f3p";

    //123123 ，密码加工
    public static String inputPassToNewPass(String pass) {
        String newPass =
                privateKey.charAt(0) + privateKey.charAt(1) + pass + privateKey.charAt(5) + "";
        return newPass;
    }

    public static void main(String[] args) {
        System.out.println(
                MD5Utils.md5(MD5Utils.inputPassToNewPass("123123")));
    }
}