package XML;

import Encryptions.DoubleEncryption;
import Encryptions.EncryptionAlgorithm;
import Encryptions.RepeatEncryption;
import Encryptions.ShiftMultiplyEncryption;
import Encryptions.ShiftUpEncryption;
import Encryptions.XorEncryption;

public class AlgorithmStringConverter {

	public EncryptionAlgorithm toAlgorithm(String desc) {
		if (desc.contains("Double")) {
			EncryptionAlgorithm e = null;
			if (desc.contains("ShiftUp"))
				e = new ShiftUpEncryption();

			if (desc.contains("ShiftMultiply"))
				e = new ShiftMultiplyEncryption();

			if (desc.contains("Xor"))
				e = new XorEncryption();

			DoubleEncryption d = new DoubleEncryption(e);

			return d;
		}

		if (desc.contains("Repeat")) {
			EncryptionAlgorithm e = null;
			if (desc.contains("ShiftUp"))
				e = new ShiftUpEncryption();

			if (desc.contains("ShiftMultiply"))
				e = new ShiftMultiplyEncryption();

			if (desc.contains("c"))
				e = new XorEncryption();
			String times = desc.replace("Repeat", "").replace("ShiftUp", "")
					.replace("ShiftMultiply", "").replace("Xor", "");
			RepeatEncryption r = new RepeatEncryption(e, Integer.valueOf(times));
			return r;
		}
		return null;
	}
}
