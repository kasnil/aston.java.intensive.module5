package aston.java.intensive.module5.application.sort;

import aston.java.intensive.module5.application.sort.strategy.BubbleUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.HeapUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.InsertionUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.MergeUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.OddEvenUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.QuickUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.SelectUserSortStrategy;
import aston.java.intensive.module5.utils.di.ServiceProvider;
import aston.java.intensive.module5.utils.sort.SortStrategy;

public class SortStrategyFactory {
    private final ServiceProvider serviceProvider;

    public SortStrategyFactory(
            ServiceProvider serviceProvider
    )
    {
        this.serviceProvider = serviceProvider;
    }

    public SortStrategy getSortStrategy(SortStrategyKind kind) {
        return switch (kind) {
            case Bubble -> serviceProvider.getService(BubbleUserSortStrategy.class).orElseThrow();
            case Heap -> serviceProvider.getService(HeapUserSortStrategy.class).orElseThrow();
            case Insertion -> serviceProvider.getService(InsertionUserSortStrategy.class).orElseThrow();
            case Merge -> serviceProvider.getService(MergeUserSortStrategy.class).orElseThrow();
            case OddEven -> serviceProvider.getService(OddEvenUserSortStrategy.class).orElseThrow();
            case Quick -> serviceProvider.getService(QuickUserSortStrategy.class).orElseThrow();
            case Select -> serviceProvider.getService(SelectUserSortStrategy.class).orElseThrow();
        };
    }
}
