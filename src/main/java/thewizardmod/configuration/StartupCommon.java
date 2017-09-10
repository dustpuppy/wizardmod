package thewizardmod.configuration;

/*
 * User: TW
 * Date: 2/4/2015
 * 
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupCommon 
{
	public static void preInitCommon()
	{
		
    ModConfiguration.preInit();
/*
    System.out.println("MBE70: myInteger=" + ModConfiguration.myInteger
                               + "; myBoolean=" + ModConfiguration.myBoolean
                               + "; myString=" + ModConfiguration.myString);
    System.out.println("MBE70: myDouble=" + ModConfiguration.myDouble
                               + "; myColour=" + ModConfiguration.myColour);
    System.out.print("MBE70: myIntList=");
    for (int value : ModConfiguration.myIntList) {
      System.out.print(value + "; ");
    }
    System.out.println();
*/
  }
	
	public static void initCommon() 
	{
	}
	
	public static void postInitCommon() 
	{
	}

}
