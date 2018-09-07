package com.sky.log.persist.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.log.persist.dao.AddLogDao;
import com.sky.log.persist.model.AddLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @Autowired
    private AddLogDao addLogDao;

    @KafkaListener(topics = {"add-log"})
    public void receivePersistLog(String data) {
        logger.info("接收到需要保存到MongoDB的日志数据, data : " + data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AddLog addLog = objectMapper.readValue(data, AddLog.class);
            addLogDao.save(addLog);
            logger.info("成功保存日志数据, data : " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}