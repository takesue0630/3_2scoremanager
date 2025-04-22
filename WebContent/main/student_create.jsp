<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
List<String> year=(List<String>)request.getAttribute("year");
List<String> class_num=(List<String>)request.getAttribute("class_num");
%>

<h2>学生情報登録</h2>

<%-- 入学年度、学生番号、氏名、クラスを入力、登録して終了ボタンを押下する --%>
<form action="StudentCreateExecute.action" method="get" class="insert">

<p>
<label>入学年度</label>
<br>
<select name="ent_year">
<%
if (year!=null) {
	int count=0;
	while (count!=year.size()) {
		if (count==10) {  %>
			<option value=<%=year.get(count) %> selected><%=year.get(count) %></option>
		<% } else { %>
			<option value=<%=year.get(count) %>><%=year.get(count) %></option>
		<% } %>
		<% count++;
	}
}
%>
</select>
</p>

<p>
<label>学生番号</label>
<br>
<input type="text" name="no" maxlength="10" placeholder="学生番号を入力してください" required>
</p>

<p>
<label>氏名</label>
<br>
<input type="text" name="name" maxlength="30" placeholder="氏名を入力してください" required>
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

<input type="submit" value="登録して終了">

</form>

<a href="StudentList.action">戻る</a>

<%@include file="../footer.jsp" %>