/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    FilesUtil.java 
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
 *    Create at:   2018年09月03日 15:11 
 *
 *    Revision: 
 *
 *    2018/9/3 15:11 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *  * @ClassName FilesUtil
 *  * @Description 文件工具类
 *  * @Author Hardy
 *  * @Date 2018年09月03日 15:11
 *  * @Version 1.0.0
 *  
 **/
public class FilesUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(FilesUtil.class);

    /**
     * 功能描述:
     * 保存日志
     * @Author: Hardy
     * @Date: 2018年09月03日 15:22:53
     * @param fileName
     * @param data
     * @return: void
     **/
    public static void saveLog(String fileName,Map<String,String> data) throws Exception{
        logger.info("写日志开始=====================START=================================");
        BufferedWriter bufWriter = null;
        OutputStreamWriter osw = null;
        try {
            //读取配置文件内容
            Map<String,String> propertiesmap = PropertiesUtil.readFileProperties();
            //获取保存路劲
            String savePath = propertiesmap.get("savePath");
            File file = new File(savePath+fileName);
            if (!file.exists() && !file.isDirectory()) {
                logger.info("目录不存在，需要创建");
                file.mkdirs();
            }
            data.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            data.put("ErrorFrom",fileName);
            Calendar Cal=Calendar.getInstance();
            Cal.setTime(new Date());
            Cal.add(Calendar.HOUR_OF_DAY,-7);
            String todayStr=new SimpleDateFormat("yyyy-MM-dd").format(Cal.getTime());

            File f = new File(savePath+fileName+File.separator+todayStr+".txt");
            if(!f.exists()){
                f.createNewFile();
            }
            osw = new OutputStreamWriter(new FileOutputStream(f, true),"UTF-8");
            bufWriter = new BufferedWriter(osw);
            JSONObject json = JSONUtil.parseObj(data);
            bufWriter.newLine();
            bufWriter.write(json.toString());
            bufWriter.newLine();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("save log faild..."+e.getMessage());
            throw new Exception("save log faild!");
        } finally {
            bufWriter.close();
            osw.close();
        }
    }
}
