package librarymanagement.client.functions;

/**
 *
 * @author XuanDat
 */
import librarymanagement.client.ItemDAO;
import librarymanagement.provider.LibraryItem;
import java.util.List;

public class StatsController {
    public String getCountReport() {
        List<LibraryItem> list = ItemDAO.getInstance().findAll();
        int book = 0, dvd = 0, mag = 0;
        for (LibraryItem item : list) {
            if (item.getType().equals("Book")) book++;
            else if (item.getType().equals("DVD")) dvd++;
            else mag++;
        }
        return "Book: " + book + "\nDVD: " + dvd + "\nMagazine: " + mag;
    }

    public String getFeeReport(String daysStr) throws Exception {
        int days;
        try {
            days = Integer.parseInt(daysStr);
        } catch (NumberFormatException e) {
            throw new Exception("Days must be a number!");
        }

        List<LibraryItem> list = ItemDAO.getInstance().findAll();
        StringBuilder sb = new StringBuilder("Items with Fee > $2:\n");
        boolean found = false;

        for (LibraryItem item : list) {
            double fee = item.calculateFee(days);
            if (fee > 2) {
                sb.append("- ").append(item.getTitle())
                  .append(" ($").append(String.format("%.2f", fee)).append(")\n");
                found = true;
            }
        }
        
        if (!found) return "No items found.";
        return sb.toString();
    }
}