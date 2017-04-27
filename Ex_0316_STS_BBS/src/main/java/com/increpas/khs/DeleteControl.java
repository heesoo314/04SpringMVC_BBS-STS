package com.increpas.khs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;

@Controller
public class DeleteControl {

	@Autowired
	private BbsDAO dao;
	
	@Autowired
	private HttpServletRequest request;
	
	
	@RequestMapping(value="/delete.inc", method=RequestMethod.POST)
	public ModelAndView delete(String seq, String nowPage){
		
		dao.deleteBbs(seq);
		
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("redirect:/list.inc?nowPage" + nowPage);	
		return mv;
	
	}
	
}
