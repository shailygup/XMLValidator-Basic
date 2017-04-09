package model;
import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import java.util.*; 

/* ------------------------------------------------------------------------------------
* JDOM Validator - Shaily Gupta and Andrew Schaub, Set 4B, ACIT 4850
*
* App workings: This application will take in a argument (xml file) from 
*                the user in the controller/view and then parse it 
*				through the SAX builder to generate a JDOM Document. 	
* 				The XML file needs to validate against the declared external dtd
*
* Condition: If valid, it shows a Vaid message "No Errors, Relax!"
*			 else it shows the incorrect XML Line and message relating 
*			 to the error
*
* End Result: Once message is displayed the control goes back to the controller for more
*			  entries
*---------------------------------------------------------------------------------------*/


public class  AssValidation
{
	String fileName; 

	public void setFileName( String value )
    {
        fileName = value;
    }

    public String getFileName() { return fileName; }

	public String ValidateXML(String input)
	{	
		//fileName = input;
		String msg= "";
		try{
			SAXBuilder b = new SAXBuilder();
			b.setValidation(true);
			msg = "No Errors, Relax!";
			try{
				Document d = b.build(new File(input));
			} catch(JDOMParseException e){
				msg = e.getMessage();
			}
			return msg;
		}catch (Exception e){

			//System.err.println(e); 
			msg = e.getMessage();
		}	
		return msg;
	}
}