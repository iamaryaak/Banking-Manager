public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public int compareTo(Date date) {
        //return 0, 1, or -1
        return 0;
    }


    public String toString() {
        // in the format mm/dd/yyyy
        return null;
    }

    public boolean isValid() {
        return true;
    }
}