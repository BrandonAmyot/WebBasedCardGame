<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"deck": {
		"id": ${id},
		"cards": [
			<c:forEach items="${deckOfCards}" var="card" varStatus="loop">
		   		{"t": "${card.type}", "n": "${card.name}", "b": "${card.basic}"}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>
		]
	}
}