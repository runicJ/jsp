package study2.pdstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class JavaFileDownloadCommand implements StudyInterface {

	@Override  // 서버가 input 클라이언트가 output 받을때 객체를 만들어서 받아줌
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		String fileName = request.getParameter("file")==null ? "" : request.getParameter("file");
		
		File file = new File(realPath + fileName);  // 객체를 파일정보에 만들어서 처리(파일객체를 만듦)  // realPath에 있는 실제 존재하는 파일임
		
		/* HTTP 프로토콜 형식에 맞도록 헤더에 전송할 파일의 정보를 담아준다.  */
		String mimeType = request.getServletContext().getMimeType(file.toString()); // 변수에 담아줌 // 사용 이유는 그림 파일이기 때문에 헤더에 넣는 것은 무조건 문자이기 때문에 file은 객체 그래서 toString 해줌 
		if(mimeType == null) response.setContentType("application/octet-stream");  // 파일의 형식이 정해지지 않으면 2진 바이너리 형식의 문자형식으로 보내겠다 세팅(octet-stream) enctype // ip통해서 지정하는 방식은 8진수
		
		String downloadName = "";
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {  // user-agent는 브라우저를 말함  // msie(마아크로 소프트 인터넷 익스플로어)가 존재하지 않는지
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");  // 7,8년 전
		}
		else downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");  //getByte 는 bit를 byte로 변환
		
		// 헤더에 정보를 첨부...
		response.setHeader("Content-Disposition", "attachment;fileName="+downloadName);  // 예약어 설명 Content-Disposition(대소문자 구분)  // header에 있는 attachment에 첨부하겠다 filename으로 넘김
		
		FileInputStream fis = new FileInputStream(file);  // 껍데기 만들어줌, 서버에 존재하면 new로 생성 -> 이제 던져줌
		//FileOutputStream fos = new FileOutputStream(file);  // 이렇게 하면 서버에서 서버로 보낸 것 cc cv => 그러나 우리는 클라이언트로 넘겨주는 것
		ServletOutputStream sos = response.getOutputStream();  // 외부에 쓰는 것 생성하지 않음, 응답해주는 것 // 이 객체 통해서 웹을 통해 내보내줌  => 껍데기만 만들어 준것 // http 통신 header을 통해 file을 담아서 넘겨줌 
	
		byte[] buffer = new byte[2048];
		int size = 0;
		//while((size=fis.read(buffer, 0, buffer.length)) != -1) {  // 이것도 가능
		while((size=fis.read(buffer)) != -1) {
			sos.write(buffer, 0, size);
		}
		//sos.flush(); // 버퍼에 남아있는 찌꺼기까지 다 넘겨라 현재는 45줄로만 가능
		sos.close();
		fis.close();
	}

}
// 하이퍼링크에 다운로드 써도됨