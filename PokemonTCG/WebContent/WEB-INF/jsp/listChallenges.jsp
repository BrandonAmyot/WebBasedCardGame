<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="application/json" %>
<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"challenges":[<c:forEach items="${list}" var="challenge" varStatus="loop">
    		{"id": ${challenge.id}, "challenger": ${challenge.challenger}, "challengee": ${challenge.challengee}, "status": ${challenge.status}, "deck": ${challenge.challengerDeckId}}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}
