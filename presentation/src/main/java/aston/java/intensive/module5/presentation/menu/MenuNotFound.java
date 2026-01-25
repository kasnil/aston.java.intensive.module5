package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;

@Menu
public final class MenuNotFound {
    private final IOService console;

    public MenuNotFound(IOService console) {
        this.console = console;
    }

    @Action
    public Response notFound(Param param) {
        console.output("Not found");
        return new Response(new Resource("index"));
    }
}
