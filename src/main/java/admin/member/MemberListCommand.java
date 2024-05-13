package admin.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;
import memeber.MemberDAO;
import memeber.MemberVO;
import study.database.LoginVO;

public class MemberListCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
//		ArrayList<MemberVO> vos = null;
		
		int level = request.getParameter("level")==null ? 999 : Integer.parseInt(request.getParameter("level"));
		
//		if(level == 999) {
//			MemberDAO dao2 = new MemberDAO();
//			
//			vos = dao2.getMemberList();
//		}
//		else {
//			vos = dao.getMemberList(level);
//		}
		ArrayList<MemberVO> vos = dao.getMemberList(level);
		
//		String strLevel = "";
//		if(level == 0) strLevel="관리자";
//		else if(level == 1) strLevel = "준회원";
//		else if(level == 2) strLevel = "정회원";
//		else if(level == 3) strLevel = "우수회원";
//		else strLevel = "전체회원";
				
		request.setAttribute("vos", vos);
		request.setAttribute("level", level);  // selected 때문에 level도 필요함(memberList.jsp에서)
//		request.setAttribute("strLevel", strLevel);
	}

}
