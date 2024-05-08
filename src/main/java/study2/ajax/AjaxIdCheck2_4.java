package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import study.database.LoginDAO;
import study.database.LoginVO;
import study2.StudyDAO;
import study2.StudyInterface;

@SuppressWarnings("serial")
@WebServlet("/AjaxIdCheck2_4")
public class AjaxIdCheck2_4 extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 세우면 안되니까 바로 차에 싣음
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginIdSearch(mid);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mid", vo.getMid());
		map.put("name", vo.getName());
		map.put("age", vo.getAge()+"");
		map.put("gender", vo.getGender());
		map.put("address", vo.getAddress());
		
		System.out.println("map : " + map);
		
		// map 형식의 자료를 JSON형식으로 변환처리한다. 
		JSONObject jObj = new JSONObject(map);
		System.out.println("jObj : " + jObj);
		
		// 여러개의 vo객체를 JSON배열로 담아서 처리한다.
		JSONArray jArray = new JSONArray();
		
		jArray.add(jObj);  // json 형식 데이터를 json배열에 담음
		
		map = new HashMap<String, String>();  // 또 선언 안해도 되니까
		map.put("mid", "atom1234");
		map.put("name", "아톰");
		map.put("age", "19");
		map.put("gender", "남자");
		map.put("address", "제주");
		
		jObj = new JSONObject(map);  // vo를 2번 넣은 것
		jArray.add(jObj);

		System.out.println("jArray : " + jArray);  // [{"name":"관리자","mid":"admin","address":"서울","gender":"여자","age":"10"},{"name":"아톰","mid":"atom1234","address":"제주","gender":"남자","age":"19"}]
		
		response.getWriter().write(jArray.toString());  // json데이터니까 문자로 안봄(+"" 해도됨)
	}
}
