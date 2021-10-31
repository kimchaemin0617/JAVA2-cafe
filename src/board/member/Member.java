package board.member;

public abstract class Member {
	
	protected String loginId;
	protected String loginPw;
	protected String nickname;	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	// 추상메서드
	// 1. 내용 없는 메서드가 생김.
	// 2. 반드시 재정의할거라고 생각을 해서 비워둔건데 그렇게 안할 수도 있다.
	// 3. 재정의를 안하면 안되게하고싶게
	public abstract void welcome();
}