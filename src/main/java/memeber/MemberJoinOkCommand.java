package memeber;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;
import common.UuidProcess;

public class MemberJoinOkCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String nickName = request.getParameter("nickName")==null ? "" : request.getParameter("nickName");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String birthday = request.getParameter("birthday")==null ? "" : request.getParameter("birthday");
		String tel = request.getParameter("tel")==null ? "" : request.getParameter("tel");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null ? "" : request.getParameter("homePage");
		String job = request.getParameter("job")==null ? "" : request.getParameter("job");
		//String hobby = request.getParameter("hobby")==null ? "" : request.getParameter("hobby");  // 같은 이름으로 온 건 배열로 넘어옴(값(같은 이름)이 여러개)
		String photo = request.getParameter("photo")==null ? "noImage.jpg" : request.getParameter("photo");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String userInfor = request.getParameter("userInfor")==null ? "" : request.getParameter("userInfor");
		
		String[] hobbys = request.getParameterValues("hobby");  // 같은 이름이 여러개니까 값이 오던 안오던 배열로 처리
		String hobby = "";  // 값을 누적할 것
		if(hobbys.length != 0) {  // 배열의 길이가 0이 아니면 값이 하나라도 넘어온 것
			for(String h : hobbys) {
				hobby += h + "/";
			}
		}
		hobby.substring(0, hobby.lastIndexOf("/"));  // 길이-1로 해도 됨
		
		// DB에 저장시킬 자료 중 not null 데이터는 Back End 체크시켜준다.(나중에 annotation 붙여서 할 것)
		
		// 아이디/닉네임 중복체크....(front에서 체크하고 왔으나 해커 어쩌구 때문에 백엔드에서도 체크)
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMemberIdCheck(mid);
		if(vo.getMid() != null) {
			request.setAttribute("message", "이미 사용중인 아이디 입니다.");
			request.setAttribute("url", "MemberJoin.mem");  // 확장자 패턴 사용 중이므로 request.getContextPath() 생략가능
			return;
		}
		
		vo = dao.getMemberNickCheck(nickName);
		if(vo.getNickName() != null) {
			request.setAttribute("msg", "이미 사용중인 닉네임 입니다.");
			request.setAttribute("url", "MemberJoin.mem");
			return;
		}
		
		// 비밀번호 암호화(sha256) 256bit 64자리(4로 나눔) - salt키를 만든 후 암호화 시켜준다.(uuid코드 중 앞자리 8자리와 같이 병형처리 후 암호화시킨다.)
		// uuid를 통한 salt키 만들기(앞에서 8자리를 가져왔다.)
		String salt = UUID.randomUUID().toString().substring(0,8);  // 문자화 해서 담음
		
		SecurityUtil securityUtil = new SecurityUtil();
		pwd = securityUtil.encryptSHA256(salt + pwd);  // 64자리의 암호화된 숫자가 pwd에 담김 // (가입시 입력한 pwd)
		
		pwd = salt + pwd;  // DB저장할 때 salt키 안 만듦(기존 pw에 합쳐서 저장)
		
		// 모든 체크가 끝난 자료는 vo에 담아서 DB에 저장처리한다.
		vo = new MemberVO();
		vo.setMid(mid);
		vo.setPwd(pwd);  // 암호화 된 것 vo.setPwd(salt + pwd);로 저장(나중에 비교할때 8자리 빼고 비교)
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setHobby(hobby);
		vo.setJob(job);
		vo.setPhoto(photo);
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		
		int res = dao.setMemberJoinOk(vo);  // vo를 넘김
		
		if(res != 0) {  // 0이 아니면 1개라도 처리한 것
			request.setAttribute("message", "회원 가입되셨습니다. \\n다시 로그인 해주세요.");
			request.setAttribute("url", "MemberLogin.mem");
		}
		else {
			request.setAttribute("message", "회원 가입 실패!");
			request.setAttribute("url", "MemberJoin.mem");
		}
	}

}
