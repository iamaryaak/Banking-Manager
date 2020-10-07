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

    /**
     *
     * @param date
     * @return
     */
    public int compareTo(Date date){
        return 0;
    } //return 0, 1, or -1


    /**
     * Changes data for data to String "mm/dd/yyyy"
     * @return String - represents the data
     */
    public String toString()
    {
        String dateOpen = (month + "/" + day + "/" + year);
        return dateOpen;
    }

    /**
     * Check if data is valid given the day, month, and year
     * @return boolean - data is valid
     */
    public boolean isValid() {
        // check if date is valid
        return false;
    }
}
