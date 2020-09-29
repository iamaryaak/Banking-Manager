package TransactionPkg;

public class Checking extends Account {

    private boolean directDeposit;

    public Checking (boolean d){
        this.directDeposit = d;

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