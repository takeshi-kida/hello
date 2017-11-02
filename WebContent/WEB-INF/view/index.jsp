<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	こんにちは、<%=request.getAttribute("userName")%>
	さん！

	<form method="post" action="./login">
		<table>
			<tr>
				<td>ユーザーIDを入力してください:</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>パスワードを入力してください:</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><button type="submit">送信</button></td>
			</tr>
		</table>
	</form>
</body>
</html>