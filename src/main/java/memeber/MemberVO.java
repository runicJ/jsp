package memeber;

public class MemberVO {  // 2
	private int idx;
	private String mid;
	private String pwd;
	private String nickName;
	private String name;
	private String gender;
	private String birthday;
	private String tel;
	private String address;
	private String email;
	private String homePage;
	private String job;
	private String hobby;
	private String photo;
	private String content;
	private String userInfor;
	private String userDel;
	private int point;
	private int level;
	private int visitCnt;
	private String startDate;  // 날짜형식을 문자형식으로 바꿔놓음(timeStamp X)
	private String lastDate;
	private int todayCnt;
	
	public MemberVO() {}
		
	public MemberVO(int idx, String mid, String pwd, String nickName, String name, String gender, String birthday,
			String tel, String address, String email, String homePage, String job, String hobby, String photo, String content,
			String userInfor, String userDel, int point, int level, int visitCnt, String startDate, String lastDate,
			int todayCnt) {
		super();
		this.idx = idx;
		this.mid = mid;
		this.pwd = pwd;
		this.nickName = nickName;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.tel = tel;
		this.address = address;
		this.email = email;
		this.homePage = homePage;
		this.job = job;
		this.hobby = hobby;
		this.photo = photo;
		this.content = content;
		this.userInfor = userInfor;
		this.userDel = userDel;
		this.point = point;
		this.level = level;
		this.visitCnt = visitCnt;
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.todayCnt = todayCnt;
	}

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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserInfor() {
		return userInfor;
	}
	public void setUserInfor(String userInfor) {
		this.userInfor = userInfor;
	}
	public String getUserDel() {
		return userDel;
	}
	public void setUserDel(String userDel) {
		this.userDel = userDel;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getVisitCnt() {
		return visitCnt;
	}
	public void setVisitCnt(int visitCnt) {
		this.visitCnt = visitCnt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public int getTodayCnt() {
		return todayCnt;
	}
	public void setTodayCnt(int todayCnt) {
		this.todayCnt = todayCnt;
	}
	
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", mid=" + mid + ", pwd=" + pwd + ", nickName=" + nickName + ", name=" + name
				+ ", gender=" + gender + ", birthday=" + birthday + ", tel=" + tel + ", address=" + address + ", email=" + email
				+ ", homePage=" + homePage + ", job=" + job + ", hobby=" + hobby + ", photo=" + photo + ", content=" + content
				+ ", userInfor=" + userInfor + ", userDel=" + userDel + ", point=" + point + ", level=" + level + ", visitCnt="
				+ visitCnt + ", startDate=" + startDate + ", lastDate=" + lastDate + ", todayCnt=" + todayCnt + "]";
	}
}
