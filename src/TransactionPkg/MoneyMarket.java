package TransactionPkg;

/**
 *
 */
public class MoneyMarket extends Account {

    /**
     *
     */
    private int withdrawals;

    /**
     *
     * @param user
     * @param amount
     * @param dateOpen
     */
    public MoneyMarket (Profile user, double amount, Date dateOpen){
        super(user, amount, dateOpen);
        this.withdrawals = 0;
    }


    /**
     *
     */
    public void countWithdrawals() {
        withdrawals++;
    }

    /**
     *
     * @param a
     * @return
     */
    @Override
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof MoneyMarket && super.equals(a))
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
        return "*Money Market*" + super.toString() + "*" + withdrawals + " withdrawals*";
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        return (double)(0.0065/12.00);
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyFee() {
        if(withdrawals > 6){
            return 12.00;
        }else{
            return 0.00;
        }

    }

}
