package aston.java.intensive.module5;

import aston.java.intensive.module5.presentation.menu.MenuGreet;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.menu.ApplicationBuilder;
import aston.java.intensive.module5.utils.menu.models.Resource;

public class Main {
    static void main() {
        var applicationBuilder = new ApplicationBuilder();
        applicationBuilder
                .addMenu(MenuGreet.class)
                .addMenu(MenuStart.class);
        applicationBuilder.build().run(new Resource("index"));
    }
}
