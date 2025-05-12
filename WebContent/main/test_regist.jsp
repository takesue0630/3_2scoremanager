<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>
<%@ page import="java.util.List, java.util.Map" %>

<%
  String selectedF1 = (String) request.getAttribute("selectedF1");
  String selectedF2 = (String) request.getAttribute("selectedF2");
  String selectedF3 = (String) request.getAttribute("selectedF3");
  String selectedF4 = (String) request.getAttribute("selectedF4");
  Map<String, String> errorMap = (Map<String, String>) request.getAttribute("errorMap");
%>

<style>
  .search-section {
    margin: 20px;
  }

  .search-form {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px;
    display: flex;
    gap: 16px;
    align-items: center;
    flex-wrap: nowrap;
    background-color: #fff;
    width: 100%;
    box-sizing: border-box;
  }

  .form-group {
    display: flex;
    flex-direction: column;
    flex-basis: 20%;
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
  }

  .error-message {
    color: red;
    font-size: 12px;
    margin-top: 4px;
  }
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
            <option value="2021" <%= "2021".equals(selectedF1) ? "selected" : "" %>>2021</option>
            <option value="2024" <%= "2024".equals(selectedF1) ? "selected" : "" %>>2024</option>
            <option value="2025" <%= "2025".equals(selectedF1) ? "selected" : "" %>>2025</option>
          </select>
        </div>

        <div class="form-group">
          <label for="f2">クラス</label>
          <select name="f2" id="f2">
            <option value="">--------</option>
            <option value="101" <%= "101".equals(selectedF2) ? "selected" : "" %>>101</option>
            <option value="131" <%= "131".equals(selectedF2) ? "selected" : "" %>>131</option>
            <option value="201" <%= "201".equals(selectedF2) ? "selected" : "" %>>201</option>
            <option value="202" <%= "202".equals(selectedF2) ? "selected" : "" %>>202</option>
          </select>
        </div>

        <div class="form-group">
          <label for="f3">科目</label>
          <select name="f3" id="f3">
            <option value="">--------</option>
            <option value="国語" <%= "国語".equals(selectedF3) ? "selected" : "" %>>国語</option>
            <option value="english" <%= "english".equals(selectedF3) ? "selected" : "" %>>英語</option>
            <option value="science" <%= "science".equals(selectedF3) ? "selected" : "" %>>理科</option>
            <option value="social" <%= "social".equals(selectedF3) ? "selected" : "" %>>社会</option>
          </select>
        </div>

        <div class="form-group">
          <label for="f4">回数</label>
          <select name="f4" id="f4">
            <option value="">--------</option>
            <option value="1" <%= "1".equals(selectedF4) ? "selected" : "" %>>1回</option>
            <option value="2" <%= "2".equals(selectedF4) ? "selected" : "" %>>2回</option>
            <option value="3" <%= "3".equals(selectedF4) ? "selected" : "" %>>3回</option>
          </select>
        </div>

        <button type="submit" class="search-button">検索</button>
      </div>
    </form>

    <!-- エラーメッセージ表示 -->
    <%
      String error = (String) request.getAttribute("error");
      if (error != null) {
    %>
      <p class="error-message"><%= error %></p>
    <%
      }
    %>

    <!-- 検索結果 -->
    <form action="TestRegistExecute.action" method="GET" style="margin-top: 20px; text-align: left;">
      <%
        @SuppressWarnings("unchecked")
        List<String> testFilter = (List<String>) request.getAttribute("test_filter");

        String subjectDisplay;
        if (selectedF3 == null || selectedF3.isEmpty()) {
          subjectDisplay = "科目：なし";
        } else {
          subjectDisplay = "科目：" + selectedF3 + "（" + selectedF4 + "回）";
        }
      %>
<p><%= subjectDisplay %></p>

<% if (selectedF1 != null && selectedF2 != null && selectedF3 != null && selectedF4 != null
       && !selectedF1.isEmpty() && !selectedF2.isEmpty() && !selectedF3.isEmpty() && !selectedF4.isEmpty()) { %>
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
        int index = 0;
        if (testFilter != null && !testFilter.isEmpty()) {
          for (String data : testFilter) {
            String[] columns = data.split(",");
            if (columns.length >= 4) {
      %>
        <tr>
          <td><%= columns[0] %></td>
          <td><%= columns[1] %></td>
          <td>
            <%= columns[2] %>
            <input type="hidden" name="studentNo_<%= index %>" value="<%= columns[2] %>" />
          </td>
          <td>
            <%= columns[3] %>
            <input type="hidden" name="studentName_<%= index %>" value="<%= columns[3] %>" />
          </td>
          <td>
            <input type="text" name="point_<%= index %>" value="<%= request.getParameter("point_" + index) != null ? request.getParameter("point_" + index) : "" %>" />
            <%
              if (errorMap != null && errorMap.containsKey("point_" + index)) {
            %>
              <div class="error-message"><%= errorMap.get("point_" + index) %></div>
            <%
              }
            %>
          </td>
        </tr>
      <%
              index++;
            } else {
      %>
        <tr>
          <td colspan="5" class="error-message">不正なデータ: <%= data %></td>
        </tr>
      <%
            }
          }
        }
      %>
    </tbody>
  </table>

  <input type="hidden" name="studentCount" value="<%= index %>" />
  <input type="hidden" name="subject" value="<%= selectedF3 %>" />
  <input type="hidden" name="subjectNum" value="<%= selectedF4 %>" />
  <input type="hidden" name="f1" value="<%= selectedF1 %>" />
  <input type="hidden" name="f2" value="<%= selectedF2 %>" />
  <input type="hidden" name="f3" value="<%= selectedF3 %>" />
  <input type="hidden" name="f4" value="<%= selectedF4 %>" />
  <button type="submit" class="search-button">登録して終了</button>
<% } else { %>
  <p class="error-message">検索条件を選択してください。</p>
<% } %>

    </form>
  </div>
</div>

<%@ include file="../footer.jsp" %>
