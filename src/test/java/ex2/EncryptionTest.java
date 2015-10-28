package ex2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import Encryptions.DoubleEncryption;
import Encryptions.EncryptionAlgorithm;
import Encryptions.RepeatEncryption;
import Encryptions.ShiftMultiplyEncryption;
import Encryptions.ShiftUpEncryption;
import Encryptions.XorEncryption;
import JavaEx.ENCFile;
import JavaEx.FileEncryptor;
import JavaEx.InvalidEncryptionKeyException;
import Log.EncryptionLogger;
import Threading.AsyncDirectoryProcessor;
import Threading.SyncDirectoryProcessor;


public class EncryptionTest {
	char[] data;
	String path = "D:\\CryptoTest.txt";
	String output = "D:\\CryptoTest_encrypted.txt";
	String keyPath = "D:\\key.txt";
	String outPutDecryption = "D:\\CryptoTest_decrypted.txt";
	EncryptionAlgorithm crypt = new ShiftUpEncryption();
	DoubleEncryption DoubleEnc = new DoubleEncryption(crypt);
	FileEncryptor encryptor = new FileEncryptor(DoubleEnc);
	int key = new Random().nextInt(177) + 1;

	public EncryptionTest() {
		try {

			data = new String("nana banna tahat shel ilana".getBytes(), "UTF-8")
					.toCharArray();
			ENCFile.writeKeyFile(keyPath, key);

			ENCFile.writeToFile(path, data);
			System.setProperty("file.encoding", "UTF-8");
			Field charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null, null);
		} catch (Exception ex) {

		}

	}

	private void testingAlgorithm(char[] data, String path, String output,
			String keyPath, String outPutDecryption,
			EncryptionAlgorithm crypt, FileEncryptor encryptor)
			throws FileNotFoundException, InvalidEncryptionKeyException {

		char[] data2;
		encryptor.setMyAlgo(crypt);
		

		encryptor.encryptFile(path, keyPath, output);

		encryptor.decryptFile(output, keyPath, outPutDecryption);

		data2 = ENCFile.getData(outPutDecryption);

		Assert.assertArrayEquals(data, data2);
	}

	
	
	@Test
	public void TestShiftUp() {
		// testing shiftUpEncryption
		try {
			ENCFile.writeKeyFile(keyPath, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					 crypt, encryptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestDouble() {
		// testing Double encryption
		try {
			ENCFile.write2KeysFile(keyPath, key+1, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					 DoubleEnc, encryptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestRepeat() {
		// testing RepeatEncryption
		crypt = new RepeatEncryption(crypt, 3);

		try {
			ENCFile.writeKeyFile(keyPath, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					crypt, encryptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestShiftMultiply() {
		// testing shiftMultiplyEncryption
		crypt = new ShiftMultiplyEncryption();

		try {
			key = 2;
			ENCFile.writeKeyFile(keyPath, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					 crypt, encryptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestShiftXor() {
		// testing XorEncryption crypt = new XorEncryption();

		try {
			key=12;
			crypt = new XorEncryption();
			ENCFile.writeKeyFile(keyPath, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					 crypt, encryptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = InvalidEncryptionKeyException.class)
	public void TestCompare() throws InvalidEncryptionKeyException {
		int key = 5668;

		try {
			ENCFile.writeKeyFile(keyPath, key);
			testingAlgorithm(data, path, output, keyPath, outPutDecryption,
					 crypt, encryptor);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void TestData(char[] d1, char[] d2) {

		Assert.assertArrayEquals(d1, d2);

	}

	@Test
	public void testObservers()
	{
		TestShiftUp();
	}

	
	public void testSyncDirectoryProcessor()
	{
		try {
			ENCFile.createFilesOfFolder("D:\\filesToencrypt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SyncDirectoryProcessor<Integer> enc=new SyncDirectoryProcessor<Integer>();
		FileEncryptor<Integer> fEnc=new FileEncryptor<Integer>(new ShiftUpEncryption());
		try {
			enc.EncryptDirectory(fEnc, "D:\\filesToencrypt", keyPath);
			enc.DecryptDirectory(fEnc, "D:\\filesToencrypt\\encrypted", keyPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidEncryptionKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void testAsyncDirectoryProcessor()
	{
		try {
			ENCFile.createFilesOfFolder("D:\\filesToencryptAsyn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AsyncDirectoryProcessor<Integer> enc=new AsyncDirectoryProcessor<Integer>();
		FileEncryptor<Integer> fEnc=new FileEncryptor<Integer>(new ShiftUpEncryption());
		
		try {
			enc.EncryptDirectory(fEnc, "D:\\filesToencryptAsyn", keyPath);
			enc.DecryptDirectory(fEnc, "D:\\filesToencryptAsyn\\encrypted", keyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidEncryptionKeyException e) {
			e.printStackTrace();
		}
		
	}
	
}
