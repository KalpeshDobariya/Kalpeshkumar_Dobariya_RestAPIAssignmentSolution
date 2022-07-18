package com.my.employeeproject.config;
  
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
  
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType; 
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
  
  @Configuration
  
  @EnableSwagger2 public class SwaggerConfig {
  
  @Bean Docket employeeApi() { return new
  Docket(DocumentationType.SWAGGER_2).groupName("Employee-API") .select()
  .apis(RequestHandlerSelectors.basePackage(
  "com.my.employeeproject.controller")) .build(); } }
  
 