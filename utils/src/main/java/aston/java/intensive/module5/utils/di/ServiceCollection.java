package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.List;

public class ServiceCollection {
    private final List<ServiceDescriptor> descriptors = ListsUtils.newArrayList();
    private boolean isReadOnly;

    public List<ServiceDescriptor> getServices() {
        return ListsUtils.newArrayList(descriptors);
    }

    public void add(ServiceDescriptor descriptor) {
        Ensure.that(this.isReadOnly).isFalse();

        descriptors.add(descriptor);
    }

    public int size() {
        return this.descriptors.size();
    }

    public void clear()
    {
        Ensure.that(this.isReadOnly).isFalse();

        this.descriptors.clear();
    }

    public boolean contains(ServiceDescriptor item) {
        return this.descriptors.contains(item);
    }

    public void makeReadOnly() {
        this.isReadOnly = true;
    }
}
