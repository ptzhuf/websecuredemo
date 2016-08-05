package org.hmzb.xss;

import org.hmzb.xss.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

@SpringBootApplication
public class XssDemoApplication implements EmbeddedServletContainerCustomizer {
    @Autowired
    private LoginInterceptor interceptor;

    public static void main(String[] args) {
        SpringApplication.run(XssDemoApplication.class, args);
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        HttpPutFormContentFilter filter = new HttpPutFormContentFilter();
        filter.setCharset(Charset.forName("utf-8"));
        return filter;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login");
            }

        };
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        // 开启session http only 防御cookie的jsessionid被js操作窃取
        ((TomcatEmbeddedServletContainerFactory) container).addContextCustomizers(context -> context.setUseHttpOnly(false));
    }
}
