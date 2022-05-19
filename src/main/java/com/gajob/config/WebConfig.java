package com.gajob.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // 프로필 이미지 업로드 폴더 경로 설정
  @Value("/Users/jieun/Desktop/")
  private String profileUploadFolder;

  public void addResoucreHandlers(ResourceHandlerRegistry registry) {
    WebMvcConfigurer.super.addResourceHandlers(registry);

    registry.addResourceHandler("/Desktop/**")
        .addResourceLocations("file:///" + profileUploadFolder).setCachePeriod(60 * 10 * 6)
        .resourceChain(true).addResolver(new PathResourceResolver());

  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("*")
        .allowedMethods("*")
        .allowedOrigins("http://localhost:3000");
  }
}
