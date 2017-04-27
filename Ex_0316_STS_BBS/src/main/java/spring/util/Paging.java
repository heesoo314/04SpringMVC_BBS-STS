package spring.util;

public class Paging {

	private int nowPage, rowTotal, totalPage;
	
	// 한 페이지에 몇개의 글, 한페이지의 몇개에 블럭
	private int blockList, blockPage; 
	private int startPage, endPage, begin, end;
	
	// 이전, 다음페이지 가능여부
	private boolean isPrePage;
	private boolean isNextPage;
	
	// JSP에서 표현할 페이징 HTML코드를 저장할 곳
	private StringBuffer sb;

	
	// 생성자
	public Paging(int nowPage, int rowTotal, int blockList, int blockPage) {
		
		super();
		this.nowPage = nowPage;
		this.rowTotal = rowTotal;
		this.blockList = blockList;
		this.blockPage = blockPage;
		
		// 이전, 다음 기능을 초기화
		isPrePage = false;
		isNextPage = false;
		
		
		// 필요한 값과 조건들을 명시	
		totalPage = (int)Math.ceil((double)rowTotal / blockList);
				
		if(nowPage > totalPage)
			nowPage = totalPage;
		
		startPage = (int)((nowPage-1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;
		
		if(endPage > totalPage)
			endPage = totalPage;
		
		
		// 이전, 다음으로 넘어갈 수 있는지 체크
		if(startPage > 1)
			isPrePage = true;
		
		if(endPage < totalPage)
			isNextPage = true;
		
		
		begin = (nowPage-1) * blockList + 1;
		end = begin + blockList - 1;	
				
		
		// 위에서 정의한 값을 가지고 HTML코드를 생성 -> StringBuffer에 저장
		sb = new StringBuffer();
		setSb(sb);
		
	}


	public int getNowPage() {
		return nowPage;
	}


	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}


	public int getRowTotal() {
		return rowTotal;
	}


	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getBlockList() {
		return blockList;
	}


	public void setBlockList(int blockList) {
		this.blockList = blockList;
	}


	public int getBlockPage() {
		return blockPage;
	}


	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getBegin() {
		return begin;
	}


	public void setBegin(int begin) {
		this.begin = begin;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public boolean isPrePage() {
		return isPrePage;
	}


	public void setPrePage(boolean isPrePage) {
		this.isPrePage = isPrePage;
	}


	public boolean isNextPage() {
		return isNextPage;
	}


	public void setNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}


	public StringBuffer getSb() {
		return sb;
	}


	public void setSb(StringBuffer sb) {
				
		sb.append("<ol class='paging'>");
		
		// 이전페이지 이동
		if(isPrePage){
			sb.append("<li><a href='list.inc?nowPage=");
			sb.append(nowPage-blockPage);
			sb.append("'> &lt; </a></li>");			
		} else {
			sb.append("<li class='disable'> &lt; </li>");			
		}		

		
		// 블럭 내에 페이지 이동
		for(int i=startPage; i<=endPage; i++){

			// 총 페이지 수보다 넘는 값이 출력되는 것을 방지
			if(i > totalPage)
				break;

			if(i == nowPage){
				sb.append("<li class='now'>");
				sb.append(i);
				sb.append("</li>");
			} else {
				sb.append("<li><a href='list.inc?nowPage=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a></li>");				
			}
			
		} // for문의 끝	
		
		
		// 다음페이지 이동
		if(isNextPage){
			sb.append("<li><a href='list.inc?nowPage=");
			sb.append(nowPage+blockPage);
			sb.append("'> &gt; </a></li>");						
		} else {
			sb.append("<li class='disable'> &gt; </li>");			
		}
		
		sb.append("</ol>");
		
	}	
	
}
