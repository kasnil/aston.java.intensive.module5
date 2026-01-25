package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;

@Menu("greet")
public final class MenuGreet {
    private final IOService console;

    public MenuGreet() {
        this.console = new ConsoleService();
    }

    @Action("en")
    public Response en(Param param) {
        console.output("Hello!");
        return new Response(new Resource("index"));
    }

    @Action("ru")
    public Response ru(Param param) {
        console.output("Привет!");
        return new Response(new Resource("index"));
    }

    @Action("es")
    public Response es(Param param) {
        console.output("Hola!");
        return new Response(new Resource("index"));
    }
}
