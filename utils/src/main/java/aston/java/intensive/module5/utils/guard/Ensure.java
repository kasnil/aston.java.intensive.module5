package aston.java.intensive.module5.utils.guard;

public final class Ensure {
    public static final ExceptionFactory exceptionFactory = new DefaultExceptionFactory();

    public static final AnyGuard any = new AnyGuard();
    public static final StringGuard string = new StringGuard();
    public static final BoolGuard bool = new BoolGuard();

    public static <T> Param<T> That(T value)
    {
        return new Param<T>(value);
    }

    public static StringParam That(String value)
    {
        return new StringParam(value);
    }

    public static BooleanParam That(Boolean value)
    {
        return new BooleanParam(value);
    }
}
