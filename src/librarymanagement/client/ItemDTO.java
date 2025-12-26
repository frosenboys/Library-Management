package librarymanagement.client;

/**
 *
 * @author XuanDat
 */
public class ItemDTO {
    private long id;
    private String title;
    private String type;
    private int value;

    public ItemDTO(long id, String title, String type, int value) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.value = value;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public int getValue() { return value; }
    public void setTitle(String title) { this.title = title; }
}