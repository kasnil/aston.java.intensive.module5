package aston.java.intensive.module5.utils.guard;

import java.util.Set;

public final class SetCollectionParam<T> extends CollectionParam<Set<T>> {
    public SetCollectionParam(Set<T> value) {
        super(value);
    }

    public CollectionParam hasItems() {
        Ensure.collection.hasItems(this.value);

        return this;
    }
}
