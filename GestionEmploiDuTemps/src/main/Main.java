package main;

import view.MainInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Launch the application in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainInterface mainInterface = new MainInterface();
            mainInterface.setVisible(true);
        });
    }
}
