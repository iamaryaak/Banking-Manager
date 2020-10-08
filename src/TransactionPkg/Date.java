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
        if(date.day == this.day && date.year == this.year && date.month == this.month){
            return 0;
        }else if(date.day < this.day && date.year < this.year && date.month < this.month){
            return -1;
        }else{
            return 1;
        }
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
        boolean val = false;
        // check if year is valid
        if(this.year == 2012 || this.year == 2016 || this.year == 2020){
            // leap year
            if(this.month == 2 && this.day == 29{
                val = true;
            }
        }

        return false;
    }
}
