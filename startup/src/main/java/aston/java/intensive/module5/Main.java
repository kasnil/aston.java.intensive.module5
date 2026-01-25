package aston.java.intensive.module5;

import aston.java.intensive.module5.presentation.menu.MenuGreet;
import aston.java.intensive.module5.presentation.menu.MenuNotFound;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.menu.ApplicationBuilder;
import aston.java.intensive.module5.utils.menu.models.Resource;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        var applicationBuilder = new ApplicationBuilder();
        applicationBuilder
                .addMenu(MenuGreet.class)
                .addMenu(MenuNotFound.class)
                .addMenu(MenuStart.class);
        applicationBuilder.build().run(new Resource("index"));
    }
}
