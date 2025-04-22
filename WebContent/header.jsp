<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
<style>
	.container {
    display: flex; /* フレックスボックスを使用 */
    width: 100%;
    height: 100%;
    margin: 0 auto; /* 中央に配置 */
	}

	.sidebar {
    flex: 25; /* 全体の25%の大きさにする */
    padding: 20px;
	border-right: solid 2px #DCDCDC;
	}

	.main {
    flex: 75; /* 全体の75%の大きさにする */
    padding: 20px;
	}
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