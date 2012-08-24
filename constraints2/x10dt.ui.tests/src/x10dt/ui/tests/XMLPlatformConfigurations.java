package x10dt.ui.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLPlatformConfigurations extends SmokeTestSetup {

	  /*
	   * The following enums are used to select dropdown items in the X10 Platform Configuration editor
	   * 	- CommInterface		: Communications Interface Mode and Type
	   * 	- Architecture
	   * 	- OS
	   */

	//maps Comminterface Mode and Type enums to/from strings
	  public static class CommInterface {
		  //create the Mode enum and map each enum to a string
		  public enum Mode { Profile	("Profile"),
							 Debug	("Debug"),
							 Launch	("Launch");
			  Mode(String key) { this.key = key; }	 //constructor sets the string in
			  private final String key;				 // private field 'key'
			  public String getKey() { return key; } //use enum to get string
		  };
		  // now go the other way - map a string key to an enum value
		  private static Map<String, Mode> modeMap = new HashMap<String, Mode>();
		  public static Mode getMode(String s) { return modeMap.get(s); } //use string to get enum
		  static {
			  for (Mode t : Mode.values()) {
				  modeMap.put(t.getKey(), t);	//initialize map
			  }
		  }

		  //create the Type enum and map each enum to a string
		  public enum Type {Sockets			("Sockets"),		
						    IBM_LoadLeveler	("IBM LoadLeveler"),		
						    Standalone		("Standalone"),		
						    IBM_Parallel_Environment("IBM Parallel Environment"),		
						    Open_MPI		("Open MPI"),		
						    MPICH2			("MPICH2");
			  Type(String key) { this.key = key; }   //constructor sets the string in
			  private final String key;              // private field 'key'          
			  public String getKey() { return key; } //use enum to get string        
		  };
		  // now go the other way - map a string key to an enum value
		  private static Map<String, Type> typeMap = new HashMap<String, Type>();
		  public static Type getType(String s) { return typeMap.get(s); } //use string to get enum
		  static {
			  for (Type t : Type.values()) {
				  typeMap.put(t.getKey(), t);
			  }
		  }
	  }

	  //maps Architecture Type enum to/from strings
	  public static class Architecture {
		  //create the Mode enum and map each enum to a string
		  public enum Type {x86("x86"), 
			  				Power("Power");
			  Type(String key) { this.key = key; }   //constructor sets the string in
			  private final String key;              // private field 'key'          
			  public String getKey() { return key; } //use enum to get string        
		  };
		  // now go the other way - map a string key to an enum value
		  private static Map<String, Type> typeMap = new HashMap<String, Type>();
		  public static Type get(String s) { return typeMap.get(s); } //use string to get enum
		  static {
			  for (Type t : Type.values()) {
				  typeMap.put(t.getKey(), t);
			  }
			  typeMap.put("i386",	Type.x86  ); //additional string reported by mac os x
		  }
	  }

	  //maps OS Type enum to/from strings
	  public static class OS {
		  //create the Mode enum and map each enum to a string
		  public enum Type {WINDOWS("WINDOWS"	),
							LINUX  ("LINUX"		),
							AIX    ("AIX"		),
							UNIX   ("UNIX"		),
							MAC    ("MAC"		);
			  Type(String key) { this.key = key; }   //constructor sets the string in
			  private final String key;              // private field 'key'          
			  public String getKey() { return key; } //use enum to get string        
		  };
		  // now go the other way - map a string key to an enum value
		  private static Map<String, Type> typeMap = new HashMap<String, Type>();
		  public static Type get(String s) { return typeMap.get(s.toUpperCase()); } //use string to get enum
		  static {
			  for (Type t : Type.values()) {
				  typeMap.put(t.getKey(), t);
			  }
			  typeMap.put("MAC OS X",	Type.MAC ); //additional string reported by mac os x
		  }
	  }
	 
	  /*
	   * This class aggregates all X10 Platform Configuration settings
	   */
	  public class PlatformConfig {

		  public boolean useLocalConnection;
		  public String  configName;
		  public String  description;
		  
		  // target settings
		  public OS.Type  			os;
		  public Architecture.Type	arch;
		  public Boolean 			set64bit;

		  // x10 settings
		  public Integer			numPlaces;
		  public ArrayList<String>	programArgs;

		  // remote connection settings
		  public String  connectionName;
		  public String  remoteHostName;
		  public Integer remoteHostPort;
		  public String  remoteHostUser;
		  public boolean usePassword;
		  public String  remoteHostPassword;
		  public String  remoteKeyFile;

		  // Communications interface
		  public CommInterface.Type interfaceType;
		  public CommInterface.Mode interfaceMode;

		  // Port forwarding settings
		  public boolean usePortForwarding;
		  public String  portForwardingTimeout;
		  public String  portForwardingLocalAddress;

		  // remote compilation settings
		  public String  outputFolder;
		  public boolean useSelectedPGAS;
		  public String  remoteDistribution;
		  public String  remotePGASDist;
		  public String  debuggerFolder;
		  public Integer debuggingPort;
	  }

	  public XMLPlatformConfigurations (String xmlFileName) {
		  super(xmlFileName);
	  }

	  public PlatformConfig getConfig(Element xmlConfigElement) throws IOException {

		  PlatformConfig platformConfig = new PlatformConfig();

		  try {

			  platformConfig.configName = getTagString("configName", xmlConfigElement);
			  platformConfig.description = getTagString("description", xmlConfigElement);

			  platformConfig.useLocalConnection = getTagBoolean("useLocalConnection", xmlConfigElement);

			  //target configuration
			  NodeList xmlTargetNode = xmlConfigElement.getElementsByTagName("target");			  
			  Element xmlTargetElement = (Element) xmlTargetNode.item(0);
			  platformConfig.os = OS.get(getTagString("osType", xmlTargetElement));
			  platformConfig.arch = Architecture.get(getTagString("architectureType", xmlTargetElement));	  
			  platformConfig.set64bit = getTagBoolean("set64BitSpace", xmlTargetElement);				  

			  //x10 configuration  NB: this node is optional
			  platformConfig.numPlaces = 4;		// set default number of places
			  // look for the node
			  NodeList xmlX10Node = xmlConfigElement.getElementsByTagName("x10");
			  if (xmlX10Node != null ) {
				  Element xmlX10Element = (Element) xmlX10Node.item(0);

				  //number of places
				  int places = getTagInteger("numPlaces", xmlX10Element);
				  if (places != 0)	//num places specified?
					  platformConfig.numPlaces = places;

				  //program arguments
				  int xmlNumArgs = xmlX10Element.getElementsByTagName("programArg").getLength();
				  platformConfig.programArgs = new ArrayList<String>(xmlNumArgs);
				  for (int xmlArgNum = 0; xmlArgNum < xmlNumArgs; xmlArgNum++) {
					  platformConfig.programArgs.add(getTagString("programArg", xmlX10Element, xmlArgNum));
				  }
			  }

			  //communication interface configuration
			  NodeList xmlCommNode = xmlConfigElement.getElementsByTagName("commInterface");			  
			  Element xmlCommElement = (Element) xmlCommNode.item(0);
			  platformConfig.interfaceType = CommInterface.getType(getTagString("interfaceType", xmlCommElement));    
			  platformConfig.interfaceMode = CommInterface.getMode(getTagString("interfaceMode", xmlCommElement));


			  if (platformConfig.useLocalConnection) {			//running on local machine
				  // replace whatever os and arch we got from xml with the local report from Java
				  platformConfig.os = OS.get(System.getProperty("os.name"));
				  platformConfig.arch = Architecture.get(System.getProperty("os.arch"));
			  }
			  else	{ //use remote connection

				  //remote connection configuration
				  NodeList xmlRemoteNode = xmlConfigElement.getElementsByTagName("remoteConnection");			  
				  Element xmlRemoteElement = (Element) xmlRemoteNode.item(0);
				  platformConfig.connectionName 	= getTagString("connectionName", xmlRemoteElement);     
				  platformConfig.remoteHostName 	= getTagString("remoteHostName", xmlRemoteElement);    
				  platformConfig.remoteHostPort 	= getTagInteger("remoteHostPort", xmlRemoteElement);    
				  platformConfig.remoteHostUser 	= getTagString("remoteHostUser", xmlRemoteElement);    
				  platformConfig.usePassword 		= getTagBoolean("usePassword", xmlRemoteElement);      
				  platformConfig.remoteHostPassword = getTagString("remoteHostPassword", xmlRemoteElement);
				  platformConfig.remoteKeyFile		= getTagString("remoteKeyFile", xmlRemoteElement);

				  //Port forwarding settings
				  NodeList xmlPortNode = xmlConfigElement.getElementsByTagName("portForwarding");			  
				  Element xmlPortElement = (Element) xmlPortNode.item(0);
				  platformConfig.usePortForwarding        	= getTagBoolean("usePortForwarding", xmlPortElement);    
				  platformConfig.portForwardingTimeout    	= getTagString("portForwardingTimeout", xmlPortElement);    
				  platformConfig.portForwardingLocalAddress	= getTagString("portForwardingLocalAddress", xmlPortElement);      

				  //Remote compilation settings
				  NodeList xmlCompileNode = xmlConfigElement.getElementsByTagName("remotePlatform");
				  Element xmlCompileElement = (Element) xmlCompileNode.item(0);
				  if (xmlCompileElement != null)
				  {
					  platformConfig.outputFolder       = getTagString("outputFolder", xmlCompileElement);
					  platformConfig.useSelectedPGAS    = getTagBoolean("useSelectedPGAS", xmlCompileElement);      
					  platformConfig.remoteDistribution = getTagString("remoteDistribution", xmlCompileElement);    
					  platformConfig.remotePGASDist     = getTagString("remotePGASDist", xmlCompileElement);    
					  platformConfig.debuggerFolder 	= getTagString("debuggerFolder", xmlCompileElement);    
					  platformConfig.debuggingPort     	= getTagInteger("debuggingPort", xmlCompileElement); 
				  }
			  } //if using remote connection

		  }
		  catch (Exception e) {
			  System.out.println("error reading platform configs file: " + e.getMessage());
			  e.printStackTrace();
		  }
		  return platformConfig;
	  }


}
