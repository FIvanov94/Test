package utils;

import driver.DriverManager;

import javax.tools.Tool;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class WindowsPopupAction {

    private Robot robot;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public WindowsPopupAction() {
    }

    public void uploadFile() {
        String userDirPath = System.getProperty("user.dir");
        Path docPath = Paths.get(userDirPath, "src", "main", "resources", "files");
        StringSelection stringSelection = new StringSelection(docPath.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        paste();
        pressEnter();
        deleteText();
        pressEnter();
    }

    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


    private void paste() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
    }

    private void deleteText() {
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }
}
