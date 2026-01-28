package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.ResourceAction;
import aston.java.intensive.module5.utils.menu.models.ResourceMenu;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ApplicationResourceBundle {

    public static final ApplicationResourceBundle RESOURCES = new ApplicationResourceBundle();
    public static final ResourceBundle resource = ResourceBundle.getBundle("application");

    private String getStringOrDefault(String key, String defaultValue)
    {
        Ensure.that(key).isNotNull();
        try
        {
            return containsKey(key) ? resource.getString(key) : defaultValue;
        }
        catch(MissingResourceException | ClassCastException exception )
        {
            return defaultValue;
        }
    }

    private boolean containsKey(String key)
    {
        Ensure.that(key).isNotNull();

        return this.resource.containsKey(key);
    }

    public Resource getStartResource(String defaultMenuValue, String defaultResourceValue) {
        return new Resource(getResourceMenu(defaultMenuValue), getResourceAction(defaultResourceValue));
    }

    public ResourceMenu getResourceMenu(String defaultValue) {
        return new ResourceMenu(getStringOrDefault("menu", defaultValue));
    }

    public ResourceAction getResourceAction(String defaultValue) {
        return new ResourceAction(getStringOrDefault("action", defaultValue));
    }
}
