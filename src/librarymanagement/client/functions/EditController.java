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
import librarymanagement.client.ItemDTO;

public class EditController {
    public ItemDTO getItemDTO(int index) {
        LibraryItem item = ItemDAO.getInstance().get(index);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getTitle(), item.getType(), item.getValue());
        }
        return null;
    }

    public void updateItem(int index, String idStr, String title, String type, String valStr) throws Exception {
        if (title.isEmpty()) throw new Exception("Title cannot be empty!");

        long id;
        int value;
        try { id = Long.parseLong(idStr); } catch (NumberFormatException e) { throw new Exception("ID must be a number!"); }
        try { value = Integer.parseInt(valStr); } catch (NumberFormatException e) { throw new Exception("Value must be a number!"); }

        LibraryItem newItem = null;
        switch (type) {
            case "Book": newItem = new Book(id, title, value); break;
            case "DVD": newItem = new DVD(id, title, value); break;
            case "Magazine": newItem = new Magazine(id, title, value); break;
        }

        if (newItem != null) {
            ItemDAO.getInstance().update(newItem);
        }
    }
}