package aston.java.intensive.module5.application.sort;

import aston.java.intensive.module5.domain.User;

import java.util.Comparator;
import java.util.List;

public class UserSortService {
    private SortStrategy<User> sortStrategy;

    public UserSortService(SortStrategy<User> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(SortStrategy<User> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public List<User> sort(List<User> users, List<UserSortField> sortFields) {

        if (sortFields == null || sortFields.isEmpty()) {
            return sortStrategy.sort(users, Comparator.naturalOrder());
        }

        Comparator<User> comparator = UserComparatorFactory.buildUserComparator(sortFields);

        return this.sortStrategy.sort(users, comparator);
    }
}
