package aston.java.intensive.module5.application;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.json.JsonArray;
import aston.java.intensive.module5.utils.json.JsonBuilder;
import aston.java.intensive.module5.utils.json.JsonException;
import aston.java.intensive.module5.utils.json.JsonObject;
import aston.java.intensive.module5.utils.json.JsonParser;
import aston.java.intensive.module5.utils.json.JsonString;
import aston.java.intensive.module5.utils.json.JsonValue;

import java.util.Collection;
import java.util.List;

public class JsonSerializerUserService implements JsonSerializerService<User>, JsonSerializerCollectionService<User> {
    @Override
    public Result<User> deserialize(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).asJsonObject().orElseThrow();
            return deserialize(jsonObject);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<Collection<User>> deserializeCollection(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).asJsonObject().orElseThrow();
            if (jsonObject.containsKey("data")) {
                List<User> users = ListsUtils.newArrayList();
                JsonArray jsonArray = jsonObject.get("data").orElseThrow().asJsonArray().orElseThrow();
                for (JsonValue jsonValue : jsonArray) {
                    users.add(deserialize(jsonValue.asJsonObject().orElseThrow()).orElseThrow());
                }
                return Result.ok(users);
            }
            return Result.err(new JsonException("Не корректный json"));
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<String> serialize(User value) {
        try {
            JsonBuilder builder = new JsonBuilder();
            builder.beginAssociativeArray();
            Serialize(builder, value);
            builder.endAssociativeArray();
            String json = builder.build();

            return Result.ok(json);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    @Override
    public Result<String> serializeCollection(Collection<User> values) {
        try {
            JsonBuilder builder = new JsonBuilder();
            builder.beginAssociativeArray();
            builder.addKey("data");
            builder.beginArray();
            builder.beginAssociativeArray();
            for (User value : values) {
                Serialize(builder, value);
            }
            builder.endAssociativeArray();
            builder.endArray();
            builder.endAssociativeArray();
            String json = builder.build();

            return Result.ok(json);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }

    private void Serialize(JsonBuilder builder, User user) {
        builder.addKey("name");
        builder.addValue(user.getName());
        builder.addKey("email");
        builder.addValue(user.getEmail());
        builder.addKey("password");
        builder.addValue(user.getPassword());
    }

    public Result<User> deserialize(JsonObject jsonObject) {
        try {
            var builder = new User.Builder();
            if (jsonObject.get("name").orElseThrow() instanceof JsonString jsonString) {
                builder.setName(jsonString.getValue());
            }
            if (jsonObject.get("email").orElseThrow() instanceof JsonString jsonString) {
                builder.setEmail(jsonString.getValue());
            }
            if (jsonObject.get("password").orElseThrow() instanceof JsonString jsonString) {
                builder.setPassword(jsonString.getValue());
            }
            return Result.ok(builder.build());
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }
}