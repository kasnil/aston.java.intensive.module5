package aston.java.intensive.module5.utils.guard;

import java.util.*;

public class CollectionGuard {
    public <T> T[] hasItems(T[] value)
    {
        Ensure.any.isNotNull(value);

        if (value.length == 0)
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.COLLECTION_IS_EMPTY);

        return value;
    }

    public <T> List<T> hasItems(List<T> value)
    {
        Ensure.any.isNotNull(value);

        if (value.size() == 0)
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.COLLECTION_IS_EMPTY);

        return value;
    }

    public <T> SequencedCollection<T> hasItems(SequencedCollection<T> value)
    {
        Ensure.any.isNotNull(value);

        if (value.size() == 0)
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.COLLECTION_IS_EMPTY);

        return value;
    }

    public <K,V> Map<K,V> hasItems(Map<K,V> value)
    {
        Ensure.any.isNotNull(value);

        if (value.size() == 0)
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.COLLECTION_IS_EMPTY);

        return value;
    }

    public <T> Set<T> hasItems(Set<T> value)
    {
        Ensure.any.isNotNull(value);

        if (value.size() == 0)
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.COLLECTION_IS_EMPTY);

        return value;
    }
}
