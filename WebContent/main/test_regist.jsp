<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../sidebar.jsp" %>

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
    color: #FFD700;
    font-size: 12px;
    margin-top: 4px;

  }
</style>

<h2 style="background-color: #DCDCDC; padding:10px;">成績管理</h2>
<div class="search-section">

  <!-- 検索フォーム -->
  <form action="TestRegist.action" method="GET">
    <div class="search-form">
      <div class="form-group">
        <label for="f1">入学年度</label>
        <select name="f1" id="f1">
          <option value="">--------</option>
          <c:forEach var="year" items="${ ent_year_set }">
		<option value="${ year }" <c:if test="${ ent_year==year }">selected</c:if>>${ year }</option>
	</c:forEach>
        </select>
      </div>

      <div class="form-group">
        <label for="f2">クラス</label>
        <select name="f2" id="f2">
          <option value="">--------</option>
          <c:forEach var="num" items="${ class_num_set }">
		<option value="${ num }" <c:if test="${ class_num==num }">selected</c:if>>${ num }</option>
	</c:forEach>
        </select>
      </div>

      <div class="form-group">
        <label for="f3">科目</label>
        <select name="f3" id="f3">
          <option value="">--------</option>
          <c:forEach var="subject" items="${ subject_set }">
		<option value="${ subject.cd }" <c:if test="${ subject_cd==subject.cd }">selected</c:if>>${ subject.name }</option>
	</c:forEach>
        </select>
      </div>

      <div class="form-group">
        <label for="f4">回数</label>
        <select name="f4" id="f4">
          <option value="">--------</option>
          <option value="1" <c:if test="${ subject_num==1 }">selected</c:if>>1回</option>
          <option value="2" <c:if test="${ subject_num==2 }">selected</c:if>>2回</option>
        </select>
      </div>

      <button type="submit" class="search-button">検索</button>
    </div>
  </form>

  <!-- エラーメッセージ表示 -->
  <p class="error-message">${ error }</p>

  <!-- 検索結果 -->
  <c:if test="${ serch }">
   <form action="TestRegistExecute.action" method="GET" style="margin-top: 20px; text-align: left;">
   	<input type="hidden" name="size" value="${ size }">
   	<input type="hidden" name="ent_year" value="${ ent_year }">
   	<input type="hidden" name="class_num" value="${ class_num }">
   	<input type="hidden" name="subject_cd" value="${ subject_cd }">
   	<input type="hidden" name="subject_num" value="${ subject_num }">

	<div>科目：${ subject.name }（${ subject_num }回）</div>

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
			<c:forEach var="test" items="${test_list}">
				<tr>
					<td>${ test.student.entYear }</td>
					<td>${ test.classNum }</td>
					<td>${ test.student.no }</td>
					<td>${ test.student.name }</td>
					<td>
						<input type="text" name="${ test.student.no }_${ subject.cd }_${ subject_num }" maxlength="10" value="${ test.point }" placeholder="削除">
						<div style="color:#ffd700;"><c:if test="${ test.point<0 || test.point>100 }">0～100の範囲で入力してください</c:if></div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="submit" class="search-button">登録して終了</button>
   </form>
  </c:if>
</div>
<%@ include file="../footer.jsp" %>