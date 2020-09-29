package TransactionPkg;

public class Savings extends Account {

    private boolean isLoyal;

    public Savings (boolean i){
        this.isLoyal = i;
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