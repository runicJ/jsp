package pds;

public class PdsVO {
	private int idx;
	private String mid;
	private String nickName; 
	private String fName;
	private String fSName;
	private int fSize;
	private String title;
	private String part;
	private String fDate;
	private int downNum;
	private String openSw;
	private String pwd;
	private String hostIp;
	private String content;
	
	private int hour_diff;  // 게시글 24시간 경과유무 체크하는 변수
	private int date_diff;  // 게시글을 일자 경과유무 체크변수
		
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfSName() {
		return fSName;
	}
	public void setfSName(String fSName) {
		this.fSName = fSName;
	}
	public int getfSize() {
		return fSize;
	}
	public void setfSize(int fSize) {
		this.fSize = fSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public int getDownNum() {
		return downNum;
	}
	public void setDownNum(int downNum) {
		this.downNum = downNum;
	}
	public String getOpenSw() {
		return openSw;
	}
	public void setOpenSw(String openSw) {
		this.openSw = openSw;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHour_diff() {
		return hour_diff;
	}
	public void setHour_diff(int hour_diff) {
		this.hour_diff = hour_diff;
	}
	public int getDate_diff() {
		return date_diff;
	}
	public void setDate_diff(int date_diff) {
		this.date_diff = date_diff;
	}
	
	@Override
	public String toString() {
		return "PdsVO [idx=" + idx + ", mid=" + mid + ", nickName=" + nickName + ", fName=" + fName + ", fSName=" + fSName
				+ ", fSize=" + fSize + ", title=" + title + ", part=" + part + ", fDate=" + fDate + ", downNum=" + downNum
				+ ", openSw=" + openSw + ", pwd=" + pwd + ", hostIp=" + hostIp + ", content=" + content + ", hour_diff="
				+ hour_diff + ", date_diff=" + date_diff + "]";
	}
	
}
