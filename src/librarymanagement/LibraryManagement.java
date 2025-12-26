package librarymanagement;

import librarymanagement.client.MenuUI;
import javax.swing.SwingUtilities;
import librarymanagement.client.FakeDatabase;

public class LibraryManagement {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuUI().setVisible(true));
        FakeDatabase.initData();
    }
}