<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html >
<html>
<head>
<meta charset="utf-8">
</head>

<body>
	<section>
		<h3 class="title">Johns Hopkins Annual Software Development
			Seminar</h3>
		<hr />

		<p>Thank you for registering, <span> ${seminar.getName()}</span>! Below is a summary of your purchase.
		</p>

		<table>
			<tr>
				<th colspan="2">Your Courses</th>
				<th>Cost</th>
				<th></th>
			</tr>

			<c:forEach items="${seminar.getCourses()}" var="entry">
				<tr>
					<td width="250"><c:out value="${entry.value}" /></td>
					<td></td>
					<td>$${seminar.getCourseFee()}</td>
				</tr>
			</c:forEach>
			<tr></tr>
			<c:choose>
				<c:when test="${seminar.getIsHotel()}">
					<tr>
						<td>Hotel Accommodation</td>
						<td></td>
						<td>$185.00</td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${seminar.getIsParking()}">
					<tr>
						<td>Parking Permit</td>
						<td></td>
						<td>$10.00</td>
						<td></td>
					</tr>
				</c:when>
			</c:choose>
			<tr></tr>
			<tr style="font-weight: bold">
				<td></td>
				<td width="50">Total</td>
				<td>$${seminar.getTotalFee()}</td>
				<td></td>
			</tr>
		</table>

	</section>
</body>
</html>