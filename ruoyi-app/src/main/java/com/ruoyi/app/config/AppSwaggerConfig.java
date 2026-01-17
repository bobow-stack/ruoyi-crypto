package com.ruoyi.app.config;

import com.ruoyi.common.config.RuoYiConfig;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * APP 端 Swagger 配置
 */
@Configuration
public class AppSwaggerConfig
{
    @Autowired
    private RuoYiConfig ruoyiConfig;

    @Value("${swagger.enabled}")
    private boolean enabled;

    @Value("${swagger.pathMapping}")
    private String pathMapping;

    @Bean
    public Docket appApi()
    {
        return new Docket(DocumentationType.OAS_30)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi.app.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(pathMapping);
    }

    private List<SecurityScheme> securitySchemes()
    {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("App-Authorization", "App-Authorization", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts()
    {
        List<SecurityContext> contexts = new ArrayList<>();
        contexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(o -> o.requestMappingPattern().matches("/app/.*"))
                .build());
        return contexts;
    }

    private List<SecurityReference> defaultAuth()
    {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        List<SecurityReference> references = new ArrayList<>();
        references.add(new SecurityReference("App-Authorization", authorizationScopes));
        return references;
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("RuoYi APP 接口文档")
                .description("APP 端接口（/app/**），使用 App-Authorization 头传递 token")
                .contact(new Contact(ruoyiConfig.getName(), null, null))
                .version("版本号：" + ruoyiConfig.getVersion())
                .build();
    }
}
