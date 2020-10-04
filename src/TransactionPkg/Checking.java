package TransactionPkg;

public class Checking extends Account {

    private boolean directDeposit;


    public Checking(Profile user, double amount, Date dateOpen, boolean d) {
        super(user, amount, dateOpen);
        this.directDeposit = d;

    }

    @Override
    public boolean isDirectDeposit(boolean d) {
        if (directDeposit == true)
        {
            System.out.println("Is direct-deposit");
            directDeposit = true;
        }
        else
            directDeposit = false;

        return directDeposit;
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