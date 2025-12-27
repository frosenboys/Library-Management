package librarymanagement.entity;

/**
 *
 * @author XuanDat
 */
public class DVD extends LibraryItem {
    private int duration;

    public DVD(long id, String title, int duration) {
        super(id, title, "DVD");
        this.duration = duration;
    }

    @Override
    public double calculateFee(int days) { return days * 1.5; }

    @Override
    public int getValue() { return duration; }
    public void setValue(int _duration) { duration = _duration; }
}