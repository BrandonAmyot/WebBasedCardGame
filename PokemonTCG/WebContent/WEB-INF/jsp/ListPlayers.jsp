<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"players":[
		<c:forEach items="${list}" var="player" varStatus="loop">
    		{"id": ${player.id}, "user": "${player.username}"}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>
	]
}