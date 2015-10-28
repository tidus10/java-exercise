package XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlElement;

import Encryptions.EncryptionAlgorithm;

@XmlRootElement(name="ProcessSettings")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessSettings {

	String Algorithm;
	String KeyPath;
	String SourceDirectory;
	String SourceFileName;

	public String getAlgorithm() {
		return Algorithm;
	}

	public void setAlgorithm(String algorithm) {
		Algorithm = algorithm;
	}

	@XmlElement
	public String getKeyPath() {
		return KeyPath;
	}

	public void setKeyPath(String keyPath) {
		KeyPath = keyPath;
	}

	@XmlElement
	public String getSourceDirectory() {
		return SourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		SourceDirectory = sourceDirectory;
	}

	@XmlElement
	public String getSourceFileName() {
		return SourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		SourceFileName = sourceFileName;
	}

}
