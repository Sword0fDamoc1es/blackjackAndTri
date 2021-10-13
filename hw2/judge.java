import java.util.*;
public class judge {
    ArrayList<player> playerPool = new ArrayList<>();
    ArrayList<dealer> dealerPool = new ArrayList<>();

    judge(){
        playerPool = new ArrayList<player>();
        dealerPool = new ArrayList<dealer>();

    }

    public void addDealer(){
        dealerPool.add(new dealer());

    }
    public void addPlayer(){
        playerPool.add(new player());
    }
    public boolean checkPlayerScore(player p){
        boolean flag = false;
        for (Integer s : p.score){
            if(s <= 21){
                flag = true;
            }
        }
        return flag;
    }

    // return three int value.
    // 0 stands for not hit 17 yet. Thus, continue hit.
    // 1 stands for over 17 but not bust. stop hit.
    // 2 stands for bust. stop hit and dealer looses.
    public int checkDealerScore(dealer d){
        //dealer only has one score.
        if(d.score.get(0)<17){
            return 0;
        }
        if(d.score.get(0)>=17){
            if(d.score.get(0)<=21){
                return 1;
            }
            else{
                return 2;
            }
        }
        return 2;
    }


    public void startGame(){

    }
    // if check win turns to endGame
    // checkWin is from interface.
    public void endGame(){

    }
}
