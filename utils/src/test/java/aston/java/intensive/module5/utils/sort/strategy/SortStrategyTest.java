package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class SortStrategyTest {
    private final SortStrategy quickSortStrategy = new QuickSortStrategy();
    private final SortStrategy bubbleSortStrategy = new BubbleSortStrategy();
    private final SortStrategy insertionSortStrategy = new InsertionSortStrategy();
    private final SortStrategy selectSortStrategy = new SelectSortStrategy();
    private final SortStrategy mergeSortStrategy = new MergeSortStrategy();
    private final SortStrategy heapSortStrategy = new HeapSortStrategy();
    private final SortStrategy oddEvenSortStrategy = new OddEvenSortStrategy();

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

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testSelectSort(Parameter<Integer> parameter) {
        selectSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testMergeSort(Parameter<Integer> parameter) {
        mergeSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testHeapSort(Parameter<Integer> parameter) {
        heapSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testOddEvenSort(Parameter<Integer> parameter) {
        oddEvenSortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    static Stream<Object> intParameters() {
        return SortStrategyDataProvider.INT_PARAMETERS.stream().map(Parameter::clone);
    }
}
