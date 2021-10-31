package board.member;

public class SpecialMember extends Member {
	
	int point;
	
	public SpecialMember(String loginId, String loginPw, String nickname, int point) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.nickname = nickname;
		this.point = point;
	}	
	public void welcome() {
		System.out.println("안녕하세요 우수회원 " + this.nickname + "님 회원님의 포인트는 " + this.point + "입니다");
	}
}