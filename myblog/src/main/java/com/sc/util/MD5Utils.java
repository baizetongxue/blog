package com.sc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by BaiZe schoolmate on 2020/5/10 17:26.
 */
public class MD5Utils {
    /**
     * md5加密
     * @param str 加密的字符串
     * @return  要加密的字符串
     */
    public static String code(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] bytes =md5.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset = 0; offset < bytes.length; offset++){
                i =bytes[offset];
                if (i<0){
                    i+=256;
                }
                if (i<16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
