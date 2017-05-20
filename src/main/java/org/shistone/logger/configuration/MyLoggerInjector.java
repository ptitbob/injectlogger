package org.shistone.logger.configuration;

import org.shistone.logger.configuration.annotation.MyLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author François Robert
 */
@Component
public class MyLoggerInjector implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    ReflectionUtils.doWithFields(bean.getClass(), field -> {
      final MyLogger annotation = field.getAnnotation(MyLogger.class);
      if (annotation != null) {
        ReflectionUtils.makeAccessible(field);
        Logger logger;
        if ("".equals(annotation.name().trim())) {
          logger = LoggerFactory.getLogger(bean.getClass());
        } else {
          logger = LoggerFactory.getLogger(annotation.name());
        }
        field.set(bean, logger);
      }
    });
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

}
