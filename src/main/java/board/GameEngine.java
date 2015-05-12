package board;
/** Interface now used by our moveServer. This will make
 *  plugging in the AI easy, as all it has to do is implement
 *  this interface!
 * 
 * @author paventcj196
 *
 */
public interface GameEngine {
	
	public void setTurn(); //Called to make it so the current Engine knows 
				//that it's turn has come up 
	public String getMove();
	public void makeMove(String s,int n);//Called to let to Engine know to make a move on its own board
	public void bootPlayer(int n);//Will be used to boot player n from the current game
	public void declareWinner(int n);//LETS the client know the game is over
}
