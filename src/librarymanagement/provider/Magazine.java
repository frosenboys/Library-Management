package librarymanagement.provider;

/**
 *
 * @author XuanDat
 */
public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(long id, String title, int issueNumber) {
        super(id, title, "Magazine");
        this.issueNumber = issueNumber;
    }

    @Override
    public double calculateFee(int days) { return days * 0.5; }

    @Override
    public int getValue() { return issueNumber; }
    public void setValue(int _issueNumber) { issueNumber = _issueNumber; }
}