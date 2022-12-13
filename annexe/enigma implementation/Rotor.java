package enigma;

/**
 * Rotor of an Enigma machine.
 * 
 * @author THIBAULT
 *
 */
public class Rotor {
	
	public Rotor(int notchPosition) {
		this.notchPosition = notchPosition;
	}

	/**
	 * The position of the notch.
	 */
	protected int notchPosition;

	/**
	 * The permutation. Could be for example A -> H, B -> Y, C -> A, etc.
	 */
	public int[] permutation = Enigma.permutation();

	/**
	 * Takes an integer and returns the crypted one.
	 * 
	 * @param n a letter
	 * @return another letter
	 */
	public int applyIn(int n) {
		return permutation[n];
	}

	/**
	 * Same as applyIn(), but in the other way.
	 * 
	 * @param n an integer coding a letter
	 * @return a crypted integer
	 */
	public int applyOut(int n) {
		for (int i = 0; i < Enigma.ALPHABET_CARDINAL; i++)
			if (permutation[i] == n)
				return i;
		throw new IllegalArgumentException("n = " + n); // Should never happen
	}

	/**
	 * Spins the rotor. That is to say, shifts all numbers by 1.
	 */
	protected boolean spin(RotorRole role, int pos) {
		switch (role) {

		// First rotor always spins
		case First:
			incrementNotchPosition();
			spin();
			return true;

		// Second spins every 26 Rotor1 spins
		case Second:
			if (pos % Enigma.ALPHABET_CARDINAL == 0) {
				incrementNotchPosition();
				spin();
				return true;
			} else
				return false;

			// Third one every 26 * 26 Rotor1 spins
		case Third:
			if (pos % (Enigma.ALPHABET_CARDINAL * Enigma.ALPHABET_CARDINAL) == 0) {
				incrementNotchPosition();
				spin();
				return true;
			} else
				return false;

		}
		return false;
	}

	private void spin() {
		for (int i = 0; i < Enigma.ALPHABET_CARDINAL; i++)
			permutation[i] = (permutation[i] + 1) % Enigma.ALPHABET_CARDINAL;
	}

	/**
	 * Increments by 1 the notch position.
	 */
	protected void incrementNotchPosition() {
		notchPosition = (notchPosition + 1) % Enigma.ALPHABET_CARDINAL;
	}

	/**
	 * Returns the notch position.
	 * 
	 * @return the notch position
	 */
	public int getNotchPosition() {
		return notchPosition;
	}

	/**
	 * Sets the notch position.
	 * 
	 * @param n the position to set the notch in.
	 */
	public void setNotchPosition(int n) {
		notchPosition = n % Enigma.ALPHABET_CARDINAL;
	}

}
