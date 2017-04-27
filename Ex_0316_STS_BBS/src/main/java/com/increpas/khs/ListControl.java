package com.increpas.khs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.Paging;


@Controller
public class ListControl {
	
	// 리스너에 의해 BbsDAO가 이미 만들어져서 context에 탑재
	// context에 bean 객체 생성x & 클래스 내 setter가 없어도
	// 있는 값을 가져와서 자동으로 dao에 담아준다.
	@Autowired
	private BbsDAO dao;
	
	// Tomcat이 구동될 때 만들어짐
	@Autowired
	private HttpServletRequest request;
	
	
	// 페이징 기법에 필요한 변수들
	public static final int BLOCK_LIST = 6;
	public static final int BLOCK_PAGE = 5;
	
	int nowPage;	// 현재 페이지
	int rowTotal;	// 총 게시물의 수
	String pageCode;	// 페이징 처리된 HTML 코드
	
	
	// 나중에 검색 기능을 넣어야 한다면...?
	String searchType, searchValue;
	
	
	@RequestMapping(value="/list.inc", method=RequestMethod.GET)
	public ModelAndView list(){	
		
		String bname = "BBS";		
		String cPage = request.getParameter("nowPage");
		
		if(cPage == null)
			nowPage = 1;
		else 
			nowPage = Integer.parseInt(cPage);
		
		
		rowTotal = dao.getTotalCount(bname);	
		
		
		// 페이징처리
		Paging page = new Paging(nowPage, rowTotal, BLOCK_LIST, BLOCK_PAGE);
		
		StringBuffer sb = page.getSb();
		pageCode = sb.toString();
		
		
		// 게시물 목록을 가져온다
		int begin = page.getBegin();
		int end = page.getEnd();
						
		Map<String, String> map = new HashMap<String, String>();
		map.put("bname", bname);
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));	
		
		BbsVO[] list = dao.getList(map);
		
		
		// 뷰(JSP)로 전달
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.addObject("nowPage", nowPage);
		mv.addObject("rowTotal", rowTotal);
		mv.addObject("pageCode", pageCode);
		mv.addObject("blockList", BLOCK_LIST);
		return mv;
		
	}
	
	
}
