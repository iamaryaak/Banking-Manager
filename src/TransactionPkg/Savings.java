package TransactionPkg;

public class Savings extends Account {

    private boolean isLoyal;

    public Savings (Profile user, double amount, Date dateOpen, boolean i){
        super(user, amount, dateOpen);
        this.isLoyal = i;
    }

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
    public boolean equals(Account a) {
        boolean instance = false;
        if (a instanceof Savings && super.equals(a))
            instance = true;

        return instance;
    }

    @Override
    public String toString()
    {
        return "*Savings*" + super.toString() + isLoyal();
    }

    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return (double)(0.0035/12.00);
        }else{
            return (double)(0.0025/12.00);
        }
    }

    @Override
    public double monthlyFee() {
        return 5.00;
    }



}