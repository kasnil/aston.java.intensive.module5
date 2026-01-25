package aston.java.intensive.module5.utils.guard;

import java.util.*;

public final class Ensure {
    public static final ExceptionFactory exceptionFactory = new DefaultExceptionFactory();

    public static final AnyGuard any = new AnyGuard();
    public static final StringGuard string = new StringGuard();
    public static final BooleanGuard bool = new BooleanGuard();
    public static final IntegerGuard integer = new IntegerGuard();
    public static final CollectionGuard collection = new CollectionGuard();

    public static <T> Param<T> that(T value)
    {
        return new Param<>(value);
    }

    public static <T extends Class<?>> Param<T> that(T theClass)
    {
        return new Param<>(theClass);
    }

    public static StringParam that(String value)
    {
        return new StringParam(value);
    }

    public static BooleanParam that(Boolean value)
    {
        return new BooleanParam(value);
    }

    public static IntegerParam that(Integer value)
    {
        return new IntegerParam(value);
    }

    public static <T> CollectionParam<T[]> that(T[] value)
    {
        return new ArrayCollectionParam(value);
    }

    public static <T> CollectionParam<List<T>> that(List<T> value)
    {
        return new ListCollectionParam(value);
    }

    public static <T> CollectionParam<SequencedCollection<T>> That(SequencedCollection<T> value)
    {
        return new SequencedCollectionParam(value);
    }

    public static <K,v> CollectionParam<HashMap<K,v>> that(Map<K,v> value)
    {
        return new MapCollectionParam(value);
    }

    public static <T> CollectionParam<HashSet<T>> that(Set<T> value)
    {
        return new SetCollectionParam(value);
    }
}
