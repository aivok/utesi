<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="esi" tagdir="/WEB-INF/tags" %>
<esi:company-page>
  <h1><c:out value="${item.name}" /></h1>
  <form method="post" action="?action=availability&id=${item.id}">
  	<c:if test="${isAvailable != null}">
  		<div>Is available: ${isAvailable}</div>
  		<c:if test="${isAvailable}">
  			<div><a href="?action=order&id=${item.id}&startDate=${startDate}&endDate=${endDate}">Order item</a></div>
  		</c:if>
  	</c:if>
  	<div>Start date (dd/MM/yyyy): <input type="text" name="startDate" value="${startDate}" /></div>
  	<div>End date (dd/MM/yyyy): <input type="text" name="endDate" value="${endDate}" /></div>
  	<div><input type="submit" value="Check availability" /></div>
  </form>
</esi:company-page>