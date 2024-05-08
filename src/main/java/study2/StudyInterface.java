package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StudyInterface {  // 하나로 써도 되지만 보통 각 프로젝트당 하나씩 만듦
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
