package x10dt.ui.tests;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Assert;

import org.eclipse.core.runtime.FileLocator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SmoketestXMLLoader {

	/*
	 * 
	 * XML support
	 * 
	 * Load and access simple XML configuration files
	 * 
	 */
	  private File fXmlFile; 
	  
	  private String xmlFileName;

	  private DocumentBuilderFactory docFactory;
	  private DocumentBuilder docBuilder;
	  private Document doc = null;
	
	  public SmoketestXMLLoader(String xmlFileName) {
	  
		  this.xmlFileName = xmlFileName;

		  System.out.println("loading config file " + xmlFileName);

		  try {
			  ClassLoader cl = X10DTTestBase.class.getClassLoader();		//archive file must be on the build path
			  URL xmlFileURL = cl.getResource(xmlFileName);	//find the file 
			  fXmlFile = new File(FileLocator.toFileURL(xmlFileURL).getFile());

			  docFactory = DocumentBuilderFactory.newInstance();
			  docBuilder = docFactory.newDocumentBuilder();
			  doc = docBuilder.parse(fXmlFile);
			  doc.getDocumentElement().normalize();
		  }
		  catch (Exception e) {
			  Assert.fail("exception in loadXML " + e.getMessage());		
		  }
	  }

	  public Document getXmlDocument() { return doc; }
	  public File getXmlFile() { return fXmlFile; }
	  public String getXmlFileName() { return xmlFileName; }

	  public NodeList getElementsByTagName(String tag) {
		  return doc.getElementsByTagName(tag);
	  }

	  
	  //retrieve first string value from element by tag
	  public  String getTagString(String tag, Element element) {
		return getTagString(tag, element, 0);
	}
	  //retrieve nth string value from element by tag
	  public  String getTagString(String tag, Element element, int listItem) {
		  NodeList nList= element.getElementsByTagName(tag).item(listItem).getChildNodes();
		  Node nValue = (Node) nList.item(0);
		  if (nValue != null)
			  return nValue.getNodeValue().trim();
		  else
			  return "";
	  }

	  //retrieve first integer value from element by tag
	  public  Integer getTagInteger(String tag, Element element) {
		  return getTagInteger(tag, element, 0);
	  }

	  //retrieve nth integer value from element by tag
	  public  Integer getTagInteger(String tag, Element element, int listItem) {
		  String tagString = getTagString(tag, element, listItem);
		  return tagString.equals("") ? 0 : Integer.valueOf(tagString);
	  }

	  //retrieve first boolean value from element by tag
	  public  Boolean getTagBoolean(String tag, Element element) {
		  return getTagBoolean(tag, element, 0);
	  }

	  //retrieve nth boolean value from element by tag
	  public  Boolean getTagBoolean(String tag, Element element, int listItem) {
		  return (getTagString(tag, element, listItem).equals("yes") ? true : false);
	  }

	  //Sometimes you might want to use a quoted string as a value 
	  // so that leading or trailing whitespace can be preserved.
	  // This strips off the quotes.  
	  //retrieve first quoted string value from element by tag
	  public  String getTagQuotedString(String tag, Element element) {
		  return getTagQuotedString(tag, element, 0);
	  }

	  //Sometimes you might want to use a quoted string as a value 
	  // so that leading or trailing whitespace can be preserved.
	  // This strips off the quotes.  
	  //retrieve nth quoted string value from element by tag
	  public  String getTagQuotedString(String tag, Element element, int listItem) {
		  NodeList nList= element.getElementsByTagName(tag).item(listItem).getChildNodes();
		  Node nValue = (Node) nList.item(0);
		  if (nValue != null) {
			  String theString = nValue.getNodeValue().trim();
			  if (theString.startsWith("\"") && theString.endsWith("\"")) {
				  return theString.substring(theString.indexOf('"')+1, theString.lastIndexOf('"'));
			  }
			  else
				  return "";
		  }
		  else
			  return "";
	  }

}
