package com.easicare.device.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger接口文档配置
 * @author df
 * @date 2019/8/6
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * Docket：如果需要分组显示不同文件夹下的controller，可以像上面代码那样写，否则写一个Docket就好了
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.easicare.device.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API描述信息")
            .description("接口访问地址：http://192.168.1.134:8080/doc.html")
            .contact(new Contact("", null, null))
            .version("1.0")
            .build();
    }

}
