<jsp:useBean id="model" class="model.AssValidation" scope="session"/>
<jsp:setProperty name="model" property="*"/> 
<html>
	<head>
		<title>Validation Results</title>
	</head>
	<body>
		<h2>Results:</h2>
		<hr/>
		<p>
			<%=
			  model.ValidateXML(model.getFileName())
			 
			 %>
			 
		</p>
		<br/>
		<a href="AssValidation.jsp">Validate More Files</a>
	</body>
</html>