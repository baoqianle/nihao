package com.turing.sb;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.turing.sb.mapper")
@ImportResource("classpath:spring-transaction.xml")
public class SpringbootmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        DruidDataSource ds=new DruidDataSource();
        return  ds;
    }
    //注册一个后台的监控Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //注册一个后台的监控servlet,并指定返回路径：location/*
        ServletRegistrationBean bean =new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //创建一个Map保存参数
        Map<String,String> param=new HashMap<>();
        //登录名
        param.put("loginUsername","liubin");
        //密码
        param.put("loginPassword","520520");
//        设置哪些IP可以访问，“”表示都可以访问
        param.put("allow","");
        //设置哪些IP不可以访问
        param.put("deny","192.168.56.100");
        //设置初始参数
        bean.setInitParameters(param);
        return  bean;
    }

    //注册一个专门的过滤器，用来监控哪些页面
    @Bean
    public FilterRegistrationBean webSatFilter(){
        //拦截器
        FilterRegistrationBean bean=new FilterRegistrationBean(new WebStatFilter());
        //拦截哪些资源
        bean.addUrlPatterns("/*");
        //设置一个map来保存初始参数
        Map<String,String> map=new HashMap<>();
        //排除不需要拦截的资源
        map.put("exclusions","*.js,*.css,*.png,*.woff,/druid/*");
        bean.setInitParameters(map);
        return  bean;
    }
}
