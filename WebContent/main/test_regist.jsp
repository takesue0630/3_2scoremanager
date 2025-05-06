<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<%@ page import="java.util.List" %>

<style>
  .search-section {
    display: block;
    margin-top: 20px;
    margin-left: 20px;
    margin-right: 20px;
  }

  .search-form {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px; /* 少し広げる */
    display: flex;
    gap: 16px; /* 余白を調整 */
    align-items: center; /* 要素を中央揃えにする */
    flex-wrap: nowrap; /* 折り返しを無効にする */
    background-color: #fff;
    width: 100%; /* 幅を100%に */
    box-sizing: border-box; /* パディングやボーダーが幅に含まれるようにする */
  }

  .form-group {
    display: flex;
    flex-direction: column;
    flex-basis: 20%; /* 要素の幅を一定に */
  }

  .form-group label {
    font-size: 14px;
    margin-bottom: 4px;
  }

  .form-group select {
    padding: 8px 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 6px;
    min-width: 140px;
  }

  .search-button {
    padding: 8px 16px;
    background-color: #555d66;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 14px;
  }

  .search-button:hover {
    background-color: #444b52;
  }

  table {
    border-collapse: collapse;
    margin-top: 20px;
    width: 100%;
  }

  table th, table td {
    padding: 10px;
    text-align: center;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
</style>


<div class="main">
<h2 style="background-color: #DCDCDC; padding:10px;">成績管理</h2>
<div class="search-section">
  <!-- 検索フォーム -->
  <form action="TestRegist.action" method="GET">
    <div class="search-form">
      <div class="form-group">
        <label for="f1">入学年度</label>
        <select name="f1" id="f1">
          <option value="">--------</option>
          <option value="2021">2021</option>
          <option value="2024">2024</option>
          <option value="2025">2025</option>
        </select>
      </div>

      <div class="form-group">
        <label for="f2">クラス</label>
        <select name="f2" id="f2">
          <option value="">--------</option>
          <option value="101">101</option>
          <option value="131">131</option>
          <option value="201">201</option>
          <option value="202">202</option>
        </select>
      </div>

      <div class="form-group">
        <label for="f3">科目</label>
        <select name="f3" id="f3">
          <option value="">--------</option>
          <option value="国語">国語</option>
          <option value="english">英語</option>
          <option value="science">理科</option>
          <option value="social">社会</option>
        </select>
      </div>

      <div class="form-group">
        <label for="f4">回数</label>
        <select name="f4" id="f4">
          <option value="">--------</option>
          <option value="1">1回</option>
          <option value="2">2回</option>
          <option value="3">3回</option>
        </select>
      </div>

      <button type="submit" class="search-button">検索</button>
    </div>
  </form>

  <!-- 検索結果 -->
<%
  @SuppressWarnings("unchecked")
  List<String> testFilter = (List<String>) request.getAttribute("test_filter"); // ← session → request に変更
%>

<%
  String selectedSubject = request.getParameter("f3");
  String Subjectnum = request.getParameter("f4");

  String subjectDisplay;
  if (selectedSubject == null || selectedSubject.isEmpty()) {
    subjectDisplay = "科目：なし";
  } else {
    subjectDisplay = "科目：" + selectedSubject + "(" + Subjectnum + "回)";
  }
%>

<p><%= subjectDisplay %></p>

<% if (testFilter != null && !testFilter.isEmpty()) { %>

  <table>
    <thead>
      <tr>
        <th>入学年度</th>
        <th>クラス番号</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>点数</th>
      </tr>
    </thead>
    <tbody>
      <%
        for (String data : testFilter) {
          String[] columns = data.split(",");
          if (columns.length >= 4) {
      %>
        <tr>
          <td><%= columns[0] %></td>
          <td><%= columns[1] %></td>
          <td>
            <%= columns[2] %>
          <input
           type="hidden"
           name="regist"
           value="${test.student.no}"
             />
         </td>
          <td><%= columns[3] %></td>
          <td><input type = "text" value = "${test.point}" name = "point_${test.student.no}"></td>
        </tr>
      <%
          } else {
      %>
        <tr>
          <td colspan="4" style="color: red;">不正なデータ: <%= data %></td>
        </tr>
      <%
          }
        }
      %>
    </tbody>
  </table>
<form action="TestListSubjectExecute.action" method="GET" style="margin-top: 20px; text-align: left;">
  <button type="submit" class="search-button">登録して終了</button>
</form>
<% } else { %>
  <p style="color:red; margin-top: 16px;">検索結果はありません。</p>
<% } %>
</div>
</div>

<%@ include file="../footer.jsp" %>
