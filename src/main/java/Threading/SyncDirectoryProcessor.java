package Threading;

import java.io.File;
import java.io.FileNotFoundException;

import Encryptions.EncryptionAlgorithm;
import JavaEx.ENCFile;
import JavaEx.FileEncryptor;
import JavaEx.InvalidEncryptionKeyException;

public class SyncDirectoryProcessor<T> implements IDirectoryProcessor<T> {

	public void EncryptDirectory(FileEncryptor<T> algo, String Directory,
			String Key) throws FileNotFoundException, InvalidEncryptionKeyException {
		File[] files = ENCFile.readFilesInDir(Directory);

		File newDir = new File(Directory + "\\encrypted");
		if (!(newDir.isDirectory() && newDir.exists())) {
			newDir.mkdir();
		}

		for (File file : files) {
			String newPath = Directory
					+ "\\encrypted\\"
					+ file.getAbsolutePath().substring(
							file.getAbsolutePath().lastIndexOf('\\'));
			algo.encryptFile(file.getAbsolutePath(), Key, newPath);
		}

	}
	
	public void DecryptDirectory(FileEncryptor<T> algo, String Directory,
			String Key) throws FileNotFoundException, InvalidEncryptionKeyException {
		File[] files = ENCFile.readFilesInDir(Directory);

		File newDir = new File(Directory + "\\decrypted");
		if (!(newDir.isDirectory() && newDir.exists())) {
			newDir.mkdir();
		}

		for (File file : files) {
			String newPath = Directory
					+ "\\decrypted\\"
					+ file.getAbsolutePath().substring(
							file.getAbsolutePath().lastIndexOf('\\'));
			algo.decryptFile(file.getAbsolutePath(), Key, newPath);
		}

	}

}
