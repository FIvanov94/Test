import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementAction;

public class Playlist {

    private static final By CREATE_PLAYLIST_BUTTON = By.className("reset");
    private static final By PLAYLIST = By.cssSelector("[class*='PlaylistsNavLink']");

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

    public By getCreatePlaylistButton() {
        return CREATE_PLAYLIST_BUTTON;
    }

    public By getPlaylist() {
        return PLAYLIST;
    }
}
