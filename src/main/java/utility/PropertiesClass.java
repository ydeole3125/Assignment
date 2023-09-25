package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {

	public Properties loadPropertiesFile()
	{
		 Properties prop = new Properties();
		    InputStream input = null;

		    try {

		        input = new FileInputStream("config.properties");

		        // load a properties file
		        prop.load(input);

		      

		    } catch (IOException ex) {
		        ex.printStackTrace();
		    } finally {
		        if (input != null) {
		            try {
		                input.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return prop;
	}
}
