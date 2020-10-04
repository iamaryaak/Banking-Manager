package TransactionPkg;

public class Savings extends Account {

    private boolean isLoyal;

    public Savings (Profile user, double amount, Date dateOpen, boolean i){
        super(user, amount, dateOpen);
        this.isLoyal = i;
    }

    @Override
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

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }

}