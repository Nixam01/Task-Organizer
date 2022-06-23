package aproproject_application;

import aproproject_application.gui.StartClientView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        if (controller == null) {
            System.err.println("Problem with system files.\nCheck if program file contains:\nsystem.json, system.properties, system.txt\nNever modify those files on your own!");
            return;
        }
        try {
            controller.startLogic();
        } catch (IOException e) {
            System.err.println("Fatal error!");
        }
        new StartClientView();
    }
}
