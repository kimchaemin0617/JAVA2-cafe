package board.member;

public class GeneralMember extends Member {
	
	public GeneralMember(String loginId, String loginPw, String nickname) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.nickname = nickname;
	}
	
	public void welcome() {
		System.out.println("안녕하세요 일반회원 " + this.nickname + "님");
	}
	
}