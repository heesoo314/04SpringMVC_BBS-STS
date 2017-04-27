package com.increpas.khs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ViewControl {

	
	@Autowired
	private BbsDAO dao;
	
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/view.inc")
	public ModelAndView view(String seq, String nowPage){
		
		BbsVO vo = dao.getBbs(seq);
	
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("vo", vo);
		mv.setViewName("view");
		
		// seq, nowPage는 포워드되기 때문에 param.### 형식으로 받을 수 있다
		
		return mv;
	}
	
}
