
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TicTacToe {
	//main
	public static void main(String[] args)  {
		Scanner input = null;
		try {
			input = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char board[] = null;
		
		//read from txt to char[]
		while(input.hasNextLine()){
			String temp = "";
			System.out.println("Board:");
			//game board consists of 3 lines in the .txt this has to be merged to a char[]
			for(int i=0;i<3;i++){
				String line = input.nextLine();
				//prints the line to console for visualizing the board
				System.out.println(line);
				//merge the three lines to single string;
				temp=temp.concat(line);
			}
			
			//skip empty row if there is
			if(input.hasNextLine()){
				input.nextLine();
			}
			
			//create char[] from the string (right format for the function)
			board = temp.toCharArray();
		
			//prints out the solutions
			System.out.println("Result: "+solution(board)+"\n");
		}
	}

	private static int solution(char board[]){
		//return 3 (unfinished as default)
		int result = 3;
		
		//number of O and X
		int nX = 0;
		int nO = 0;
		
		//default false but if noticed a char otherwise than X, O or whitespace then invalid board
		boolean unknownChar = false;
		
		//magic square eg. see http://mathworld.wolfram.com/MagicSquare.html
		// 8,1,6
		// 3,5,7
		// 4,9,2
		// All columns rows and diagonals have the sum of 15
		int magicSquare[] = {8,1,6,3,5,7,4,9,2};
		
		//initialize for both O and X
		int magicSquareX[] = new int[9];
		int magicSquareO[] = new int[9];
		
		//count instances of X and O
		//assign values for their magic squares zero if not X or respectively O there
		for(int i=0;i<board.length;i++){
			if(board[i] == 'X'){
				nX++;
				magicSquareX[i] = magicSquare[i];
				magicSquareO[i] = 0;
			}
			else if(board[i] == 'O'){
				nO++;
				magicSquareO[i] = magicSquare[i];
				magicSquareX[i] = 0;
			}
			//if character is something else than X O or whitespace then we have a unknow char => invalid board
			else if(board[i]!=' ' && board[i]!='\0'){
				unknownChar = true;
			}
		}
		//invalid board start with this since otherwise unnecessary calculations
		//1. board should always be size 9
		//2. Since X always starts there should not be more O than X
		//3. If there is 2 or more X than O then the board is also invalid since X at some point has put twice in a row
		//4. Unknown character (other than O X or whitespace)
		if(board.length!=9 || nO>nX || nX-nO>1 || unknownChar){
			result = 4;
		}
		else if(nX>2){ //don't bother checking for solution if nX<3 because then there cannot be a solution and game is unfinished
          
		  //calculate if either X or O has a winning row/column/diagonal
		  boolean xWins = calculateMagicSquare(magicSquareX);
		  boolean oWins = calculateMagicSquare(magicSquareO);
		  
		  //something fishy has happened since both can't have a winning combination
		  //i.e. the game hasn't been played by the rules
		  if(xWins && oWins){
			  result = 4;
		  }
		  //X has a winning combination  O not X wins
		  else if(xWins && !oWins){
			  result = 1;
		  }
		  //O has a winning combination  X not O wins
		  else if(oWins && !xWins){
			  result = 0;
		  }
		  //tie if no one has a winning combination and there are 9 pieces on the board if less pieces then unfinished
		  else if((!xWins&&!oWins) && nX+nO==9){
	          result = 2;
	      }
		}
		return result;
	}
	
	//see if any row, column or diagonal has the sum of 15
	private static boolean calculateMagicSquare(int[] magicSquare) {
		boolean solution = false;
		//check rows
		solution = checkRows(magicSquare);
		//check columns
		if(!solution){
			solution = checkColumns(magicSquare);

		}
		//check diagonals if no winning combination yet

		if(!solution){
			solution = checkDiagonals(magicSquare);
		}
		//false if no winning combination true otherwise
		return solution;
	}

	private static boolean checkDiagonals(int[] magicSquare) {
		return ((magicSquare[0]+magicSquare[4]+magicSquare[8]==15) || (magicSquare[2]+magicSquare[4]+magicSquare[6]==15));
	}

	private static boolean checkColumns(int[] magicSquare) {
		boolean solution = false;
		int sum = 0;
		for(int col=0;col<3;col++){
			if(!solution){
				for(int row=col; row<=6+col;row+=3){
					sum+=magicSquare[row];
				}
				//if the sum of a column is 15 then there are 3 in a column
				if(sum==15){
					solution = true;
				}
				sum = 0;
			}
		}
		return solution;
	}

	private static boolean checkRows(int[] magicSquare) {
		boolean solution = false;
		int sum = 0; 
		for(int row=0;row<3;row++){
			if(!solution){
				for(int col=0+row*3; col<3+row*3;col++){
					sum+=magicSquare[col];
				}
				//if the sum of a row is 15 then there are 3 in a row
				if(sum==15){
					solution = true;
				}
				sum = 0;
			}
		}
		return solution;
	}
}
