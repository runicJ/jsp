package memeber;

public class MemberChatVO {
	private int idx;
	private String mid;
	private String chat;
	
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
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	
	@Override
	public String toString() {
		return "MemberChatVO [idx=" + idx + ", mid=" + mid + ", chat=" + chat + "]";
	}
}
