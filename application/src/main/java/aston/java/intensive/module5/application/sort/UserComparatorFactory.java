package aston.java.intensive.module5.application.sort;

import aston.java.intensive.module5.domain.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class UserComparatorFactory {

    private static final List<UserSortField> DEFAULT_ORDER =
            List.of(UserSortField.NAME, UserSortField.EMAIL, UserSortField.PASSWORD);

    public static Comparator<User> buildUserComparator(List<UserSortField> orderFields ) {
        if (orderFields == null || orderFields.isEmpty()) {
            throw new IllegalArgumentException("Порядок сортировки отсутствует");
        }

        List<UserSortField> fullOrder = new ArrayList<>();

        // сначала пользовательский порядок
        for (UserSortField field : orderFields) {
            if (!fullOrder.contains(field)) {
                fullOrder.add(field);
            }
        }

        // дополняем базовым порядком
        for (UserSortField field : DEFAULT_ORDER) {
            if (!fullOrder.contains(field)) {
                fullOrder.add(field);
            }
        }

        Comparator<User> comparator = comparatorByField(orderFields.get(0));
        for (int i = 1; i < orderFields.size(); i++) {
            comparator = comparator.thenComparing(comparatorByField(orderFields.get(i)));
        }

        return comparator;
    }

    private static Comparator<User> comparatorByField(UserSortField sortField) {
       return switch (sortField) {
            case NAME -> Comparator.comparing(User::getName);
            case EMAIL -> Comparator.comparing(User::getEmail);
            case PASSWORD -> Comparator.comparing(User::getPassword);
       };
    }

}
