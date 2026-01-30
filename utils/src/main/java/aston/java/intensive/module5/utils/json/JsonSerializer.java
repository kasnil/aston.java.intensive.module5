package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.ReflectionException;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.serializer.SerializerCollection;
import aston.java.intensive.module5.utils.serializer.SerializerException;
import aston.java.intensive.module5.utils.serializer.SerializerProperty;
import aston.java.intensive.module5.utils.serializer.Serializer;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public abstract class JsonSerializer<T> implements Serializer<T>, SerializerCollection<T> {
    @Override
    public Result<T> deserialize(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).asJsonObject().orElseThrow();
            return deserialize(jsonObject);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<Collection<T>> deserializeCollection(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).asJsonObject().orElseThrow();
            if (jsonObject.containsKey("data")) {
                List<T> values = ListsUtils.newArrayList();
                JsonArray jsonArray = jsonObject.get("data").orElseThrow().asJsonArray().orElseThrow();
                for (JsonValue jsonValue : jsonArray) {
                    values.add(deserialize(jsonValue.asJsonObject().orElseThrow()).orElseThrow());
                }
                return Result.ok(values);
            }
            return Result.err(new JsonException("Не корректный json"));
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    protected abstract Result<T> deserialize(JsonObject jsonObject);

    @Override
    public Result<String> serialize(T value) {
        try {
            JsonBuilder builder = new JsonBuilder();
            builder.beginAssociativeArray();
            serialize(builder, value);
            builder.endAssociativeArray();
            String json = builder.build();

            return Result.ok(json);
        } catch (SerializerException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<String> serializeCollection(Collection<T> values) {
        try {
            JsonBuilder builder = new JsonBuilder();
            builder.beginAssociativeArray();
            builder.addKey("data");
            builder.beginArray();
            for (T value : values) {
                builder.beginAssociativeArray();
                serialize(builder, value);
                builder.endAssociativeArray();
            }
            builder.endArray();
            builder.endAssociativeArray();
            String json = builder.build();

            return Result.ok(json);
        } catch (SerializerException e) {
            return Result.err(e);
        }
    }

    private void serialize(JsonBuilder builder, T value) {
        final var fieldInfoCollection = getFieldInfoCollection(value.getClass());
        for (FieldInfo fieldInfo : fieldInfoCollection) {
            try {
                final String jsonKey = fieldInfo.getSerializerName();
                final var jsonValue = ReflectUtils.getFieldValue(value, fieldInfo.field());
                builder.addKey(jsonKey)
                        .addValue(jsonValue);
            } catch (ReflectionException e) {
                throw new JsonException(e, "Ошибка получения значения поля '%s' класса '%s'", fieldInfo.field().getName(), value.getClass().getName());
            }
        }
    }

    private static <T> Collection<FieldInfo> getFieldInfoCollection(Class<T> targetClass) {
        return Stream
                .of(targetClass.getDeclaredFields())
                .map(field -> new FieldInfo(field, field.getAnnotation(SerializerProperty.class)))
                .filter(fieldInfo -> nonNull(fieldInfo.annotation()))
                .collect(Collectors.toUnmodifiableList());
    }

    private record FieldInfo(Field field, SerializerProperty annotation) {
        public String getSerializerName() {
            return StringUtils.isNullOrEmpty(this.annotation.name())
                    ? field.getName()
                    : annotation.name();
        }
    }
}