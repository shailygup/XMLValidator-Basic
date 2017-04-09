package model;
import java.io.*;
import  java.sql.*;
import java.util.*;

public class  CreateXMLFile
{

	private Statement stmt = null;
	private Connection dbconn = null;

	private String result   = "OK";
	private String query    = "";
	private String dburl    = "jdbc:odbc:WackoOrders";
	//private String db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\apache-tomcat-7.0.50\\webapps\\KlugBA1\\WEB-INF\\classes\\database\\WackoOrders.accdb;DriverID=01";
	private String dbdriver = "sun.jdbc.odbc.JdbcOdbcDriver" ;


	public static void main(String[] args) 
	{
		String result = "";
		CreateXMLFile g = new CreateXMLFile();

		result = g.getProductXML(args[0]);

		System.out.println(result);
	}

	private String writeXMLFile(String fileName, String data) {
		try
		{
			FileWriter fstream = new FileWriter(fileName+".xml");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(data);
			out.close();
		}
		catch (IOException e) {
			result  += " Error writing XML file: "+e.toString();
		}
		return result;
	}

	private String getProductXML(String tableName){
	
		String query = "SELECT * FROM "+tableName ;
		ResultSet myResult = null;
		ResultSetMetaData metadata = null;
		System.out.println(query);
		
		
		try {
			result = connect();
			System.out.println(result);
			myResult = stmt.executeQuery(query);
			System.out.println(myResult);
			metadata = myResult.getMetaData();
			int colCount = metadata.getColumnCount();

			result =  "<?xml version= \"1.0\" ?>\r\n";
			result += "<orders>\r\n";

			while(myResult.next()){
				result += "\t<"+tableName+" "+metadata.getColumnName(1)+"=\""+myResult.getInt(1)+"\">\r\n";
				for(int i=2; i<=colCount; i++){
					String colName = metadata.getColumnName(i);
					colName = colName.replace(" ", "");
					result += "\t\t<"+colName+">"+myResult.getString(i)+"</"+colName+">\r\n";					
				}
				result += "\t</"+tableName+">\r\n\r\n";

			}
			result += "</orders>";

			writeXMLFile(tableName, result);


		}catch (Exception e)
	    {   
	        result = "Error in in getProductXML routine: "+e.getMessage();
	    }
		finally{
			close();
		}
		return result;
	}
		
	private String connect(){
		try
		{
			 Class.forName(dbdriver);
			/*  getConnection(URL,User,Pw) */
			dbconn = DriverManager.getConnection(dburl) ;
			//dbconn = DriverManager.getConnection(db, "", "");

			/*create a SQL Statement */
			stmt = dbconn.createStatement();
	
		}
		catch (ClassNotFoundException e)
		{   
			result  = " Error creating database drive class: "+ e.toString();
		}
		catch (SQLException e)
		{   
			result  = " Error processing connect: "+e.toString();
		}  
		return result;
	  }

	  private String close(){
			try
			{   if (dbconn != null)
				{
				stmt.close();
				dbconn.close(); }
			}
			catch (SQLException e)
			{   
				result  = "Error in closing connection: "+e.toString();
			}
			return result;
		}
			


}
