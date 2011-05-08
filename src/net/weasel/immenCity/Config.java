package net.weasel.immenCity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Config 
{
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	
	public static void loadSettings()
	{
		
	}
	
	public String[] getSettingValue(String fileName, String optionName, String defaultValue, String splitValue)
    {
    	Boolean gotLine; // Verification variable
    	String[] returnValue = new String[100]; // Settings max at 100 values
    	String curLine;
    	
    	gotLine = false; // We don't have the settings found yet
    	
    	if( new File(fileName).exists() == false )
    	{
    		return( new String("File not found.ZQX").split("ZQX") );
    	}
		if(splitValue.equals("")) {
			splitValue = "afs4wfa3waawfa3dogrsijkge5ssioeguhwar3awwa3rar";
		}
    	
        try {
        	// Get the line from the file
			FileInputStream fstream = new FileInputStream(fileName);
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				curLine = in.readLine().toString();
				if(curLine.split("=", 2)[0].equalsIgnoreCase(optionName)) {
					returnValue = new String[100];
					returnValue = curLine.split("=", 2)[1].split(splitValue);
					gotLine = true;
				}
			}
			
			in.close();
			
			// If the line does not exist, create it
			if(!gotLine) 
			{
                returnValue = defaultValue.split(splitValue);
                FileOutputStream out;
                PrintStream p;
                try {
	                out = new FileOutputStream(fileName, true);
	                p = new PrintStream( out );
	                p.println (optionName+"="+defaultValue);
	                p.close();
                } catch (Exception e) {
                	logOutput("Error writing to file");
                }
			}
		}
        catch (Exception e) {
        	logOutput("-=-");
        	logOutput("File input error: "+e.toString());
        	logOutput("File input error: "+e.getStackTrace().toString());
        	logOutput("-=-");
		}
		finally {
		}
		
		return returnValue;
    }

    public String[] getSetting( String which, String Default )
    {
        return( getSettingValue(immenCity.pluginIni, which, Default, "" ) );
    }

    public boolean getBooleanSetting( String which, boolean dValue )
    {
    	boolean retVal;
    	
    	String dString = ( dValue ? "true" : "false" );
    	
        if( getSettingValue(immenCity.pluginIni, which, dString, "" )[0].equalsIgnoreCase("true") )
        	retVal = true;
        else
        	retVal = false;
        
        return( retVal );
    }

    public double getDblSetting( String item, double d )
    {
    	double retVal = Double.parseDouble( getSettingValue(immenCity.pluginIni, item, Double.toString(d), "" )[0]);
    	return retVal;
    }

    public Float getFloatSetting( String item, Float dValue )
    {
    	Float retVal = Float.valueOf(getSettingValue(immenCity.pluginIni, item, dValue.toString(), "" )[0]);
    	return retVal;
    }
}
