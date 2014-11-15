package net.softwareminds.foosballbooking.client.config;

import net.softwareminds.foosballbooking.client.controller.FoosballBookingClientController;
import net.softwareminds.foosballbooking.client.oauth2.OAuthClientCredentialClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  @Scope("session")
  public OAuthClientCredentialClient oAuthClientCredentialClient() {
    return new OAuthClientCredentialClient();
  }

  @Bean
  @Scope("session")
  public FoosballBookingClientController foosballServiceController() {
    return new FoosballBookingClientController();
  }

  @Bean
  public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
    ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
    contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");

    MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
    defaultView.setExtractValueFromSingleKeyModel(true);

    ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
    contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
    contentViewResolver.setViewResolvers(Arrays.<ViewResolver>asList(viewResolver));
    contentViewResolver.setDefaultViews(Arrays.<View>asList(defaultView));
    return contentViewResolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

}
