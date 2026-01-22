package aston.java.intensive.module5.presentation;

import aston.java.intensive.module5.application.sort.UserSortField;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserSortMenu {

    private final Scanner scanner;

    public UserSortMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Optional<List<UserSortField>> chooseSortOrder() {

        List<UserSortField> result = new ArrayList<>();
        EnumSet<UserSortField> available = EnumSet.allOf(UserSortField.class);

        boolean firstStep = true;

        while (!available.isEmpty()) {
            System.out.println("Выберите поле для сортировки (0 — завершить выбор):");

            int index = 1;
            for (UserSortField field : available) {
                System.out.println(index + ". " + field.getDisplayName());
                index++;
            }
            System.out.println("0. Завершить выбор");

            int choice = scanner.nextInt();

            //пользователь сразу отказался от кастомной сортировки
            if (choice == 0 && firstStep) {
                return Optional.empty();
            }

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > available.size()) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
                continue;
            }

            UserSortField selected = getByIndex(available, choice - 1);
            result.add(selected);
            available.remove(selected);
            firstStep = false;
        }

        return Optional.of(result);
    }

    private static UserSortField getByIndex(EnumSet<UserSortField> set, int index) {
        int i = 0;
        for (UserSortField field : set) {
            if (i == index) return field;
            i++;
        }
        throw new IllegalArgumentException();
    }
}
