package pds;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.SecurityUtil;

public class PdsInputOkCommand implements PdsInterface {

	@SuppressWarnings("rawtypes")
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pds");
		int maxSize = 1024 * 1024 * 30;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());  // 생성과 동시에 업로드 된다.(알아서 넘긴 데이터가 realPath 자리에 알아서 저장이 됨)
		
		Enumeration fileNames = multipartRequest.getFileNames();  // MultipartRequest 데이터 여러개 열거형으로  // 여러개 올릴 수 있지만 마지막 것만 기억(동적폼으로 해결) => jsp 누적(스프링에서 이렇게 안함)
		
		String file = "";
		String oFileName = "";
		String fSName = "";
		
		while(fileNames.hasMoreElements()) {  // 하나씩 꺼내서 값이 있느냐
			file = (String) fileNames.nextElement();
			
			if(multipartRequest.getFilesystemName(file) != null) {
				oFileName += multipartRequest.getOriginalFileName(file) + "/";
				fSName += multipartRequest.getFilesystemName(file) + "/";
			}
		}
		oFileName = oFileName.substring(0, oFileName.lastIndexOf("/"));
		fSName = fSName.substring(0, fSName.lastIndexOf("/"));
		
		// 업로드시킨 파일을 DB에 저장시키기 위해서 전송된 폼의 내용들을 모두 변수에 담아준다.
		String mid = multipartRequest.getParameter("mid")==null ? "" : multipartRequest.getParameter("mid");
		String nickName = multipartRequest.getParameter("nickName")==null ? "" : multipartRequest.getParameter("nickName");
		int fSize = multipartRequest.getParameter("fSize")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("fSize"));
		String title = multipartRequest.getParameter("title")==null ? "" : multipartRequest.getParameter("title");
		String part = multipartRequest.getParameter("part")==null ? "" : multipartRequest.getParameter("part");
		String openSw = multipartRequest.getParameter("openSw")==null ? "" : multipartRequest.getParameter("openSw");
		String pwd = multipartRequest.getParameter("pwd")==null ? "" : multipartRequest.getParameter("pwd");
		String hostIp = multipartRequest.getParameter("hostIp")==null ? "" : multipartRequest.getParameter("hostIp");
		String content = multipartRequest.getParameter("content")==null ? "" : multipartRequest.getParameter("content");
		
		// 비밀번호 암호화(SHA256) - salt키 적용 처리하지 않음
		SecurityUtil security = new SecurityUtil();  // 여기서 salt 처리 안했음(프로젝트할때 하나만 넣고 ppt에 넣어놓자)
		pwd = security.encryptSHA256(pwd);
		
		// 가공처리된 모든 자료들을 VO에 담아서 DB에 저장한다.
		PdsVO vo = new PdsVO();
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setfName(oFileName);
		vo.setfSName(fSName);
		vo.setfSize(fSize);
		vo.setTitle(title);
		vo.setPart(part);
		vo.setOpenSw(openSw);
		vo.setPwd(pwd);
		vo.setHostIp(hostIp);
		vo.setContent(content);
		
		PdsDAO dao = new PdsDAO();
		int res = dao.setPdsInputOk(vo);
		
		if(res != 0) {
			request.setAttribute("message", "자료실에 자료가 업로드 되었습니다.");
			request.setAttribute("url", "PdsList.pds");
		}
		else {
			request.setAttribute("message", "자료실에 자료가 업로드 실패~~");
			request.setAttribute("url", "PdsInput.pds");
		}
	}
}
