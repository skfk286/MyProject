<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" border="1">
		<form action="modify" method="post">
			<input type="hidden" name="bCd" value="${dto.bCd }">
				<tr>
					<td>작성자</td>
					<td><input type="text" value=${dto.bAuthor } name="bAuthor"
						size="50"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" value=${dto.bTitle } name="bTitle" size="50"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="bContent" rows="10">${dto.bContent }</textarea></td>
				</tr> 
				<tr>
					<td colspan="2"> <input type="submit" value="수정">&nbsp;&nbsp; <a href="list">목록보기</a>
					<a href="delete?bCd=${dto.bCd }">삭제</a>
					</td>
				</tr>
		</form>
	</table>
</body>
</html>