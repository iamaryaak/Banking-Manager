package TransactionPkg;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date (int y, int m, int d)
    {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public int compareTo(Date date){
        return 0;
    } //return 0, 1, or -1


    public String toString()
    {
        String dateOpen = (month + "/" + day + "/" + year);
        return dateOpen;
    } //in the format mm/dd/yyyy

    public boolean isValid() {
        // check if date is valid
        return false;
    }
}
