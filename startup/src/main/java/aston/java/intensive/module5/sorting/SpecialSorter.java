package aston.java.intensive.module5.sorting;

import aston.java.intensive.module5.domain.User;
import java.util.Comparator;
import java.util.List;

public class SpecialSorter {
    public static void specialSortById(List<User> users) {
        if (users == null || users.isEmpty()) return;
        List<User> sortedEvenUsers = users.stream()
            .filter(user -> user.getId() != null && user.getId() % 2 == 0)
            .sorted(Comparator.comparingLong(User::getId))
            .toList();
        int evenIndex = 0;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId() != null && user.getId() % 2 == 0) {
                users.set(i, sortedEvenUsers.get(evenIndex));
                evenIndex++;
            }
        }
    }
}
