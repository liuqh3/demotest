package com.liu.mybatis.demotest.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletConfigPropertySource;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.util.HashMap;

//导入druid数据源
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){

        return new DruidDataSource();

    }
    //配置Druid的监控 配置一个后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean  bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams= new HashMap<>();
        initParams.put("username","liu");
        initParams.put("password","123456");
        initParams.put("alow","");//默认允许所有访问
        initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    //配置一个filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        Map<String,String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPtterns(arrays.alist("/*"));
        return bean;
    }

}
