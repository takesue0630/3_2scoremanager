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

	<c:choose>
		<c:when test="${ list.size()>0 }">
			<div>科目：${ subject }</div>

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
			<c:forEach var="testlistsubject" items="${list}">
				<tr>
					<td>${ testlistsubject.entYear }</td>
					<td>${ testlistsubject.classNum }</td>
					<td>${ testlistsubject.studentNo }</td>
					<td>${ testlistsubject.studentName }</td>
					<td>
						<c:choose>
							<c:when test="${ testlistsubject.points[1] }!=null">
								${ testlistsubject.points[1] }
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${ testlistsubject.points[2] }!=null">
								${ testlistsubject.points[2] }
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
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