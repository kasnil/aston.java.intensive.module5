package aston.java.intensive.module5.utils.guard;

import java.util.*;

public class CollectionGuard {
    public <T> T[] hasItems(T[] value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.length == 0) {
            String message = errorMessage == null ? ExceptionMessages.COLLECTION_IS_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T> List<T> hasItems(List<T> value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.size() == 0) {
            String message = errorMessage == null ? ExceptionMessages.COLLECTION_IS_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T> SequencedCollection<T> hasItems(SequencedCollection<T> value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.size() == 0) {
            String message = errorMessage == null ? ExceptionMessages.COLLECTION_IS_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <K,V> Map<K,V> hasItems(Map<K,V> value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.size() == 0) {
            String message = errorMessage == null ? ExceptionMessages.COLLECTION_IS_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T> Set<T> hasItems(Set<T> value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.size() == 0) {
            String message = errorMessage == null ? ExceptionMessages.COLLECTION_IS_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }
}
