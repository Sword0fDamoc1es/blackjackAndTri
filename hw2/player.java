public abstract class player{
    public abstract void out();
    public abstract String toString();
    public abstract void receiveCard(Card c, Integer i);
    public abstract Integer getMoney();
    public abstract String getName();
    public abstract void clearState();
    public abstract void makeBet(Integer b);
    public abstract void reward(Integer n);
}