package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardGoodCheckToggleCommand implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
     
     BoardDAO dao = new BoardDAO();
            
     HttpSession session = request.getSession();
     ArrayList<String> contentLike = (ArrayList<String>) session.getAttribute("sContentLike");
     if(contentLike == null) contentLike = new ArrayList<String>();  // null 값 체크 해야함 contentLike 객체가 없으면 생성해 주세요
     String imsiContentLike = "boardLike" + idx;
     if(!contentLike.contains(imsiContentLike)) {
         dao.setBoardGoodCheckPlusMinus(idx,+1);
         contentLike.add(imsiContentLike);

     }
     else{
         dao.setBoardGoodCheckPlusMinus(idx,-1);
         contentLike.remove(imsiContentLike);

     }
     
     session.setAttribute("sContentLike", contentLike);
	}

}
