package aston.java.intensive.module5.utils.csv;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.ReflectionException;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.json.JsonException;
import aston.java.intensive.module5.utils.serializer.SerializerCollectionService;
import aston.java.intensive.module5.utils.serializer.SerializerProperty;
import aston.java.intensive.module5.utils.serializer.SerializerService;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public abstract class CsvSerializerService<T> implements SerializerService<T>, SerializerCollectionService<T> {
    @Override
    public Result<T> deserialize(String csv) {
        try {
            final var parser = new CsvParser();
            var rows = parser.parse(csv, true);
            Map<String, String> row = new HashMap<>();
            for (var col : rows.keySet()) {
                var value = rows.get(col).getFirst();
                row.put(col, value);
            }
            return deserialize(row);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<Collection<T>> deserializeCollection(String csv) {
        try {
            final var parser = new CsvParser();
            List<T> result = ListsUtils.newArrayList();
            var rows = parser.parse(csv, true);

            int i = 0;
            boolean hasValue = true;
            while(hasValue) {
                Map<String, String> row = new HashMap<>();
                for (var entry : rows.entrySet()) {
                    List<String> values = entry.getValue();
                    if (i == values.size()) {
                        hasValue = false;
                        break;
                    }
                    var value = values.get(i);
                    row.put(entry.getKey(), value);
                }
                if (hasValue) {
                    i++;
                    result.add(deserialize(row).orElseThrow());
                }
            }

            return Result.ok(result);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    protected abstract Result<T> deserialize(Map<String, String> row);

    @Override
    public Result<String> serialize(T value) {
        try {
            StringBuilder sb = new StringBuilder();
            final var fieldInfoCollection = getFieldInfoCollection(value.getClass());

            CsvBuilder builder = new CsvBuilder();
            serializeHeader(builder, fieldInfoCollection, value.getClass());
            serialize(builder, fieldInfoCollection, value);
            String csv = builder.build();

            return Result.ok(csv);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<String> serializeCollection(Collection<T> values) {
        try {
            var targetClass = values.stream().findAny().get().getClass();
            final var fieldInfoCollection = getFieldInfoCollection(targetClass);

            CsvBuilder builder = new CsvBuilder();
            serializeHeader(builder, fieldInfoCollection, targetClass);
            for (T value : values) {
                serialize(builder, fieldInfoCollection, value);
            }
            String csv = builder.build();

            return Result.ok(csv);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    private void serializeHeader(CsvBuilder builder, final Collection<FieldInfo> fieldInfoCollection, Class<?> targetClass) {
        builder.beginScope();
        for (FieldInfo fieldInfo : fieldInfoCollection) {
            try {
                final String header = fieldInfo.getSerializerName();
                builder.addValue(header);
            } catch (ReflectionException e) {
                throw new CsvException(e, "Ошибка получения имени поля '%s' класса '%s'", fieldInfo.field().getName(), targetClass.getName());
            }
        }
        builder.endScope();
    }

    private void serialize(CsvBuilder builder, final Collection<FieldInfo> fieldInfoCollection, final T value) {
        builder.beginScope();
        for (FieldInfo fieldInfo : fieldInfoCollection) {
            try {
                final String header = fieldInfo.getSerializerName();
                final var csvValue = ReflectUtils.getFieldValue(value, fieldInfo.field());
                builder.addValue(csvValue);
            } catch (ReflectionException e) {
                throw new JsonException(e, "Ошибка получения значения поля '%s' класса '%s'", fieldInfo.field().getName(), value.getClass().getName());
            }
        }
        builder.endScope();
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