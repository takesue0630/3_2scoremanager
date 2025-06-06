<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset = 'UTF-8'>
<title>得点管理</title>
<style>
    #container {
        display: flex; /* フレックスボックスを使用 */
        width: 100%;
        height: auto%;
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

    /* ヘッダーのスタイル */
    header {
        display: flex; /* ヘッダーをフレックスコンテナにする */
        justify-content: space-between; /* 子要素を左右に配置し、間にスペースを空ける */
        align-items: center; /* 子要素を垂直方向の中央に配置 */
        padding: 10px 20px; /* ヘッダーにpaddingを追加 */
        background-color: #66cccc; /* ヘッダーの背景色 (任意) */
        border-bottom: 1px solid #dee2e6; /* ヘッダーの下線 (任意) */
    }

    .title h1 {
        margin: 0; /* タイトルのmarginを削除 */
    }

    .sub-menu {
        /* 特に変更なし。必要に応じてスタイルを調整 */
    }

    .sub-menu span {
        margin-right: 10px; /* 名前とログアウトの間のスペース (任意) */
    }

</style>
</head>
<body>
<header>
<h1>得点管理システム</h1>
<div class="sub-menu">


       <span>${ teacher.name }</span>
	<c:if test="${teacher != null}">
	<a href="Logout.action">ログアウト</a>
	</c:if>

</div>
</header>
<div id = "container">
