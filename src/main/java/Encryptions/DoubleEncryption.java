package Encryptions;

import java.io.FileNotFoundException;
import java.util.Random;

import JavaEx.ENCFile;
import JavaEx.InvalidEncryptionKeyException;

public class DoubleEncryption implements
		EncryptionAlgorithm<DoubleEncryptionKey> {

	EncryptionAlgorithm<Integer> myAlgo;
	String keyPathOne;
	String keyPathTwo;

	public DoubleEncryption(EncryptionAlgorithm<Integer> encryptor) {
		myAlgo = encryptor;
	}

	public void encryptFile(String originalFilePath, String twoKeys,
			String OutPutPath) throws FileNotFoundException,
			InvalidEncryptionKeyException {

		DoubleEncryptionKey dKey = new DoubleEncryptionKey();
		int[] keys = ENCFile.read2Keys(twoKeys);
		dKey.setFirstKey(keys[0]);
		dKey.setSecondKey(keys[1]);
		keyPathOne = originalFilePath.substring(0,
				originalFilePath.lastIndexOf('\\'));
		keyPathOne += "\\FirstKey.txt";
		keyPathTwo = originalFilePath.substring(0,
				originalFilePath.lastIndexOf('\\'));
		keyPathTwo += "\\SecondKey.txt";
		ENCFile.writeKeyFile(keyPathOne, dKey.getFirstKey());
		ENCFile.writeKeyFile(keyPathTwo, dKey.getSecondKey());

		myAlgo.encryptFile(originalFilePath, keyPathOne, OutPutPath);

		myAlgo.encryptFile(OutPutPath, keyPathTwo, OutPutPath);

	}

	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException {
		int keys[] = ENCFile.read2Keys(keyPath);
		keyPathOne = keyPath.substring(0, keyPath.lastIndexOf('\\'));
		keyPathOne += "\\key1.txt";
		ENCFile.writeKeyFile(keyPathOne, keys[0]);

		keyPathTwo = keyPath.substring(0, keyPath.lastIndexOf('\\'));
		keyPathTwo += "\\key2.txt";
		ENCFile.writeKeyFile(keyPathTwo, keys[1]);
		myAlgo.decryptFile(encryptedFilePath, keyPathOne, OutPutPath);
		myAlgo.decryptFile(OutPutPath, keyPathTwo, OutPutPath);
	}

	public int getKeyStregth() {
		// TODO Auto-generated method stub
		return myAlgo.getKeyStregth();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Double"+myAlgo.toString();
	}

}
