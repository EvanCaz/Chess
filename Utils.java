import java.util.Scanner;

public class Utils {
    public String moveValidation() {
        Scanner scnr = new Scanner(System.in);
        String pattern = "[A-H][1-8]\\s[A-H][1-8]";
        String move = ""; 
        boolean valid = false;
        
        System.out.print("Please enter your move: ");
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
    
    /**
     * Utility method to convert a user input in the form(A1 H2) to two index points.
     * 
     * @param move A string holding the move positions.
     * @return an array of size 4 with column and corresponding row in sucessiv
     */
    public static int[] stringToIndex(String moveInput) {
       String[] input = moveInput.split(" ");
       String startingPosition = input[0];
       String endingPosition = input[1];
       
       int fromColumn = startingPosition.charAt(0) - 'A';
       int toColumn = endingPosition.charAt(0) - 'A';
       int fromRow = 8 - Character.getNumericValue(startingPosition.charAt(1));
       int toRow = 8 - Character.getNumericValue(endingPosition.charAt(1));

       return new int[]{fromColumn, fromRow, toColumn, toRow};
    }
 }
 