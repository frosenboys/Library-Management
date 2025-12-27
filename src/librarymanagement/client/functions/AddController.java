package librarymanagement.client.functions;

/**
 *
 * @author XuanDat
 */
import librarymanagement.entity.Magazine;
import librarymanagement.entity.Book;
import librarymanagement.entity.LibraryItem;
import librarymanagement.entity.DVD;
import librarymanagement.client.ItemDAO;

public class AddController {
    public void addItem(String idStr, String title, String type, String valueStr) throws Exception {
        if (title.isEmpty()) throw new Exception("Title cannot be empty!");
        
        long id;
        int value;
        
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new Exception("ID must be a number!");
        }

        try {
            value = Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            throw new Exception("Value must be a number!");
        }

        LibraryItem item = null;
        switch (type) {
            case "Book":
                item = new Book(id, title, value);
                break;
            case "DVD":
                item = new DVD(id, title, value);
                break;
            case "Magazine":
                item = new Magazine(id, title, value);
                break;
        }

        if (item != null) {
            if (!ItemDAO.getInstance().save(item)) throw new Exception("ID Existed!");
        }
    }
}