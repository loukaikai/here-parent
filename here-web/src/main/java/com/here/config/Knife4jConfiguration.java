
package com.here.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;


/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName Knife4jConfiguration.java
 * @Description TODO
 * @createTime 2023年01月14日 17:35:00
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)//导入其他的配置类 让配置生效
public class Knife4jConfiguration {

    @Bean
    public Docket buildDocket() {
        HashSet<String> strings = new HashSet<>();
        strings.add("application/json");
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                //设置返回数据类型
                .produces(strings)
                //分组名称
                .groupName("here-web")
                .select()
                //这里指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.here.modules"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("here元宇宙后端","","");
        return new ApiInfoBuilder()
                .title("here元宇宙后端")
                .description("here元宇宙后端")
                .contact(contact)
                .version("1.0.0").build();
    }
}
