package enigma;

import java.util.Random;

/**
 * An Enigma machine.
 * 
 * @author THIBAULT
 *
 */
public class Machine {

	/**
	 * The initial Rotor1's notch position.
	 */
	private static int INITIAL_ROTOR1_POSITION = 0;

	/**
	 * The first rotor.
	 */
	private Rotor rotor1;

	/**
	 * The second rotor.
	 */
	private Rotor rotor2;

	/**
	 * The third rotor.
	 */
	private Rotor rotor3;

	/**
	 * The reflector.
	 */
	private Reflector reflector = new Reflector();

	/**
	 * The plugboard.
	 */
	private Plugboard plugboard = new Plugboard();

	public Machine(int rotor1_position, int rotor2_position, int rotor3_position) {
		rotor1 = new Rotor(rotor1_position);
		rotor2 = new Rotor(rotor2_position);
		rotor3 = new Rotor(rotor3_position);
	}

	public Machine() {
		this(Enigma.rd.nextInt() % Enigma.ALPHABET_CARDINAL, Enigma.rd.nextInt() % Enigma.ALPHABET_CARDINAL, Enigma.rd.nextInt() % Enigma.ALPHABET_CARDINAL);
	}

	/**
	 * Encrypts a message.
	 * 
	 * @param message the message to be encrypted
	 * @return an int array containing the crypted message
	 */
	public int[] encrpyt(String message) {
		var crypted_message = new int[message.length()];
		for (int i = 0; i < message.length(); i++) {
			crypted_message[i] = applyToLetter(message.charAt(i));
		}
		return crypted_message;
	}

	/**
	 * Simulates a key push to a letter.
	 * 
	 * @param c the character to encrypt
	 * @return the int corresponding to the crypted letter
	 */
	private int applyToLetter(char c) {
		pushPawl(INITIAL_ROTOR1_POSITION++);
		return encrpyt(Enigma.charToInt(c));
	}

	// Personal use
	static int rotor1_spins, rotor2_spins, rotor3_spins = 0;

	/**
	 * "Pushes the pawl", that is to say tries to spin the rotors.
	 */
	private void pushPawl(int count) {
		if (count == Integer.MAX_VALUE)
			count = 0;
		if (rotor1.spin(RotorRole.First, count))
			rotor1_spins++;
		if (rotor2.spin(RotorRole.Second, count))
			rotor2_spins++;
		if (rotor3.spin(RotorRole.Third, count))
			rotor3_spins++;
	}

	/**
	 * Decrypts a message.
	 * 
	 * @param crypted_message the crypted message
	 * @return the decrypted message
	 */
	public String decrpyt(int[] crypted_message) {
		var builder = new StringBuilder();
		for (int i = 0; i < crypted_message.length; i++) {

		}
		return builder.toString();
	}

	/**
	 * Encrypts an integer n. 1) swaps the letter with the plugboard 2) goes through
	 * all rotots and relfector 3) swaps the letter with the plugboard another time
	 * 
	 * @param n
	 * @return
	 */
	private int encrpyt(int n) {
		int n1 = plugboard.get(n); // First plugboard swap
		int n2 = applyRotors(n1); // Rotors do their work
		int n3 = plugboard.get(n2); // Second plugboard swap
		return n3;
	}
	
	private int decrypt(int n) {
		int n1 = plugboard.get(n); // First plugboard swap
		int n2 = applyRotorsOut(n1); // Rotors do their work
		int n3 = plugboard.get(n2); // Second plugboard swap
		return n3;
	}

	/**
	 * Applies all three rotors and the reflector to an integer. In total, an
	 * integer N will be encrypted 7 times.
	 * 
	 * @param n the integer directly coding a letter
	 * @return the encrypted integer
	 */
	private int applyRotors(int n) {
		int n1 = rotor1.applyIn(n); // First Rotor1 swap
		int n2 = rotor2.applyIn(n1); // First Rotor2 swap
		int n3 = rotor3.applyIn(n2); // First Rotor3 swap
		int n4 = reflector.applyIn(n3); // Reflector swap
		int n5 = rotor3.applyOut(n4); // Second Rotor1 swap
		int n6 = rotor2.applyOut(n5); // Second Rotor2 swap
		int n7 = rotor1.applyOut(n6); // Second Rotor3 swap
		return n7;
	}

	private int applyRotorsOut(int n) {
		int n1 = rotor1.applyIn(n); // First Rotor1 swap
		int n2 = rotor2.applyIn(n1); // First Rotor2 swap
		int n3 = rotor3.applyIn(n2); // First Rotor3 swap
		int n4 = reflector.applyIn(n3); // Reflector swap
		int n5 = rotor3.applyOut(n4); // Second Rotor1 swap
		int n6 = rotor2.applyOut(n5); // Second Rotor2 swap
		int n7 = rotor1.applyOut(n6); // Second Rotor3 swap
		return n7;
	}

	/**
	 * Converts an integer array to a String. That is to say converts every integer
	 * to its corresponding letter.
	 * 
	 * @param crypted_message the crypted message in int array format
	 * @return the crypted message in String format
	 */
	public String getCryptedMessage(int[] crypted_message) {
		var builder = new StringBuilder();
		for (int i = 0; i < crypted_message.length; i++)
			builder.append(Enigma.intToChar(crypted_message[i]));
		return builder.toString();
	}

	/**
	 * Swaps two letters from the plugboard.
	 * 
	 * @param a the first letter
	 * @param b the second letter
	 */
	public void swap(char a, char b) {
		plugboard.swap(Enigma.charToInt(a), Enigma.charToInt(b));
	}
}
