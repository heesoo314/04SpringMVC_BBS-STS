package com.increpas.khs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileSave;

@Controller
public class AnswerControl {

	
	@Autowired
	private BbsDAO dao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext ctx;
	
	// Context-servlet.xml에서 <property ... />로 선언한 객체
	private String uploadPath;	
		
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	

	// 1. [글내용보기] -> [답글 쓰기] 폼으로 이동
	// 이때, seq, groups, steop, lev, nowPage가 파라미터로 전달된다.
	@RequestMapping(value="/answer.inc", method=RequestMethod.GET)
	public ModelAndView ansForm(BbsVO vo) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("vo", vo);
		mv.setViewName("answer");
		
		return mv;		
		
	}
	
	// 2.[답글쓰기] -> DB에 삽입 후 게시판으로 이동
	@RequestMapping(value="/answer.inc", method=RequestMethod.POST)
	public ModelAndView answer(BbsVO vo) throws Exception {
		
		
		// 파일이 첨부되었는지를 판단
		if(vo.getUpload().getSize() > 0){
			
			// 파일이 저장되는 "/upload" 폴더의 경로를 절대경로로 만든다
			String path = ctx.getRealPath(uploadPath);
			
			// vo에 저장된 파일과 파일명을 얻기
			MultipartFile mf = vo.getUpload();			
			String filename = mf.getOriginalFilename();
			
			// 중복된 이름인지 아닌지 판단
			filename = FileSave.checkSameFileName(filename, path);
			
			// 파일 저장
			mf.transferTo(new File(path, filename));
			System.out.println(filename);
			
			// DB에 파일명을 저장하기 위해 vo.name 변수로 지정
			vo.setUploadFileName(filename);			
						
		}		

		// 같은 그룹내의 참조글의 lev값보다 큰 글 +1
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("groups", vo.getGroups());
		map.put("lev", vo.getLev());
		
		dao.updateLev(map);
		
		
		// 참조하는 글의 step과 lev를 1씩 증가시켜준다.
		int step = Integer.parseInt(vo.getStep());
		int lev = Integer.parseInt(vo.getLev());

		vo.setStep(String.valueOf(step + 1));
		vo.setLev(String.valueOf(lev + 1));	
		vo.setIp(request.getRemoteAddr());
		
		// 답변 쓰기
		dao.writeAns(vo);
				
		
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("redirect:/list.inc?nowPage" + vo.getNowPage());	
		return mv;
		
	}
	
}
