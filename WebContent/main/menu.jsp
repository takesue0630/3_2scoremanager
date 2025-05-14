<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>


<style>
	.manu-container {
		display: flex;
		justify-content: space-between;
		padding: 20px;
	}

	.box {
		width: 280px;
		height: 200px;
		border-radius: 15px;
		text-align: center;
		align-items: center;
		font-size: 20px;
	}
    .logout-container {
        text-align: right;
        margin-top: 10px;
        margin-right: 10px;
    }

</style>
<h2 style="background-color:#d3d3d3;">メニュー</h2>

<div class="manu-container">

	<div class="box" style="background-color:#f08080 ;">
		<a href="StudentList.action">学生管理</a>
	</div>

	<div class="box" style="background-color:#90ee90 ;">
	    成績管理<br>
	    <a href="TestRegist.action">成績登録</a><br>
	    <a href="test_list">成績参照</a><br>
	</div>

	<div class="box" style="background-color:#d8bfd8 ;">
	<a href="SubjectList.action">科目管理</a>
	</div>
</div>
<%@include file="../footer.jsp" %>