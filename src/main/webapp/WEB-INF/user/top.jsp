<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>TOP画面</title>
</head>
<body>
<h1>ログイン成功</h1>
<span class="label label-danger">${Error}</span>
<h2>${currentUser.name}</h2>
<h3><a href="/category/insert">カテゴリ新規登録へ</a></h3>
<h3><a href="/task/insert">タスク新規登録へ</a></h3>
<h4><a href="/user/logout">ログアウト</a></h4>
</body>
</html>