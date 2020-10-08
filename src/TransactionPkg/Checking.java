package TransactionPkg;

public class Checking extends Account {

    private boolean directDeposit;


    public Checking(Profile user, double balance, Date dateOpen, boolean d) {
        super(user, balance, dateOpen);
        this.directDeposit = d;

    }

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
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Checking && super.equals(a))
            instance = true;

        return instance;
    }

    @Override
    public String toString(){
        return "*Checking*" +  super.toString() + isDirectDeposit();
    }

    @Override
    public double monthlyInterest() {
        return 0.0042;
    }

    @Override
    public double monthlyFee() {
        return 25.00;
    }



}