package aston.java.intensive.module5.utils.serializer;

import aston.java.intensive.module5.utils.Result;

public interface Serializer<T> {
    Result<T> deserialize(String json);

    Result<String> serialize(T value);
}
