public class RatRoute {
    char[][] myBoard;
    int myCheeseRow;
    int myCheeseCol;
    public int cheeseDistance(int r, int c){
        return Math.abs(r - myCheeseRow) + Math.abs(c - myCheeseCol);
    }
    public int moveCount(int r, int c){
        if (r < 0 || c < 0 ||
        c >= myBoard[0].length ||
        r >= myBoard.length){
            return 0;
        }

        if(myBoard[r][c] == 'X') return 0;
        if(r == myCheeseRow && c == myCheeseCol) return 1;
        int distanceNow = cheeseDistance(r, c);
        int[] deltaRow = {-1, 0, 0, 1};
        int[] deltaCol = {0, -1, 1, 0};
        int total = 0; //why does total not mess things up?

        for(int k = 0; k < deltaRow.length; k++){
            int nr = r + deltaRow[k];
            int nc = c + deltaCol[k];
            if(cheeseDistance(nr, nc) < distanceNow){
                int next = moveCount(nr, nc);
                total += next;
                //next is gonna be total from the next recursive call, so picture a global total with an infinite number of branching
                //'tree' totals. Each of these will be fed backwards into the previous total...there will be four 'leaf' totals per tree total
                //all either one or zero on the ground floor, and then any number of numbers after that. Each is fed upwards and backwards
                //again, until they are all summed into the largest total and returned. In this way it behaves the like a global variable
                // It's the Library of fucking Babel
            }
        }
        return total;
    }
    public int numRoutes(String[] enc) {
        int ratRow = 0;
        int ratCol = 0;
        myBoard = new char[enc.length][enc[0].length()];
        for(int r = 0; r < myBoard.length; r++){
            for(int c = 0; c < myBoard[0].length; c++){
                myBoard[r][c] = enc[r].charAt(c);
                if(myBoard[r][c] == 'R') {
                    ratRow = r;
                    ratCol = c;
                }
                else if (myBoard[r][c] ==  'C'){
                    myCheeseRow = r;
                    myCheeseCol = c;
                }
            }
        }
        return moveCount(ratRow, ratCol);
    }
    public static void main(String[] args){
        String[] board = {"C........R"};
        //if you put C........R in there the debugger won't run
        RatRoute c = new RatRoute();
        System.out.println(c.numRoutes(board));
    }
}