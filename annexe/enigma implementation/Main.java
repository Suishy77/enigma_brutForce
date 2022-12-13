package enigma;

public class Main {

	public static void main(String[] args) {

		Machine m = new Machine();
		
//		m.swap('K', 'G');
		
		System.out.println(m.getCryptedMessage(m.encrpyt("A".repeat(32))));
	}

}
