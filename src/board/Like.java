package board;

public class Like {
	private int postNo;
	private String likedUser;
	private String regDate;
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getLikedUser() {
		return likedUser;
	}
	public void setLikedUser(String likedUser) {
		this.likedUser = likedUser;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public Like(int postNo, String likedUser, String regDate) {
		super();
		this.postNo = postNo;
		this.likedUser = likedUser;
		this.regDate = regDate;
	}
	
}
