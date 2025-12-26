package librarymanagement.provider;

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
    public double calculateFee(int days) { return days * 1.0; }

    @Override
    public int getValue() { return pages; }
    public void setValue(int _pages) { pages = _pages; }
}