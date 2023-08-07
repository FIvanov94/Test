package enums;

import exceptions.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum DropDownOptions {

    SYSTEM_DEFAULT("System (default)"),
    LIGHT("Light"),
    DARK("Dark"),
    DARK_LEGACY("Dark (legacy)");

    List<String> dropDownOptions;

    public String getName() {
        Optional<String> dropDownOption = dropDownOptions.stream().findFirst();
        return dropDownOption.get();
    }

    DropDownOptions(String... dropDownOptions) {
        this.dropDownOptions = Arrays.asList(dropDownOptions);
    }

    public List<String> getDropDownOptions() {
        return dropDownOptions;
    }

    public static DropDownOptions getDropDownOptionsByName(String dropDownOptionName) {
        for (DropDownOptions dropDownOption : DropDownOptions.values()) {
            if (dropDownOption.name().equalsIgnoreCase(dropDownOptionName) || dropDownOption.getDropDownOptions().stream()
                    .anyMatch(p -> p.equalsIgnoreCase(dropDownOptionName))) {
                return dropDownOption;
            }
        }
        throw new ApplicationException("There is no dropdown option with name " + dropDownOptionName);
    }
}
