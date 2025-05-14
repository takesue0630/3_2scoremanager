<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color: #DCDCDC; padding:10px;">成績一覧（科目）</h2>

<div style="border:2px solid #DCDCDC; border-radius:5px; margin:10px;">
	<form action="TestListSubjectExecute.action" method="get" style="display:flex;">
		<div style="text-align:center; width:15%; padding:10px;">
			<p>科目情報</p>
		</div>

		<div style="width:15%; padding:10px;">
			<label>入学年度</label>
			<br>
			<select name="f1" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ ent_year_set }">
					<option value="${ year }" <c:if test="${ ent_year==year }">selected</c:if>>${ year }</option>
				</c:forEach>
			</select>
		</div>

		<div style="width:15%; padding:10px;">
			<label>クラス</label>
			<br>
			<select name="f2" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="num" items="${ class_num_set }">
					<option value="${ num }" <c:if test="${ class_num==num }">selected</c:if>>${ num }</option>
				</c:forEach>
			</select>
		</div>

		<div style="width:30%; padding:10px;">
			<label>科目</label>
			<br>
			<select name="f3" style="width:100%; height:30px;">
				<option value="0">--------</option>
				<c:forEach var="subject" items="${ subject_set }">
					<option value="${ subject.cd }" <c:if test="${ subject.name==subject_name }">selected</c:if>>${ subject.name }</option>
				</c:forEach>
			</select>
		</div>

		<div style="text-align:center; width:15%; margin:auto;">
			<input type="submit" value="検索">
		</div>
	</form>

	<div style="color:#ffd700; margin-left:40px;">${ error }</div>

	<div style="margin:10px; border-bottom:2px solid #DCDCDC;"></div>

	<form action="TestListStudentExecute.action" method="get" style="display:flex;">
		<div style="text-align:center; width:15%; padding:10px;">
			<p>学生情報</p>
		</div>

		<div style="width:30%; padding:10px;">
			<label>学生番号</label>
			<br>
			<input type="text" required name="f4" placeholder="学生番号を入力してください" style="width:100%; height:25px;">
		</div>

		<div style="text-align:center; width:15%; margin:auto;">
			<input type="submit" value="検索">
		</div>
	</form>
</div>

<c:choose>
	<c:when test="${ list.size()>0 }">
		<div>科目：${ subject_name }</div>

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
							<c:when test="${ testlistsubject.points[\"1\"]!=null }">
								${ testlistsubject.points["1"] }
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${ testlistsubject.points[\"2\"]!=null }">
								${ testlistsubject.points["2"] }
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

<%@include file="../footer.jsp" %>