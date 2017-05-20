package org.shistone.logger.configuration.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Annotation qui servira a identifier les champs succeptible d'être intancié par un logger
 * @author François Robert
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@Documented
public @interface MyLogger {

  String name() default "";

}
