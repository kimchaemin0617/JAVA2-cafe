package board;

import java.util.ArrayList;

public class Article {
	private int no;
	private String title;
	private String writer;
	private String body;
	private String regDate;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	Article(int no, String title, String writer, String body, String regDate) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.body = body;
		this.regDate = regDate;
	}
}