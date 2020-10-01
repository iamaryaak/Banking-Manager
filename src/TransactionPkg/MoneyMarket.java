package TransactionPkg;

public class MoneyMarket extends Account {

    private int withdrawals;

    public MoneyMarket (Profile user, double amount, Date dateOpen){
        super(user, amount, dateOpen);
        acc_type = "OM";

    }

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}