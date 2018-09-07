/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util.mjf 
 *
 *    Filename:    MJFUtil.java 
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
 *    Create at:   2018年08月30日 17:10 
 *
 *    Revision: 
 *
 *    2018/8/30 17:10 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util.mjf;

import com.xiaoleilu.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.crypto.Cipher;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *  * @ClassName MJFUtil
 *  * @Description 明捷付支付工具类
 *  * @Author Hardy
 *  * @Date 2018年08月30日 17:10
 *  * @Version 1.0.0
 *  
 **/
public class MJFUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(MJFUtil.class);
    private static final String KEY_ALGORITHM = "RSA";
    private static final String CHARSET = "UTF-8";
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 功能描述:
     * 获取响应报文
     * @Author: Hardy
     * @Date: 2018年08月30日 17:24:28
     * @param in
     * @return: java.lang.String
     **/
    private static String getResponseBodyAsString(InputStream in) throws Exception{
        try {
            BufferedInputStream buf = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            StringBuffer data = new StringBuffer();
            int readDataLen;
            while ((readDataLen = buf.read(buffer)) != -1) {
                data.append(new String(buffer, 0, readDataLen, CHARSET));
            }
            System.out.println("响应报文=" + data);
            return data.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取响应报文异常:"+e.getMessage());
            throw new Exception("获取响应报文异常!");
        }
    }

    /**
     * 提交请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String request(String url, String params) throws Exception{
        try {
            System.out.println("请求报文:" + params);
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(1000 * 5);
            //conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            //conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
            OutputStream outStream = conn.getOutputStream();
            outStream.write(params.toString().getBytes(CHARSET));
            outStream.flush();
            outStream.close();
            return getResponseBodyAsString(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发起支付请求异常:"+e.getMessage());
            throw new Exception("发起支付请求异常!");
        }
    }

    /**
     * 功能描述:
     * MD5加密
     * @Author: Hardy
     * @Date: 2018年08月30日 17:22:53
     * @param s
     * @param encoding
     * @return: java.lang.String
     **/
    public final static String encrypt(String s) throws Exception{
        try {
            byte[] btInput = s.getBytes(CHARSET);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("MD5加密异常:"+e.getMessage());
            throw new Exception("MD5加密异常!");
        }
    }

    /**
     * 转换成Json格式
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        StringBuffer json = new StringBuffer();
        json.append("{");
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            json.append("\"").append(key).append("\"");
            json.append(":");
            json.append("\"").append(value).append("\"");
            if (it.hasNext()) {
                json.append(",");
            }
        }
        json.append("}");
        System.out.println("mapToJson=" + json.toString());
        return json.toString();
    }

    /**
     * 功能描述:
     * 公钥加密
     * @Author: Hardy
     * @Date: 2018年08月30日 17:15:49
     * @param data 待加密串
     * @param publicKey 公钥
     * @return: byte[]
     **/
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception{
        byte[] key = Base64Util.decode(publicKey);
        try {
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 密钥材料转换
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            // 产生公钥
            PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
            // 数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            int blockSize = cipher.getOutputSize(data.length) - 11;
            return doFinal(data, cipher,blockSize);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("加密异常:"+e.getMessage());
            throw new Exception("加密异常");
        }
    }

    /**
     * 功能描述:
     * 私钥解密
     * @Author: Hardy
     * @Date: 2018年08月30日 17:17:05
     * @param data 待解密串
     * @param privateKeyValue  私钥
     * @return: byte[]
     **/
    public static byte[] decryptByPrivateKey(byte[] data, String privateKeyValue) throws Exception{
        byte[] key = Base64Util.decode(privateKeyValue);
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int blockSize = cipher.getOutputSize(data.length);
            return doFinal(data, cipher,blockSize);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("私钥解密异常:"+e.getMessage());
            throw new Exception("私钥解密异常!");
        }
    }

    /**
     * 功能描述:
     * 加密解密共用核心代码，分段加密解密
     * @Author: Hardy
     * @Date: 2018年08月30日 17:18:38
     * @param decryptData
     * @param cipher
     * @param blockSize
     * @return: byte[]
     **/
    public static byte[] doFinal(byte[] decryptData, Cipher cipher,int blockSize)throws Exception {
        try {
            int offSet = 0;
            byte[] cache = null;
            int i = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while (decryptData.length - offSet > 0) {
                if (decryptData.length - offSet > blockSize) {
                    cache = cipher.doFinal(decryptData, offSet, blockSize);
                } else {
                    cache = cipher.doFinal(decryptData, offSet, decryptData.length - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * blockSize;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("加密解密核心算法异常:"+e.getMessage());
            throw new Exception("加密解密核心算法异常!");
        }
    }

    /**
     * 功能描述:
     * json转map
     * @Author: Hardy
     * @Date: 2018年08月30日 17:19:04
     * @param json
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String, String> jsonToMap(JSONObject json){
        Map<String, String> data = new TreeMap<>();
        Iterator<String> iterator = json.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String value = json.getStr(key);
            data.put(key, value);
        }
        return data;
    }
}
