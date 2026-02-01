package aston.java.intensive.module5.application.serializer;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.domain.dto.Email;
import aston.java.intensive.module5.domain.dto.EmailValidator;
import aston.java.intensive.module5.domain.dto.Password;
import aston.java.intensive.module5.domain.dto.PasswordValidator;
import aston.java.intensive.module5.domain.dto.UserName;
import aston.java.intensive.module5.domain.dto.UserNameValidator;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.serializer.json.JsonObject;
import aston.java.intensive.module5.utils.serializer.json.JsonSerializer;
import aston.java.intensive.module5.utils.serializer.json.JsonString;


public class JsonSerializerUserService extends JsonSerializer<User> {

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

    public User jsonToUser(JsonObject json) {

        String name = json.get("name")
                .map(v -> ((JsonString) v).getValue())
                .orElseThrow(() -> new IllegalArgumentException("Отсутствует поле 'name'"));

        String email = json.get("email")
                .map(v -> ((JsonString) v).getValue())
                .orElseThrow(() -> new IllegalArgumentException("Отсутствует поле 'email'"));

        String password = json.get("password")
                .map(v -> ((JsonString) v).getValue())
                .orElseThrow(() -> new IllegalArgumentException("Отсутствует поле 'password'"));

        return new User.Builder()
                .setName(
                        UserName.of(name)
                                .orElseThrow(() -> {
                                    var errors = new UserNameValidator().validate(new UserName(name));
                                    return new IllegalArgumentException(errors.getErrors().getFirst().errorMessage() + ". Имя пользователя: " + name);})
                )
                .setEmail(
                        Email.of(email)
                                .orElseThrow(() -> {
                                    var errors = new EmailValidator().validate(new Email(email));
                                    return new IllegalArgumentException(errors.getErrors().getFirst().errorMessage() + ". Email пользователя: " + email);})
                )
                .setPassword(
                        Password.of(password)
                                .orElseThrow(() -> {
                                    var errors = new PasswordValidator().validate(new Password(password));
                                    return new IllegalArgumentException(errors.getErrors().getFirst().errorMessage());})
                )
                .build();
    }

}