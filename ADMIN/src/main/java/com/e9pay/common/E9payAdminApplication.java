package com.e9pay.common;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.e9pay")
@EnableJpaRepositories(basePackages = "com.e9pay.repository")
@EntityScan(basePackages="com.e9pay.entity")
public class E9payAdminApplication implements WebMvcConfigurer {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new SpringApplicationBuilder(E9payAdminApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

        // checkBeansPresence("loginController", "loginDao");
    }

    private static void checkBeansPresence(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " +
                    applicationContext.containsBean(beanName));
        }
    }

}
