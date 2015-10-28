package Threading;

import java.io.FileNotFoundException;

import Encryptions.EncryptionAlgorithm;
import JavaEx.FileEncryptor;
import JavaEx.InvalidEncryptionKeyException;

public interface IDirectoryProcessor<T> {

	public void EncryptDirectory(FileEncryptor<T> algo, String Directory,
			String Key) throws FileNotFoundException, InvalidEncryptionKeyException;
	public void DecryptDirectory(FileEncryptor<T> algo, String Directory,
			String Key) throws FileNotFoundException, InvalidEncryptionKeyException;
}
