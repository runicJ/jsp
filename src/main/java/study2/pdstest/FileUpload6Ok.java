package study2.pdstest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
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
@WebServlet("/FileUpload6Ok")
public class FileUpload6Ok extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("images/pdstest/");  // java에서 기본 인코딩이 utf-8, size는 위에서 지정
		
		// reqeust 객체 내부 getParts() 메소드를 사용하여 배열로 받는다.
		Collection<Part> fileParts = request.getParts();  // 한개씩 뽑으려고 Collection 객체에 담은 것// 제너릭 안에 VO처럼 Part로 넣음 배열로 복수로 받음  // request에서 넘어온 객체를 그냥 받음 getParts()에 아무것도 안적어도 됨
		
		for(Part filePart : fileParts) {
			if(!filePart.getName().equals("fName")) continue;  // 앞에서 넘어온 이름과 같지 않아 이거 처리안하면 null값이 넘어옴
			if(filePart.getSize() == 0) continue;  // getSize 파일크기 continue skip 처리

			String fileName = filePart.getSubmittedFileName();  // 이름으로 inputStream 만듦 // 배열에 있는거 하나 꺼낸 것
			InputStream fis = filePart.getInputStream();
			
			String uid = UUID.randomUUID().toString().substring(0,8);
			fileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_" + uid + fileName.substring(fileName.lastIndexOf("."));
			
			FileOutputStream fos = new FileOutputStream(realPath + fileName);  // output 객체 생성
			
			byte[] buffer = new byte[2048];
			int size = 0;
			while((size=fis.read(buffer)) != -1) {
				fos.write(buffer, 0, size);
			}
			fos.close();
			fis.close();
		}
		
		request.setAttribute("message", "파일이 업로드 되었습니다.");
		request.setAttribute("url", "FileUpload6.st");
		
		String viewPage = "include/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
} 
