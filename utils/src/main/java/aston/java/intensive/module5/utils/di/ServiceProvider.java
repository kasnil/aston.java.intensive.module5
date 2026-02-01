package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.Result;

import java.util.List;

public interface ServiceProvider {
    <T> Result<T> getService(Class<T> serviceClass);

    List<ServiceDescriptor> getDescriptors();
}
