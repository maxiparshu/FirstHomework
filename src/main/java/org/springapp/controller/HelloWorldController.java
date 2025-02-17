package org.springapp.controller;

import org.springapp.model.HelloWorld;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
  @RequestMapping("/hello_world")
  public String helloWorldHandler(Model model){
    HelloWorld helloWorld = new HelloWorld();
    helloWorld.setVersion("1.0");
    model.addAttribute("hello_world", helloWorld);
    return "hello_world";
  }
}
