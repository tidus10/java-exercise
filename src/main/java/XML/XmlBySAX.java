package XML;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlBySAX implements XMLTool{
	
	public static ProcessSettings settings=new ProcessSettings();
	
	

	public static ProcessSettings getSettings() {
		return settings;
	}



	public static void setSettings(ProcessSettings settings) {
		XmlBySAX.settings = settings;
	}



	public ProcessSettings getProcesssSettings(String fileName)
			throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		DefaultHandler handler = new MySAXHandler();
		saxParser.parse(fileName, handler);
		return settings;
	}

}
