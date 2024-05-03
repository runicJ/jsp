package study.password;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/study/password/Passcheck")
public class Passcheck extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		System.out.println("원본자료 : ");
		System.out.println("idx : " + idx);
		System.out.println("mid : " + mid);
		System.out.println("pwd : " + pwd);
		
		if(idx == 1) {
			// 숫자만을 암호화 하는 경우... 암호키(16진수 => 0x1234ABCD)로 배타적 연산
			int key = 0x1234ABCD;  // 16진수 정수  // Salt key
			int encPwd, decPwd;  // encoding(암호화) decoding(복호화)
			encPwd = Integer.parseInt(pwd) ^ key;  // eor xor => ^
			
			System.out.println("인코딩 된 비밀번호 : " + "1234ABCD" + encPwd);  // <= 같은 salt키를 쓰면 안됨(보안에 어떻게 만드느냐에 따라서)
			System.out.println("앞에서 인코딩(암호화)된 pwd를 DB에 저장 처리한다.");  // 지금은 한단계지만 원래 더 여러 단계를 거침
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DB에 저장된 pwd를 다시 불러와서 디코딩 처리한다.");
			
			decPwd = encPwd ^ key;  // 방금 DB에 넣은 encPwd를 꺼내서 key에 넣음
			
			System.out.println("디코딩(복호화) 된 비밀번호 : " + decPwd);
			System.out.println("로그인 인증 처리한다.");
			System.out.println("~~~~~~~~~~~~ The End ~~~~~~~~~~~~");
		}
		else if(idx == 2) {
			// 숫자 또는 문자 조합으로 암호화 하는 방법
			// 예) 문자 A(65)로 전송되면 A의 아스키코드를 변형처리해서 암호화한다.
			long intPwd;
			String strPwd = "";  // 소문자는 지금 안됨
			// 입력받은 암호를 한 문자씩 꺼내서 아스키 코드로 변형 뒤, 문자로 누적처리(숫자 65666768(ABCD)를 문자로 해야 누적처리 됨)해서 만들어준다.
			pwd = pwd.toUpperCase();
			for(int i=0; i<pwd.length(); i++) {
				intPwd = (long) pwd.charAt(i);  // 뒤는 char(2) => 타입을 long과 맞춰줘야
				strPwd += intPwd;
			}
			System.out.println("strPwd(아스키 코드 문자로 변환된 비밀번호 : " + strPwd);
			
			// eor 연산 (이건 숫자 연산 <= 위의 문자로 만들어져 있는 얘를 숫자로 바꿔야 함)
			intPwd = Long.parseLong(strPwd);  // wrapper class 이용
			
			// 암호화 시킬 salt키를 선정...
			long key = 0x1234ABCD;
			long encPwd;
			
			encPwd = intPwd ^ key;
			
//			strPwd = key + encPwd + "";  // salt key도 넣어서 "" 문자처리
			strPwd = key + String.valueOf(encPwd);
//			strPwd = String.valueOf(encPwd);
			
			// 암호화된 코드와 salt키를 합쳐서 DB에 저장처리한다.
			System.out.println("인코딩(암호화)된 비밀번호(DB에 저장될 비밀번호) : " + strPwd);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
			
			// 다시 로그인할때 DB의 비밀번호를 가져와서 복호화 시켜준다.
			long decPwd;
			intPwd = Long.parseLong(strPwd.substring(9));  // 다시 타입을 맞춰서 decPwd와 비교  // 앞에 salt키 뺴야 하니까 8번째부터 가져와라
//			intPwd = Long.parseLong(strPwd);
			decPwd = intPwd ^ key;
			System.out.println("디코딩(복호화) 된 비밀번호 : " + decPwd);  // 넣은 것과 틀림(아스키코드로 바꾼 것을 문자로 바꿔야 함)
			
			// 복호화된 비밀번호는 숫자이기에 문자로 변환 후 2개씩(아스키코드는 두자리) 문자 처리한다.
			strPwd = String.valueOf(decPwd);  // (String) 해도됨
			
			String result = "";  // 여기에 누적
			char ch;
			
			// 문자로 변환된 복호화 코드를 각각 2자리씩 잘라서 문자로 변형 후 누적처리.... 처음 비밀번호화 비교한다.
			for(int i=0; i<strPwd.length(); i+=2) {
				ch = (char) Integer.parseInt(strPwd.substring(i, i+2));  // 65에 char 씌우면 A
				result += ch;
			}
			System.out.println();
			System.out.println("최종 변환된 비밀번호(원본 비번과 비교하세요) : " + result);
			System.out.println("로그인 인증 처리한다.");
			System.out.println("~~~~~~~~~~~~ The End ~~~~~~~~~~~~");
		}
		else if(idx == 3) {
			// 숫자또는 문자 또는 조합으로 암호화 하는 방법 - salt키를 랜덤하게 받아서 처리한다.
			// 예) 문자 A로 전송되면 A의 아스키코드를 변형처리해서 암호화한다.
			long intPwd;
			String strPwd = "";
			// 입력받은 암호를 한문자씩 꺼내어서 아스키코드로 변형뒤, 문자로 누적처리해서 만들어준다.
			//pwd = pwd.toUpperCase();
			for(int i=0; i<pwd.length(); i++) {
				intPwd = (long) pwd.charAt(i);
				strPwd += intPwd;
			}
			System.out.println("strPwd(아스크코드문자로 변환된 비밀번호) : " + strPwd);
			
			intPwd = Long.parseLong(strPwd);
			
			// 암호화시킬 salt키를 선정...
//			long key = 0x1234ABCD;
			int key = (int)(Math.random()*(99999999 - 10000000 + 1)) + 10000000;
			System.out.println("randKey : " + key);
			long encPwd;
			
			encPwd = intPwd ^ key;
			
			strPwd = String.valueOf(encPwd);
			
			String dbSavePwd = strPwd.length() + key + strPwd;
			// 암호화된 코드와 salt키를 합쳐서 DB에 저장처리한다.
			System.out.println("인코딩(암호화)된 비밀번호(DB에 저장될 비밀본호) : " + dbSavePwd);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DB에서 꺼낸 비밀번호 : " + (dbSavePwd));
			int keyLength = Integer.parseInt(dbSavePwd.substring(0,1));
			String saltKey = dbSavePwd.substring(1,keyLength-1);
			String sourcePassword = dbSavePwd.substring(keyLength);
			System.out.println("DB에서 분리한 saltKey : " + saltKey);
			System.out.println("DB에서 분리한 DATA : " + sourcePassword);
			
			
			// 다시 로그인할때 DB의 비밀번호를 가져와서 복호화 시켜준다.
			long decPwd;
			intPwd = Long.parseLong(strPwd);
			decPwd = intPwd ^ key;
			System.out.println("디코딩(복호화)된 비밀번호 : " + decPwd);
			
			// 복호화된 비밀번호는 숫자이기에 문자료 변환후 2개씩 문자 처리한다.
			strPwd = String.valueOf(decPwd);
			
			String result = "";
			char ch;
			
			// 문자로 변환된 복호화 코드를 각각 2자리씩 잘라서 문자로 변형후 누적처리.... 처음 비밀번호와 비교한다.
			for(int i=0; i<strPwd.length(); i+=2) {
				ch = (char) Integer.parseInt(strPwd.substring(i, i+2));
				result += ch;
			}
			System.out.println();
			System.out.println("최종 변환된 비밀번호(원본 비번과 비교하세요) : " + result);
		}
		
		response.sendRedirect(request.getContextPath()+"/study/password/passCheck.jsp?msg="+"OK");  // 받는쪽에서 msg에 OK가 들어오면 처리되도록
	}
}
