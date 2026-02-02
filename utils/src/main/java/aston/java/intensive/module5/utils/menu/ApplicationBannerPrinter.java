package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.menu.models.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ApplicationBannerPrinter {
    private static final String BANNER_LOCATION = "banner.txt";
    private static final Banner DEFAULT_BANNER = new Banner();

    private final ClassLoader classLoader;

    ApplicationBannerPrinter(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void print(PrintStream printStream) {
        var textBanner = getTextBanner();
        if (textBanner.isPresent()) {
            printStream.println();
            printStream.println(textBanner.get());
            printStream.println();
        } else {
            DEFAULT_BANNER.print(printStream);
        }
    }

    private Optional<String> getTextBanner() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BANNER_LOCATION);
            byte[] bytes = inputStream.readAllBytes();
            return Optional.of(new String(bytes, StandardCharsets.UTF_8));
        }
        catch (Exception ex) {
            return Optional.empty();
        }
    }
}
