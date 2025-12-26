package librarymanagement.client;

/**
 *
 * @author XuanDat
 */
import librarymanagement.provider.*;
import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    public static List<LibraryItem> items = new ArrayList<>();

    public static void initData() {
        items.clear(); 

        items.add(new Book(101, "Java Programming", 300));
        items.add(new Book(102, "Clean Code", 450));
        items.add(new Book(103, "Head First Design Patterns", 600));

        items.add(new DVD(201, "Avengers: Endgame", 181));
        items.add(new DVD(202, "Inception", 148));

        items.add(new Magazine(301, "Forbes", 12));
        items.add(new Magazine(302, "PC World", 55));
    }
    
    public static boolean insertItem(LibraryItem item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                return false;
            }
        }
        items.add(item);
        return true;
    }
    
    public static boolean updateItem(LibraryItem newItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == newItem.getId()) {
                items.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            if (item.getId() == id) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public static LibraryItem findItemById(int id) {
        for (LibraryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}