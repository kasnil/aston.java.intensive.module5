package aston.java.intensive.module5.application;

import aston.java.intensive.module5.utils.Result;

import java.util.Collection;

public interface JsonSerializerCollectionService<T> {
    Result<Collection<T>> deserializeCollection(String json);

    Result<String> serializeCollection(Collection<T> value);
}
