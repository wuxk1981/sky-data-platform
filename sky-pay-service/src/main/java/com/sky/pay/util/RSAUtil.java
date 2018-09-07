/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    RSAUtil.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 16:26 
 *
 *    Revision: 
 *
 *    2018/8/26 16:26 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.*;
import java.util.Enumeration;

/**
 *  * @ClassName RSAUtil
 *  * @Description RSA加密工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 16:26
 *  * @Version 1.0.0
 *  
 **/
public class RSAUtil {
    private static final String ALG_RSA = "RSA";
    private static final String ALG_DSA = "DSA";
    /**
     * 功能描述:
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @Author: Hardy
     * @Date: 2018年08月26日 16:32:11
     * @return: java.lang.String
     **/
    public static String encrypt(String data, String publicKey) {
        String encryptData = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFac.generatePublic(new X509EncodedKeySpec(hexString2ByteArr(publicKey)));

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] result = cipher(data.getBytes("UTF-8"), cipher, getBlockSize(pubKey) - 11);
            encryptData = byteArr2HexString(result);
        } catch (Exception e) {
            e.printStackTrace();
            encryptData = null;
        }
        return encryptData;
    }


    /**
     * 功能描述:
     * 私钥解密
     *
     * @param encryptedData
     * @param privateKey
     * @Author: Hardy
     * @Date: 2018年08月26日 16:32:34
     * @return: java.lang.String
     **/
    public static String decrypt(String encryptedData, String privateKey) {
        String decryptData = null;
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(hexString2ByteArr(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] result = cipher(hexString2ByteArr(encryptedData), cipher, getBlockSize(priKey));
            decryptData = new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            decryptData = null;
        }
        return decryptData;
    }

    /**
     * 功能描述:
     * byte转String
     *
     * @param bytearr
     * @Author: Hardy
     * @Date: 2018年08月26日 16:30:08
     * @return: java.lang.String
     **/
    public static String byteArr2HexString(byte[] bytearr) {
        if (bytearr == null) {
            return "null";
        }
        StringBuffer sb = new StringBuffer();

        for (int k = 0; k < bytearr.length; k++) {
            if ((bytearr[k] & 0xFF) < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(bytearr[k] & 0xFF, 16));
        }
        return sb.toString();
    }

    /**
     * 功能描述:
     * String 转 byte
     *
     * @param hexString
     * @Author: Hardy
     * @Date: 2018年08月26日 16:30:28
     * @return: byte[]
     **/
    public static byte[] hexString2ByteArr(String hexString) {
        if ((hexString == null) || (hexString.length() % 2 != 0)) {
            return new byte[0];
        }

        byte[] dest = new byte[hexString.length() / 2];

        for (int i = 0; i < dest.length; i++) {
            String val = hexString.substring(2 * i, 2 * i + 2);
            dest[i] = ((byte) Integer.parseInt(val, 16));
        }
        return dest;
    }

    // 转换成十六进制字符串
    public static String Byte2String(byte[] b) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            // if (n<b.length-1) hs=hs+":";
        }
        return hs.toUpperCase();
    }

    public static byte[] StringToByte(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = b.length - 1; i > -1; i--) {
            b[i] = new Integer(temp & 0xff).byteValue();// 将最高位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }

    private static byte[] cipher(byte[] data, Cipher cipher, int blockSize) throws Exception {
        final ByteArrayInputStream in = new ByteArrayInputStream(data);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] cache = new byte[blockSize];
        while (true) {
            final int r = in.read(cache);
            if (r < 0) {
                break;
            }
            final byte[] temp = cipher.doFinal(cache, 0, r);
            out.write(temp, 0, temp.length);
        }
        return out.toByteArray();
    }

    private static int getBlockSize(final Key key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String alg = key.getAlgorithm();
        final KeyFactory keyFactory = KeyFactory.getInstance(alg);
        if (key instanceof PublicKey) {
            final BigInteger prime;
            if (ALG_RSA.equals(alg)) {
                prime = keyFactory.getKeySpec(key, RSAPublicKeySpec.class).getModulus();
            } else if (ALG_DSA.equals(alg)) {
                prime = keyFactory.getKeySpec(key, DSAPublicKeySpec.class).getP();
            } else {
                throw new NoSuchAlgorithmException("不支持的解密算法：" + alg);
            }
            return prime.toString(2).length() / 8;
        } else if (key instanceof PrivateKey) {
            final BigInteger prime;
            if (ALG_RSA.equals(alg)) {
                prime = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class).getModulus();
            } else if (ALG_DSA.equals(alg)) {
                prime = keyFactory.getKeySpec(key, DSAPrivateKeySpec.class).getP();
            } else {
                throw new NoSuchAlgorithmException("不支持的解密算法：" + alg);
            }
            return prime.toString(2).length() / 8;
        } else {
            throw new RuntimeException("不支持的密钥类型：" + key.getClass());
        }
    }

    public PrivateKey GetPvkformPfx(String strPfx, String strPassword) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(strPfx);
            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            System.out.println("keystore type=" + ks.getType());
            // Now we loop all the aliases, we need the alias to get keys.
            // It seems that this value is the "Friendly name" field in the
            // detals tab <-- Certificate window <-- view <-- Certificate
            // Button <-- Content tab <-- Internet Options <-- Tools menu
            // In MS IE 6.
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
            }
            // Now once we know the alias, we could get the keys.
            System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
            System.out.println("cert class = " + cert.getClass().getName());
            System.out.println("cert = " + cert);
            System.out.println("public key = " + pubkey);
            System.out.println("private key = " + prikey);
            return prikey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述:
     * 签名
     * @Author: Hardy
     * @Date: 2018年08月29日 22:54:43
     * @param data
     * @param privateKey
     * @return: java.lang.String
     **/
    public static String sign(String data, String privateKey)throws Exception{
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(hexString2ByteArr(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);
            Signature si = Signature.getInstance("SHA1WithRSA");
            si.initSign(priKey);
            si.update(data.getBytes("UTF-8"));
            byte[] dataSign = si.sign();
            return byteArr2HexString(dataSign);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("签名异常!");
        }
    }
}
