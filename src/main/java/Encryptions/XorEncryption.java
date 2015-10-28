package Encryptions;

import java.io.FileNotFoundException;

import JavaEx.ENCFile;
import JavaEx.InvalidEncryptionKeyException;

public class XorEncryption implements EncryptionAlgorithm<Integer> {

	public static final int keyStrength = 2;

	public void encryptFile(String originalFilePath, String key,
			String OutPutPath) throws InvalidEncryptionKeyException,
			FileNotFoundException {
		Integer key2 = ENCFile.readKeyFile(key);
		if (key2 < 0 || key2 > 99)
			throw new InvalidEncryptionKeyException();
		int num = key2;
		char[] data = ENCFile.getData(originalFilePath);
		for (int i = 0; i < data.length; i++) {
			int xor = ((int) data[i]) ^ num;
			data[i] = (char) (0xff & xor);
		}
		String newPath = originalFilePath.substring(0,
				originalFilePath.lastIndexOf('.'))
				+ "_encrypted"
				+ originalFilePath.substring(originalFilePath.lastIndexOf('.'));
		ENCFile.writeToFile(newPath, data);


	}

	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException {
		int num = ENCFile.readKeyFile(keyPath);
		char[] data = ENCFile.getData(encryptedFilePath);
		for (int i = 0; i < data.length; i++) {
			int xor = ((int) data[i]) ^ num;
			data[i] = (char) (0xff & xor);
		}
		String newPath = encryptedFilePath.substring(0,
				encryptedFilePath.lastIndexOf('.'))
				+ "_decrypted"
				+ encryptedFilePath.substring(encryptedFilePath
						.lastIndexOf('.'));
		ENCFile.writeToFile(newPath, data);

	}

	public int getKeyStregth() {
		// TODO Auto-generated method stub
		return keyStrength;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Xor";
	}
}
