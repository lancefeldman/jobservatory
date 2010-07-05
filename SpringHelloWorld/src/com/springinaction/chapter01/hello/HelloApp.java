package com.springinaction.chapter01.hello;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class HelloApp {
  public static void main(String[] args) throws Exception {
    BeanFactory factory = 
//        new XmlBeanFactory(new FileSystemResource("C:\\Usr\\Workspaces\\Eclipse 3.5 EE\\SpringHelloWorld\\src\\com\\springinaction\\chapter01\\hello\\hello.xml"));
    	new XmlBeanFactory(new ClassPathResource("hello.xml"));

    GreetingService greetingService = 
        (GreetingService) factory.getBean("greetingService");

    greetingService.sayGreeting();
  }
}
