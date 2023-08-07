package enums;

import exceptions.ApplicationException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum PlayerControlButtons {

    PREVIOUS_TRACK("Previous Track"),
    PLAY("Play"),
    NEXT_TRACK("Next Track"),
    PAUSE("PAUSE");

    List<String> buttons;

    PlayerControlButtons(String... playerControlButtons) {
        this.buttons = Arrays.asList(playerControlButtons);
    }

    public String getName() {
        Optional<String> buttonName = buttons.stream().findFirst();
        return buttonName.get();
    }

    public List<String> getButtons() {
        return buttons;
    }

    public static PlayerControlButtons getFooterButtonByName(String buttonName) {
        for (PlayerControlButtons controlButton : PlayerControlButtons.values()) {
            if (controlButton.name().equalsIgnoreCase(buttonName) || controlButton.getButtons().stream()
                    .anyMatch(p -> p.equalsIgnoreCase(buttonName))) {
                return controlButton;
            }
        }
        throw new ApplicationException("There is no player control button with name " + buttonName);
    }

}
