package aston.java.intensive.module5.utils.menu.models;

public record Resource(ResourceMenu menu, ResourceAction action) {
    public Resource(ResourceMenu menu) {
        this(menu, new ResourceAction());
    }

    public Resource(String menu, String action) {
        this(new ResourceMenu(menu), new ResourceAction(action));
    }

    public Resource(String menu) {
        this(new ResourceMenu(menu));
    }

    public boolean isExit() {
        return this.menu.isExit();
    }

    public static Resource exit() {
        return new Resource(ResourceMenu.exit());
    }
}
