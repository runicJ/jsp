package study2.transaction;

public class BankBookVO {
	private int idx;
	private String mid;
	private int balance;
	
	private int contentIdx;
	private String content;
	
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getContentIdx() {
		return contentIdx;
	}
	public void setContentIdx(int contentIdx) {
		this.contentIdx = contentIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BankBookVO [idx=" + idx + ", mid=" + mid + ", balance=" + balance + ", contentIdx=" + contentIdx
				+ ", content=" + content + "]";
	}
}
