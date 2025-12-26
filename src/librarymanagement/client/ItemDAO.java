package librarymanagement.client;

/**
 *
 * @author XuanDat
 */
import librarymanagement.provider.LibraryItem;
import java.util.List;

public class ItemDAO {
    private static ItemDAO instance;

    private ItemDAO() {}

    public static ItemDAO getInstance() {
        if (instance == null) instance = new ItemDAO();
        return instance;
    }

    public boolean save(LibraryItem item) {
        if(!FakeDatabase.insertItem(item)){
            return false;
        }
        return true;
    }

    public void update(LibraryItem newItem) {
        FakeDatabase.updateItem(newItem);
    }

    public void delete(int index) {
        if (index >= 0 && index < FakeDatabase.items.size()) {
            FakeDatabase.items.remove(index);
        }
    }

    public LibraryItem get(int index) {
        if (index >= 0 && index < FakeDatabase.items.size()) {
            return FakeDatabase.items.get(index);
        }
        return null;
    }

    public List<LibraryItem> findAll() {
        return FakeDatabase.items;
    }
}