package pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsTotalDownloadCommand implements PdsInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		// DB에 저장된 파일의 정보(원본파일/서버에 저장된 파일)를 가져온다.
		PdsDAO dao = new PdsDAO();
		PdsVO vo = dao.getPdsIdxSearch(idx);  // 앞에서 안가져 왔으니 vo에서 값을 불러옴
		
		// 원본파일명과 서버저장파일명을 각각 분리시킨다.
		String[] fNames = vo.getfName().split("/");
		String[] fSNames = vo.getfSName().split("/");  // 이 작업이 귀찮으면 jsp에서 넘기면 됨
		
		// 파일을 압축하기 위해 필요한 객체들을 선언한다.
		FileInputStream fis = null;
		FileOutputStream fos = null;  // 새로운 파일 공간에 넣어준다.
		ZipOutputStream zos = null;  // zip파일로 쓰는 것(output) 만들어줌
		ServletOutputStream sos = null;  // 서블릿 외부로 가면 무조건 output(클라이언트로 가는 것)
		
		String realPath = request.getServletContext().getRealPath("/images/pds/");  // 파일이 들어갈거니까 끝에 /까지 // 여기 경로 설정  // 받아주는 곳들은 output들은 아직까지 값이 없음 껍데기(파일명, 경로)
		String zipPath = request.getServletContext().getRealPath("/images/pds/temp/");
		String zipName = vo.getTitle() + ".zip";  // 여기까지 경로, 이름 준 것 // 아래부터 껍데기
		
		fos = new FileOutputStream(zipPath + zipName);  // 출력된 압축파일의 이름 경로
		zos = new ZipOutputStream(fos);  // 껍데기를 이렇게 만든것(아무것도 안함)
		
		byte[] buffer = new byte[2048];
		int size = 0;
		
		// 각각의 파일을 압축처리 하기위한 fis을 생성을 zos(zipFileOutputStream)에 처리한다.
		for(int i=0; i<fSNames.length; i++) {
			File file = new File(realPath + fSNames[i]);
			fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(fNames[i]));  // 각각의 사용했던 Entry만 닫아야 전체 zos가 아니라 
			
			while((size=fis.read(buffer)) != -1) {  // buffer만큼 읽어와라
				zos.write(buffer,0,size);
			}
			zos.flush();  // 찌꺼기 있으면 마저 보내고
			zos.closeEntry();  // 바로 121.jpg 못함 닫고 다시 열어야함 그냥 그대로 계속 진행하면 오버플로우
			fis.close();  // 여기까지 한개의 작업이 끝나고 for문을 다시 시작
		}
		zos.close();
		
		// 서버에서 압축작업이 완료되면, 압축파일을 클라이언트로 전송하고, 서버에 존재하는 압축파일(temp에 담았던 것)을 삭제시킨다.
		// 클라이언트로 파일을 전송할 시는 HTTP 프로토콜 형식에 맞도록 헤더에 정보를 제공해줘야 한다.
		
		/* HTTP 프로토콜 형식에 맞도록 헤더에 전송할 파일의 정보를 담아준다.  */
		//String mimeType = request.getServletContext().getMimeType(file.toString()); // 변수에 담아줌 // 사용 이유는 그림 파일이기 때문에 헤더에 넣는 것은 무조건 문자이기 때문에 file은 객체 그래서 toString 해줌 
		//if(mimeType == null) response.setContentType("application/octet-stream");  // mimeType이 압축파일이나 이미 zip파일 압축파일이기 때문에 이 과정을 빼도 된다.  // 파일의 형식이 정해지지 않으면 2진 바이너리 형식의 문자형식으로 보내겠다 세팅(octet-stream) enctype // ip통해서 지정하는 방식은 8진수
		
		String downloadName = "";
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {  // user-agent는 브라우저를 말함  // msie(마아크로 소프트 인터넷 익스플로어)가 존재하지 않는지
			downloadName = new String(zipName.getBytes("UTF-8"), "8859_1");  // 7,8년 전
		}
		else downloadName = new String(zipName.getBytes("EUC-KR"), "8859_1");  //getByte 는 bit를 byte로 변환
		
		// 헤더에 정보를 첨부...
		response.setHeader("Content-Disposition", "attachment;fileName="+downloadName);  // 예약어 설명 Content-Disposition(대소문자 구분)  // header에 있는 attachment에 첨부하겠다 filename으로 넘김
		
		fis = new FileInputStream(zipPath + zipName);  // temp에 있으니까 그냥 realPath가 아니라 zipPath  // 껍데기 만들어줌, 서버에 존재하면 new로 생성 -> 이제 던져줌
		sos = response.getOutputStream();  // 서블릿 밖에서는 객체만 만들어 주면됨(경로, 파일명 다시 설정x)  // 외부에 쓰는 것 생성하지 않음, 응답해주는 것 // 이 객체 통해서 웹을 통해 내보내줌  => 껍데기만 만들어 준것 // http 통신 header을 통해 file을 담아서 넘겨줌 
	
//		byte[] buffer = new byte[2048];
//		int size = 0;
		while((size=fis.read(buffer)) != -1) {
			sos.write(buffer, 0, size);
		}
		sos.flush(); // 버퍼에 남아있는 찌꺼기까지 다 넘겨라 현재는 45줄로만 가능
		sos.close();
		fis.close();
		
		// 클라이언트에 파일전송을 마치면, 서버에 존재하는 zip파일을 삭제처리한다.
		new File(zipPath + zipName).delete();
		
		// 다운로드 수를 증가한다.
		dao.setPdsDownNumCheck(idx);
	}

}
