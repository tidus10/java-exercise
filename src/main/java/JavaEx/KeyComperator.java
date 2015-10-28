package JavaEx;

import java.util.Comparator;

import Encryptions.EncryptionAlgorithm;

public class KeyComperator implements Comparator<EncryptionAlgorithm> {


	public int compare(EncryptionAlgorithm o1, EncryptionAlgorithm o2) {
		
		int k1= o1.getKeyStregth();
		int k2= o2.getKeyStregth();
		
		return k2-k1;
	}

}
