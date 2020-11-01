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
     * @param y Year
     * @param m Month
     * @param d Day
     */
    public Date (int y, int m, int d)
    {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    /**
     * Compares the dates to be used properly in the sorting method in Account Database
     * @param date Date value needed to compare to
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
     * @param year Year in question, to check validity
     * @return boolean if it is a valid leap year or not
     */
    public boolean isLeap(int year)
    {
        // Return true if year is
        // a multiple of 4 and not
        // multiple of 100.
        // OR year is multiple of 400.
        int yearFour = 4;
        int cent = 100;
        int quadCent = 400;
        return (((year % yearFour == 0) &&
                (year % cent != 0)) ||
                (year % quadCent == 0));
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
        int uno = 1;
        int monthMax = 12;
        // check if year is valid
        if (y > MAX_VALID_YR || y < MIN_VALID_YR)
            return false;
        if (m < uno || m > monthMax)
            return false;
        if (d < uno || d > monthMax)
            return false;

        // Handle February month
        // with leap year
        int feb = 2;
        int maxFeb = 29;
        int minFeb = 28;
        if (m == feb)
        {
            if (isLeap(y))
                return (d <= maxFeb);
            else
                return (d <= minFeb);
        }

        int four = 4;
        int six = 6;
        int nine = 9;
        int thirty = 30;
        int eleven = 11;
        if (m == four || m == six || m == nine || m == eleven)
            return (d <= thirty);

        return true;

    }
}
