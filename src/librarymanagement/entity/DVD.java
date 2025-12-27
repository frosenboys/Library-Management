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
    public double calculateFee(int days) { return (duration / 30) * 0.05 * days; }

    @Override
    public int getValue() { return duration; }
    public void setValue(int _duration) { duration = _duration; }
}