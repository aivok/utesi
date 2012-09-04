<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="esi" tagdir="/WEB-INF/tags" %>
<esi:company-page>
  <h1>Eq. list</h1>
  <div>
  	<div><a href="boss/">Boss</a> | <a href="?action=requests">Rent requests</a></div>
  </div>
  <ul>
  	<c:forEach items="${equipments}" var="equipment">
	   	<li>
	   		<a href="?action=view&id=${equipment.id}"><c:out value="${equipment.name} - ${equipment.priceStringWithUnit}" /></a>
	   	</li>
  	</c:forEach>
  </ul>
</esi:company-page>