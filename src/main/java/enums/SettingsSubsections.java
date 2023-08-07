package enums;

import exceptions.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum SettingsSubsections {

    LIBRARY("Library"),
    AUDIO("Audio"),
    INTERFACE("Interface"),
    ABOUT("About");

    private List<String> subsections;

    public String getName() {
        Optional<String> subSection = subsections.stream().findFirst();
        return subSection.get();
    }

    SettingsSubsections(String... subSections) {
        this.subsections = Arrays.asList(subSections);
    }

    public List<String> getSubsections() {
        return subsections;
    }

    public static SettingsSubsections getSubsectionByName(String subsectionName) {
        for (SettingsSubsections subsection : SettingsSubsections.values()) {
            if (subsection.name().equalsIgnoreCase(subsectionName) || subsection.getSubsections().stream()
                    .anyMatch(p -> p.equalsIgnoreCase(subsectionName))) {
                return subsection;
            }
        }
        throw new ApplicationException("There is no subsection with name " + subsectionName);
    }
}
