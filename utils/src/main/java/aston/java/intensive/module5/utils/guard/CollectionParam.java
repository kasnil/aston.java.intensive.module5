package aston.java.intensive.module5.utils.guard;

public sealed abstract class CollectionParam<T> extends Param<T> permits
        ArrayCollectionParam, ListCollectionParam, SequencedCollectionParam, MapCollectionParam, SetCollectionParam {
    public CollectionParam(T value) {
        super(value);
    }

    public CollectionParam hasItems() {
        return hasItems(null);
    }

    public abstract CollectionParam hasItems(String errorMessage);
}