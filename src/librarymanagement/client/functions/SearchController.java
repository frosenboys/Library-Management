package librarymanagement.client.functions;

/**
 *
 * @author XuanDat
 */
import librarymanagement.client.ItemDAO;
import librarymanagement.client.ItemDTO;
import librarymanagement.provider.LibraryItem;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private List<ItemDTO> convertToDTO(List<LibraryItem> entities) {
        List<ItemDTO> dtos = new ArrayList<>();
        for (LibraryItem item : entities) {
            dtos.add(new ItemDTO(item.getId(), item.getTitle(), item.getType(), item.getValue()));
        }
        return dtos;
    }

    public List<ItemDTO> getAll() {
        return convertToDTO(ItemDAO.getInstance().findAll());
    }

    public List<ItemDTO> searchByTitle(String keyword) {
        List<LibraryItem> all = ItemDAO.getInstance().findAll();
        List<LibraryItem> result = new ArrayList<>();
        
        for (LibraryItem item : all) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(item);
            }
        }
        return convertToDTO(result);
    }
}