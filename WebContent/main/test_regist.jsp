<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>成績管理</h2>

<label>入学年度</label>
<form action="TestRegist" method="GET">
  <select name="f1" id="options">
    <option value="option1">2023</option>
    <option value="option2">2024</option>
    <option value="option3">2025</option>
  </select>

  <label>クラス</label>
<form action="TestRegist" method="GET">
  <select name="f2" id="options">
    <option value="option1">101</option>
    <option value="option2">102</option>
    <option value="option3">201</option>
    <option value="option4">202</option>
  </select>

    <label>科目</label>
<form action="TestRegist" method="GET">
  <select name="f3" id="options">
    <option value="option1">101</option>
    <option value="option2">102</option>
    <option value="option3">201</option>
    <option value="option4">202</option>
  </select>

  <input type="submit" value="送信">
</form>


<%@include file="../footer.jsp" %>