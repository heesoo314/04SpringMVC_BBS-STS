package mybatis.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BbsVO;

public class BbsDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	
	// /////////////////////////////// 비즈니스 로직 ///////////////////////////////
	
	// 페이징 기법을 위해 전체 게시물의 수 반환
	public int getTotalCount(String bname){
		return template.selectOne("bbs.totalCount", bname);
	}
	
	
	// 페이징 기법을 활용한 게시물 목록 보기 
	public BbsVO[] getList(Map<String, String> map){
		
		List<BbsVO> list = template.selectList("bbs.list", map);
		
		BbsVO[] ar = null;
		if(list != null && list.size() > 0){
			ar = new BbsVO[list.size()];
			list.toArray(ar);
		}
		
		return ar;
	}
	
	
	// 게시물 내용 보기 
	public BbsVO getBbs(String seq){		
		return template.selectOne("bbs.getBbs", seq);		
	}
	
	
	// 게시물 저장 (원글)
	public int writeBbs(BbsVO vo){		
		return template.insert("bbs.write", vo);		
	}
		

	// 게시물 수정 
	public int editBbs(BbsVO vo){
		return template.update("bbs.edit", vo);
	}	
	
	// 게시물 삭제 
	public int deleteBbs(String seq){
		return template.update("bbs.delete", seq);
	}
	
	
	
	// 답변 저장하기 전에 lev (순서) 값을 1씩 증가
	public int updateLev(Map<String, String> map){
		return template.update("bbs.updateLev", map);
	}
	
	
	// 답글쓰기
	public int writeAns(BbsVO vo){		
		return template.insert("bbs.writeAns", vo);
	}
	
	
}
