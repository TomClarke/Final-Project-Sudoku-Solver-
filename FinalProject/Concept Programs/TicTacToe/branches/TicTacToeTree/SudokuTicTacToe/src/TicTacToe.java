package src2;


import java.util.Scanner;



// Big Java Book Program example practise
public class TicTacToe {
	

	private static final int row = 3;
	private static final int col = 3;
	public Board board;
	public int player1;
	public int player2;
	public Board bestBoard;
	public Scanner in = new Scanner(System.in);
	
	public static void main(String [] args){
		System.out.println("Welcome");
		TicTacToe game = new TicTacToe();
	}
	
	public TicTacToe(){
		board = new Board();
		start();
	}
	
	public void start(){
		//setup 
		
		System.out.println("Starting TicTacToe game..");
		System.out.println("How many Players");
		int NoPlayers = in.nextInt();
		if (NoPlayers == 1){
			player1 = 1;
			player2 = 0;
			//player 1
			playAuto();
		}
		if (NoPlayers == 2){
			player1 = 1;
			player2 = 2;
			//player 2 real or not
			play(player1);
		}
		
		
		
		
	}
	
	public void play(int player){
		
		//TicTacToe game = new TicTacToe();
		 int otherPlayer = 0;
		System.out.println("please enter the row :");
		int row = in.nextInt();
		System.out.println("please enter the cols :");
		int col = in.nextInt();
		//enter 
		board.PrintBoard();
		//place X
		
		if(!board.SetPosition(row, col, player)){
			play(player);
		}
		else{
	    board.PrintBoard();
	
		int num = board.Check();
		System.out.println(num);
		if(num == 1){
			won(player);	
		}
		if(num == 2){
			won(player);
		}
		if(num == 0){
				System.out.println("turn 2 :");
				otherPlayer = turn(player);
		}
		if(num == 4){
			draw();
		}
		System.out.println("Next player"+ otherPlayer);
		play(otherPlayer); //"X";
		}
	}


	
	
	public void playAuto(){		
		int player = 2; //"O";
		//best option
	
		int[] move = new int [3]; 
	    move = board.findPosition(player);
	    board.SetPosition(move[0], move[1], move[2]);
	    board.PrintBoard();
	  
		//board.SetPosition(bestBoards, player);
		if(board.Check() == player){
			won(player2);
		}
		if(board.Check() == 0||board.Check()==1){
			System.out.println("turn 1 :");
			turn(2);
		}
		if(board.Check() == 4){
			draw();
		}
		
		
		//place O
	}
	public int turn(int player){
		
		if (player == 1){
			//player 2's go
			return 2;
		}
		else if (player == 2){
			return 1;
		}
		//whos turn is it
		//if X then play
		//if Y then playAuto
		return player;
		
	}
	
	public void won(int player){
		//search board 
		//who won
		System.out.println("Player "+ player + " won: ");
		System.out.println("Rematch? Y=1 Else press any key");
		int rematch =  in.nextInt();
		if(rematch == 1){
			main(null);
		}
		else{
			System.out.println("Thanks for playing");
		}
		//display win
	 
	}
	public void draw(){
	System.out.println("Draw");
	System.out.println("Rematch? Y=1 Else press any key");
	int rematch =  in.nextInt();
	if(rematch == 1){
		main(null);
	}
	else{
		System.out.println("Thanks for playing");
	}
	}
}
	

