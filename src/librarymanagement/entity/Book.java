package librarymanagement.entity;

/**
 *
 * @author XuanDat
 */
public class Book extends LibraryItem {
    private int pages;

    public Book(long id, String title, int pages) {
        super(id, title, "Book");
        this.pages = pages;
    }

    @Override
    public double calculateFee(int days) { return  (pages / 100) * 0.1 * days; }

    @Override
    public int getValue() { return pages; }
    public void setValue(int _pages) { pages = _pages; }
}