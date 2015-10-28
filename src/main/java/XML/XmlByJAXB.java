package XML;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlByJAXB implements XMLTool {

	public ProcessSettings getProcesssSettings(String fileName)
			throws Exception {
		JAXBContext jc = JAXBContext.newInstance(ProcessSettings.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File(fileName);
		ProcessSettings settings = (ProcessSettings) unmarshaller
				.unmarshal(xml);
		return settings;
	}

}
