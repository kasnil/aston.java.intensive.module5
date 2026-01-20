package aston.java.intensive.module5.utils.guard;

import java.util.SequencedCollection;

public final class SequencedCollectionParam<T> extends CollectionParam<SequencedCollection<T>> {
    public SequencedCollectionParam(SequencedCollection<T> value) {
        super(value);
    }

    public CollectionParam hasItems() {
        Ensure.collection.hasItems(this.value);

        return this;
    }
}
