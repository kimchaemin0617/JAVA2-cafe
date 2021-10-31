package board;

public class Comment {
	private int postNo; 
	private String body;
	private String writer;
	private String regDate;
	
	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int parantNo) {
		this.postNo = parantNo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Comment(int postNo, String body, String writer, String regDate) {
		super();
		this.postNo = postNo;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
}