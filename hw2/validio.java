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
                    // b = scan.nextInt();
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

    public int validMoney(String prompt){
        System.out.println("Please input money for "+prompt);
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b<=0){
                    System.out.println("Please input bet larger than 0.");
                    // b = scan.nextInt();
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

    public int validMoney(int m){// changed a  input bug.
        System.out.println("Please input money,");
        System.out.println("banker needs at least : "+ 3*m+" money.");
        
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b<3*m){
                    System.out.println("Please input bet at least "+3*m);
                    // b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input valid bet. integer only.");
                // b = scan.nextInt();
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
                    // b = scan.nextInt();
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

    public int validBet(Integer m, String prompt){
        System.out.println("Please input bet for "+ prompt);
        
        int b;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                b = scan.nextInt();
                if(b<=0 || b>m){
                    System.out.println("Please input bet larger than 0 and less than "+m);
                    // b = scan.nextInt();
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
                    // b = scan.nextInt();
                    continue;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please input integer only.");
                // b = scan.nextInt();
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
                    // b = scan.nextInt();
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

    public String playername(String prompt){
        System.out.println("Please input name for "+prompt+":");
        
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
                    // b = scan.nextInt();
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

    public int operationType(int double_up, int split){
        System.out.println("Please input operation type:");
        System.out.println("1. Hit;");
        System.out.println("2. Stand;");
        // if ((double_up==1) & (split==1)){
        //     if (double_up==1){System.out.println("3. Double up;");}
        //     if (split==1){System.out.println("4. Split.");}
        // }
        if (double_up==1){System.out.println("3. Double up;");}
        if (split==1){System.out.println("4. Split;");}
        int b;
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                b = scan.nextInt();
                if(b<1 || b >4 ){
                    System.out.println("please input valid operation type.");
                    // b = scan.nextInt();
                    continue;
                }
                else if(b==3 && double_up==0){
                    System.out.println("please input valid operation type.");
                    // b = scan.nextInt();
                    continue;
                }
                else if(b==4 && split==0){
                    System.out.println("please input valid operation type.");
                    // b = scan.nextInt();
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

        System.out.println("Please choose numbers from "+split+" to split: ");
        Integer a;
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                a = scan.nextInt();
                if(split.contains(a)){
                    break;
                }else{
                    System.out.println("invalid input!");
                    // a = scan.nextInt();
                }
            }catch(Exception e){
                System.out.println("please input valid number.");

            }
        }

        return a;
    }
    public String validyn(){
        System.out.println("please choose yes 'y'  or no  'n'. ");
        String a;
        while(true){
            Scanner scan = new Scanner(System.in);
            try{
                a = scan.nextLine();
                if(!a.equals("n") && !a.equals("y")){
                    System.out.println("invalid input!");
                }else{
                    break;
                }
            }catch(Exception e){
                System.out.println("please input valid number.");
            }
        }
        return a;
    }

    public static void main(String[] args) {
        validio io = new validio();
        int n = io.validBet(15);
        System.out.println(n);
        // int num = io.playerNumber();
        // String name = io.playername();
        // System.out.println(name);

    }
}
