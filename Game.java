class Game {
    // attributes include Board, Both Players, and CurTurn
    public Board gameBoard; // game board
    public Player playerW;
    public Player playerB; 
    public int curTurn;
    public Utils funcs;
    
    /* no player classes yet, so to avoid it is commented out
     * 1 for w 0 for b, cuz w starts first, maybe should be static accross all Game objects idk
    */
    // constructor/desctructor
    public Game() {
        playerW = new Player();
        playerB = new Player();
        playerW.color = 1;
        playerB.color = 0;
        curTurn = 1;
        gameBoard = new Board();
        funcs = new Utils();

    }

    // Methods include start, end, play, and check
    public void start() {
        /* The start method will probably just say sum shit like welcome to chess and 
         * will probably also loop as games can be played back to back, so once play ends,
         * ends says do u wanna play again and calls start
         * 
        */
        System.out.println("Player one will begin as White, and player Two will be black.");
        System.out.println("The pieces will be labeled as follows: Pawn's are wp for white and bp for black, Kings are wK for white and bK for black, and so forth for the rest of the pieces.");
        System.out.println("Moves will be entered as 'E4 E7', where to first location is source location and second is destination.");
        System.out.println();
        play();
    }

    public boolean end() {
        // ends the game, is probably called when a game ending condition is met
        return false;
    }

    public void play() {
        /* loop for game, i thinks steps will be as in order
         * Display the board
         * prompts correct player for input for turn, white starts first
         * checks if input is valid, but not if a vaid move
         * starts over, probably maintained while the method end returns true
        */ 
        String move;
        boolean end = end(); // this and the line below can be combined
        while(end == false){ 
            // the loop
            gameBoard.printChessBoard();
            if(curTurn == 1){
                System.out.print("Player one ");
                move = funcs.moveValidation();
                playerW.makeMove(move, gameBoard);
                curTurn = 0;
                // gets the move and passes it to Player obejct method makeMove()
            } else if (curTurn == 0){
                System.out.print("Player Two ");
                move = funcs.moveValidation();
                // playerB.makeMove(move, gameBoard);
                curTurn = 1;
                // gets the move and passes it to Player obejct method makeMove()
            }
            end = end(); // does game countine?
        }
    
    }
}