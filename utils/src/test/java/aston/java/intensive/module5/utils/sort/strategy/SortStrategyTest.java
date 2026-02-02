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
        testSort(quickSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testBubbleSort(Parameter<Integer> parameter) {
        testSort(bubbleSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testInsertionSort(Parameter<Integer> parameter) {
        testSort(insertionSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testSelectSort(Parameter<Integer> parameter) {
        testSort(selectSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testMergeSort(Parameter<Integer> parameter) {
        testSort(mergeSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testHeapSort(Parameter<Integer> parameter) {
        testSort(heapSortStrategy, parameter);
    }

    @ParameterizedTest
    @MethodSource("intParameters")
    public void testOddEvenSort(Parameter<Integer> parameter) {
        testSort(oddEvenSortStrategy, parameter);
    }

    private void testSort(SortStrategy sortStrategy, Parameter<Integer> parameter) {
        sortStrategy.sort(parameter.actual(), (Comparator<Integer>) Integer::compare);
        assertIterableEquals(parameter.expected(), parameter.actual());
    }

    static Stream<Object> intParameters() {
        return SortStrategyDataProvider.INT_PARAMETERS.stream().map(Parameter::clone);
    }
}
