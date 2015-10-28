package Encryptions;

import java.io.FileNotFoundException;

import JavaEx.InvalidEncryptionKeyException;

public interface EncryptionAlgorithm<T> {

	
	public int getKeyStregth();

	public void encryptFile(String originalFilePath, String key, String OutPutPath)
			throws FileNotFoundException, InvalidEncryptionKeyException;

	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException;
}
