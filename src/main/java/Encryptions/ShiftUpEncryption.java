package Encryptions;

import java.io.FileNotFoundException;

import JavaEx.ENCFile;
import JavaEx.InvalidEncryptionKeyException;

public class ShiftUpEncryption implements EncryptionAlgorithm<Integer> {

	public static final int keyStrength = 3;

	/***
	 * this function gets normal file decrypte him and create file with the key
	 * 
	 * @param path
	 * @throws InvalidEncryptionKeyException
	 * @throws Exception
	 */
	public void encryptFile(String originalFilePath, String key, String OutPutPath)
			throws FileNotFoundException, InvalidEncryptionKeyException {

		Integer key2=ENCFile.readKeyFile(key);
		if (key2 < 0 || key2 > 256)
			throw new InvalidEncryptionKeyException();
		int num = key2;
		char[] data = ENCFile.getData(originalFilePath);

		for (int i = 0; i < data.length; i++) {
			data[i] += num;
		}
		String newPath = originalFilePath.substring(0,
				originalFilePath.lastIndexOf('.'))
				+ "_encrypted"
				+ originalFilePath.substring(originalFilePath.lastIndexOf('.'));
		ENCFile.writeToFile(OutPutPath, data);

		
	}

	/***
	 * This function get path to the Encryption file and the key file and
	 * Decrypt the file
	 * 
	 * @param path
	 * @param keyPath
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException {

		int num = ENCFile.readKeyFile(keyPath);
		char[] data = ENCFile.getData(encryptedFilePath);
		for (int i = 0; i < data.length; i++) {
			data[i] -= num;
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
		return "ShiftUp";
	}

}
