package aston.java.intensive.module5.utils.guard;

import java.util.Map;

public final class MapCollectionParam<K, V> extends CollectionParam<Map<K, V>> {
    public MapCollectionParam(Map<K, V> value) {
        super(value);
    }

    public CollectionParam hasItems(String errorMessage) {
        Ensure.collection.hasItems(this.value, errorMessage);

        return this;
    }
}
