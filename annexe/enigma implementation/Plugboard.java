package enigma;

/**
 * Plugboard of an Enigma machine.
 * Swaps letters on the board for better encrypting.
 * 
 * @author THIBAULT
 *
 */
public class Plugboard {
	
	/**
	 * The integers coding the alphabet.
	 */
	private static int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
	
	/**
	 * Swaps two letters from the boad.
	 * 
	 * @param i the first position
	 * @param j the second position
	 */
	public void swap(int i, int j) {
		int tmp = board[i];
		board[i] = board[j];
		board[j] = tmp;
	}
	
	/**
	 * Returns the letter coded by the corresponding integer.
	 * If a letter hasn't been swaped with another, then it will return the same one.
	 * 
	 * @param i the integer
	 * @return the new integer
	 */
	public int get(int i) {
		return board[i];
	}
	
}
