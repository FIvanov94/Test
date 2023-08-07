package enums;

import exceptions.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Section {

    LIBRARY("Library"),
    PLAYLISTS("Playlists"),
    SETTINGS("Settings");

    private final List<String> titles;

    Section(String... titles) {
        this.titles = Arrays.asList(titles);
    }

    public List<String> getTitles() {
        return titles;
    }

    public String getName() {
        Optional<String> footerButtonName = titles.stream().findFirst();
        return footerButtonName.get();
    }

    public static Section getFooterButtonByName(String footerButtonName) {
        for (Section footerButton : Section.values()) {
            if (footerButton.name().equalsIgnoreCase(footerButtonName) || footerButton.getTitles().stream()
                    .anyMatch(p -> p.equalsIgnoreCase(footerButtonName))) {
                return footerButton;
            }
        }
        throw new ApplicationException("There is no footer button with name " + footerButtonName);
    }


    @Override
    public String toString() {
        return this.getName();
    }
}
