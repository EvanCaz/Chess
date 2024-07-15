import java.util.Scanner;

public class Utils {
    public String moveValidation() {
        Scanner scnr = new Scanner(System.in);
        String pattern = "[A-H][1-8]\\s[A-H][1-8]";
        String move = ""; 
        boolean valid = false;
        
        System.out.println("please enter your move: ");
        while(!valid){
            move = scnr.nextLine().trim();
            if(move.matches(pattern)){
                valid = true;
            } else {
                System.out.println("Enter correct format as expained, case sensitive, one space inbetween: ");
            }
        }
        return move;
    }
 }
 