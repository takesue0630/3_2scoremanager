<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<div class="main">

<h2 style="background-color: #DCDCDC;">学生情報登録</h2>

<%-- 入学年度、学生番号、氏名、クラスを入力、登録して終了ボタンを押下する --%>
<form action="StudentCreateExecute.action" method="get" class="insert">

<div style="margin-top:10px; margin-bottom:10px;">
	<label>入学年度</label>
	<select name="ent_year" style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
		<option value="0">--------</option>
		<c:forEach var="year" items="${ ent_year }">
			<option value="${ year }" <c:if test="${ year==f1 }">selected</c:if>>${ year }</option>
		</c:forEach>
	</select>
	<div style="color:#ffd700;">${error1}</div>
</div>


<div style="margin-top:10px; margin-bottom:10px;">
	<label>学生番号</label>
	<input type="text" name="no" maxlength="10" placeholder="学生番号を入力してください" value="${no}" required style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
	<div style="color:#ffd700;">${error2}</div>
</div>


<div style="margin-top:10px; margin-bottom:10px;">
	<label>氏名</label>
	<br>
	<input type="text" name="name" maxlength="30" placeholder="氏名を入力してください" value="${name}" required style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
</div>

<div style="margin-top:10px; margin-bottom:10px;">
	<label>クラス</label>
	<select name="class_num" style="width:100%; padding:10px; margin-top:10px; margin-bottom:10px;">
		<c:forEach var="classnum" items="${ class_num }">
			<option value="${ classnum }" >${ classnum }</option>
		</c:forEach>
	</select>
</div>

<input type="submit" value="登録して終了">

</form>

<a href="StudentList.action">戻る</a>

</div>

<%@include file="../footer.jsp" %>