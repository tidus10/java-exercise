package ex2;

import org.junit.Assert;
import org.junit.Test;

import XML.*;

public class XMLTest {

	@Test
	public void testXMLbyDOM() {
		try {
			XMLTool domParser = new XmlByDOM();
			ProcessSettings settings = domParser
					.getProcesssSettings("XMLConfigure.xml");
			Assert.assertEquals(settings.getAlgorithm(), "DoubleShiftUp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testXMLbySAX() {
		ProcessSettings settings=null;
		try {
			XMLTool saxParser = new XmlBySAX();
			settings = saxParser
					.getProcesssSettings("XMLConfigure.xml");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(settings.getAlgorithm(), "DoubleShiftUp");

	}
	
	@Test
	public void testXMLbyJAXB() {
		ProcessSettings settings=null;
		try {
			XMLTool JAXBParser = new XmlByJAXB();
			settings = JAXBParser
					.getProcesssSettings("XMLConfigure.xml");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(settings.getAlgorithm(), "DoubleShiftUp");

	}

}
