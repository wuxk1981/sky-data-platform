/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    HttpUtils.java 
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
 *    Create at:   2018年08月26日 17:56 
 *
 *    Revision: 
 *
 *    2018/8/26 17:56 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  * @ClassName HttpUtils
 *  * @Description HttpClient请求工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 17:56
 *  * @Version 1.0.0
 *  
 **/
public class HttpUtil {

    //日志
    public static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 功能描述:
     * 适用于有些http请求不需要传contentType的
     * @Author: Hardy
     * @Date: 2018年08月31日 18:39:40
     * @param data
     * @param url
     * @param contentType
     * @return: java.lang.String
     **/
    public static String post(Map<String,String> data,String url,String contentType) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();
            HttpPost httppost = new HttpPost(url);
            if(data != null){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for(Map.Entry<String, String> entry : data.entrySet()){
                    NameValuePair v = new BasicNameValuePair(entry.getKey(),entry.getValue());
                    nvps.add(v);
                }
                httppost.setEntity(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
                if(StrUtil.isNotBlank(contentType)){
                    httppost.setHeader("Content-Type", "application/json");
                }
            }
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }

    public static String post(String data,String url,String contentType) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();
            HttpPost httppost = new HttpPost(url);
            // 设置包体参数。
            StringEntity se = new StringEntity(data);
            se.setContentEncoding("UTF-8");
            if(StrUtil.isNotBlank(contentType)){
                se.setContentType("application/json");
            }
            httppost.setEntity(se);
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }

    /**
     * 功能描述:
     * content-type:application/json
     * @Author: Hardy
     * @Date: 2018年08月31日 17:19:54
     * @param data
     * @param payUrl
     * @return: java.lang.String
     **/
    public static String toPostJson(Map<String,String> data,String url) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();
            HttpPost httppost = new HttpPost(url);
            if(data != null){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for(Map.Entry<String, String> entry : data.entrySet()){
                    NameValuePair v = new BasicNameValuePair(entry.getKey(),entry.getValue());
                    nvps.add(v);
                }
                httppost.setEntity(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
                httppost.setHeader("Content-Type", "application/json");
            }
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }

    /**
     * 功能描述:
     * content-type:application/json
     * @Author: Hardy
     * @Date: 2018年08月31日 17:31:52
     * @param data
     * @param url
     * @return: java.lang.String
     **/
    public static String toPostJson(String data,String url) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();
            HttpPost httppost = new HttpPost(url);
            // 设置包体参数。
            StringEntity se = new StringEntity(data);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");
            httppost.setEntity(se);
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }

    /**
     * 功能描述:
     * content-type:application/x-www-form-urlencoded
     * @Author: Hardy
     * @Date: 2018年08月31日 17:34:20
     * @param data
     * @param url
     * @return: java.lang.String
     **/
    public static String toPostForm(Map<String,String> data,String url) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();
            HttpPost httppost = new HttpPost(url);
            if(data != null){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for(Map.Entry<String, String> entry : data.entrySet()){
                    NameValuePair v = new BasicNameValuePair(entry.getKey(),entry.getValue());
                    nvps.add(v);
                }
                httppost.setEntity(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
                httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            }
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }

    /**
     * 功能描述:
     * content-type:application/x-www-form-urlencoded
     * @Author: Hardy
     * @Date: 2018年08月31日 17:34:20
     * @param data
     * @param url
     * @return: java.lang.String
     **/
    public static String toPostForm(String data,String url) throws Exception{
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createConnectionManager())
                    .build();

            HttpPost httppost = new HttpPost(url);
            // 设置包体参数。
            StringEntity se = new StringEntity(data);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(se);
            return formatResponse(httpclient.execute(httppost));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("HTTPCLIENT-toPostJson请求异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            formatHttpClient(httpclient);
        }
    }
    /**
     * 功能描述:
     * 处理请求结果
     * @Author: Hardy
     * @Date: 2018年08月31日 17:22:17
     * @param
     * @return: java.lang.String
     **/
    private static String formatResponse(CloseableHttpResponse response) throws Exception{
        try {
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),Consts.UTF_8));
                StringBuffer sb = new StringBuffer();
                String content = null;
                while((content = reader.readLine()) != null){
                    sb.append(content);
                }
                return sb.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("处理HTTPCLIENT请求结果异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            response.close();
        }
    }

    /**
     * 功能描述:
     * 处理HTTPCLIENT连接
     * @Author: Hardy
     * @Date: 2018年08月31日 17:38:02
     * @param httpclient
     * @return: void
     **/
    private static void formatHttpClient(CloseableHttpClient httpclient) throws Exception{
        try {
            if(httpclient != null){
                httpclient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("关闭HTTPCLIENT连接异常:"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成表单
     * @Author: Hardy
     * @Date: 2018年08月31日 17:16:49
     * @param params
     * @param payUrl
     * @return: java.lang.String
     **/
    public static String generatorForm(Map<String, String> params,String payUrl) {
        String FormString = "<body onLoad=\"document.actform.submit()\">正在处理请稍候.....................<form  id=\"actform\" name=\"actform\" method=\"post\" action=\""
                + payUrl + "\">";
        for (String key : params.keySet()) {
            if (StrUtil.isNotBlank(params.get(key)))
                FormString += "<input name=\"" + key + "\" type=\"hidden\" value='" + params.get(key) + "'>\r\n";
        }
        FormString += "</form></body>";
        return FormString;
    }

    /**
     * 功能描述:
     * 创建连接
     * @Author: Hardy
     * @Date: 2018年08月31日 17:17:55
     * @param
     * @return: org.apache.http.impl.conn.PoolingHttpClientConnectionManager
     **/
    private static PoolingHttpClientConnectionManager createConnectionManager() throws Exception {
        TrustManager tm = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
        };
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[] { tm }, null);

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(context,
                NoopHostnameVerifier.INSTANCE);

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        return connectionManager;
    }
}
