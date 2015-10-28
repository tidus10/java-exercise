package JavaEx;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ENCFile {
	/***
	 * writing data to specific file
	 * 
	 * @param path
	 * @param data
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public synchronized static void writeToFile(String path, char[] data)
			throws FileNotFoundException {
		PrintWriter write = new PrintWriter(path);
		write.print(data);
		write.close();
		/*
		 * FileOutputStream file = new FileOutputStream(path); file.write(data);
		 * file.close();
		 */
	}

	public static void writeToFile(String path, byte[] data) throws IOException {
		FileOutputStream file = new FileOutputStream(path);
		file.write(data);
		file.close();
	}

	/***
	 * read key from key file
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public synchronized static int readKeyFile(String path) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader(path));
		int num = in.nextInt();
		in.close();
		return num;
	}

	/***
	 * write key to file
	 * 
	 * @param path
	 * @param key
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public static void writeKeyFile(String path, int key)
			throws FileNotFoundException {

		PrintWriter write = new PrintWriter(path);
		write.print(key);
		write.close();
	}

	/***
	 * get data from specific file
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public synchronized static char[] getData(String path) throws FileNotFoundException {
		String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
		return content.toCharArray();

	}

	public static void write2KeysFile(String keyPathOne, int num, int key)
			throws FileNotFoundException {
		PrintWriter write = new PrintWriter(keyPathOne);
		write.println(key);
		write.println(num);
		write.close();
	}

	public static int[] read2Keys(String keyPathOne)
			throws FileNotFoundException {
		int x[] = new int[2];
		Scanner in = new Scanner(new FileReader(keyPathOne));
		int num = in.nextInt();
		int key = in.nextInt();
		in.close();
		x[0] = key;
		x[1] = num;
		return x;
	}

	public static File[] readFilesInDir(String path) {
		File folder = new File(path);
		FileFilter filter = new FileFilter() {

			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.isFile()
						&& pathname.getAbsolutePath().contains(".txt");
			}
		};

		return folder.listFiles(filter);

	}

	public static void createFilesOfFolder(String folder) throws IOException {
		File dir = new File(folder);

		if (dir.exists())
			dir.delete();
		dir.mkdir();

		List<String> hellos = new ArrayList<String>(Collections.nCopies(78645*6,
				"Hello"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		for (String element : hellos) {
			out.writeUTF(element);
		}
		byte[] bytes = baos.toByteArray();

		for (int i = 0; i < 3; i++) {
			RandomAccessFile f = new RandomAccessFile(folder + "\\File" + i
					+ ".txt", "rw");
			// f.setLength(1024 * 1024 * 3);
			f.write(bytes);
			f.close();
		}

	}
}
