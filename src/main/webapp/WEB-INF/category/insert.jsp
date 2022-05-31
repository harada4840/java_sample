<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>カテゴリ登録ページ</title>
</head>
<body>
<h3>カテゴリ新規作成画面</h3>
<form action="/category/insert" method="post">
    <div class="form-group">
        <label>カテゴリ名</label>
        <input type="text" class="form-control"  data-cke-saved-name="name" name="name"><br>
    </div>
    <button type="submit">送信</button>
</form>
</body>
</html>