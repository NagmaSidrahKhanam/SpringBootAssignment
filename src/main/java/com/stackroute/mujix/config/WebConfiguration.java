package com.stackroute.mujix.config;

import com.stackroute.mujix.exceptions.TrackAlreadyExistsException;
import com.stackroute.mujix.model.Track;
import com.stackroute.mujix.service.TrackService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class WebConfiguration {
    private TrackService trackService;
@Autowired
    public WebConfiguration(TrackService userService) {
        this.trackService = userService;
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent cfr) {
        try {
            trackService.saveTrack(new Track(1,"Summer","Calvin Harris",23));
            trackService.saveTrack(new Track(2,"Love me like","Olivia",34));
            System.out.println("Context Refreshed");
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
//        servletRegistrationBean.addUrlMappings("/console/*");
//        return servletRegistrationBean;
//
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.stackroute.muzix.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}
