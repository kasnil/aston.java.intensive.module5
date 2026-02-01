package aston.java.intensive.module5.utils;

import java.util.Arrays;

public class AggregateException extends RuntimeException {
    private final Throwable[] secondaryCauses;

    public AggregateException(Throwable primary, Throwable... others) {
        super(primary);
        this.secondaryCauses = others == null ? new Throwable[0] : others;
    }

    public Throwable[] getCauses() {
        int start = 0;
        int size = this.secondaryCauses.length;
        final Throwable primary = getCause();
        if (primary != null) {
            start = 1;
            size++;
        }

        Throwable[] all = new Exception[size];

        if (primary != null) {
            all[0] = primary;
        }

        Arrays.fill(all, start, all.length, this.secondaryCauses);
        return all;
    }
}