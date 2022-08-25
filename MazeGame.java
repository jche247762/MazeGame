import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MazeGame {
	static int goldNumber=0;
	static int remainingLives;
	static int boardCols;
	static int boardRows;
	static int StepNumber;
	static int currentPositionX;
	static int currentPositionY;
	static int destinationOfX;
	static int destinationOfY;
	static File fileName;
	static char[][] boardGame;
     /**
     * Initialises the game from the given configuration file.
     * This includes the number of lives, the number of steps, the starting gold
     * and the board.
     *
     * If the configuration file name is "DEFAULT", load the default
     * game configuration.
     */
    public static void initialiseGame(String configFileName) throws IOException {
        // TODO: Implement this method.
		ArrayList<String> lines = new ArrayList<>();
		File f;
		if (configFileName.equals("DEFAULT")){
			f = new File("DEFAULT.txt");
		}else {
			f=new File(configFileName);
		}
		Scanner file = new Scanner(f);
		String a = file.nextLine();

		remainingLives=Integer.parseInt(a.split(" ")[0]);
		StepNumber=Integer.parseInt(a.split(" ")[1]);
		goldNumber=Integer.parseInt(a.split(" ")[2]);
		boardRows=Integer.parseInt(a.split(" ")[3]);
		while(file.hasNextLine()){

			lines.add(file.nextLine());
		}
		boardCols = lines.get(0).length();//initialise
		boardGame = new char[boardRows][boardCols];


		//System.out.println(boardRows);
		for(int i=0; i<boardRows;i++){
			for(int j=0;j<boardCols;j++){
				//System.out.println("a");
				boardGame[i][j]=lines.get(i).charAt(j);

			}
		}
		for(int i = 0;i < boardRows; i++){
			for(int j = 0; j < boardCols; j++){
				if(String.valueOf(boardGame[i][j]).equals("&")){
					currentPositionX=j;
					currentPositionY=i;
				}
				if(boardGame[i][j]=='@'){
					destinationOfX=j;
					destinationOfY=i;
				}
			}
		}
		//System.out.println(lines);
		//System.out.println(boardCols);
		//System.out.println(Arrays.toString(boardGame[0]));
		//System.out.println(Arrays.deepToString(boardGame));
		//System.out.println(currentPositionX);
		//System.out.println(currentPositionY);
		//System.out.println(remainingLives);

	}

    /**
     * Save the current board to the given file name.
     * Note: save it in the same format as you read it in.
     * That is:
     *
     * <number of lives> <number of steps> <amount of gold> <number of rows on the board>
     * <BOARD>
     */
    public static void saveGame(String toFileName) throws IOException {
        // TODO: Implement this method.
		//System.out.printf("%d,%d,%d,%d",remainingLives,StepNumber,goldNumber,boardRows);
		File f = new File(toFileName);
		PrintWriter p = new PrintWriter(f);
		p.println(remainingLives + " " + StepNumber + " " + goldNumber + " " + boardRows);
		for(int i = 0; i < boardRows;i++){
			for(int j = 0; j< boardCols;j++){
				p.print(boardGame[i][j]);
			}
			p.println();
		}
		p.close();
		System.out.println("Successfully saved the current game configuration to "+toFileName+ " .");
    }

    /**
     * Gets the current x position of the player.
     *
     */
    public static int getCurrentXPosition() {
        // TODO: Implement this method.
        return currentPositionX;
    }

    /**
     * Gets the current y position of the player.
     */
    public static int getCurrentYPosition() {
        // TODO: Implement this method.
        return currentPositionY;
    }

    /**
     * Gets the number of lives the player currently has.
     */
    public static int numberOfLives() {
        // TODO: Implement this method.
        return remainingLives;
    }

    /**
     * Gets the number of remaining steps that the player can use.
     */
    public static int numberOfStepsRemaining() {
        // TODO: Implement this method.
        return StepNumber;
    }

    /**
     * Gets the amount of gold that the player has collected so far.
     */
    public static int amountOfGold() {
        // TODO: Implement this method.
        return goldNumber;
    }


    /**
     * Checks to see if the player has completed the maze.
     * The player has completed the maze if they have reached the destination.
     */
    public static boolean isMazeCompleted() {
        boolean iscompleted=false;
		if(currentPositionX==destinationOfX&&currentPositionY==destinationOfY){
			iscompleted=true;
		}else{
			iscompleted=false;
		}
		return iscompleted;
    }

    /**
     * Checks to see if it is the end of the game.
     * It is the end of the game if one of the following conditions is true:
     *  - There are no remaining steps.
     *  - The player has no lives.
     *  - The player has completed the maze.
     */
    public static boolean isGameEnd() {
        // TODO: Implement this method
		if(isMazeCompleted()==true||StepNumber<=0||remainingLives<=0){
			return true;
		}else{
			return false;
		}
	}

    /**
     * Checks if the coordinates (x, y) are valid.
     * That is, if they are on the board.
     */
    public static boolean isValidCoordinates(int x, int y) {
        // TODO: Implement this method.
		if(y<0||x<0||y>(boardRows-1)||x>(boardCols-1)){
			return false;
		}else{
			return true;
		}
	}
    /**
     * Checks if a move to the given coordinates is valid.
     * A move is invalid if:
     *  - It is move to a coordinate off the board.
     *  - There is a wall at that coordinate.
     *  - The game is ended.
     */
    public static boolean canMoveTo(int x, int y) {
        // TODO: Implement this method.
        //boolean canMoveTo=false;
		if(isValidCoordinates(x,y)){
			if(isGameEnd()||boardGame[y][x]=='#'){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
    /**
     * Move the player to the given coordinates on the board.
     * After a successful move, it prints "Moved to (x, y)."
     * where (x, y) were the coordinates given.
     *
     * If there was gold at the position the player moved to,
     * the gold should be collected and the message "Plus n gold."
     * should also be printed, where n is the amount of gold collected.
     *
     * If it is an invalid move, a life is lost.
     * The method prints: "Invalid move. One life lost."
     */
	public static void moveTo(int x, int y) {
        // TODO: Implement this method.
		if(canMoveTo(x,y)==true){
			System.out.println("Moved to ("+x+", "+y+").");

			if(Isint(boardGame[y][x])){
				//String s = String.valueOf(boardGame[y][x]);
				int a = Integer.parseInt(String.valueOf(boardGame[y][x]));
//				System.out.println(boardGame[y][x]);
				goldNumber=goldNumber+a;
				System.out.println("Plus "+a+" gold.");
			}
			boardGame[currentPositionY][currentPositionX]='.';
			currentPositionX=x;
			currentPositionY=y;
			boardGame[y][x]='&';

		}else{

			remainingLives=remainingLives-1;
			System.out.println("Invalid move. One life lost.");
		}
		StepNumber=StepNumber-1;
	}


	public static boolean Isint(char a){
		try{
			int b = Integer.parseInt(String.valueOf(a));
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

    /**
     * Prints out the help message.
     */
    public static void printHelp() {
        // TODO: Implement this method.
		System.out.println("Usage: You can type one of the following commands.");
		System.out.println("help         Print this help message.");
		System.out.println("board        Print the current board.");
		System.out.println("status       Print the current status.");
		System.out.println("left         Move the player 1 square to the left.");
		System.out.println("right        Move the player 1 square to the right.");
		System.out.println("up           Move the player 1 square up.");
		System.out.println("down         Move the player 1 square down.");
		System.out.println("save <file>  Save the current game configuration to the given file.");
    }

    /**
     * Prints out the status message.
     */
    public static void printStatus() {
        // TODO: Implement this method.
		System.out.println("Number of live(s): "+remainingLives);
		System.out.println("Number of step(s) remaining: "+StepNumber);
		System.out.println("Amount of gold: "+goldNumber);
    }

    /**
     * Prints out the board.
     */
    public static void printBoard() {
        // TODO: Implement this method.
		for (int i = 0 ; i < boardRows ; i++){
			for (int j = 0 ; j < boardCols ; j++){
				System.out.print(boardGame[i][j]);
		}
		System.out.println();
	}
	}

    /**
     * Performs the given action by calling the appropriate helper methods.
     * [For example, calling the printHelp() method if the action is "help".]
     *
     * The valid actions are "help", "board", "status", "left", "right",
     * "up", "down", and "save".
     */
    public static void performAction(String move) throws IllegalArgumentException {
        // TODO: Implement this method.
		if (move.equals("")) return;
		if(move.equalsIgnoreCase("help")){
			printHelp();
		}else if(move.equalsIgnoreCase("board")){
			printBoard();
		}else if(move.equalsIgnoreCase("status")){
			printStatus();
		}else if(move.equalsIgnoreCase("up")){
			moveTo(getCurrentXPosition(),getCurrentYPosition()-1);
		}else if(move.equalsIgnoreCase("right")){
			moveTo(getCurrentXPosition()+1,getCurrentYPosition());
		}else if(move.equalsIgnoreCase("down")){
			moveTo(getCurrentXPosition(),getCurrentYPosition()+1);
		}else if(move.equalsIgnoreCase("left")){
			moveTo(getCurrentXPosition()-1,getCurrentYPosition());
		}else if(move.split(" ")[0].equalsIgnoreCase("save")){
			if (move.split(" ").length != 2) throw new IllegalArgumentException();
			try{
				saveGame(move.split(" ")[1]);
			}catch(IOException e){
				System.out.println("Error: Could not save the current game configuration to '" + move.split(" ")[1] + "'.");
			}
		}else throw new IllegalArgumentException();

}
    /**
     * The main method of your program.
     */
    public static void main(String[] args) {
        // Run your program (reading in from args etc) from here.
		if(args.length<1){
			System.out.println("Error: Too few arguments given. Expected 1 argument, found 0.");
			System.out.println("Usage: MazeGame [<game configuration file>|DEFAULT]");
			return;
		}else if(args.length>1){
			System.out.println("Error: Too many arguments given. Expected 1 argument, found "+args.length+".");
			System.out.println("Usage: MazeGame [<game configuration file>|DEFAULT]");
			return;
		}else{
			try{
				initialiseGame(args[0]);
			}catch(IOException e){
				System.out.println("Error: Could not load the game configuration from '"+args[0]+"'.");
//				e.printStackTrace();
				return;
			}
			Scanner keyboard=new Scanner(System.in);
			while(keyboard.hasNextLine()){
				//System.out.println(currentPositionX);
				//System.out.println(currentPositionY);
				String str=keyboard.nextLine();
				try {
					performAction(str);
				}catch (IllegalArgumentException e) {
					System.out.println("Error: Could not find command '"+str+"'.");
					System.out.println("To find the list of valid commands, please type 'help'.");
					continue;
				}
				if(isGameEnd()){
				if (isMazeCompleted()){
					System.out.println("Congratulations! You completed the maze!");
					System.out.println("Your final status is:");
					printStatus();
				}else if(remainingLives<=0&&StepNumber<=0){
					System.out.println("Oh no! You have no lives and no steps left.");
					System.out.println("Better luck next time!");
				}else if(remainingLives<=0){
					System.out.println("Oh no! You have no lives left.");
					System.out.println("Better luck next time!");
				}else if(StepNumber<=0){
					System.out.println("Oh no! You have no steps left.");
					System.out.println("Better luck next time!");
				}
				}
			}
			if(!isGameEnd()){
				System.out.println("You did not complete the game.");
			}
    }
	}
	}
