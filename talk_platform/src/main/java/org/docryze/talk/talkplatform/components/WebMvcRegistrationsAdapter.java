package org.docryze.talk.talkplatform.components;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Configuration
@EnableWebMvc
public class WebMvcRegistrationsAdapter extends ExceptionHandlerExceptionResolver {

}
