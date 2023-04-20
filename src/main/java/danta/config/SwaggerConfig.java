package danta.config;


import danta.controller.order.MyOrderController;
import danta.controller.order.MyOrderRestController;
import danta.controller.order.OrderController;
import danta.controller.user.UserRestController;
import danta.controller.user.UserTestRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public ApiInfo apiInfo() {
        log.info("apiInfo");
        return new ApiInfoBuilder()
                .title("SpringBoot Practice Rest API Documentation")
                .description("springboot rest api practice.")
                .version("0.1")
                .build();
    }

//    public Docket api(Docket docket) {
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404) 등의 노출 여부
//                .apiInfo(apiInfo()) // Swagger UI 로 노출할 정보
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.danta/**")) // api 스펙이 작성되어 있는 패키지 (controller)
//                .paths(PathSelectors.any()) // apis 에 위치하는 API 중 특정 path 를 선택
//                .build()
//                .tags(new Tag(UserRestController.TAG, "회원 로직을 관리하는 Rest API", 110),
//                        new Tag(UserTestRestController.TAG, "회원 로직을 테스트하는 관리하는 Rest API", 210),
//                        new Tag(OrderController.TAG, "결제 로직을 관리하는 API", 210),
//                        new Tag(MyOrderController.TAG, "자신의 결제 로직을 관리하는 API", 210),
//                        new Tag(MyOrderRestController.TAG,"자신의 결제 로직을 관리하는 Rest API", 210));
//    }

    public Docket api(Docket docket) {
        //
        docket.tags(new Tag(UserRestController.TAG, "회원 로직을 관리하는 Rest API", 110),
                        new Tag(UserTestRestController.TAG, "회원 로직을 테스트하는 관리하는 Rest API", 210),
                        new Tag(OrderController.TAG, "결제 로직을 관리하는 API", 210),
                        new Tag(MyOrderController.TAG, "자신의 결제 로직을 관리하는 API", 210),
                        new Tag(MyOrderRestController.TAG,"자신의 결제 로직을 관리하는 Rest API", 210));
        return docket;
    }
}