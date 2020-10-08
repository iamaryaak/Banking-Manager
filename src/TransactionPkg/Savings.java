package TransactionPkg;

/**
 *
 */
public class Savings extends Account {

    /**
     *
     */
    private boolean isLoyal;

    /**
     *
     * @param user
     * @param amount
     * @param dateOpen
     * @param i
     */
    public Savings (Profile user, double amount, Date dateOpen, boolean i){
        super(user, amount, dateOpen);
        this.isLoyal = i;
    }

    /**
     *
     * @return
     */
    public String isLoyal() {
        String saveType;
        if (isLoyal)
        {
            saveType = "*special Savings account*";
        }
        else
            saveType = "";

        return saveType;
    }

    /**
     *
     * @param a
     * @return
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Savings && super.equals(a))
            instance = true;

        return instance;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "*Savings*" + super.toString() + isLoyal();
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return (double)(0.0035/12.00);
        }else{
            return (double)(0.0025/12.00);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyFee() {
        return 5.00;
    }



}