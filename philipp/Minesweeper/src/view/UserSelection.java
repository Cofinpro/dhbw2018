package view;

import javax.swing.*;
import java.awt.*;

public class UserSelection {

    public static int selectDifficulty(String title, String message, Object[] options) {
        Frame frame = new Frame();
        int n = JOptionPane.showOptionDialog(frame,
                message,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[4]);

        return n;
    }
}
