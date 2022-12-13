package enigma;

import java.util.Arrays;
import java.util.Random;

/**
 * Util class for the Enigma machine.
 * 
 * @author THIBAULT
 *
 */
public class Enigma {
	
	protected static int ALPHABET_CARDINAL = 26;
	
	public static Random rd = new Random();
	
//	protected static Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
	protected static Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public static int[] permutation() {
		int[] t = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
		for (int i = 0; i < ALPHABET_CARDINAL; i++) {
			for (int j = i; j < ALPHABET_CARDINAL; j++) {
				int rand = rd.nextInt(ALPHABET_CARDINAL);
				int tmp = t[j];
				t[j] = t[rand];
				t[rand] = tmp;
			}
		}
		return t;
	}
	
	public static int charToInt(char c) {
		return c - 65;
	}
	
	public static char intToChar(int n) {
		return alphabet[n];
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(permutation()));
	}
	
}
