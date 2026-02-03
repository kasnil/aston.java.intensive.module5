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
import aston.java.intensive.module5.utils.sort.strategy.BubbleSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.HeapSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.InsertionSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.MergeSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.OddEvenSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.QuickSortStrategy;
import aston.java.intensive.module5.utils.sort.strategy.SelectSortStrategy;

public class SortStrategyFactory<T> {
    private final ServiceProvider serviceProvider;

    public SortStrategyFactory(
            ServiceProvider serviceProvider
    )
    {
        this.serviceProvider = serviceProvider;
    }

    public SortStrategy<T> getSortStrategy(SortStrategyKind kind) {
        return switch (kind) {
            case Bubble -> serviceProvider.getService(BubbleSortStrategy.class).orElseThrow();
            case Heap -> serviceProvider.getService(HeapSortStrategy.class).orElseThrow();
            case Insertion -> serviceProvider.getService(InsertionSortStrategy.class).orElseThrow();
            case Merge -> serviceProvider.getService(MergeSortStrategy.class).orElseThrow();
            case OddEven -> serviceProvider.getService(OddEvenSortStrategy.class).orElseThrow();
            case Quick -> serviceProvider.getService(QuickSortStrategy.class).orElseThrow();
            case Select -> serviceProvider.getService(SelectSortStrategy.class).orElseThrow();
        };
    }
}
