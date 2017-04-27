package com.increpas.khs;

import java.io.File;

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
public class EditControl {

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
	

	@RequestMapping(value="/edit.inc", method=RequestMethod.GET)
	public ModelAndView editForm(String seq, String nowPage){
		
		BbsVO vo = dao.getBbs(seq);
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		
		mv.setViewName("edit");
		return mv;
		
	}
	
	
	@RequestMapping(value="/edit.inc", method=RequestMethod.POST)
	public ModelAndView edit(BbsVO vo) throws Exception {
		
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

		vo.setIp(request.getRemoteAddr());

		// mybatis를 이용하여 vo 전달
		dao.editBbs(vo);

		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.setViewName("redirect:/view.inc?seq=" + vo.getSeq() + "&nowPage=" + vo.getNowPage());
		return mv;
		
	}
	
	
}
