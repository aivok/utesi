<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="esi" tagdir="/WEB-INF/tags" %>
<esi:company-page>
	<h1>Request list</h1>
	<div><a href="../">Switch to site engineer view</a></div>
	<ul>
	  	<c:forEach items="${requests}" var="req">
		   	<li>
		   		<a href="?id=${req.id}&accept=true">Accept ${req.id}</a> / <a href="?id=${req.id}&accept=false">Reject ${req.id}</a>
		   	</li>
	  	</c:forEach>
  	</ul>
</esi:company-page>