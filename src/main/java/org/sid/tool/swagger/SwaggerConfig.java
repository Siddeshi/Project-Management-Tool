package org.sid.tool.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.sid.tool"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API", "Spring Boot REST API for Prject Management Tool",
                "1.0",
                "Terms of service",
                "Siddesh",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }

    /*
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/project-management-tool.*"), regex("/project-management-tool.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Project Management Tool API")
                .description("API reference for developers")
                .termsOfServiceUrl("http://tool.sid.org")
                .contact("siddusrb@gmail.com").license("Apache License")
                .licenseUrl("siddusrb@gmail.com").version("1.0").build();
    }*/
}
