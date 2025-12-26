package librarymanagement.client.functions;

/**
 *
 * @author XuanDat
 */
import librarymanagement.client.ItemDAO;

public class DeleteController {
    public void deleteItem(int index) {
        ItemDAO.getInstance().delete(index);
    }
}