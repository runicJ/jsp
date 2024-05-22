package pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsDeleteCheckCommand implements PdsInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		String[] fSNames = request.getParameter("fSName").split("/");
		
		String realPath = request.getServletContext().getRealPath("/images/pds/");  // 경로에 파일 지울거니까 마지막 /
		for(String fSName : fSNames) {
			new File(realPath + fSName).delete();  // 실제 존재하는 파일은 파일(io) 객체를 만들고 지워야 함
		}
		
		// 서버의 파일을 삭제 후 DB에서 자료를 삭제처리한다.
		PdsDAO dao = new PdsDAO();
		int res = dao.setPdsDelete(idx);
		
		response.getWriter().write(res + "");
	}

}
