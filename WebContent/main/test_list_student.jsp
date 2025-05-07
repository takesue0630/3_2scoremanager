<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">成績一覧（学生）</h2>

<form action="StudentList.action" method="get">
	<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px; display:flex;">
		<div style="width:30%; padding:10px;">
		</div>
	</div>
</form>

<c:choose>
	<c:when test="${ list.size()>0 }">
		<div>氏名：${ student.name }(${ student.no })</div>

		<table style="width:100%;">
			<thead>
				<th style="text-align:left; width:50%;">科目名</th>
				<th style="text-align:left; width:20%;">科目コード</th>
				<th style="text-align:left; width:15%;">回数</th>
				<th style="text-align:left; width:15%;">点数</th>
			</thead>

			<tbody>
				<c:forEach var="testliststudent" items="${list}">
					<tr>
						<td>${ testliststudent.subjectName }</td>
						<td>${ testliststudent.subjectCd }</td>
						<td>${ testliststudent.num }</td>
						<td>${ testliststudent.point }</td>
					<tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div>成績情報が存在しませんでした</div>
	</c:otherwise>
</c:choose>

<%@include file="../footer.jsp" %>