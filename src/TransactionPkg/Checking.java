package TransactionPkg;

public class Checking extends Account {

    private boolean directDeposit;


    public Checking(Profile user, double amount, Date dateOpen, boolean d) {
        super(user, amount, dateOpen);
        this.directDeposit = d;

    }

    @Override
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

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}