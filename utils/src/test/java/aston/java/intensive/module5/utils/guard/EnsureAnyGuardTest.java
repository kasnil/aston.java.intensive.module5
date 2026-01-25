package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureAnyGuardTest {
    @Test
    public void testIsNotNull() {
        String value = "";

        assertDoesNotThrow(() -> Ensure.that(value).isNotNull());
    }

    @Test
    public void testIsNotNullFailed() {
        String value = null;

        assertThrows(GuardException.class, () -> Ensure.that(value).isNotNull());
    }

    @Test
    public void testHasAnnotation() {
        WithSomeAnnotation value = new WithSomeAnnotation();

        assertDoesNotThrow(() -> Ensure.that(value).hasAnnotation(SomeAnnotation.class));
    }

    @Test
    public void testHasAnnotationFailed() {
        WithoutSomeAnnotation value = new WithoutSomeAnnotation();

        assertThrows(GuardException.class, () -> Ensure.that(value).hasAnnotation(SomeAnnotation.class));
    }

    @Test
    public void testIsImplementsInterface() {
        WithSomeInterface value = new WithSomeInterface();

        assertDoesNotThrow(() -> Ensure.that(value).isImplementsInterface(SomeInterface.class));
    }

    @Test
    public void testIsImplementsInterfaceFailed() {
        WithoutSSomeInterface value = new WithoutSSomeInterface();

        assertThrows(GuardException.class, () -> Ensure.that(value).isImplementsInterface(SomeInterface.class));
    }

    @Test
    public void testIsInterface() {
        assertDoesNotThrow(() -> Ensure.that(SomeInterface.class).isInterface());
    }

    @Test
    public void testIsInterfaceFailed() {
        assertThrows(GuardException.class, () -> Ensure.that(WithoutSSomeInterface.class).isInterface());
    }
}

@SomeAnnotation
record WithSomeAnnotation() { }

record WithoutSomeAnnotation() { }

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SomeAnnotation {
}

record WithSomeInterface() implements SomeInterface { }

record WithoutSSomeInterface() { }

interface SomeInterface { }