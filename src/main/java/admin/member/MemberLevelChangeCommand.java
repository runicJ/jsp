package admin.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;

public class MemberLevelChangeCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));  // 앞에서 분리해서 보냄
		int level = request.getParameter("level")==null ? 999 : Integer.parseInt(request.getParameter("level"));
        String strIdx = request.getParameter("strIdx")==null ? "" :request.getParameter("strIdx");
        
        String[] arrayIdx = null;
		
		AdminDAO dao = new AdminDAO();
		 int res =0;
	        
        if(!strIdx.equals("")) {
            arrayIdx = strIdx.split("/");
            for(int i = 0; i<arrayIdx.length; i++) {
                res=dao.setMemberLevelChange(Integer.parseInt(arrayIdx[i]), level);
            }
            return;
        }
        
		res = dao.setMemberLevelChange(idx, level);
		
		response.getWriter().write(res + "");
	}

}
