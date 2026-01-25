package aston.java.intensive.module5.presentation;

import aston.java.intensive.module5.presentation.menu.MenuGreet;
import aston.java.intensive.module5.presentation.menu.MenuNotFound;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.menu.ApplicationBuilder;
import aston.java.intensive.module5.utils.menu.models.Resource;

public class UI {
    public void run() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            IO.println("i = " + i);
        }

        var applicationBuilder = new ApplicationBuilder();
        applicationBuilder
                .addMenu(MenuGreet.class)
                .addMenu(MenuNotFound.class)
                .addMenu(MenuStart.class);
        applicationBuilder.build().run(new Resource("index"));
    }
}