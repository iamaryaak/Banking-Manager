package TransactionPkg;

public class MoneyMarket extends Account {

    private int withdrawals;

    public MoneyMarket (int w){
        this.withdrawals = w;
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