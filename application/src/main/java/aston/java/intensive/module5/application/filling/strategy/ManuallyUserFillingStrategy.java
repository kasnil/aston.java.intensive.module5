package aston.java.intensive.module5.application.filling.strategy;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.application.filling.helper.ConsoleInputHelper;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.domain.dto.Email;
import aston.java.intensive.module5.domain.dto.Password;
import aston.java.intensive.module5.domain.dto.UserName;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;

public class ManuallyUserFillingStrategy implements FillingStrategy<User> {

    private final IOService console;
    private final ConsoleInputHelper input;

    public ManuallyUserFillingStrategy(IOService console) {
        this.console = console;
        this.input = new ConsoleInputHelper(console);
    }

    public ManuallyUserFillingStrategy() {
        this(new ConsoleService());
    }

    @Override
    public void fill(int count, Repository<User> repository) {

        for (int i = 1; i <= count; i++) {
            console.output("Пользователь " + i + ":");

            UserName name = input.readValidated("Введите имя: ", UserName::of);
            Email email = input.readValidated("Введите email: ", Email::of);
            Password password = input.readValidated("Введите пароль: ", Password::of);

            User user = new User.Builder()
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .build();

            repository.add(user);
        }
    }
}
