package aston.java.intensive.module5.utils.guard;

import java.util.List;

public final class ListCollectionParam<T> extends CollectionParam<List<T>> {
    public ListCollectionParam(List<T> value) {
        super(value);
    }

    public CollectionParam hasItems() {
        Ensure.collection.hasItems(this.value);

        return this;
    }
}
