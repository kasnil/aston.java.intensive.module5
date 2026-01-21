package aston.java.intensive.module5.utils.guard;

public final class ArrayCollectionParam<T> extends CollectionParam<T[]> {
    public ArrayCollectionParam(T[] value) {
        super(value);
    }

    public CollectionParam hasItems(String errorMessage) {
        Ensure.collection.hasItems(this.value, errorMessage);

        return this;
    }
}
