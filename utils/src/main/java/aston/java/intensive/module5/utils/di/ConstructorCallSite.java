package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.lang.reflect.Constructor;

public class ConstructorCallSite extends CallSite {
    private final Constructor<?> constructor;
    private final CallSite[] constructorParameters;

    public ConstructorCallSite(Class<?> serviceClass, Constructor<?> constructor) {
        this(serviceClass, constructor, ArrayUtils.newArray());
    }

    public ConstructorCallSite(Class<?> serviceClass, Constructor<?> constructor, CallSite[] constructorParameters) {
        Ensure.that(constructorParameters).isNotNull();

        super(serviceClass);
        this.constructor = constructor;
        this.constructorParameters = constructorParameters;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public CallSite[] getConstructorParameters() {
        return constructorParameters;
    }
}
