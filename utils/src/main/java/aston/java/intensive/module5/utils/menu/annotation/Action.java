package aston.java.intensive.module5.utils.menu.annotation;

import aston.java.intensive.module5.utils.menu.models.ResourceAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String value() default ResourceAction.DEFAULT;
}
