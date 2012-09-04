<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="esi" tagdir="/WEB-INF/tags" %>
<esi:company-page>
	<h1>Rent requests</h1>
	<div>
  		<div><a href="boss/">Switch to works engineer view</a> | <a href="?">Equipment list</a></div>
  	</div>
	<ul>
		<c:forEach items="${rentRequests}" var="req">
			<li>${req.priceListItem.name} (<fmt:formatDate type="date" value="${req.startDate}" /> - <fmt:formatDate type="date" value="${req.endDate}" />)  ${req.status.value} 
				<a href="?action=cancelRentRequest&id=${req.id}">Cancel</a></li>
		</c:forEach>
	</ul>
</esi:company-page>