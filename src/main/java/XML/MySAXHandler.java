package XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXHandler extends DefaultHandler {

	boolean isAlg = false;
	boolean isKeyPath = false;
	boolean isSourceDirectory = false;
	boolean isSourceFileName = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("Algorithm"))
			isAlg = true;
		if (qName.equalsIgnoreCase("KeyPath"))
			isKeyPath = true;
		if (qName.equalsIgnoreCase("SourceDirectory"))
			isSourceDirectory = true;
		if (qName.equalsIgnoreCase("SourceFileName"))
			isSourceFileName = true;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isAlg) {
			AlgorithmStringConverter c = new AlgorithmStringConverter();

			XmlBySAX.settings.setAlgorithm(new String(ch, start,
					length));
			isAlg = false;
		}
		if (isKeyPath) {
			XmlBySAX.settings.setKeyPath(new String(ch, start, length));
			isKeyPath = false;
		}
		if (isSourceDirectory) {
			XmlBySAX.settings.setSourceDirectory(new String(ch, start, length));
			isSourceDirectory = false;
		}
		if (isSourceFileName) {
			XmlBySAX.settings.setSourceFileName(new String(ch, start, length));
			isSourceFileName = false;
		}
	}

}
