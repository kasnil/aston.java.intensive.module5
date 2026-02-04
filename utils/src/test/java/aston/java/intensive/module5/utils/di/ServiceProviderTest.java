package aston.java.intensive.module5.utils.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceProviderTest {
    @Test
    public void testGetService() {
        var serviceLocator = new ServiceLocator();
        serviceLocator.addSingleton(ClassWithDefaultConstructor.class);
        serviceLocator.addSingleton(ClassWithDefaultConstructorAndExtendsClassWithDefaultConstructor.class);
        serviceLocator.addSingleton(new ClassWithManualInitialize());
        serviceLocator.addSingleton(ClassWithDeclaredConstructor.class);
        serviceLocator.addSingleton(EmptyInterface.class, ClassWithDeclaredConstructorAndImplementsEmptyInterface.class);

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        var classWithDefaultConstructor = serviceProvider.getService(ClassWithDefaultConstructor.class);
        assertTrue(classWithDefaultConstructor.isOk());

        var classWithDefaultConstructorAndExtendsClassWithDefaultConstructor = serviceProvider.getService(ClassWithDefaultConstructorAndExtendsClassWithDefaultConstructor.class);
        assertTrue(classWithDefaultConstructorAndExtendsClassWithDefaultConstructor.isOk());

        var classWithManualInitialize = serviceProvider.getService(ClassWithManualInitialize.class);
        assertTrue(classWithManualInitialize.isOk());

        var classWithDeclaredConstructor = serviceProvider.getService(ClassWithDeclaredConstructor.class);
        assertTrue(classWithDeclaredConstructor.isOk());
        assertEquals(classWithDefaultConstructor.getValue(), classWithDeclaredConstructor.getValue().classWithDefaultConstructor);

        var classWithDeclaredConstructorAndImplementsEmptyInterface = serviceProvider.getService(EmptyInterface.class);
        assertTrue(classWithDeclaredConstructorAndImplementsEmptyInterface.isOk());

        assertTrue(serviceProvider.getService(ClassNotRegisteredInServiceCollection.class).isErr());
        assertThrows(IllegalArgumentException.class, () -> serviceProvider.getService(ClassNotRegisteredInServiceCollection.class).orElseThrow());
    }
}

class ClassWithDefaultConstructor {
}

class ClassWithDeclaredConstructor {
    public final ClassWithDefaultConstructor classWithDefaultConstructor;

    public ClassWithDeclaredConstructor(ClassWithDefaultConstructor classWithDefaultConstructor) {
        this.classWithDefaultConstructor = classWithDefaultConstructor;
    }
}

class ClassWithDefaultConstructorAndExtendsClassWithDefaultConstructor extends ClassWithDefaultConstructor {
}

interface EmptyInterface {
}

class ClassWithDeclaredConstructorAndImplementsEmptyInterface implements EmptyInterface {
    public final ClassWithDefaultConstructor classWithDefaultConstructor;
    public final ClassWithDeclaredConstructor classWithDeclaredConstructor;

    public ClassWithDeclaredConstructorAndImplementsEmptyInterface(
            ClassWithDefaultConstructor classWithDefaultConstructor,
            ClassWithDeclaredConstructor classWithDeclaredConstructor) {
        this.classWithDefaultConstructor = classWithDefaultConstructor;
        this.classWithDeclaredConstructor = classWithDeclaredConstructor;
    }
}

class ClassWithManualInitialize {
}

class ClassNotRegisteredInServiceCollection {
}
