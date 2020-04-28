package com.ycjung.dto;

import java.sql.Timestamp;
import java.util.Date;

public class DTO {
	private int bCd;
	private String bTitle;
	private String bAuthor; 
	private Timestamp bDate;
	private String bContent;
	private int bHits; 
	private int bGroup;
	private int bStep;
	private int bIndent;
	
	
	public DTO() { }
	
	public DTO(int bCd, String bTitle, String bAuthor, Timestamp bDate, String bContent, int bHits, int bGroup,
			int bStep, int bIndent) {
		this.bCd = bCd;
		this.bTitle = bTitle;
		this.bAuthor = bAuthor;
		this.bDate = bDate;
		this.bContent = bContent;
		this.bHits = bHits;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}
	public int getbCd() {
		return bCd;
	}
	public void setbCd(int bCd) {
		this.bCd = bCd;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbAuthor() {
		return bAuthor;
	}
	public void setbAuthor(String bAuthor) {
		this.bAuthor = bAuthor;
	}
	public Timestamp getbDate() {
		return bDate;
	}
	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbHits() {
		return bHits;
	}
	public void setbHits(int bHits) {
		this.bHits = bHits;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	@Override
	public String toString() {
		return "DTO [bCd=" + bCd + ", bTitle=" + bTitle + ", bAuthor=" + bAuthor + ", bDate=" + bDate + ", bContent="
				+ bContent + ", bHits=" + bHits + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent=" + bIndent
				+ "]";
	}
	
	
}
