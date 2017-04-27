<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="resources/css/view.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function check(ff) {
		//유효성 검사

		if(ff.writer.value == ""){
			alert("글쓴이를 입력해주세요");
		}
		
		if(ff.title.value == ""){
			alert("제목을 입력해주세요");
		}
		
		if(ff.pwd.value == ""){
			alert("비밀번호를 입력해주세요");
		}
		
		ff.submit();
	}
</script>
</head>

<body>

	<div id="wrap">

		<h2>답변쓰기</h2>

		<div class="tt">

			<form action="answer.inc" method="post" enctype="multipart/form-data">
				<input type="hidden" name="bname" value="BBS" />
				<input type="hidden" name="seq" value="${ vo.seq }" />
				<input type="hidden" name="groups" value="${ vo.groups }" />
				<input type="hidden" name="step" value="${ vo.step }" />
				<input type="hidden" name="lev" value="${ vo.lev }" />
				<input type="hidden" name="nowPage" value="${ vo.nowPage }" />
				
				<%@ include file="insertForm.jsp" %>
			
			</form>

		</div>

	</div>

</body>
</html>












