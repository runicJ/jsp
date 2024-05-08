package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import study.database.LoginDAO;
import study.database.LoginVO;
import study2.StudyDAO;
import study2.StudyInterface;

@SuppressWarnings("serial")
@WebServlet("/AjaxIdCheck2_3")
public class AjaxIdCheck2_3 extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 세우면 안되니까 바로 차에 싣음
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginIdSearch(mid);  // vo값을 키와 벨류로 가공해서 전달(백엔드에서 처리)
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mid", vo.getMid());  // 키에는 "" 뒤에는 vo에 들어있던 값  // vos.add / map.put
		map.put("name", vo.getName());
		map.put("age", vo.getAge()+"");  // 위에 String 키,값인데 age는 숫자이므로 문자로
		map.put("gender", vo.getGender());
		map.put("address", vo.getAddress());
		
		System.out.println("map : " + map);  // map : {address=서울, gender=여자, name=관리자, mid=admin, age=10} => json타입x [{"키":"값","키":"값"},]
		
		// map 형식의 자료를 JSON형식으로 변환처리한다. 
		JSONObject jObj = new JSONObject(map);
		System.out.println("jObj : " + jObj);  // jObj : {"name":"관리자","mid":"admin","address":"서울","gender":"여자","age":"10"}  => js에서 object로 바꿔서 사용
		
		// JSON객체의 문자열로 변환....(방법2)
//		String str = jObj.toJSONString();
//		System.out.println("str : " + str);
		
		response.getWriter().write(jObj + "");  // 문자로 넘어가는 것  //{"name":"관리자","mid":"admin","address":"서울","gender":"여자","age":"10"} => jsonx 문자방식
		//response.getWriter().write(str);
	}
}
