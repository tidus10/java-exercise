package Threading;

import java.io.FileNotFoundException;

import Encryptions.EncryptionAlgorithm;
import JavaEx.InvalidEncryptionKeyException;

public class EncryptThread<T> implements Runnable {

	EncryptionAlgorithm<T> algo;
	String Key;
	String newPath;
	String originalPath;

	public EncryptThread(EncryptionAlgorithm<T> algo, String originalPath,
			String key, String newPath) {
		super();
		this.algo = algo;
		this.originalPath = originalPath;
		Key = key;
		this.newPath = newPath;

	}

	public void run() {
		try {
			algo.encryptFile(originalPath, Key, newPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidEncryptionKeyException e) {
			e.printStackTrace();
		}

	}

}
