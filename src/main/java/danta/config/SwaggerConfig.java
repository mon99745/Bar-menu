package danta.config;


import danta.controller.user.UserRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Slf4j
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드
                .apiInfo(apiInfo())
                .tags(new Tag(UserRestController.TAG, "회원 로직을 관리하는 Rest API", 110))
//                new Tag(UserTestRestController.TAG, "회원 로직을 테스트하는 관리하는 Rest API", 210),
//                new Tag(OrderController.TAG, "결제 로직을 관리하는 API", 210),
//                new Tag(MyOrderController.TAG, "자신의 결제 로직을 관리하는 API", 210),
//                new Tag(MyOrderRestController.TAG,"자신의 결제 로직을 관리하는 Rest API", 210));
                .select()
                .apis(RequestHandlerSelectors.basePackage("danta.controller"))
                .build();

    }

    public ApiInfo apiInfo() {
        log.info("apiInfo");
        return new ApiInfoBuilder()
                .title("SpringBoot Rest API Documentation")
                .description("springboot rest api practice.")
                .version("0.1")
                .build();
    }
}