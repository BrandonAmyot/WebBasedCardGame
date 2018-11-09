<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
{
	"players":[
		<c:forEach items="${list}" var="player">
    		{"id": ${player.id}, "user": "${player.username}"}
		</c:forEach>
	]
}