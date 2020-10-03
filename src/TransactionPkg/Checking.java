package TransactionPkg;

public class Checking extends Account {

    private boolean directDeposit;


    public Checking(Profile user, double amount, Date dateOpen, boolean d) {
        super(user, amount, dateOpen);
        this.directDeposit = d;

    }

    @Override
    public String toString() {
        String direct = "";
        if (directDeposit == true)
        {
            direct = "true";

        }

        // I want this toString to replicate what we did with the date, but without
        // creating an instance variable.

        return direct;
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