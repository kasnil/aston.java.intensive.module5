package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;

public class ReflectUtils {
    public static <T extends Annotation> Optional<T> getAnnotation(Method method, Class<T> theAnnotation) {
        T aAnnotation = method.getAnnotation(theAnnotation);

        return aAnnotation == null ? Optional.empty() : Optional.of(aAnnotation);
    }

    public static <T extends Annotation> Optional<T> getAnnotation(Class<?> theClass, Class<T> theAnnotation) {
        T aAnnotation = null;

        if (theClass.isAnnotationPresent(theAnnotation)) {
            aAnnotation = theClass.getAnnotation(theAnnotation);
        }
        return aAnnotation == null ? Optional.empty() : Optional.of(aAnnotation);
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
        return getAnnotation(theClass, theAnnotation).isPresent();
    }

    public static boolean isImplementsInterface(Class<?> theClass, Class theAnnotation) {
        Class[] implementedInterfaces = getInterfaces(theClass);
        for (Class implementedInterface : implementedInterfaces) {
            if (implementedInterface.equals(theAnnotation)) return true;
        }
        return false;
    }

    public static Class[] getInterfaces(Class<?> theClass) {
        return theClass.getInterfaces();
    }

    public static Object newInstance(Class type) {
        return newInstance(type, ArrayUtils.newArray(type.getClass(), 0), null);
    }

    public static Object newInstance(Class type, Class[] parameterTypes, Object[] args) {
        return newInstance(getConstructor(type, parameterTypes), args);
    }

    public static Object newInstance(final Constructor constructor, final Object[] args) {

        boolean flag = constructor.isAccessible();
        try {
            if (!flag) {
                constructor.setAccessible(true);
            }
            Object result = constructor.newInstance(args);
            return result;
        } catch (InstantiationException e) {
            throw new ReflectionException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectionException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectionException(e.getTargetException());
        } finally {
            if (!flag) {
                constructor.setAccessible(flag);
            }
        }

    }

    public static Constructor getConstructor(Class type, Class[] parameterTypes) {
        Constructor constructor = null;
        try {
            constructor = type.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException e) {
            throw new ReflectionException(e);
        }
    }

    public static Optional<Method> getInterfaceMethod(Class<?> targetClass, Class<?> interfaceClass) {
        Ensure.that(interfaceClass).isInterface();

        Class[] implementedInterfaces = getInterfaces(targetClass);
        for (Class implementedInterface : implementedInterfaces) {
            if (implementedInterface.equals(interfaceClass)) {
                for (Method m : implementedInterface.getMethods()) {
                    if (Modifier.isAbstract(m.getModifiers())) {
                        return Optional.of(m);
                    }
                }
            }
        }
        return Optional.empty();
    }
}