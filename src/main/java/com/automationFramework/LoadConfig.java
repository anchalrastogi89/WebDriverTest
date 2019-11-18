package com.automationFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadConfig 
{
	private String env, driverPath;
	
	/**
	 * @return the env
	 */
	public String getEnv() {
		return env;
	}

	/**
	 * @return the driverPath
	 */
	public String getDriverPath() {
		return driverPath;
	}
	
	/*This method read the config file for environment and store in env variable
	driverPath = stores value for browser driver based on operating system.*/
	
	public LoadConfig()
	{
	try
	{
		InputStream input = new FileInputStream(Paths.get(".").toAbsolutePath().normalize().toString()+"/src/test/resources/config.properties");
        Properties prop = new Properties();
        // load the properties file
        prop.load(input);
        
        // get the environment value
        this.env = prop.getProperty("test.env", prop.getProperty("default.env"));        
        if (System.getProperty("os.name").startsWith("Mac")) {
        	this.driverPath = new StringBuilder(Paths.get(".").toAbsolutePath().normalize().toString())
        										.append("/src/test/resources/chromedriver").toString();
        }
        else if (System.getProperty("os.name").startsWith("Windows")) {
        	this.driverPath = new StringBuilder(Paths.get(".").toAbsolutePath().normalize().toString())
			.append("/src/test/resources/chromedriver.exe").toString();
        }
	 } 
	catch (IOException io) {
         io.printStackTrace();
     }
	}
}

