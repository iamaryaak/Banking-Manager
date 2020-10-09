package TransactionPkg;

/**
 * A class that holds the Date object and it's appropriate methods
 * Handles compareTo, toString, isLeap, isValid
 */
public class Date implements Comparable<Date> {
    /**
     * Declares the variables private to the Date Class, used for Date object
     */
    private int year;
    private int month;
    private int day;

    int MAX_VALID_YR = 2021;
    int MIN_VALID_YR = 1999;

    /**
     * Default constructor for the date class, initializes the variables associated with it
     * @param y
     * @param m
     * @param d
     */
    public Date (int y, int m, int d)
    {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    /**
     * Compares the dates to be used properly in the sorting method in Account Database
     * @param date
     * @return int - based on the outcome of the comparison
     */
    public int compareTo(Date date) {
        // get integer value of date
        int dateInt = (this.year * 10000) + (this.month * 100) + this.day;
        int compdateInt = (date.year * 10000) + (date.month * 100) + date.day;
        if(compdateInt > dateInt){
            return 1;
        }else if(compdateInt < dateInt){
            return -1;
        }else{
            return 0;
        }

    }


    /**
     * Changes data for data to String "mm/dd/yyyy"
     * @return String - represents the data
     */
    public String toString()
    {
        return (month + "/" + day + "/" + year);
    }

    /**
     * Helper Method - Check if data is valid given the day, month, and year
     * @return boolean - data is valid
     */
    public boolean isLeap(int year)
    {
        // Return true if year is
        // a multiple of 4 and not
        // multiple of 100.
        // OR year is multiple of 400.
        return (((year % 4 == 0) &&
                (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    /**
     * Checks if the date is a valid date
     * @return Boolean - returns false if the date is not valid, true if it is
     */
    public boolean isValid() {
        // check if date is valid
        int y = this.year;
        int m = this.month;
        int d = this.day;
        // check if year is valid
        if (y > MAX_VALID_YR || y < MIN_VALID_YR)
            return false;
        if (m < 1 || m > 12)
            return false;
        if (d < 1 || d > 31)
            return false;

        // Handle February month
        // with leap year
        if (m == 2)
        {
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }


        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);

        return true;

    }
}
