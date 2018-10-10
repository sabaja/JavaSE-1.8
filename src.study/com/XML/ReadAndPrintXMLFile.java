package com.XML;

import java.io.StringReader;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadAndPrintXMLFile {

	public static void main(String argv[]) {
		try {

			String xml = 
					"<?xml version=\"1.0\"?>"+
							"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:rol=\"http://www.visa.com/ROLSI\">"+ 
								"<soapenv:Header/>"+
								"<soapenv:Body>" +
									"<rol:SIUploadImageRequest>"+ 
										"<rol:RequestHeader>" +
											"<rol:User id=\"rtsca9227c\" type=\"loginId\"/>"+ 
											"<!--Optional:-->"+
											"<rol:MemberRole>I</rol:MemberRole>"+ 
										"</rol:RequestHeader>"+
										"<rol:RequestData>"+ 
											"<!--You have a CHOICE of the next 2 items at this level-->"+ 
											"<rol:VisaCaseNumber>1048857041</rol:VisaCaseNumber>"+ 
											"<rol:AttachmentDescriptor>"+ 
												"<!--Optional:-->"+  
												"<rol:Attachment id=\"1\">"+
													"<!--Optional:--><rol:ContentType>image/jpeg</rol:ContentType>"+ 
													"<!--Optional:--><rol:Comment>test da rtsi</rol:Comment>"+ 
													"<!--Optional:--><rol:ImageData>cid:1234567</rol:ImageData>"+ 
												"</rol:Attachment>"+  
											"</rol:AttachmentDescriptor>"+ 
											"<rol:DocType>CALET</rol:DocType>"+
										"</rol:RequestData>"+  
									"</rol:SIUploadImageRequest>"+  
								"</soapenv:Body>"+ 
							"</soapenv:Envelope>";

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(xml)));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList node = doc.getElementsByTagName("rol:ImageData");
			System.out.println("Node name: " + node.item(0).getNodeName());
			Node n = node.item(0);
			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
		        Element elem = (Element) n;
		        System.out.println("Element value first: " + elem.getTextContent());
		        elem.setTextContent("SUCA");
		        System.out.println("Element value after: " + elem.getTextContent());
		    }
			
		    
			/*
			for (int s = 0; s < node.getLength(); s++) {

				Node firstPersonNode = node.item(s);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstPersonNode;

					// -------
					NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
					Element firstNameElement = (Element) firstNameList.item(0);

					NodeList textFNList = firstNameElement.getChildNodes();
					System.out.println("First Name : " + ((Node) textFNList.item(0)).getNodeValue().trim());

					// -------
					NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
					Element lastNameElement = (Element) lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					System.out.println("Last Name : " + ((Node) textLNList.item(0)).getNodeValue().trim());

					// ----
					NodeList ageList = firstPersonElement.getElementsByTagName("age");
					Element ageElement = (Element) ageList.item(0);

					NodeList textAgeList = ageElement.getChildNodes();
					System.out.println("Age : " + ((Node) textAgeList.item(0)).getNodeValue().trim());

					// ------

				} // end of if clause

			} // end of for loop with s var*/

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		// System.exit (0);

	}// end of main

}