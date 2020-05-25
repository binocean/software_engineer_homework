package com.seehin.softwareclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 12:14
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }

    /**
     * 所有的WebMvcConfigurerAdapter组件都会一起起作用
     * 将组件注册在容器
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/user/login").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns
        ("/index.html","/","/user/login","/asserts/**","/webjars/**","/favicon.ico");
            }
        };
        return adapter;
    }

    /*@Bean
    public LocaleResolver localeResolver() {

        return new MyLocaleResolver();
    }*/

}