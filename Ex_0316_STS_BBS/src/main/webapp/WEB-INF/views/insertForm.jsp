<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<table cellspacing="1" cellpadding="2">
	<tbdoy>
	<tr>
		<td class="line" colspan="2"></td>
	</tr>
	<tr>
		<th>작성자</td>
		<td><input type="text" name="writer" /></td>
	</tr>
	<tr>
		<th>제목</td>
		<td><input type="text" name="title" /></td>
	</tr>
	<tr>
		<th>내용</td>
		<td><textarea name="content" cols="50" rows="10"></textarea></td>
	</tr>
	<tr>
		<th>첨부파일
		</td>
		<td><input type="file" name="upload" /></td>
	</tr>
	<tr>
		<th>비밀번호</td>
		<td><input type="password" name="pwd" /> 
			<span style="color: #0066CC">* 삭제.수정시 필요</span>
		</td>
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
