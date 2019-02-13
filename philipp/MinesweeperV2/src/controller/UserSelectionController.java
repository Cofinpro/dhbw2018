package controller;

import javax.swing.*;

public class UserSelectionController {

    public static int getUserChoice(String title, String message, Object[] options) {
        JFrame frame = new JFrame();
        int n = JOptionPane.showOptionDialog(frame,
                message,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return n;
    }
}
