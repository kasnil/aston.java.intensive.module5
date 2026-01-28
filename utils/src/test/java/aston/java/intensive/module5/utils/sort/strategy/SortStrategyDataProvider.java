package aston.java.intensive.module5.utils.sort.strategy;

import java.util.Arrays;
import java.util.List;

public final class SortStrategyDataProvider {
    public static final List<Parameter<Integer>> INT_PARAMETERS = List.of(
            Parameter.of(List.of(5, 30), Arrays.asList(5, 30)),
            Parameter.of(List.of(-30, -7, -5), Arrays.asList(-5, -30, -7 )),
            Parameter.of(List.of(-10, 0, 5, 7, 30), Arrays.asList(5, 30, 7, 0, -10)),
            Parameter.of(List.of(1, 2, 3, 4, 5), Arrays.asList(1, 2, 3, 4, 5)),
            Parameter.of(List.of(1, 2, 3, 4, 5), Arrays.asList(5, 4, 3, 2, 1)),
            Parameter.of(List.of(-10, -5, 0, 1, 5, 7, 30, 100), Arrays.asList(5, 30, 7, 0, -10, 100, 1, -5)),
            Parameter.of(List.of(-10, -5, -4, -3, 0, 1, 5, 7, 30, 100 ), Arrays.asList(-10, -5, -4, -3, 0, 1, 5, 7, 30, 100)),
            Parameter.of(List.of(1, 1, 1, 5, 5, 5, 10, 10, 10), Arrays.asList(5, 10, 10, 5, 5, 10, 1, 1, 1)),
            Parameter.of(List.of(5), Arrays.asList(5)),
            Parameter.of(List.of(), Arrays.asList())
    );
}
