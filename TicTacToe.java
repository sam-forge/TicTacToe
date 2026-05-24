import java.util.*;

class GameBoard{
    Scanner sc;
    ArrayList<Integer> playerPositions = new ArrayList<>();
    ArrayList<Integer> cpuPositions = new ArrayList<>();
    char[][] gameboard;
    GameBoard(){
        sc = new Scanner(System.in);
        gameboard = new char[][]{
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}
        };
    }

    public void printGameboard(){
        for (char[] row : gameboard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public void placePiece(int pos, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }
    
        switch(pos){
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break; 
            }
        }

    public String checkWinner(){
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List<Integer> l : winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations, you win!";
            } else if(cpuPositions.containsAll(l)){
                return "CPU wins,Better luck next time!";
            }
        }
        if(playerPositions.size() + cpuPositions.size() == 9){
            return "It's a draw!";
        }
        return "";
    }
}

public class TicTacToe {

    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        gb.printGameboard();

        while(true){
            System.out.println("Enter your placement (1-9): ");
            int playerPos = gb.sc.nextInt();
            
            if(playerPos < 1 || playerPos > 9){
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                continue;
            }

            while(gb.playerPositions.contains(playerPos) || gb.cpuPositions.contains(playerPos)){
                System.out.println("Position taken! Enter a correct position");
                playerPos = gb.sc.nextInt();
            }
            gb.placePiece(playerPos, "player");
            gb.printGameboard();
            System.out.println();

            String winner = gb.checkWinner();
            if(!winner.isEmpty()){
                System.out.println(winner);
                break;
            }
            
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(gb.playerPositions.contains(cpuPos) || gb.cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            gb.placePiece(cpuPos, "cpu");
            gb.printGameboard();
            System.out.println();
            
            winner = gb.checkWinner();
            if(!winner.isEmpty()){
                System.out.println(winner);
                break;
            }
        }
    }
}