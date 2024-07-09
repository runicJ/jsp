package photoGallery;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhotoGallerContentCommand implements PhotoGalleryInterface {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int photoIdx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		// 게시글 조회수 1씩 증가시키기(중복방지)
		HttpSession session = request.getSession();
		ArrayList<String> contentReadNum = (ArrayList<String>) session.getAttribute("sContentIdx");
		if(contentReadNum == null) contentReadNum = new ArrayList<String>();
		String imsiContentReadNum = "photoGallery" + photoIdx;
		if(!contentReadNum.contains(imsiContentReadNum)) {
			dao.setPhotoGalleryReadNumPlus(photoIdx);
			contentReadNum.add(imsiContentReadNum);
		}
		session.setAttribute("sContentIdx", contentReadNum);
		
		// 조회자료 1건 담아서 내용보기로 보낼 준비.. photoStorage테이블의 정보도 함께 담아서 넘겨준다.(content화면에서 여러장의 사진을 보이고자 함)
		PhotoGalleryVO vo = dao.getPhotoGalleryIdxSearch(photoIdx);
		request.setAttribute("vo", vo);
		
		// 댓글 처리
		ArrayList<PhotoGalleryVO> replyVos = dao.getPhotoGalleryReply(photoIdx);
		request.setAttribute("replyVos", replyVos);
		
		// photoStorage테이블의 정보 넘겨주기(content화면에서 여러장의 사진을 보이고자 함)
		
	}

}
