import java.util.*;

public class Game {
    
    public void run() {
        System.out.println();
        String prompt = "Please select your choice:\n1. BlackJack;\n2. TriantaEna;\n3. Quit.";
        validio io = new validio();
        int op = io.selectGame(prompt);
        while (op!=3){
            if (op==1){ 
                runBJ();
            }
            else if (op==2){ 
                runTri();
            }
            op = io.selectGame(prompt);
        }
    }
    public void runBJ() {
        BJGame BJ = new BJGame();
        BJ.run();
    }

    public void runTri() {
        TriGame newGame = new TriGame();
        newGame.run();
    }
    
    public void intro() {
        System.out.println("Welcom to Games designed by Mao & Yizheng!!!");
        System.out.println("Now we provide two beg games: BlackJack Game and TriantaEna Game");
        System.out.println("In these two games, you will make bet and try your best to earn money!!!");
        System.out.println("Enjoy your games!");
    }
    public static void main(String[] args){
        Game new_game = new Game();
        new_game.run();
    }
    
}