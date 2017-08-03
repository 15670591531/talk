package org.docryze.talk.talkplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Demo
 */
@RestController
@EnableAutoConfiguration
public class DemoController {

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoController.class,args);
    }
}
