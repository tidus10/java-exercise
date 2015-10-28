package JavaEx;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

import Encryptions.DoubleEncryption;
import Encryptions.ShiftUpEncryption;
import Log.EncryptionLog4JLogger;
import Log.EncryptionLogger;

public class Program {

	public static void main(String[] args) {
		try {
		System.setProperty("file.encoding","UTF-8");
		Field charset = Charset.class.getDeclaredField("defaultCharset");
		charset.setAccessible(true);
		charset.set(null,null);
		
			run();
		} catch (Exception e) {
			EncryptionLog4JLogger.logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private static void run() throws Exception {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		ShiftUpEncryption crypt = new ShiftUpEncryption();

		DoubleEncryption DoubleEnc = new DoubleEncryption(crypt);
		FileEncryptor emcryptor = new FileEncryptor(DoubleEnc);
		EncryptionLogger logger=new EncryptionLogger();
		emcryptor.registerAll(logger);
		String path, keyPath;
		char c = 'e';
		do {
			// Runtime.getRuntime().exec("clear");
			System.out.println("Menu:");
			System.out.println("a. Encript");
			System.out.println("b. Decript");
			System.out.println("e. exit");
			c = input.next().charAt(0);

			switch (c) {
			case 'a'://
				System.out.println("Please enter file path to Encrypt");
				path = input.next();

				int num = rand.nextInt(128) + 1;
				String newPath = path.substring(0, path.lastIndexOf('.'))
						+ "_encrypted" + path.substring(path.lastIndexOf('.'));
				String keyPath2=path.substring(0, path.lastIndexOf('\\'))+"\\key.txt";
				ENCFile.writeKeyFile(keyPath2, num);
				emcryptor.encryptFile(path, keyPath2, newPath);
				System.out
						.println("The encryption file and key are in the folder:");
				System.out.println(path.substring(0, path.lastIndexOf('\\')));
				break;
			case 'b':
				System.out.println("Please enter file path to Decrypt");
				path = input.next();
				System.out
						.println("Please enter file path to the Decryption key");
				keyPath = input.next();
				newPath = path.substring(0, path.lastIndexOf('.'))
						+ "_decrypted" + path.substring(path.lastIndexOf('.'));
				emcryptor.decryptFile(path, keyPath, newPath);
				System.out.println("The decryption file is in the folder:");
				System.out.println(path.substring(0, path.lastIndexOf('\\')));
				break;
			case 'e':
				System.out.println("Bye Bye");
				break;

			default:
				break;
			}

		} while (c != 'e');
	}

}
