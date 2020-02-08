package com.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * mybatis逆向工程，创建generator.xml和 Generator.java文件
 * 运行main函数，即可创建pojo,映射文件，接口，等
 * 只针对单表操作
 * @author 王少彬。。。。
 *
 */
public class Generator {
	public static void main(String[] args)  throws Exception{
		 
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        File configFile = new File("generator.xml"); 
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //将配置文件的信息转化为Configuration对象
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //逆向工程核心对象
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
        myBatisGenerator.generate(null);
	}
}
