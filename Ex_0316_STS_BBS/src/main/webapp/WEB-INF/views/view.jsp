<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="resources/css/view.css" rel="stylesheet" type="text/css">


<script type="text/javascript">

	function download(fname){
	
		location.href="FileDownload?dir=upload&filename="+fname;
		//위의 FileDownload는 서블릿이다.
	}
	
	
	function check(ff){
		
		if(confirm("삭제하시겠습니까?")){
			f1.action = "delete.inc";
			f1.method="post";
		}	
		
		f1.submit();
	}
	
</script>

</head>

<body>

	<div id="wrap">

		<h2>글내용보기</h2>

		<div class="tt">

			<form>
				<input type="hidden" name="bname" value="BBS" />
				
				<table cellspacing="1" cellpadding="2">
					<tbdoy>
					<tr>
						<td class="line" colspan="2"></td>
					</tr>
					<tr>
						<th>글쓴이</td>
						<td>${vo.writer}</td>
					</tr>
					<tr>
						<th>제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<th>등록일</td>
						<td>${vo.regdate}</td>						
					</tr>
					<tr>					
						<th>조회수</td>
						<td>${vo.hit}</td>
					</tr>
					<tr>
						<th>첨부파일</td>
						<td><a href="javascript:download('${vo.uploadFileName}')">
							${vo.uploadFileName} </a></td>
					</tr>
					<tr>
						<td colspan="2" height="100"><pre>${vo.content}</pre></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="pwd" name="pwd" /><span style="color: #0066CC">&nbsp;*삭제하려면 비밀번호를 입력하세요</span></td>
					</tr>
					<tr>
						<td class="line" colspan="2"></td>
					</tr>
					</tbdoy>
					
					<tfoot>
						<tr>
							<td align="left">
								<img class="btn btn_list"	src="resources/images/but_list.gif"
								onclick="javascript:location.href='list.inc?nowPage=${param.nowPage}'" /> 
							</td>
							<td  align="right">
								<img class="btn btn_answer"	src="resources/images/but_answer.gif" 
								onclick="javascript:location.href='answer.inc?seq=${param.seq}&groups=${vo.groups }&step=${vo.step }&lev=${vo.lev }&nowPage=${ param.nowPage }'" /> 
								
								<img class="btn btn_modify"	src="resources/images/but_modify.gif" 
								onclick="javascript:location.href='edit.inc?seq=${param.seq}&nowPage=${ param.nowPage }'" /> 
							
								<img class="btn btn_undel"	src="resources/images/but_undel.gif" onclick="check(this.form)" /> 
							</td>
						</tr>
					</tfoot>
					
				</table>
			</form>
			
			<form name="f1">
				<input type="hidden" name="seq" value="${ param.seq }" />
				<input type="hidden" name="nowPage" value="${ param.nowPage }" />
			</form>
		</div>

	</div>

</body>
</html>












