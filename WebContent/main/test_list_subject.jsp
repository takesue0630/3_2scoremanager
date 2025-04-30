<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div class="main">

<h2 style="background-color: #DCDCDC; padding:10px;">成績一覧（科目）</h2>

<form action="StudentList.action" method="get">

<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px; display:flex;">

<div style="width:30%; padding:10px;">


</div>

</div>

</form>

<c:choose >
	<c:when test="${ size>0 }">
		<div>検索結果${ size }件</div>

		<table style="width:100%;">
		<thead>
			<th style="text-align:left; width:20%;">入学年度</th>
			<th style="text-align:left; width:15%;">クラス</th>
			<th style="text-align:left; width:20%;">学生番号</th>
			<th style="text-align:left; width:20%;">氏名</th>
			<th style="text-align:left; width:10%;">1回</th>
			<th style="text-align:left; width:10%;">2回</th>
		</thead>

		<tbody>
		<c:forEach var="student" items="${list}">
			<tr>
				<td>${ student.entYear }</td>
				<td>${ student.classNum }</td>
				<td>${ student.no }</td>
				<td>${ student.name }</td>
				<td>
					<c:choose test="">
						
					</c:choose>
				</td>
				<td></td>
			<tr>
		</c:forEach>
		</tbody>

		</table>
	</c:when>
	<c:otherwise>
		<div>学生情報が存在しませんでした</div>
	</c:otherwise>
</c:choose>

</div>


<%@include file="../footer.jsp" %>