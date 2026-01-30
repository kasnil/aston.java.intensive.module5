package aston.java.intensive.module5.application;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.json.JsonObject;
import aston.java.intensive.module5.utils.json.JsonSerializerService;
import aston.java.intensive.module5.utils.json.JsonString;

public class JsonSerializerUserService extends JsonSerializerService<User> {

    @Override
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