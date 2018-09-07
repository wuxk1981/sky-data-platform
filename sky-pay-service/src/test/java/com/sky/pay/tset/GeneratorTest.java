package com.sky.pay.tset;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorTest {

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("D:\\spring-cloud-workspace\\sky-code\\sky-data-platform\\sky-pay-service\\src\\main\\resources\\conf\\genarator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    } 
    public static void main(String[] args) throws Exception {
        try {
            GeneratorTest generator = new GeneratorTest();
            generator.generator();
            System.err.println("生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
