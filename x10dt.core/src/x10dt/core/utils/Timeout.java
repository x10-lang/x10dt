package x10dt.core.utils;

public class Timeout {
	public static String MULTIPLIER_KEY = "com.ibm.etools.swtbot.multiplier";
	public static long MULTIPLIER = Long.valueOf(System.getProperty(MULTIPLIER_KEY, "1"));
	public static long FIVE_SECONDS = 5000 * MULTIPLIER;
	public static long TEN_SECONDS = 10000 * MULTIPLIER;
	public static long FIFTEEN_SECONDS = 15000 * MULTIPLIER;
	public static long TWENTY_SECONDS = 20000 * MULTIPLIER;
	public static long THIRTY_SECONDS = 30000 * MULTIPLIER;
	public static long FORTY_SECONDS = 40000 * MULTIPLIER;
	public static long ONE_MINUTE = 60000 * MULTIPLIER;

}
