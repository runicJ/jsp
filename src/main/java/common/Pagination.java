package common;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
	
	public static PaginationVO pageChange(HttpServletRequest request, int pag, int pageSize, int totRecCnt) {  // 메소드 만들어오기(뭐를 넣을지 정하자, 값을 받아와야함, page, pageSize) => 페이지 vo를 만들어 와야함(기존 것 백업 받고 안전하게 해야함)
		PaginationVO pVo = new PaginationVO();
		
		pag = pag == 0 ? 1 : pag;  // 현재 페이지 번호(첫 화면은 무조건 1p)
		pageSize = pageSize == 0 ? 10 : pageSize;  // 한 화면의 레코드 갯수
		
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;  // 총 페이지의 수
		if(pag > totPage) pag = 1;  // 무조건 1페이지로 가도록
		
		int startIndexNo = (pag - 1) * pageSize;  // 현재 페이지에서 출력할 '시작 인덱스 번호'
		
		int curScrStartNo = totRecCnt - startIndexNo;  // 현재 화면에 표시될 '시작 실제 게시글 번호'
		
		int blockSize = 3;  // 블록의 크기
		
		int curBlock = (pag - 1) / blockSize;  // 현재 페이지가 속한 블록번호
		
		int lastBlock = (totPage - 1) / blockSize;  // 마지막 블록
		
		pVo.setPag(pag);
		pVo.setPageSize(pageSize);
		pVo.setTotRecCnt(totRecCnt);
		pVo.setTotPage(totPage);
		pVo.setStartIndexNo(startIndexNo);
		pVo.setCurScrStartNo(curScrStartNo);
		pVo.setBlockSize(blockSize);
		pVo.setCurBlock(curBlock);
		pVo.setLastBlock(lastBlock);
		
		return pVo;
	}
}
