package com.lrm;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;


class DESUtils {

    private static Key key;
    private static final String PRIVATE_KEY = "ABC";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(PRIVATE_KEY.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密，返回BASE64的加密字符串
     * @param str
     * @return
     */
    public static String getEncryptString(String str) throws Exception {
        byte[] strBytes = str.getBytes("UTF-8");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptStrBytes = cipher.doFinal(strBytes);
        return Base64.getEncoder().encodeToString(encryptStrBytes);
    }

    /**
     * 对BASE64加密字符串进行解密
     * @param str
     * @return
     */
    public static String getDecryptString(String str) throws Exception {
        byte[] strBytes = Base64.getDecoder().decode(str);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptStrBytes = cipher.doFinal(strBytes);
        return new String(encryptStrBytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String name = "201720724黄冠雅";
        String password = "Cat<%1?2>Dog";
        System.out.println("明文：" + name);
        System.out.println("明文：" + password);
        String encryname = getEncryptString(name);
        String encrypassword = getEncryptString(password);
        System.out.println("加密：" + encryname);
        System.out.println("加密：" + encrypassword);

        System.out.println("解密：" + getDecryptString(encryname));
        System.out.println("解密：" + getDecryptString(encrypassword));
    }
}