<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html >
<html>
<head>
<meta charset="utf-8">
<title>Johns Hopkins Seminar Summary</title>
<link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
	<header>JOHNS HOPKINS UNIVERSITY</header>

	<section>
		<h3 class="title">Johns Hopkins annual software development
			seminar</h3>
		<hr />

		<p>
			Dear <strong>${seminar.getName()}</strong>, your total cost is $<strong>
				${seminar.getTotalFee()}</strong>.
		</p>

		<fieldset style="background: lightblue;">
			<legend>Payment Details</legend>
			<div class="container">
				<div class="one">Credit Card Type</div>
				<div class="two">
					<label> <input type="radio" id="discover" required />Discover
					</label> <label> <input type="radio" id="master" />Master card
					</label> <label> <input type="radio" id="visa" />VISA
					</label>
				</div>
			</div>
			<div class="container">
				<div class="one">Credit Card Number</div>
				<div class="two">
					<input type=text inputmode="numeric" pattern="[0-9\s]{13,19}"
						maxlength="19" placeholder="xxxx xxxx xxxx xxxx" id="number"
						required />
				</div>
			</div>
			<div class="container">
				<div class="one">Expiration Date</div>
				<div class="two">
					<input type="month" id="expiration" required />
				</div>
			</div>
		</fieldset>

		<div id="errorMessages"></div>

		<fieldset class="footer-menu">
			<legend>More Actions</legend>
			<form action="SeminarController" method="post">
				<input type="hidden" name="action" value="edit"> <input
					class="greenBtn" type="submit" value="Add More Courses">
			</form>

			<form action="RegistrationController" method="post"
				onsubmit="return validateForm()">
				<input class="greenBtn" type="submit" value="Confirm Registration">
			</form>
		</fieldset>
	</section>

	<script>
		function validateForm() {
			let isValidated = true;
			const errorMessages = document.getElementById("errorMessages");
			errorMessages.innerHTML = "";
			if (!document.getElementById("discover").checked
					&& !document.getElementById("master").checked
					&& !document.getElementById("visa").checked) {
				errorMessages.innerHTML += "Please select a credit card type <br/>";
				isValidated = false;
			}
			if (document.getElementById("number").value == "") {
				errorMessages.innerHTML += "Please put in your credit card number <br/>";
				isValidated = false;
			}
			if (document.getElementById("expiration").value == "") {
				errorMessages.innerHTML += "Please put in your credit card's expiration date <br/>";
				isValidated = false;
			}

			return isValidated;
		}
	</script>
</body>
</html>