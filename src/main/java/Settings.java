import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementAction;

public class Settings {

    private static final By SETTINGS_LIBRARY_BUTTONS = By.cssSelector("[class*='Button-module__button']");
    private static final By LIBRARY_SETTINGS_BUTTON = By.cssSelector("[href='#/settings/library']");
    private static final By AUDIO_SETTINGS_BUTTON = By.cssSelector("[href='#/settings/audio']");
    private static final By INTERFACE_SETTINGS_BUTTON = By.cssSelector("[href='#/settings/interface']");
    private static final By ABOUT_SETTINGS_BUTTON = By.cssSelector("[href='#/settings/about']");
    private static final By THEME_DROPDOWN_MENU = By.id("setting-theme");

    private ElementAction elementAction;

    private ElementAction getElementAction() {
        if (elementAction == null) {
            this.elementAction = new ElementAction(getDriver());
        }
        return elementAction;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public WebElement getAddFilesButton() {
        return getDriver().findElements(SETTINGS_LIBRARY_BUTTONS).get(0);
    }

    public By getLibraryButton() {
        return LIBRARY_SETTINGS_BUTTON;
    }

    public By getAudioButton() {
        return AUDIO_SETTINGS_BUTTON;
    }

    public By getInterfaceButton() {
        return INTERFACE_SETTINGS_BUTTON;
    }

    public By getAboutButton() {
        return ABOUT_SETTINGS_BUTTON;
    }

    public By getThemeDropdownMenu() {
        return THEME_DROPDOWN_MENU;
    }
}
