<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"decks": [<c:forEach items="${listOfDecks}" var="deck" varStatus="loop">${deck.id}<c:if test="${!loop.last}">, </c:if></c:forEach>]
}