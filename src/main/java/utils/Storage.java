package utils;

import exceptions.ApplicationException;
import exceptions.AutomationImplementationException;

import java.util.HashMap;
import java.util.Map;

public class Storage {

//    Constants
    public static final String FOOTER_BUTTON_CONST = "#wrap footer [title='%s']";
    public static final String VOLUME_SLIDER_INITIAL_POSITION = "volumeSliderInitialPosition";

    private static volatile Storage instance;

    private static Map<String, Object> map = new HashMap<>();

    public static Storage getInstance() {
        if (instance == null) {
            synchronized (Storage.class) {
                if (instance == null) {
                    instance = new Storage();
                }
            }
        }
        return instance;
    }

    public void storeValue(String key, Object value) {
        if (CustomStringUtils.isEmpty(key) || value == null) {
            throw new ApplicationException("Key or Value cannot be null! Please supply valid method arguments");
        }
        map.put(key, value);
    }

     public String getAsString(String key) {
        verifyKeyExists(key);
        return map.get(key).toString();
    }

    public int getAsInteger(String key) {
        return Integer.parseInt(getAsString(key));
    }

    private void verifyKeyExists(String key) {
        if (!map.containsKey(key)) {
            throw new AutomationImplementationException("There is no stored data with key " + key);
        }
    }
}
