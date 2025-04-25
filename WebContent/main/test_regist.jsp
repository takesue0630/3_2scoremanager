<style>
  .search-form {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px;
    display: flex;
    gap: 16px;
    align-items: flex-end;
    flex-wrap: wrap;
    background-color: #fff;
    width: fit-content;
  }

  .form-group {
    display: flex;
    flex-direction: column;
  }

  .form-group label {
    font-size: 14px;
    margin-bottom: 4px;
  }

  .form-group select {
    padding: 6px 8px;
    border: 1px solid #ccc;
    border-radius: 6px;
    min-width: 120px;
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
</style>



<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2 style="background-color:#C0C0C0; margin-top: 10px;margin-right: 60px;">
  学生管理
</h2>
<form action="TestRegist" method="GET">
  <div class="search-form">
    <div class="form-group">
      <label for="f1">入学年度</label>
      <select name="f1" id="f1">
        <option value="">--------</option>
        <option value="2023">2023</option>
        <option value="2024">2024</option>
        <option value="2025">2025</option>
      </select>
    </div>

    <div class="form-group">
      <label for="f2">クラス</label>
      <select name="f2" id="f2">
        <option value="">--------</option>
        <option value="101">101</option>
        <option value="102">102</option>
        <option value="201">201</option>
        <option value="202">202</option>
      </select>
    </div>

    <div class="form-group">
      <label for="f3">科目</label>
      <select name="f3" id="f3">
        <option value="">--------</option>
        <option value="math">数学</option>
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



<%@include file="../footer.jsp" %>