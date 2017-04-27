package spring.util;

import java.io.File;

public class FileSave {

	// 파일이름 중복시 처리하는 메소드
	public static String checkSameFileName(String filename, String path){

		// 파일의 이름과 확장자를 추출
		// 예) imsi.txt -> name: imsi, suffix: .txt
		int period = filename.lastIndexOf(".");
		String fname = filename.substring(0, period);
		String suffix = filename.substring(period);

		// 파일을 저장할 경로
		String saveFileName = path + System.getProperty("file.separator") + filename;

		// 파일이 존재하는지를 알기 위해서는
		// 무조건 java.io.File 객체를 생성하여 exists() 메소드를 사용
		File f = new File(saveFileName);	

		int idx = 1;
		while( f != null && f.exists() ){

			StringBuffer sb = new StringBuffer();
			sb.append(fname);
			sb.append("(");
			sb.append(idx++);
			sb.append(")");
			sb.append(suffix);

			filename = sb.toString();

			// imsi(1).txt 파일이 있을 수 있기 때문에 
			saveFileName = path + System.getProperty("file.separator") + filename;

			f = new File(saveFileName); // -> while문의 시작으로 가서 반복문 수행

		}

		return filename;


	}

}
