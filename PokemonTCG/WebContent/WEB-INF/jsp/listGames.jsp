<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"games": [
		<c:forEach items="${games}" var="game" varStatus="loop">
    		{"id": ${game.id}, "players": [${game.playerA}, ${game.playerB}]}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}