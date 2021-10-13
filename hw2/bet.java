public class bet {
    private int amount;

    bet(){
        setBet(300);
    }

    public int getBet(){
        return amount;
    }
    public void setBet(int n){
        amount = n;
    }
    // reduceBet can reduce to 0. no negtive.
    // the check reduce amount should be in other functions.

    public void reduceBet(int re){
        amount = amount - re;
    }


}
