<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div class="main">
	<h2 style="background-color: #DCDCDC; padding:10px;">学生情報変更</h2>

	<form action="StudentUpdateExecute.action" method="get" class="insert">
		<div style="margin-top:10px; margin-bottom:10px;">
			<label>入学年度</label>
			<input class="boader_none" type="text" name="ent_year" value="${ student.entYear }" readonly style="border:none; outline:none; width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
		</div>

		<div style="margin-top:10px; margin-bottom:10px;">
			<label>学生番号</label>
			<input class="boader_none" type="text" name="no" value="${ student.no }" readonly  style="border:none; outline:none; width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
		</div>

		<div style="margin-top:10px; margin-bottom:10px;">
			<label>氏名</label>
			<input type="text" name="name" maxlength="30" value="${ student.name }" placeholder="氏名を入力してください" required  style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
		</div>

		<div style="margin-top:10px; margin-bottom:10px;">
			<label>クラス</label>
			<select name="class_num" style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
				<c:forEach var="classnum" items="${ class_num }">
					<option value="${ classnum }">${ classnum }</option>
				</c:forEach>
			</select>
		</div>

		在学中<input type="checkbox" name="is_attend" <c:if test="${ student.isAttend }">checked</c:if>><br>

		<input type="submit" value="変更">
	</form>

	<a href="StudentList.action">戻る</a>
</div>

<%@include file="../footer.jsp" %>