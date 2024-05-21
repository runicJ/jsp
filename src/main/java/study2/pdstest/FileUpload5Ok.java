package study2.pdstest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(  // annotation java servlet에서 제공
		// location = "/경로명",(String 타입)  // 현제 realPath로 따로 쓰므로 안씀
		fileSizeThreshold = 1024 * 1024,  // 업로드 시에 메모리에 저장되는 임시 파일 크기 설정(int) 1MByte
		maxFileSize = 1024 * 1024 * 5,  // 업로드 시 1개 파일의 최대 용량은 5MByte(long 타입)
		maxRequestSize = 1024 * 1024 * 5 * 10  // 한번에 request 시에 전송할 파일의 최대 크기(long 타입)
)
@SuppressWarnings("serial")
@WebServlet("/FileUpload5Ok")
public class FileUpload5Ok extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("images/pdstest/");  // java에서 기본 인코딩이 utf-8, size는 위에서 지정
		
		// reqeust 객체 내부 getPart() 메소드를 사용하여 업로드되는 1개 파일읮 정보를 받는다.
		Part filePart = request.getPart("fName");  // form 태그의 file 속성의 name명을 적는다.
		String fileName = filePart.getSubmittedFileName();  // 클라이언트에서 전송한 파일name(서버에 저장되는 파일명 x)
		InputStream fis = filePart.getInputStream();  // 파일 입력 스트림 생성 // 자바는 무조건 입력, 출력이 있어야함
		
		// 파일명 중복방지를 위한 처리(atom_5fefq8j2.jpg)
		String uid = UUID.randomUUID().toString().substring(0,8);
		fileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_" + uid + fileName.substring(fileName.lastIndexOf("."));  // f1246753atom.jpg 순서를 바꾸면 atom.jpgf1246753 => 가공해야함
		
		FileOutputStream fos = new FileOutputStream(realPath + fileName);  // 파일 출력 스트림 생성 // FIS로 담고 FOS new로 생성(c->s) // input(클라이언트) output 모두 new로 생성해야함 => 서버내에서 input output 할때 ctrl c ctrl v // s->c input new로 생성 받을때 껍데기 만들어서 해줌
		// 여기까진 저장안된 것 (FIS FOS 껍데기 만들어서 Byte 단위로 파일 업로드 시켜야함(getByte, 배열도 가능)
		
		// 전송되는 파일을 2048Byte 단위로 버퍼에 읽어서 서버 파일시스템에 저장한다. 2048byte = 2k(서버에 부하가 안될 정도로만), 4k
		byte[] buffer = new byte[2048];  // buffer는 변수명
		int size = 0;  // 체크하기 위해 size란 변수
		while((size=fis.read(buffer)) != -1) {  // 읽어서 체크  // 0바이트로 체크하지 않고 -1로(-1이 아닌동안에 자료가 아직 남아있다)
			fos.write(buffer, 0, size);  // size에 있는 만큼 다 뒤져서 넣어라
		}
		fos.close();  // 닫아줘야 함
		fis.close();
		
		request.setAttribute("message", "파일이 업로드 되었습니다.");
		request.setAttribute("url", "FileUpload5.st");
		
		String viewPage = "include/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
