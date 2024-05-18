package snotepad.helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageBox {

    static Component parentComponent;

    public static void show(String title, String message) {
        if (parentComponent != null) {
            JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static boolean confirm(String title, String message) {
        boolean ans = false;
        int user_ans = -1;
        if (parentComponent != null) {
            user_ans = JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION);
        } else {
            user_ans = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }

        if (user_ans == JOptionPane.CLOSED_OPTION || user_ans == JOptionPane.NO_OPTION) {
            ans = false;
        } else {
            ans = true;
        }

        return ans;
    }

}
