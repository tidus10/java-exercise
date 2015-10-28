package Encryptions;

import java.io.FileNotFoundException;

import JavaEx.ENCFile;
import JavaEx.InvalidEncryptionKeyException;

public class ShiftMultiplyEncryption implements EncryptionAlgorithm<Integer> {

	public static final int keyStrength = 1;

	public void encryptFile(String originalFilePath, String key, String OutPutPath)
			throws FileNotFoundException, InvalidEncryptionKeyException {
		Integer key2=ENCFile.readKeyFile(key);
		if (key2 < 0 || key2 > 9)
			throw new InvalidEncryptionKeyException();
		
		char[] data = ENCFile.getData(originalFilePath);
		for (int i = 0; i < data.length; i++) {
			data[i] *= key2;
		}
		String newPath = OutPutPath;
		ENCFile.writeToFile(newPath, data);

	}

	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException {
		int num = ENCFile.readKeyFile(keyPath);
		char[] data = ENCFile.getData(encryptedFilePath);
		for (int i = 0; i < data.length; i++) {
			data[i] /= num;
		}
		String newPath = OutPutPath;
		ENCFile.writeToFile(newPath, data);

	}

	public int getKeyStregth() {
		// TODO Auto-generated method stub
		return keyStrength;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ShiftMultiply";
	}
}
