package TransactionPkg;

/**
 *
 */
public class Checking extends Account {

    /**
     *
     */
    private boolean directDeposit;

    /**
     *
     * @param user
     * @param balance
     * @param dateOpen
     * @param d
     */
    public Checking(Profile user, double balance, Date dateOpen, boolean d) {
        super(user, balance, dateOpen);
        this.directDeposit = d;

    }

    /**
     *
     * @return
     */
    public String isDirectDeposit() {
        String depositType;
        if (directDeposit)
        {
            depositType = "*direct deposit account*";
        }
        else
            depositType = "";

        return depositType;
    }

    /**
     *
     * @param a
     * @return
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Checking && super.equals(a))
            instance = true;

        return instance;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "*Checking*" +  super.toString() + isDirectDeposit();
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        return (double)(0.0005/12.00);
    }


    /**
     *
     * @return
     */
    @Override
    public double monthlyFee() {
        return 25.00;
    }



}