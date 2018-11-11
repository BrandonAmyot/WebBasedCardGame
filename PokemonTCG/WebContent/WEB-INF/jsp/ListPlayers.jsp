<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"players":[
		<c:forEach items="${list}" var="player" varStatus="loop">
    		{"id": ${player.id}, "user": "${player.username}"}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}