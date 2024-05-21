package study2.pdstest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class FileUpload4OkCommand implements StudyInterface {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest");
		int maxSize = 1024 * 1024 * 10;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());  // 같은 이름이 있으면 DefaultFileRenamePolicy로 +1을 붙임
		
		// 업로드된 파일의 정보를 추출해본다.
		
		String fNames = multipartRequest.getParameter("fNames");
	
		Enumeration fileNames = multipartRequest.getFileNames();
		String file = "";
		String oFileName = "";
		String fsName = "";
		
		while(fileNames.hasMoreElements()) {
			file = (String) fileNames.nextElement();
			oFileName += multipartRequest.getOriginalFileName(file) + "/";
			fsName += multipartRequest.getFilesystemName(file) + "/";
		}
		oFileName = oFileName.substring(0, oFileName.lastIndexOf("/"));
		fsName = fsName.substring(0, fsName.lastIndexOf("/"));
		
		System.out.println("원본 파일명 : " + oFileName);  // 여러개 전송해도 하나로 봄
		System.out.println("서버에 저장된 파일명 : " + fsName);
		
		System.out.println("클라이언트에서 업로드된 파일명 : " + fNames);
		
		if(oFileName != null && !oFileName.equals("")) {
			request.setAttribute("message", "파일이 업로드 되었습니다.");
		}
		else {
			request.setAttribute("message", "파일이 업로드 실패~~");
		}
		request.setAttribute("url", "FileUpload4.st");
	}

}
