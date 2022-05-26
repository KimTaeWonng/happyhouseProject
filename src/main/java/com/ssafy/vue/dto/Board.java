package com.ssafy.vue.dto;


public class Board {
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private int hit;
	private String regtime;
	private int comment;
	
	
	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int commentCnt) {
		this.comment = commentCnt;
	}

	public Board(int articleno, String userid, String subject, String content, int hit, String regtime, int comment) {
		super();
		this.articleno = articleno;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.regtime = regtime;
		this.comment = comment;
	}
	
	

}