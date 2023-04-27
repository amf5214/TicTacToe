/*
 * The University of North Carolina at Charlotte
 * ITCS 3153 - Intro to Artificial Intelligence
 * 
 * Programming Assignment 2 - Adversarial Search
 * 
 * Based on code from Dilip Kumar Subramanian
 * 
 * Modified by Julio C. Bahamon
 */

import java.util.ArrayList;


public class Minimax
{
	private static String AI_AGENT = "X";
	private static String HUMAN_PLAYER = "O";
	
	/*
	 * We assume that MAX moves first
	 * 
	 * This method returns the move that results in the best utility.
	 * 
	 * @param GameState
	 * 			The current state/node from which the move is being made.
	 * 
	 */
	public static GameState miniMax(GameState currentState)
	{
		//	Keep track of the number of states generated/explored
		GameAI.setTotalCount(GameAI.getTotalCount() + 1);
		
		GameState bestMove = null;
		
		//	TODO: Implement the miniMax (or min-max) algorithm, as discussed in class

		//	Generate the set of moves that are available from the current state
		ArrayList<GameState> successors = GameState.generateSuccessors(currentState, AI_AGENT);

		// Debug code to view successor moves
		Log.debug("Available moves:");

		int totalChildren = 0;

		for (GameState child : successors)
		{
			GameAI.showBoardState(child.getBoardState());
			totalChildren++;
		}

		Log.debug("There are " + totalChildren + " available moves (successor states):");
		
		// Debug code to show best move that has been selected
		if (bestMove != null)
		{
			Log.debug("Selected move: ");
			GameAI.showBoardState(bestMove.getBoardState());
		}


		return bestMove;
	}
	
	
	/*
	 * Checks the given gameState and returns the utility.
	 * Utility is calculated as follows:
	 * 		- If MAX wins, the utility is +1
	 * 		- If MIN wins, the utility is -1
	 * 		- If game is tied, the utility is 0
	 * 
	 * Makes the assumption that we are at a terminal (or leaf) node.
	 * This method should only be called on a node in which the game has ended.
	 * 
	 * @param GameState
	 * 			The terminal state/node that is being evaluated.
	 */
	public static int calculateUtility(GameState gameState)
	{
		//	First, check for a winner
		if (GameState.isWinState(gameState.getBoardState()))
		{
			if (GameState.checkWinner(gameState.getBoardState(), AI_AGENT))
			{
//				//	Debug code. Enable/disable as needed
//				Log.debug("Leaf node - MAX wins:");
//				GameAI.showBoardState(gameState.getBoardState());
				
				//	MAX wins
				return 1;
			}
			else
			{
//				//	Debug code. Enable/disable as needed
//				Log.debug("Leaf node - MIN wins:");
//				GameAI.showBoardState(gameState.getBoardState());
				
				// MIN wins
				return -1;
			}
		}
		else
		{
			//	Assuming that the board is full, this is a tie.
			return 0;
		}
	}
}