package photoGallery;

public class PhotoGalleryVO {
	// photoGallery.sql
	private int idx;
	private String mid;
	private String part;
	private String title;
	private int photoCount;
	private String hostIp;
	private String pDate;
	private int goodCount;
	private int readNum;
	
	// photoStorage.sql
	private int storageIdx;
	private String fSName;
	
	// photoReply.sql
	private int replyIdx;
	private String replyMid;
	private int replyPhotoIdx;
	private String content;
	private String prDate;
	
	private int replyCnt;	// 댓글 개수
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPhotoCount() {
		return photoCount;
	}
	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getpDate() {
		return pDate;
	}
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public int getStorageIdx() {
		return storageIdx;
	}
	public void setStorageIdx(int storageIdx) {
		this.storageIdx = storageIdx;
	}
	public String getfSName() {
		return fSName;
	}
	public void setfSName(String fSName) {
		this.fSName = fSName;
	}
	public int getReplyIdx() {
		return replyIdx;
	}
	public void setReplyIdx(int replyIdx) {
		this.replyIdx = replyIdx;
	}
	public String getReplyMid() {
		return replyMid;
	}
	public void setReplyMid(String replyMid) {
		this.replyMid = replyMid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPrDate() {
		return prDate;
	}
	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}
	public int getReplyPhotoIdx() {
		return replyPhotoIdx;
	}
	public void setReplyPhotoIdx(int replyPhotoIdx) {
		this.replyPhotoIdx = replyPhotoIdx;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	@Override
	public String toString() {
		return "PhotoGalleryVO [idx=" + idx + ", mid=" + mid + ", part=" + part + ", title=" + title + ", photoCount="
				+ photoCount + ", hostIp=" + hostIp + ", pDate=" + pDate + ", goodCount=" + goodCount + ", readNum=" + readNum
				+ ", storageIdx=" + storageIdx + ", fSName=" + fSName + ", replyIdx=" + replyIdx + ", replyMid=" + replyMid
				+ ", replyPhotoIdx=" + replyPhotoIdx + ", content=" + content + ", prDate=" + prDate + ", replyCnt=" + replyCnt
				+ "]";
	}
}
