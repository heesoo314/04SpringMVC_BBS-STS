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

		<h2>글쓰기</h2>

		<div class="tt">
			<form action="edit.inc" method="post" enctype="multipart/form-data">

				<input type="hidden" name="bname" value="BBS" />
				<input type="hidden" name="seq" value="${ param.seq }" />
				<input type="hidden" name="nowPage" value="${ param.nowPage }" />

				<table cellspacing="1" cellpadding="2">
					<tbdoy>
					<tr>
						<td class="line" colspan="2"></td>
					</tr>
					<tr>
						<th>작성자</td>
						<td><input type="text" name="writer" value="${ vo.writer }"/></td>
					</tr>
					<tr>
						<th>제목</td>
						<td><input type="text" name="title" value="${ vo.title }"></td>
					</tr>
					<tr>
						<th>내용</td>
						<td><textarea name="content" cols="50" rows="10">${ vo.content }</textarea></td>
					</tr>
					<tr>
						<th>첨부파일</td>
						<td>
							<input type="file" name="upload" /><br/>
							<span>( ${ vo.uploadFileName })</span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</td>
						<td><input type="password" name="pwd" value="${ vo.pwd }"/> 
							<span style="color: #0066CC">* 삭제.수정시 필요</span></td>
					</tr>
					<tr>
						<td class="line" colspan="2"></td>
					</tr>
					</tbdoy>

					<tfoot>
						<tr>
							<td><img class="btn btn_list" src="resources/images/but_list.gif"
								onclick="javascript:location.href='list.inc?nowPage=${param.nowPage}'" />
							</td>
							<td align="right">
								<input type="button" onclick="check(this.form)" value="보내기" /> 
								<input type="reset" value="재입력" /></td>

						</tr>
					</tfoot>
					
				</table>

			</form>
	
		</div>

	</div>

</body>
</html>












