package aston.java.intensive.module5.utils.serializer;

import aston.java.intensive.module5.utils.Result;

public interface SerializerService<T> {
    Result<T> deserialize(String json);

    Result<String> serialize(T value);
}
