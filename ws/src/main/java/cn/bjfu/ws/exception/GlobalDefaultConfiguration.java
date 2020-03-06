package cn.bjfu.ws.exception;


import cn.bjfu.ws.exception.advice.CommonResponseDataAdvice;
import cn.bjfu.ws.exception.exception.GlobalDefaultExceptionHandler;
import cn.bjfu.ws.interceptor.TokenInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfiguration {
  @Bean
  public TokenInterceptor tokenInterceptor(){
    return new TokenInterceptor();
  }

  @Bean
  public GlobalDefaultExceptionHandler globalDefaultExceptionHandler() {
    return new GlobalDefaultExceptionHandler();
  }

  @Bean
  public CommonResponseDataAdvice commonResponseDataAdvice(GlobalDefaultProperties globalDefaultProperties){
    return new CommonResponseDataAdvice(globalDefaultProperties);
  }

}
