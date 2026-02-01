package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.NotSupportedException;
import aston.java.intensive.module5.utils.ReflectUtils;

import java.util.Optional;

public final class RuntimeResolver {
    public Optional<Object> Resolve(CallSite callSite) {

        return Optional.ofNullable(visitCallSiteMain(callSite));
    }

    private Object visitCallSiteMain(CallSite callSite)
    {
        if (callSite.getValue() != null)
        {
            return callSite.getValue();
        }
        Object resolved = switch (callSite)
        {
            case ConstructorCallSite constructorCallSite -> visitConstructor(constructorCallSite);
            default -> throw new NotSupportedException(String.format("Call site тип '%s' не поддерживается.", callSite.getServiceClass().getName()));
        };

        callSite.setValue(resolved);

        return resolved;
    }

    private Object visitConstructor(ConstructorCallSite constructorCallSite) {
        if(constructorCallSite.getConstructorParameters().length == 0) {
            return ReflectUtils.newInstance(constructorCallSite.getConstructor());
        } else {
            var parameterValues = new Object[constructorCallSite.getConstructorParameters().length];
            for (int i = 0; i < parameterValues.length; i++)
            {
                parameterValues[i] = visitCallSiteMain(constructorCallSite.getConstructorParameters()[i]);
            }
            return ReflectUtils.newInstance(constructorCallSite.getConstructor(), parameterValues);
        }
    }
}
