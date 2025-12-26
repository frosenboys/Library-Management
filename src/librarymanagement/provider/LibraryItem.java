package librarymanagement.provider;

/**
 *
 * @author XuanDat
 */
public class LibraryItem {
    protected long id;
    protected String title;
    protected String type;

    public LibraryItem(long id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public long getId() { return id; }
    public void setId(long _id) { id = _id; }
    
    public String getTitle() { return title; }
    public void setTitle(String _title) { title = _title; }
    
    public String getType() { return type; }
    public void getType(String _type) { type = _type; }

    public double calculateFee(int days) {
        return 0.0;
    }

    public int getValue() {
        return 0;
    }
}