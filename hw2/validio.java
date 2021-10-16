import java.util.*;
public class validio {
    public int validMoney(){
        System.out.println("Please input money");
        
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b<=0){
                    System.out.println("Please input bet larger than 0.");
                    b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input valid bet. integer only.");
                b = scan.nextInt();
            }
            
            
            
        }

        return b;
    }

    public int validBet(Integer m){
        System.out.println("Please input bet");
        
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b<=0 || b>m){
                    System.out.println("Please input bet larger than 0 and less than "+m);
                    b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input valid bet. integer only.");
            }
            
            
            
        }

        return b;
    }

    public int selectGame(){

        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b != 0 && b != 1){
                    System.out.println("Please input 0,1 to select game.");
                    b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input integer only.");
                b = scan.nextInt();
            }
            
            
            
        }

        return b;
    }
    public int playerNumber(){
        System.out.println("Please input number of players");
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b <= 1){
                    System.out.println("Please input number > 0.");
                    b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input integer only.");
            }
            
        }
        // scan.close();
        return b; 
    }
    public String playername(){
        System.out.println("Please input playername");
        
        String a = "";
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                a = scan.nextLine();
                break;
            }
            catch(Exception e){
                System.out.println("Please input valid name.");
            }
        }

        return a;
    }
    public int operationType(){
        System.out.println("Please input operation type");
        
        int b;
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                b = scan.nextInt();
                if(b<1 || b >4 ){
                    System.out.println("please input valid operation type.");
                    b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input valid number.");

            }
        }

        return b;
    }

    public Integer validSplit(ArrayList<Integer> split){

        System.out.println("choose numbers to split: ");
        System.out.println(split.toString());
        Integer a;
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                a = scan.nextInt();
                if(split.contains(a)){
                    break;
                }else{
                    System.out.println("invalid input!");
                    a = scan.nextInt();
                }
            }catch(Exception e){
                System.out.println("please input valid number.");

            }
        }

        return a;
    }

    public static void main(String[] args) {
        validio io = new validio();
        int num = io.playerNumber();
        String name = io.playername();
        // System.out.println(name);
    }
}
