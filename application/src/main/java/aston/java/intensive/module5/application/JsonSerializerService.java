package aston.java.intensive.module5.application;

import aston.java.intensive.module5.utils.Result;

public interface JsonSerializerService<T> {
    Result<T> deserialize(String json);

    Result<String> serialize(T value);
}
