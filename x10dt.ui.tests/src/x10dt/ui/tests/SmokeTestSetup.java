package x10dt.ui.tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import x10dt.ui.tests.X10DTTestBase.Architecture;
import x10dt.ui.tests.X10DTTestBase.CommInterface;
import x10dt.ui.tests.X10DTTestBase.OS;
import x10dt.ui.tests.X10DTTestBase.PlatformConfig;

public class SmokeTestSetup  extends X10DTTestBase {
	
	/*
	 * This class aggregates all Smoke Test configuration settings
	 */
	public String className;							//imported class name
	public String classSourceFileName;					//className + ".x10";
	public String archiveName;							// file name of archive to import
	public String projectName;							// name of target project to accept the import
	public List<String> expectedOutput;					//expected console output when project is executed (list of strings, one per line of output)
	public List<TypeSearchInfo> declarationCheckList;	//list of x10 type declarations to test. types are declared or referenced in the project

	public  SmokeTestSetup(Document xmlConfigurations) {

		NodeList setupList = xmlConfigurations.getElementsByTagName("smokeTestSetup");
		Element setup = (Element) setupList.item(0);		//we're only expecting one of these. any additional elements will be ignored
		try {

			className			= getTagString("className", setup, 0);
			classSourceFileName = (className + ".x10");
			archiveName 		= getTagString("archiveName", setup, 0);
			projectName 		= getTagString("projectName", setup, 0);

			//expected console output
			Element xmlConsoleLines = (Element) setup.getElementsByTagName("expectedOutput").item(0);			//we're only expecting one of these. any additional output lists will be ignored
			int	numLines = xmlConsoleLines.getElementsByTagName("line").getLength();
			expectedOutput = new ArrayList<String>(numLines);
			if (xmlConsoleLines != null) {
				for (int xmlConsoleLineNum = 0; xmlConsoleLineNum < numLines; xmlConsoleLineNum++) {
					expectedOutput.add(getTagQuotedString("line", xmlConsoleLines, xmlConsoleLineNum));
				}
			}

			// type declaration searches
			NodeList xmlTypeSearchList = setup.getElementsByTagName("typeDeclarationList");			  
			Element xmlTypeSearches = (Element) xmlTypeSearchList.item(0);		//we're only expecting one of these. any additional elements will be ignored
			NodeList xmlSearchInfoList = xmlTypeSearches.getElementsByTagName("typeSearchInfo");			  
			int	numSearches = xmlSearchInfoList.getLength();
			declarationCheckList = new ArrayList<TypeSearchInfo>(numSearches);
			if (xmlSearchInfoList != null) {
				for (int xmlTypeSearchNum = 0; xmlTypeSearchNum < numSearches; xmlTypeSearchNum++) {

					Element xmlTypeSearchInfo = (Element)xmlSearchInfoList.item(xmlTypeSearchNum);
					TypeSearchInfo searchInfo = new TypeSearchInfo(
							getTagQuotedString("searchString"   , xmlTypeSearchInfo, 0) ,
							getTagQuotedString("typeName"       , xmlTypeSearchInfo, 0) ,
							getTagInteger("expectToFind"  		, xmlTypeSearchInfo, 0) ,
							getTagQuotedString("fileName"       , xmlTypeSearchInfo, 0) ,
							getTagQuotedString("typeDeclaration", xmlTypeSearchInfo, 0) 
					);
					declarationCheckList.add(searchInfo);
				}
			}
		}
		catch (Exception e) {
			System.out.println("error reading smoke test configs from XML file: " + e.getMessage());
			e.printStackTrace();
		}
	}	
}
