<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"cards": [
		<c:forEach items="${deckOfCards}" var="card" varStatus="loop">
	   		{"id": ${card.cardId}, "t": "${card.type}", "n": "${card.name}", <c:if test="${card.basic} != null">"b": "${card.basic}</c:if>"}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}