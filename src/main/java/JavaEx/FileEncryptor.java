package JavaEx;

import java.io.FileNotFoundException;
import java.util.Observer;

import Encryptions.EncryptionAlgorithm;

import EventArgs.EncryptionLogEventArgs;
import Observables.*;

public class FileEncryptor<T> implements EncryptionAlgorithm<T> {

	EncryptionAlgorithm<T> myAlgo;
	EncryptionStartObserveble EnStartEvent;
	EncryptionEndObserveble EnEndEvent;
	DecryptionStartObserveble DeStartEvent;
	DecryptionEndObserveble DeEndEvent;

	public void registerAll(Observer o) {
		EnStartEvent.addObserver(o);
		EnEndEvent.addObserver(o);
		DeStartEvent.addObserver(o);
		DeEndEvent.addObserver(o);
	}

	public void registerEnStartEvent(Observer o) {
		EnStartEvent.addObserver(o);
	}

	public void registerEnEndEvent(Observer o) {
		EnEndEvent.addObserver(o);
	}

	public void registerDeStartEvent(Observer o) {
		DeStartEvent.addObserver(o);
	}

	public void registerDeEndEvent(Observer o) {
		DeEndEvent.addObserver(o);
	}

	public EncryptionAlgorithm getMyAlgo() {
		return myAlgo;
	}

	public void setMyAlgo(EncryptionAlgorithm myAlgo) {
		this.myAlgo = myAlgo;
	}

	public FileEncryptor(EncryptionAlgorithm encryptor) {
		myAlgo = encryptor;
		EnStartEvent = new EncryptionStartObserveble();
		EnEndEvent = new EncryptionEndObserveble();
		DeStartEvent = new DecryptionStartObserveble();
		DeEndEvent = new DecryptionEndObserveble();
	}

	public void encryptFile(String originalFilePath, String key, String OutPutPath)
			throws FileNotFoundException, InvalidEncryptionKeyException {
		long millis = System.currentTimeMillis() % 1000;
		EncryptionStarted(new EncryptionLogEventArgs(originalFilePath,
				OutPutPath, myAlgo, millis));
		myAlgo.encryptFile(originalFilePath, key, OutPutPath);
		millis = System.currentTimeMillis() % 1000;
		EncryptionEnded(new EncryptionLogEventArgs(originalFilePath,
				OutPutPath, myAlgo, millis));
	}

	public void decryptFile(String encryptedFilePath, String keyPath,
			String OutPutPath) throws FileNotFoundException {
		long millis = System.currentTimeMillis() % 1000;
		DecryptionStarted(new EncryptionLogEventArgs(encryptedFilePath,
				keyPath, myAlgo, millis));
		myAlgo.decryptFile(encryptedFilePath, keyPath, OutPutPath);
		millis = System.currentTimeMillis() % 1000;
		DecryptionEnded(new EncryptionLogEventArgs(encryptedFilePath, keyPath,
				myAlgo, millis));
	}

	public int getKeyStregth() {
		return myAlgo.getKeyStregth();
	}

	public void EncryptionStarted(EncryptionLogEventArgs e) {
		
		EnStartEvent.notifyObservers(e);
	}

	public void EncryptionEnded(EncryptionLogEventArgs e) {
		EnEndEvent.notifyObservers(e);
	}

	public void DecryptionStarted(EncryptionLogEventArgs e) {
		DeStartEvent.notifyObservers(e);
	}

	public void DecryptionEnded(EncryptionLogEventArgs e) {
		DeEndEvent.notifyObservers(e);
	}

}
