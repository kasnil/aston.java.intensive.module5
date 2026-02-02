package aston.java.intensive.module5.application.filling;

import aston.java.intensive.module5.application.filling.strategy.FillingStrategyKind;
import aston.java.intensive.module5.application.filling.strategy.FromFileUserFillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.ManuallyUserFillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.RandomUserFillingStrategy;
import aston.java.intensive.module5.utils.di.ServiceProvider;

public class FillingStrategyFactory {
    private final ServiceProvider serviceProvider;

    public FillingStrategyFactory(
            ServiceProvider serviceProvider
    )
    {
        this.serviceProvider = serviceProvider;
    }

    public FillingStrategy getFillingStrategy(FillingStrategyKind kind) {
        return switch (kind) {
            case FromFile -> serviceProvider.getService(FromFileUserFillingStrategy.class).orElseThrow();
            case Manually -> serviceProvider.getService(ManuallyUserFillingStrategy.class).orElseThrow();
            case Random -> serviceProvider.getService(RandomUserFillingStrategy.class).orElseThrow();
        };
    }
}
