<jsp:useBean id="model" class="model.AssValidation" scope="session"/>
<jsp:setProperty name="model" property="*"/> 
<html>
	<head>
		<title>Input Form</title>
	</head>
	<body>
		<h2>XML File Validation</h2>
		
		<p> Specify file name: </p>
		<form method="POST" ACTION="DisplayResults.jsp">
			<input type="text" name="fileName" />
			
			<input type="submit" value="Submit">
		</form>
	</body>
</html>