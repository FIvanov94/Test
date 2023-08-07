
import dataTables.DropDownOptionsDataTableType;
import enums.PlayerControlButtons;
import enums.Section;
import enums.SettingsSubsections;
import exceptions.ApplicationException;
import exceptions.AutomationImplementationException;
import driver.DriverManager;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.CustomStringUtils;
import utils.ElementAction;
import utils.Storage;
import utils.WindowsPopupAction;

import java.awt.*;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TestSteps {

    private static final Logger logger = LogManager.getLogger(TestSteps.class);

    private ElementAction elementAction;
    private Library librarySection = new Library();
    private Settings settingsSection = new Settings();
    private Playlist playlistSection = new Playlist();

    public static Scenario scenario;


    private ElementAction getElementAction() {
        if (elementAction == null) {
            this.elementAction = new ElementAction(getDriver());
        }
        return elementAction;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("I am in the {string} section")
    public void iAmInTheSection(String sectionName) {
        Section section = Section.getFooterButtonByName(sectionName);
        By element = getElementAction().buildCssSelectorByString(Storage.FOOTER_BUTTON_CONST, section.getName());
        getElementAction().clickElement(element);
        switch (section) {
            case LIBRARY:
                Assert.assertTrue(getElementAction().isDisplayed(librarySection.getAddMusicButtonLocator()));
                break;
            case SETTINGS:
                Assert.assertTrue(getElementAction().isDisplayed(settingsSection.getAddFilesButton()));
                break;
            case PLAYLISTS:
                Assert.assertTrue(getElementAction().isDisplayed(playlistSection.getCreatePlaylistButton()));
                break;
            default: throw new AutomationImplementationException("There is no section with name " + section + " did you forget to add it to switch/case?");
        }
    }

    @When("I click the {string} footer button")
    public void clickTheFooterButton(String sectionName) {
        Section section = Section.getFooterButtonByName(sectionName);
        By element = getElementAction().buildCssSelectorByString(Storage.FOOTER_BUTTON_CONST, section.getName());
        getElementAction().clickElement(element);
    }

    @When("I click the Add Music button")
    public void clickedTheSettingsButton() {
        getElementAction().clickElement(librarySection.getAddMusicButtonLocator());
    }


    @When("I add files")
    public void addFiles() {
        getElementAction().clickElement(settingsSection.getAddFilesButton());
        getElementAction().waitExplicitlyInMilliseconds(500);
        WindowsPopupAction windowsPopupAction = new WindowsPopupAction();
        windowsPopupAction.uploadFile();
        Section footerButton = Section.getFooterButtonByName("Library");
        By element = getElementAction().buildCssSelectorByString(Storage.FOOTER_BUTTON_CONST, footerButton.getName());
        getElementAction().clickElement(element);
    }

    @When("I add files successfully")
    public void addFilesSuccessfully() throws AWTException, InterruptedException {
        addFiles();
        iShouldSeeTheFile();
    }

    @Then("the {string} section should be opened")
    public void theSectionShouldBeDisplayed(String sectionName) {
        Section section = Section.getFooterButtonByName(sectionName);
        getElementAction().clickElement(getElementAction().buildCssSelectorByString(Storage.FOOTER_BUTTON_CONST, section.getName()));
        switch (section) {
            case LIBRARY:
                Assert.assertTrue(getElementAction().isDisplayed(librarySection.getAddMusicButtonLocator()));
                break;
            case SETTINGS:
                Assert.assertTrue(getElementAction().isDisplayed(settingsSection.getAddFilesButton()));
                break;
            case PLAYLISTS:
                Assert.assertTrue(getElementAction().isDisplayed(playlistSection.getCreatePlaylistButton()));
                break;
            default: throw new AutomationImplementationException("There is no such section " + section + " did you forget to add it to switch/case?");
        }
    }

    @When("I hover on the speaker icon")
    public void hoverOnSpeakerIcon() {
        getElementAction().isDisplayed(librarySection.getSpeakerIconLocator());
        WebElement speakerIcon = getDriver().findElement(librarySection.getSpeakerIconLocator());
        getElementAction().getActions().moveToElement(speakerIcon).perform();
    }

    @Then("the volume slider should be displayed")
    public void theVolumeSliderShouldBeDisplayed() {
        Assert.assertTrue(getElementAction().isDisplayed(librarySection.getVolumeBarLocator()));
    }

    @Then("the library should be empty")
    public void theLibraryShouldBeEmpty() {
        Assert.assertTrue(getElementAction().isDisplayed(librarySection.getAddMusicButtonLocator()));
    }

    @Then("I should see the file")
    public void iShouldSeeTheFile() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Assert.assertTrue(getElementAction().isDisplayed(librarySection.getTracksLocator()));
    }

    @When("I adjust the volume")
    public void adjustTheVolume() {
        hoverOnSpeakerIcon();
        theVolumeSliderShouldBeDisplayed();
        String volumeSliderPosition = CustomStringUtils.trimNonNumeric(getElementAction().getAttribute(librarySection.getVolumeSliderLocator(), "style"));
        Storage.getInstance().storeValue(Storage.VOLUME_SLIDER_INITIAL_POSITION, volumeSliderPosition);
        librarySection.adjustVolumeLevels(librarySection.getVolumeSliderLocator(), -25, 0);
    }

    @Then("the volume level should be changed")
    public void theVolumeLevelShouldBeChanged() {
        hoverOnSpeakerIcon();
        theVolumeSliderShouldBeDisplayed();
        String volumeSliderPosition = CustomStringUtils.trimNonNumeric(getElementAction().getAttribute(librarySection.getVolumeSliderLocator(), "style"));
        int newVolumeSliderPosition = Integer.parseInt(volumeSliderPosition);
        Assert.assertNotEquals(newVolumeSliderPosition, Storage.getInstance().getAsInteger(Storage.VOLUME_SLIDER_INITIAL_POSITION),
                "The volume slider position was not changed");
    }

    @When("I select the first track")
    public void selectTrack() {
        getElementAction().waitExplicitlyInMilliseconds(500);
        List<WebElement> trackList = getDriver().findElements(librarySection.getTracksListLocator());
        Assert.assertTrue(trackList.size() != 0, "There are no tracks in the track list");
        getElementAction().clickElement(trackList.stream().findFirst().get());

    }

    @When("I press the {string} button")
    public void pressPlayerControlButton(String button) {
        getElementAction().waitExplicitlyInMilliseconds(500);
        PlayerControlButtons controlButton = PlayerControlButtons.getFooterButtonByName(button);
        switch (controlButton) {
            case PLAY:
                getElementAction().clickElement(librarySection.getPlayButtonLocator());
                break;
            case PAUSE:
                getElementAction().clickElement(librarySection.getPauseButtonLocator());
                break;
            case NEXT_TRACK:
                getElementAction().clickElement(librarySection.getNextTrackButton());
                break;
            case PREVIOUS_TRACK:
                getElementAction().clickElement(librarySection.getPreviousTrackButtonLocator());
                break;
            default:
                throw new AutomationImplementationException("There is no such Button " + button + " did you forget to add it to switch/case?");
        }
    }

    @Then("the track should start playing")
    public void theTrackShouldStartPlaying(){
        Assert.assertTrue(getElementAction().isDisplayed(librarySection.getPauseButtonLocator()), "The pause button was not displayed!");
        Assert.assertTrue(getElementAction().isDisplayed(librarySection.getPlayingBarLocator()), "The playing bar was not displayed");
    }

    @When("I click the {string} subsection button in settings")
    public void clickButtonInSettings(String subsectionButton) {
        SettingsSubsections subsection = SettingsSubsections.getSubsectionByName(subsectionButton);
        switch (subsection) {
            case LIBRARY:
                getElementAction().clickElement(settingsSection.getLibraryButton());
                break;
            case AUDIO:
                getElementAction().clickElement(settingsSection.getAudioButton());
                break;
            case INTERFACE:
                getElementAction().clickElement(settingsSection.getInterfaceButton());
                break;
            case ABOUT:
                getElementAction().clickElement(settingsSection.getAboutButton());
                break;
            default:
                throw new AutomationImplementationException("There is no such subsection " + subsectionButton + " did you forget to add it to switch/case?");
        }
    }

    @When("I expand the Theme dropdown menu")
    public void expandThemeDropDownMenu() {
        WebElement element = getDriver().findElement(settingsSection.getThemeDropdownMenu());
        Select select = new Select(element);
        select.getOptions();
    }

    @Then("I should see the following options after expanding the dropdown menu:")
    public void iShouldSeeTheFollowingOptions(List<DropDownOptionsDataTableType> dropDownOptions) {
        WebElement element = getDriver().findElement(settingsSection.getThemeDropdownMenu());
        getElementAction().clickElement(element);
        Select select = new Select(element);
        List<WebElement> allOptions = select.getOptions();
        if (allOptions.size() == dropDownOptions.size()) {
        for (int i = 0; i < allOptions.size(); i++) {
            Assert.assertEquals(allOptions.get(i).getText(), dropDownOptions.get(i).getDropDownOption().getName());
        }
        } else {
            throw new ApplicationException("Actual options size: " + allOptions.size() +
                    " is not equal to the expected options size: " + dropDownOptions.size());
        }

    }

    @When("I try to create a playlist")
    public void tryToCreateAPlaylist() {
        getElementAction().clickElement(playlistSection.getCreatePlaylistButton());
    }

    @Then("the playlist should be created successfully")
    public void thePlaylistShouldBeCreatedSuccessfully() {
        Assert.assertTrue(getElementAction().isDisplayed(playlistSection.getPlaylist()));
    }

    @When("I create a playlist")
    public void createAPlaylist() {
        tryToCreateAPlaylist();
        thePlaylistShouldBeCreatedSuccessfully();
    }

    @When("I search for artist {string}")
    public void searchForArtist(String artist) {
        WebElement search = getDriver().findElement(librarySection.getSearchTextFieldLocator());
        getElementAction().clickElement(search);
        search.sendKeys(artist);
        getElementAction().waitExplicitlyInMilliseconds(500);
    }

    @Then("only the song by {string} artist should be displayed in the track list")
    public void onlyTheSongByArtistShouldBeDisplayedInTheTrackList(String artist) {
        List<WebElement> artistsElements = getDriver().findElements(librarySection.getTrackArtistLocator());
        String expectedArtist = getElementAction().getAttribute(librarySection.getSearchTextFieldLocator(), "value");
        List<String> artists = new LinkedList<>();

            if (artistsElements.size() == 1) {
                artists.add(artistsElements.get(0).getText());
            } else  {
                throw new ApplicationException("Unexpected number artists " + artistsElements.size() + " in the track list");
            }
                Assert.assertTrue(artists.get(0).equalsIgnoreCase(artist), "The artist is not as expected... \nExpected: " + expectedArtist
                + "\nActual: " + artist);
    }

    @Then("The next/previous song should start playing")
    public void theNextSongShouldStartPlaying() {
        getElementAction().waitExplicitlyInMilliseconds(500);
        WebElement currentTrackPlayingIcon = getDriver().findElement(librarySection.getCurrentTrackPlayingIconLocator());
        WebElement currentTrackPlayingTitle = getDriver().findElement(with(By.tagName("div")).toRightOf(currentTrackPlayingIcon));
        WebElement currentTrackPlayingPlayerTitle = getDriver().findElement(librarySection.getCurrentTrackPlayingPlayerTitle());
        Assert.assertTrue(currentTrackPlayingPlayerTitle.getText().contains(currentTrackPlayingTitle.getText()),
                "The current track playing is not as expected!");
    }

}
