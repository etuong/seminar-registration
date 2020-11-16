<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Seminar Registration</title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
	<header>JOHNS HOPKINS UNIVERSITY</header>
	<section>
		<h3 class="title">Johns Hopkins annual software development
			seminar</h3>
		<hr />
		<form name="seminar" action="SeminarController" method="post">
			<fieldset>
				<legend>Select Your Courses</legend>
				<select name=courses multiple size="6" id="courses" required>
					<option value="a4"
						${seminar.isCourseSelected("a4") ? 'selected' :'' }>A4 -
						Web Services</option>
					<option value="a1"
						${seminar.isCourseSelected("a1") ? 'selected' :'' }>A1 -
						J2EE Design Patterns</option>
					<option value="a3"
						${seminar.isCourseSelected("a3") ? 'selected' :'' }>A3 -
						Service Oriented Architectures</option>
					<option value="a2"
						${seminar.isCourseSelected("a2") ? 'selected' :'' }>A2 -
						Enterprise Service Bus</option>
					<option value="a6"
						${seminar.isCourseSelected("a6") ? 'selected' :'' }>A6 -
						Secure Messaging</option>
					<option value="a5"
						${seminar.isCourseSelected("a5") ? 'selected' :'' }>A5 -
						Web Services Security</option>
				</select>
			</fieldset>
			<fieldset>
				<legend>Additional Fees and Charges</legend>
				<label> <input type="checkbox" name="hotel"
					${seminar.getIsHotel() ? 'checked' :'' } />Hotel Accommodation
					(Conference Guest Special Fee - Parking Included)
				</label> <br /> <label> <input type="checkbox" name="parking"
					${seminar.getIsParking() ? 'checked' :'' } />Parking Permit 
			</fieldset>
			<input type="hidden" name="action" value="compute"> <input
				type="submit" value="Compute Seminar Costs">
		</form>
	</section>
</body>

</html>