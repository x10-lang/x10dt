package x10dt.ui.tests;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * This class imports and represents a <smokeTestSetup> XML structure
 * 
 * The format of <smokeTestSetup is as follows

    <smokeTestSetup> <!-- configuration information for  the smoke test                -->
                     <!--   includes                                                   -->
                     <!--       name of project to create                              -->
                     <!--       file name of archive to import                         -->
                     <!--       expected type declarations for verifying the indexer   -->
                     <!--       expected output for verification                       -->


        <projectName> ArchiveTest_CPPBack </projectName>    <!-- name of project to create                 -->
        <archiveName> ArchiveTestFile.zip </archiveName>    <!-- archive file must be on test's build path -->
        <className> QSort </className>                      <!-- name of test class being imported         -->

    	<!-- Optional: <expectedOutput> tag contains project's expected console output -->  	
        <expectedOutput>
                <!-- console output to expect when project is executed                                                -->
                <!--     output lines are expected to be contiguous and appear in this order                          -->
                <!--     note:   these lines may be embedded in a larger output stream. The test will search for the  -->
                <!--             first matching line and proceed from there                                           -->
                <!-- Sample                                                                                           -->
                <!--        <line>  "Your ad here!" </line>                                                           -->
                <!--        <line>  "Call Today!"   </line>                                                           -->
        </expectedOutput>

    	<!-- Optional: <typeDeclarationList> tag contains info used to verify X10DT's type indexing and search system -->  	
        <typeDeclarationList>
                <!-- the smoke test uses this information to check the type indexing system.                                -->
                <!-- it will perform a complete check for every instance of <typeSearchInfo>.                               -->
                <!-- the test uses:                                                                                         -->
                <!--     - searchString:     may contain wildcards and is used to find a list of matching type declarations -->
                <!--     - typeName:         the type name expected to be found                                             -->
                <!--     - typeDeclaration:  the type declaration expected to be found                                      -->
                <!--     - fileName:         the source file name where the type declaration is expected to be found        -->
                <!--     - expectToFind:      the minimum number of matching types the search is expected to find           -->
                <!-- Sample                                                                                                 -->
                <!--        <typeSearchInfo>                                                                                -->
                <!--            <searchString>   "Foo*"                          </searchString>                            -->
                <!--            <typeName>       "Foobar"                        </typeName>                                -->
                <!--            <typeDeclaration>"public class Foobar(foo: Int)" </typeDeclaration>                         -->
                <!--            <fileName>       "FooProject.x10"                </fileName>                                -->
                <!--            <expectToFind>   2                               </expectToFind>                            -->
                <!--        </typeSearchInfo>                                                                               -->
        </typeDeclarationList>                                                                                              

    </smokeTestSetup>
 */

/*
 * This class aggregates all Smoke Test configuration settings
 */

public class SmokeTestSetup extends SmoketestXMLLoader {

	//TypeSearchInfo specifies the test parameters for the 'Open X10 Type...' and X10 Search dialogs
	public static class TypeSearchInfo {
		TypeSearchInfo(String searchString, String typeName, Integer expectToFind, String fileName, String typeDeclaration) {
			this.searchString = searchString;
			this.typeName = typeName;
			this.fileName = fileName;
			this.typeDeclaration = typeDeclaration;
			this.expectToFind = expectToFind;
		}
		String	searchString;          // The search string                                      
		String	typeName;              // The type we're looking for                             
		String	typeDeclaration;       // The expected type declaration text in the source file 
		String	fileName;              // The source file where we're expecting to find the type 
		Integer	expectToFind;          // The minimum number of search results we're looking for
	}

	public String className;							//imported class name
	public String classSourceFileName;					//className + ".x10";
	public String archiveName;							// file name of archive to import
	public String projectName;							// name of target project to accept the import
	public List<String> expectedOutput;					//expected console output when project is executed (list of strings, one per line of output)
	public List<TypeSearchInfo> declarationCheckList;	//list of x10 type declarations to test. types are declared or referenced in the project

	public SmokeTestSetup (String xmlFileName) {

		super(xmlFileName);

		try {
			NodeList setupList = getElementsByTagName("smokeTestSetup");
			Element setup = (Element) setupList.item(0);		//we're only expecting one of these. any additional elements will be ignored

			if (setup == null) {
				Assert.fail("<smokeTestSetup> tag not found in xml configuration file");
			}
			else {
				className			= getTagString("className", setup);
				classSourceFileName = (className + ".x10");
				archiveName 		= getTagString("archiveName", setup);
				projectName 		= getTagString("projectName", setup);
			}

			// Optional <expectedOutput> tag
			//  - contains lines of expected console output

			//Look for an expected output list
			Element xmlConsoleLines = (Element) setup.getElementsByTagName("expectedOutput").item(0);			//we're only expecting one of these. any additional output lists will be ignored
			if (xmlConsoleLines != null) { //find it?
				int	numLines = xmlConsoleLines.getElementsByTagName("line").getLength();	//how many lines have we got?
				expectedOutput = new ArrayList<String>(numLines);				//we'll get all the lines and shove them into this ArrayList

				for (int xmlConsoleLineNum = 0; xmlConsoleLineNum < numLines; xmlConsoleLineNum++) {
					expectedOutput.add(getTagQuotedString("line", xmlConsoleLines, xmlConsoleLineNum));
				}
			}

			// Optional <typeDeclarationList> tag
			//	- contains a set of type declaration searches

			//Look for a type declaration list
			NodeList xmlTypeSearchList = setup.getElementsByTagName("typeDeclarationList");
			Element xmlTypeSearches = (Element) xmlTypeSearchList.item(0);		//we're only expecting one of these. any additional elements will be ignored

			if (xmlTypeSearches != null) {	//get it? got it. good.

				//look for one or more type search specifications in the list
				NodeList xmlSearchInfoList = xmlTypeSearches.getElementsByTagName("typeSearchInfo");
				if (xmlSearchInfoList != null) {  //find anything?

					int	numSearches = xmlSearchInfoList.getLength();					//get a head count						
					declarationCheckList = new ArrayList<TypeSearchInfo>(numSearches);	//we're going to stuff them all into this ArrayList

					for (int xmlTypeSearchNum = 0; xmlTypeSearchNum < numSearches; xmlTypeSearchNum++) {

						Element xmlTypeSearchInfo = (Element)xmlSearchInfoList.item(xmlTypeSearchNum);	//make a new list element
						TypeSearchInfo searchInfo = new TypeSearchInfo(									// and fill it up
								getTagQuotedString("searchString"   , xmlTypeSearchInfo) ,
								getTagQuotedString("typeName"       , xmlTypeSearchInfo) ,
								getTagInteger("expectToFind"  		, xmlTypeSearchInfo) ,
								getTagQuotedString("fileName"       , xmlTypeSearchInfo) ,
								getTagQuotedString("typeDeclaration", xmlTypeSearchInfo) 
						);
						declarationCheckList.add(searchInfo);											// and append it
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println("error reading smoke test configs from XML file: " + e.getMessage());
			e.printStackTrace();
		}
	}	
}
