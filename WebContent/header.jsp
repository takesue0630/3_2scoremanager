<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
<style>
<%--
	.container {
    display: flex; /* フレックスボックスを使用 */
    width: 100%;
    height: 100%;
    margin: 0 auto; /* 中央に配置 */
	}

	.sidebar {
    flex: 25; /* 3:1 の比率でカラムを広くする */
    padding: 20px;
	border-right: solid 2px #DCDCDC;
	}

	.main {
    flex: 75; /* サイドバーを小さくする */
    padding: 20px;
	}

	header {
	background-color: #87cefa;
	padding: 10px;
	display: flex; /* フレックスボックスを使用 */
    width: 100%;
    height: 100%;
    margin: 0 auto; /* 中央に配置 */
	}

	.title{
	width: 70%;
	}

	.sub-menu{
	width: 30%;
	float: right;
	}

	h2 {
	background-color: #DCDCDC;
	}

	footer {
	background-color: #DCDCDC;
	}

	footer p {
	text-align: center;
	}

	ul {
	list-style-type: none;
	}

	.insert p input,.insert p select{
	width: 100%;
	height: 30px;
	}

	.boader_none {
	border: none;
	outline: none;
	}
--%>
</style>
</head>
<body>
<header>
<div class="title">
<h1>得点管理システム</h1>
</div>

<div class="sub-menu">
<span>${ teacher.name }</span>
<a href="logout">ログアウト</a>
</div>
</header>
<div class="container">