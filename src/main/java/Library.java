import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementAction;

public class Library {


    private static final By ADD_MUSIC_BUTTON = By.cssSelector("[class*='ViewMessage-module__subMessage'] a");
    private static final By TRACKS = By.cssSelector("[class*='TracksList-module__tiles']");
    private static final By TRACKS_LIST = By.cssSelector("[class*='TrackRow-module__track']");
    private static final By SPEAKER_ICON = By.cssSelector("[class*='VolumeControl-module'] [class*='PlayerControls-module']");
    private static final By VOLUME_BAR = By.className("rangeslider");
    private static final By VOLUME_SLIDER = By.className("rangeslider__handle");
    private static final By PLAY_BUTTON = By.className("fa-play");
    private static final By PAUSE_BUTTON = By.className("fa-pause");
    private static final By PREVIOUS_TRACK_BUTTON = By.className("fa-backward");
    private static final By NEXT_TRACK_BUTTON = By.className("fa-forward");
    private static final By PLAYING_BAR = By.cssSelector("[class*='Header-module__header__playingBar']");
    private static final By SEARCH_TEXT_FIELD = By.cssSelector("[class*='Search-module'] input");
    private static final By TRACK_ARTIST = By.cssSelector("[class*='TrackRow-module__track'] [class*='cellArtist']");
    private static final By TRACK_TITLE = By.cssSelector("[class*='TracksList-module__tiles'] [class*='cellTrack_']");
    private static final By CURRENT_TRACK_PLAYING_ICON = By.cssSelector("[class*='PlayingIndicator-module__animation']");
    private static final By CURRENT_TRACK_PLAYING_PLAYER_TITLE = By.cssSelector("[class*='PlayingBarInfo-module__metas']");


    private ElementAction elementAction;

    public ElementAction getElementAction() {
        if (elementAction == null) {
            this.elementAction = new ElementAction(getDriver());
        }
        return elementAction;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public By getAddMusicButtonLocator() {
        return ADD_MUSIC_BUTTON;
    }

    public By getTracksLocator() {
        return TRACKS;
    }

    public By getTracksListLocator() {
        return TRACKS_LIST;
    }

    public By getSpeakerIconLocator() {
        return SPEAKER_ICON;
    }

    public By getVolumeBarLocator() {
        return VOLUME_BAR;
    }

    public By getVolumeSliderLocator() {
        return VOLUME_SLIDER;
    }

    public By getPlayButtonLocator() {
        return PLAY_BUTTON;
    }

    public By getPauseButtonLocator() {
        return PAUSE_BUTTON;
    }

    public By getPlayingBarLocator() {
        return PLAYING_BAR;
    }

    public By getTrackArtistLocator() {
        return TRACK_ARTIST;
    }

    public By getSearchTextFieldLocator() {
        return SEARCH_TEXT_FIELD;
    }

    public By getTrackTitleLocator() {
        return TRACK_TITLE;
    }

    public By getCurrentTrackPlayingIconLocator() {
        return CURRENT_TRACK_PLAYING_ICON;
    }

    public By getCurrentTrackPlayingPlayerTitle() {
        return CURRENT_TRACK_PLAYING_PLAYER_TITLE;
    }

    public By getPreviousTrackButtonLocator() {
        return PREVIOUS_TRACK_BUTTON;
    }

    public By getNextTrackButton() {
        return NEXT_TRACK_BUTTON;
    }

    public void adjustVolumeLevels(By element, int xOffSet, int yOffSet) {
        WebElement webElement = getDriver().findElement(element);
        getElementAction().getActions().moveToElement(webElement)
                .clickAndHold()
                .moveByOffset(xOffSet, yOffSet)
                .release()
                .build()
                .perform();;
    }

}
