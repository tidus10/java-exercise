package Threading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Encryptions.EncryptionAlgorithm;
import JavaEx.ENCFile;
import JavaEx.FileEncryptor;
import JavaEx.InvalidEncryptionKeyException;

public class AsyncDirectoryProcessor<T> implements IDirectoryProcessor<T> {

	public void EncryptDirectory(final FileEncryptor<T> algo,
			final String Directory, final String Key)
			throws FileNotFoundException, InvalidEncryptionKeyException {
		File[] files = ENCFile.readFilesInDir(Directory);

		File newDir = new File(Directory + "\\encrypted");
		if (!(newDir.isDirectory() && newDir.exists())) {
			newDir.mkdir();
		}
		List<Thread> threads = new ArrayList<Thread>();
		for (final File file : files) {
			final String newPath = Directory
					+ "\\encrypted"
					+ file.getAbsolutePath().substring(
							file.getAbsolutePath().lastIndexOf('\\'));

			EncryptThread<T> enc = new EncryptThread<T>(algo,
					file.getAbsolutePath(), Key, newPath);
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						algo.encryptFile(file.getAbsolutePath(), Key, newPath);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (InvalidEncryptionKeyException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			threads.add(t);
		}

		for (Thread thread : threads) {
			while (thread.isAlive()) 
				Thread.yield();

		}
	}

	public void DecryptDirectory(final FileEncryptor<T> algo, String Directory,
			final String Key) throws FileNotFoundException,
			InvalidEncryptionKeyException {
		File[] files = ENCFile.readFilesInDir(Directory);

		File newDir = new File(Directory + "\\decrypted");
		if (!(newDir.isDirectory() && newDir.exists())) {
			newDir.mkdir();
		}
		List<Thread> threads = new ArrayList<Thread>();
		for (final File file : files) {
			final String newPath = Directory
					+ "\\decrypted"
					+ file.getAbsolutePath().substring(
							file.getAbsolutePath().lastIndexOf('\\'));

			EncryptThread<T> enc = new EncryptThread<T>(algo,
					file.getAbsolutePath(), Key, newPath);
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						algo.decryptFile(file.getAbsolutePath(), Key, newPath);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} 
				}
			});
			t.start();
			threads.add(t);
		}

		for (Thread thread : threads) {
			while (thread.isAlive()) 
				Thread.yield();

		}
	}

}
