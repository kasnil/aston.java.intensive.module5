package aston.java.intensive.module5.utils.sort.strategy;

import java.util.ArrayList;
import java.util.List;

public record Parameter<T extends Object>(List<T> expected, List<T> actual) {
    public static <T extends Object> Parameter<T> of(List<T> expected, List<T> actual) {
        return new Parameter<>(expected, actual);
    }

    @Override
    protected Object clone() {
        return new Parameter<>(this.expected, new ArrayList<>(this.actual));
    }
}