<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Module 11 Assignment</title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
	<header>JOHNS HOPKINS UNIVERSITY</header>
	<section>
		<h3 class="title">Johns Hopkins annual software development
			seminar</h3>
		<hr />
		<form name="seminar" action="UserController" method="post">
			<fieldset class="contact-information">
				<legend>Contact Information</legend>
				<div>
					<label for="name">Name:</label> <input type="text" id="name"
						value="${seminar.getName()}" placeholder="First Last" required
						name="name">
				</div>
				<div>
					<label for="email">E-mail:</label> <input type="email" id="email"
						value="${seminar.getEmail()}" placeholder="email@domain" required
						name="email">
				</div>
			</fieldset>
			<fieldset>
				<legend>Employment Status</legend>
				<label> <input type="radio" name="employment" id="employee"
					${seminar.getEmploymentStatus().equals("JHU Employee") ? 'checked' :'' }
					required value="employee" />JHU Employee
				</label> <label> <input type="radio" name="employment" id="student"
					${seminar.getEmploymentStatus().equals("JHU Student") ? 'checked' :'' }
					value="student" />JHU Student
				</label> <label> <input type="radio" name="employment" id="speaker"
					${seminar.getEmploymentStatus().equals("Speaker") ? 'checked' :'' }
					value="speaker" />Speaker
				</label> <label> <input type="radio" name="employment" id="other"
					${seminar.getEmploymentStatus().equals("Other") ? 'checked' :'' }
					value="other" />Other
				</label>
			</fieldset>
			<input type="submit" value="Proceed to Registration">
		</form>
	</section>
</body>

</html>