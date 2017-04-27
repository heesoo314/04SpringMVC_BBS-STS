
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List, mybatis.vo.BbsVO"%>


<%-- JSP 태그라이브러리(JSTL)
	톰켓 사이트(http://tomcat.apache.org/taglibs/)에 접속하여
	화면 내용 상단에 있는 [Apache Standard Taglib]이라는
	링크부분을 선택한다.
	JSTL 1.1버전의 [download]선택함! 그리고 [binaries/]를 선택!
	화면 아래쪽의 [jakarta-taglibs-standard-1.1.2.zip]파일을
	다운로드 후 압축해제한다.
 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link href="resources/css/list.css" rel="stylesheet" type="text/css">
</head>

<body>

	<div id="wrap">
	
	<h2>BBS 목록</h2>


	<div class="tt">	
	<!--주요내용시작 -->

		<form>
		<table width="100%" border="0" cellspacing="1" cellpadding="2">
			<thead>
				<tr>
					<th width="10%">번호</td>
					<th width="40%">제목</td>
					<th width="15%">글쓴이</td>
					<th width="20%">날짜</td>
					<th width="15%">조회수</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="vo" items="${ list }" varStatus="stat">
					<tr>
						<td>
						<!--  <s:property value="rowTotal-((nowPage-1)*blockList+#stat.index)"/> -->
							${rowTotal-((nowPage-1)*blockList+stat.index) }
						</td>
						<td class="title">
			
							<%-- step값만큼 들여쓰기하는 반복문 --%> 
							<c:forEach begin="1" end="${ vo.step }">
								<c:out value="&nbsp;&nbsp;" escapeXml="false" />
							</c:forEach> 
						
							<%-- step이 0이 아닌 경우엔 화살표 이미지 출력 --%> 
							<c:if test="${vo.step > 0 }">
								<img src="resources/images/arrow.JPG" />
							</c:if> 
							<!-- <s:a href="%{viewURL}"> --> 
							<!-- <s:property value="title"/> -->
							<!-- </s:a> --> 
							<a href="view.inc?seq=${vo.seq }&nowPage=${nowPage}"> ${vo.title }</a>
						</td>
						<td>${vo.writer }</td>
						<td>${vo.regdate }</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>
			
				<c:if test="${empty list }">
					<tr>
						<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>

			<tfoot>
				<tr>
					<td></td>
					<td colspan="3">${ pageCode }</td>
					<td>
						<img class="btn but_write" src="resources/images/but_write.gif"
						onclick="javascript:location.href='write.inc?nowPage=${nowPage}'" ></td>
				</tr>

			</tfoot>
		</table>
		</form>

		<!--주요내용끝 -->	
		</div>
	
	</div>

</body>
</html>
