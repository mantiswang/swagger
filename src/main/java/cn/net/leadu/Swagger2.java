package cn.net.leadu;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ywang on 2017/6/12.
 */

@EnableSwagger2
@Configuration
public class Swagger2 {


  @Bean
  public Docket createRestApi() {

//    ArrayList list = new ArrayList(2);
//    list.add(new ResponseMessageBuilder()
//        .code(500)
//        .message("500 message")
//        .responseModel(new ModelRef("Error"))
//        .build());
//    list.add(new ResponseMessageBuilder()
//        .code(403)
//        .message("Forbidden!").build());

    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .groupName("模版工程")
        .apiInfo(apiInfo())
        .enableUrlTemplating(true)
//        .useDefaultResponseMessages(false)
//        .globalResponseMessage(RequestMethod.GET,
//            list)
        .forCodeGeneration(true)
        .select()
        .apis(RequestHandlerSelectors.basePackage("cn.net.leadu.controller"))
        .paths(PathSelectors.any())
        .build()
        .genericModelSubstitutes(Optional.class)
        ;

    return  docket;

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("接口文档")
        .description("xxx Restful APIs")
        .termsOfServiceUrl("")
//        .contact(new Contact("ywang","", "wangyue@leadu.com.cn"))
        .version("1.0")
        .build();
  }

}
