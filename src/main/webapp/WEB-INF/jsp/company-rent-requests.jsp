<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="esi" tagdir="/WEB-INF/tags" %>
<esi:company-page>
	<h1>Rent requests</h1>
	<div>
  		<div><a href="boss/">Boss</a> | <a href="?">Equipment list</a></div>
  	</div>
	<ul>
		<c:forEach items="${rentRequests}" var="req">
			<li>${req.equipment.name} (${req.startDate} - ${req.endDate}) <a href="?action=cancelRentRequest&id=${req.id}">Cancel</a></li>
		</c:forEach>
	</ul>
</esi:company-page>