import java.util.*;
public class validio {
    public int validBet(){
        Scanner scan = new Scanner(System.in);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b<=0){
                    System.out.println("Please input bet larger than 0.");
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
        scan.close();
        return b;
    }

    public int selectGame(){
    
        Scanner scan = new Scanner(System.in);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b != 0 && b != 1){
                    System.out.println("Please input 0,1 to select game.");
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
        scan.close();
        return b;
    }
    public int playerNumber(){
       
        Scanner scan = new Scanner(System.in);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b <= 1){
                    System.out.println("Please input number > 0.");
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
        scan.close();
        return b; 
    }
    public String playername(){
        Scanner scan = new Scanner(System.in);
        String a = "";
        while(true){
            try{
                a = scan.nextLine();
                break;
            }
            catch(Exception e){
                System.out.println("Please input valid name.");
            }
        }
        scan.close();
        return a;
    }
    public int operationType(){
        Scanner scan = new Scanner(System.in);
        int b;
        while(true){
            try{
                b = scan.nextInt();
                if(b<1 || b >4 ){
                    System.out.println("please input valid operation type.");
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
        scan.close();
        return b;
    }

    public Integer validSplit(ArrayList<Integer> split){
        Scanner scan = new Scanner(System.in);
        System.out.println("choose numbers to split: ");
        System.out.println(split.toString());
        Integer a;
        while(true){
            try{
                a = scan.nextInt();
                if(split.contains(a)){
                    break;
                }else{
                    System.out.println("invalid input!");
                }
            }catch(Exception e){
                System.out.println("please input valid number.");
            }
        }
        scan.close();
        return a;
    }
    public String validyn(){
        Scanner scan = new Scanner(System.in);
        System.out.println("please choose yes 'y'  or no  'n'. ");
        String a;
        while(true){
            try{
                a = scan.nextLine();
                if(a.equals("n") || a.equals("y")){
                    System.out.println("invalid input!");
                }else{
                    break;
                }
            }catch(Exception e){
                System.out.println("please input valid number.");
            }
        }
        scan.close();
        return a;
    }

}
