package aston.java.intensive.module5.application.serializer;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.serializer.csv.CsvSerializer;

import java.util.Map;

public class CsvSerializerUserService extends CsvSerializer<User> {

    @Override
    public Result<User> deserialize(Map<String, String> row) {
        try {
            var builder = new User.Builder()
                    .setName(row.get("name"))
                    .setEmail(row.get("email"))
                    .setPassword(row.get("password"));
            return Result.ok(builder.build());
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }
}