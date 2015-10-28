package Encryptions;

import java.io.FileNotFoundException;

import JavaEx.InvalidEncryptionKeyException;

public class RepeatEncryption implements EncryptionAlgorithm<Integer> {

	EncryptionAlgorithm<Integer> myAlgo;
	int nTimes;

	public RepeatEncryption(EncryptionAlgorithm<Integer> crypto, int n) {
		myAlgo = crypto;
		nTimes = n;
	}

	public void encryptFile(String originalFilePath, String key, String OutPutPath) throws FileNotFoundException, InvalidEncryptionKeyException {
		for (int i = 0; i < nTimes; i++) {
			myAlgo.encryptFile(originalFilePath, key, OutPutPath);
		}
	}

	public void decryptFile(String encryptedFilePath, String keyPath, String OutPutPath) throws FileNotFoundException
			 {
		for (int i = 0; i < nTimes; i++) {
			myAlgo.decryptFile(encryptedFilePath, keyPath, OutPutPath);
		}
	}

	public int getKeyStregth() {
		// TODO Auto-generated method stub
		return myAlgo.getKeyStregth();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Repeat"+myAlgo.toString()+nTimes;
	}
}
