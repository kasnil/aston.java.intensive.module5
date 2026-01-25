package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Response;

@Menu
public final class MenuNotFound {
    @Action
    public Response notFound(Param param) {
        System.out.println("Exit");
        return Response.exit();
    }
}
