package admin.complaint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;

public class ComplaintListCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		
		ArrayList<ComplaintVO> vos = dao.getComplaintList();
		
		request.setAttribute("vos", vos);
		request.setAttribute("complaintCnt", vos.size());  // 갯수 넘김(jsp에서 vos의 length 구해도됨
	}

}
