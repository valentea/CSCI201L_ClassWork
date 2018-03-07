<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My JSP Form</title>
</head>
<body>
	<h2>College Selection</h2>
	<%
	String collegeError = (String)request.getAttribute("collegeError");
	if(collegeError != null && collegeError.length() > 0){
	%>
		<%= collegeError %>
	<%
	}
	%>
	<form action="validateForme" name="collegeForm" method="Post">
		Email <input type="text" name="email" /><br />
		College <input name="college" list="collegeList" /><br />
			<datalist id="collegeList">
				<option value = "USC" />
				<option value = "Berkly" />
				<option value = "UCLA" />
				<option value = "Stanford" />
			</datalist>
			<input type="submit" name="submit" value="Submit College" />
	</form>
</body>
</html>