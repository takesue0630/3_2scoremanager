<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<style>
    .logout-container {
        text-align: right;
        margin-top: 10px;
        margin-right: 10px;
    }
</style>

<div class="logout-container">
    <a href="<%= request.getContextPath() %>/Logout.action">ログアウト</a>
</div>

<h2>メニュー</h2>

<a href="StudentList.action">学生管理</a>

<div>
    成績管理
    <br>
    <a href="test_regist">成績登録</a>
    <br>
    <a href="test_list">成績参照</a>
</div>

<br>
<a href="subject_list">科目管理</a>

<%@include file="../footer.jsp" %>