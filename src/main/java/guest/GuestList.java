package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/guest/GuestList")
public class GuestList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO();
		
  	ArrayList<GuestVO> vos = dao.getGuestList();  // 조건이 없음 다 가져와야 함  // 값이 여러개니까 ArrayList<제너릭은GuestVO> vos
  	
  	request.setAttribute("vos", vos);
  	
  	String viewPage = "/guest/guestList.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);
	}
}
