package com.ndhuy.auth.exception.infrastructure;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageConfig {
    @Bean
public Validator validatorFactory (MessageSource messageSource) {
    LocalValidatorFactoryBean validator =  new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource);
    return validator;
}

@Bean
public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
    bean.addBasenames("classpath:org.hibernate.validator.ValidationMessages", "classpath:validation_errors"); // validation_errors.properties is my resource bundle
    bean.setDefaultEncoding("UTF-8");
    return bean;
}
}
