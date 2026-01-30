package aston.java.intensive.module5.application.serializer;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.serializer.csv.CsvSerializer;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvSerializerUserServiceTest {
    private final CsvSerializer<User> csvSerializerUserService = new CsvSerializerUserService();

    @Test
    public void testDeserialize() {
        String str = """
                name,email,password
                Андрей,andrey@example.org,123456
                """;

        Result<User> deserializeResult = csvSerializerUserService.deserialize(str);

        assertTrue(deserializeResult.isOk());
        assertEquals("Андрей", deserializeResult.getValue().getName());
        assertEquals("andrey@example.org", deserializeResult.getValue().getEmail());
        assertEquals("123456", deserializeResult.getValue().getPassword());
    }

    @Test
    public void testDeserializeCollection() {
        String str = """
                name,email,password
                Андрей,andrey@example.org,123456
                Роман,roman@example.org,654321
                """;

        Result<Collection<User>> deserializeResult = csvSerializerUserService.deserializeCollection(str);

        assertTrue(deserializeResult.isOk());
        assertEquals(2, deserializeResult.getValue().size());
    }

    @Test
    public void testSerialize() {
        final User user = new User.Builder()
                .setName("Андрей")
                .setEmail("andrey@example.org")
                .setPassword("123456")
                .build();

        Result<String> serializeResult = csvSerializerUserService.serialize(user);

        assertTrue(serializeResult.isOk());
        assertEquals("""
                name,email,password
                Андрей,andrey@example.org,123456
                """, serializeResult.getValue());
    }

    @Test
    public void testSerializeCollection() {
        List<User> users = ListsUtils.newArrayList(
                new User.Builder()
                        .setName("Андрей")
                        .setEmail("andrey@example.org")
                        .setPassword("123456")
                        .build(),
                new User.Builder()
                        .setName("Роман")
                        .setEmail("roman@example.org")
                        .setPassword("654321")
                        .build()
        );

        Result<String> serializeResult = csvSerializerUserService.serializeCollection(users);

        assertTrue(serializeResult.isOk());
        assertEquals("""
                name,email,password
                Андрей,andrey@example.org,123456
                Роман,roman@example.org,654321
                """, serializeResult.getValue());
    }
}
