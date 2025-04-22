<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
List<String> class_num=(List<String>)request.getAttribute("class_num");
%>

<h2>学生情報変更</h2>

<form action="StudentUpdateExecute.action" method="get" class="insert">

<p>
<label>入学年度</label>
<br>
<input class="boader_none" type="text" name="ent_year" value="${ student.entYear }" readonly>
</p>

<p>
<label>学生番号</label>
<br>
<input class="boader_none" type="text" name="no" value="${ student.no }" readonly>
</p>

<p>
<label>氏名</label>
<br>
<input type="text" name="name" maxlength="30" value="${ student.name }" placeholder="氏名を入力してください" required>
</p>

<p>
<label>クラス</label>
<br>

<select name="class_num">
<% if (class_num!=null) { %>
	<% int count=0; %>
	<% while (count!=class_num.size()) { %>
		<option value=<%=class_num.get(count) %>><%=class_num.get(count) %></option>
		<% count++; %>
	<% } %>
<% } %>
</select>
</p>

在学中<input type="checkbox"><br>

<input type="submit" value="変更">

</form>

<a href="StudentList.action">戻る</a>

<%@include file="../footer.jsp" %>