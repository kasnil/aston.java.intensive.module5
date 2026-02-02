package aston.java.intensive.module5.application.filling.strategy;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.domain.dto.Email;
import aston.java.intensive.module5.domain.dto.Password;
import aston.java.intensive.module5.domain.dto.UserName;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.utils.faker.DataSet;
import aston.java.intensive.module5.utils.faker.Gender;

public class RandomUserFillingStrategy implements FillingStrategy<User> {

    private final DataSet dataSet;

    public RandomUserFillingStrategy(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public void fill(int count, Repository<User> repository) {
        for (int i = 0; i < count; i++) {
            Gender gender = null;

            String firstName = dataSet.firstName(gender);
            String lastName = dataSet.lastName(gender);

            UserName name = UserName
                    .of(firstName + " " + lastName)
                    .orElseThrow();

            Email email = Email
                    .of(dataSet.email(firstName, lastName))
                    .orElseThrow();

            Password password = Password
                    .of(dataSet.password(6))
                    .orElseThrow();

            User user = new User.Builder()
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .build();

            repository.add(user);
        }
    }
}
