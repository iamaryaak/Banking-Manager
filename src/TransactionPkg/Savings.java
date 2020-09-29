package TransactionPkg;

public class Savings extends Account {

    private boolean isLoyal;

    public Savings(Boolean iL){
        this.isLoyal = iL;
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