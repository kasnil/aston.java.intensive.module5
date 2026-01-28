package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


public class SortStrategyTest {
    private final SortStrategy quickSortStrategy = new QuickSortStrategy();
    private final SortStrategy bubbleSortStrategy = new BubbleSortStrategy();
    private final SortStrategy insertionSortStrategy = new InsertionSortStrategy();

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testQuickSort(Parameter<Integer> parameter) {
        quickSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testBubbleSort(Parameter<Integer> parameter) {
        bubbleSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testInsertionSort(Parameter<Integer> parameter) {
        insertionSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    static Stream<Parameter<Integer>> intParameters() {
        return SortStrategyDataProvider.INT_PARAMETERS.stream();
    }
}
