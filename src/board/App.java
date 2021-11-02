// 실행시 무조건 좋아요를 누른 것으로 표현되는거 수정 중

package board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import board.member.GeneralMember;
import board.member.Member;
import board.member.SpecialMember;

public class App {

	// 변수 선언
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Article> articles = new ArrayList<>();
	private ArrayList<Comment> comments = new ArrayList<>();
	private ArrayList<Member> members = new ArrayList<>();
	private ArrayList<Like> likes = new ArrayList<>();
	private int articleNo = 1;
	private Member loginedUser = null; // 로그인한 유저

	// 메서드 선언
	public void run() {
		makeTestData();
		Member TestId1 = new SpecialMember("qwe", "qwe", "테스트 아이디 1", 99999);
		Member TestId2 = new SpecialMember("asd", "asd", "테스트 아이디 2", 99999);
		Member TestId3 = new GeneralMember("zxc", "zxc", "테스트 아이디 3");
		
		members.add(TestId1);
		members.add(TestId2);
		members.add(TestId3);
		
		loginedUser = members.get(0);

		while (true) {
			if (loginedUser == null) {
				System.out.print("명령어를 입력해주세요 : ");
			} else {
				System.out.print("명령어를 입력해주세요 [" + loginedUser.getNickname() + "(" + loginedUser.getLoginId() + ")]: ");
			}
			String command = sc.nextLine();

			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (command.equals("help")) {
				printHelp();

			} else if (command.equals("add")) {
				if (loginCheck()) {
					add();
				}

			} else if (command.equals("list")) {
				list(articles);

			} else if (command.equals("update")) {
				update();

			} else if (command.equals("delete")) {
				delete();

			} else if (command.equals("search")) {
				search();

			} else if (command.equals("read")) {

				if (loginCheck()) {
					read();
				}

			} else if (command.equals("signup")) {
				signup();

			} else if (command.equals("login")) {
				login();

			} else if (command.equals("logout")) {
				logout();

			}
		}
	}

	private void logout() {

		loginedUser = null;
		System.out.println("로그아웃 되셨습니다.");

	}

	private void login() {
		boolean isSuccessLogin = false;

		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getLoginId().equals(loginId)) {
				if (m.getLoginPw().equals(loginPw)) {
					loginedUser = m;
					m.welcome();
					isSuccessLogin = true;
				}
			}
		}

		if (!isSuccessLogin) {
			System.out.println("잘못된 회원정보 틀림");
		}

	}

	private void signup() {

		System.out.print("1. 우수회원, 2. 일반회원");
		int memberFlag = Integer.parseInt(sc.nextLine());

		System.out.print("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		System.out.print("닉네임을 입력해주세요 : ");
		String nickname = sc.nextLine();

		Member m = null;

		if (memberFlag == 1) {
			m = new SpecialMember(loginId, loginPw, nickname, 0);
		} else {
			m = new GeneralMember(loginId, loginPw, nickname);
		}

		members.add(m);
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void read() {
		System.out.print("상세보기할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		int index = getIndexByAritlceNo(no);
		Article a = articles.get(index);
		if (index != -1) {
			readPost(a);
			readProcess(a);
		} else {
			System.out.println("없는 게시물입니다.");
		}

	}

	public void readPost(Article a) {
		System.out.println("==== " + a.getNo() + "번 게시물 ====");
		System.out.println("번호 : " + a.getNo());
		System.out.println("제목 : " + a.getTitle());
		System.out.println("-------------------");
		System.out.println("내용 : " + a.getBody());
		System.out.println("-------------------");
		System.out.println("작성자 : 익명");
		System.out.println("등록날짜: " + a.getRegDate());
		System.out.println("======= 댓글 =======");
		if (comments.size() == 0) {
			System.out.println("****등록된 댓글이 없습니다.****");
		}
		for (int i = 0; i < comments.size(); i++) {
			Comment c = comments.get(i);
			if (c.getPostNo() == a.getNo()) {
				System.out.println("내용: " + c.getBody());
				System.out.println("작성자: " + c.getWriter());
				System.out.println("작성일: " + c.getRegDate());
				System.out.println("===================");
			} else {
				System.out.println("****등록된 댓글이 없습니다.****");
			}
		}
		int likeNum = 0;
		int likedNum = userLiked(a.getNo());
		
		if (likedNum == -1) {
			System.out.print("🤍");
		} else {
			System.out.print("🖤");
		}
		for(int i = 0; i < likes.size(); i++) {
			Like l = likes.get(i);
			if(l.getPostNo() == a.getNo()) {
				likeNum++;
			}
		}
		System.out.println(likeNum);
	}

	// 상세보기 기능
	private void readProcess(Article a) {

		while (true) {
			System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int rcmd = Integer.parseInt(sc.nextLine());

			if (rcmd == 1) {
				System.out.println("[댓글 등록 기능 구현]");
				if (loginCheck()) {
					commentAdd(a.getNo());
					readPost(a);
				} else {
					System.out.println("로그인이 필요한 기능입니다.");
				}
			} else if (rcmd == 2) {
				System.out.println("[좋아요]");
				if (loginCheck()) {
					like(a.getNo());
					readPost(a);
				} else {
					System.out.println("로그인이 필요한 기능입니다.");
				}
			} else if (rcmd == 3) {
				System.out.println("[수정]");
			} else if (rcmd == 4) {
				System.out.println("[삭제]");
			} else if (rcmd == 5) {
				break;
			}
		}

	}

	private void like(int postNo) {
		String regDate = getCurrentData();
		
		int index = userLiked(postNo);
		if(index == -1) {
			Like l = new Like(postNo, loginedUser.getLoginId(), regDate);
			likes.add(l);
			System.out.println(loginedUser.getLoginId());
			System.out.println("좋아요완료");
			System.out.println(postNo);
			System.out.println(index);
			System.out.println(loginedUser.getLoginId());
		}
		else {
			for(int i = 0; i < likes.size(); i++) {
				Like l = likes.get(i);
				if(l.getPostNo() == postNo) {
					if(l.getLikedUser() == loginedUser.getLoginId()) {
						likes.remove(i);
						System.out.println("좋아요 해제");
					}
				}
			}
			
		}
	}

	private int userLiked(int postNo) {
		
		int index = -1;
		
		for(int i = 0; i < likes.size(); i++) {
			Like l = likes.get(i);
			if(l.getLikedUser() == loginedUser.getLoginId() && l.getPostNo() == postNo){
				index = i;
				break;
			}
		}
		
		return index;

	}

	public void commentAdd(int postNo) {
		System.out.print("댓글을 입력해주세요.: ");
		String comment = sc.nextLine();

		String regDate = getCurrentData();
		Comment c = new Comment(postNo, comment, loginedUser.getNickname(), regDate);
		comments.add(c);

		System.out.println("댓글 등록이 완료되었습니다.");
	}

	// 함수 -> 기능
	// 코드 재활용
	// 코드의 구조화 -> 집중
	// 코드가 깔끔 -> 가독성이 올라간다.
	// ===========================================================
	// 검색 키워드로 검색하기
	public void search() {
		System.out.print("검색 키워드 입력 : ");
		String keyword = sc.nextLine();

		ArrayList<Article> searchedList = new ArrayList<>();
		// 번호로 찾기
		for (int i = 0; i < articles.size(); i++) {
			Article a = articles.get(i);
			if (a.getTitle().contains(keyword)) {
				searchedList.add(a);
			}
		}

		list(searchedList);
	}

	// ===========================================================
	// 번호로 게시물 인덱스 찾는 함수
	public int getIndexByAritlceNo(int no) {

		int index = -1; // 0이 아닌 이유 : 값이 없을 경우 없다는 것을 표현하기 위함. 0은 인덱스로서 의미를 가지니까

		for (int i = 0; i < articles.size(); i++) {

			Article a = articles.get(i);

			if (no == a.getNo()) {
				index = i;
				break;
			}
		}

		return index;
	}

	// ===========================================================
	// 게시물을 삭제하는 함수
	public void delete() {
		System.out.println("삭제할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		int index = getIndexByAritlceNo(no);

		if (index != -1) {
			articles.remove(index);
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ===========================================================
	// 게시물을 수정해주는 함수
	public void update() {
		System.out.println("수정할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());

		int index = getIndexByAritlceNo(no);

		if (index != -1) {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();

			Article a = articles.get(index);
			a.setTitle(title);
			a.setBody(body);

			articles.set(index, a);

		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ===========================================================
	// 게시물 목록을 보여주는 함수
	public void list(ArrayList<Article> articleList) {

		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			System.out.println("번호 : " + a.getNo());
			System.out.println("제목 : " + a.getTitle());
			System.out.println("작성자 : " + a.getWriter());
			System.out.println("작성일 : " + a.getRegDate());
			System.out.println("조회수 : " + 0);
			System.out.println("===============");
		}

	}

	// ===========================================================
	// 게시물 추가하는 함수
	public void add() {

		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();

		// 현재 시간 구해서 등록.
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		String regDate = getCurrentData();
		Article a = new Article(articleNo, title, loginedUser.getNickname(), body, regDate);
		articles.add(a);

		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	// ===========================================================
	// 도움말 출력 함수
	public static void printHelp() {
		System.out.println("========================");
		System.out.println("help : 도움말");
		System.out.println("add : 데이터 추가");
		System.out.println("read : 데이터 조회");
		System.out.println("update : 데이터 수정");
		System.out.println("delete : 데이터 삭제");
		System.out.println("exit : 프로그램 종료");
		System.out.println("========================");
	}

	public void makeTestData() {
		setTestData("안녕하세요", "반갑습니다");
		setTestData("하이~", "냉무");
		setTestData("가입인사드립니다.", "잘부탁드립니다.");
	}

	public void setTestData(String title, String body) {

		String regDate = getCurrentData();
		Article a = new Article(articleNo, title, "홍길동", body, regDate);

		articles.add(a);
		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	public String getCurrentData() {
		LocalDate now = LocalDate.now();

		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		// 포맷 적용
		String formatedNow = now.format(formatter);

		return formatedNow;

	}

	public boolean loginCheck() {

		if (loginedUser != null) {
			return true;
		} else {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}
	}
}