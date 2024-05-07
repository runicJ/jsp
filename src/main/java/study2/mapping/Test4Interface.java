package study2.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Test4Interface {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

// controller를 하나씩 만들지 않고 하나로 만들어서 if문으로 나눔
// interface는 없어도 되고 안써도 됨, 이름을 command로 동일시(통일성) 하기 위해서 사용(협업할때 사용)