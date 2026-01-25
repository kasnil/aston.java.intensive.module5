package aston.java.intensive.module5.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectUtils {
    public static <T extends Annotation> T getAnnotation(Class<?> theClass, Class<T> theAnnotation) {
        T aAnnotation = null;

        if (theClass.isAnnotationPresent(theAnnotation)) {
            aAnnotation = theClass.getAnnotation(theAnnotation);
        }
        return aAnnotation;
    }

    public static List<Method> getAnnotatedMethods(
            Class<?> targetClass, Class<? extends Annotation> annotationClass) {
        List<Method> aMethods = ListsUtils.newArrayList();

        for (Method aMethod : targetClass.getMethods()) {
            if (aMethod.getAnnotation(annotationClass) != null) {
                aMethods.add(aMethod);
            }
        }

        return aMethods;
    }

    public static boolean hasAnnotation(Class<?> theClass, Class<? extends Annotation> theAnnotation) {
        return getAnnotation(theClass, theAnnotation) != null;
    }
}
