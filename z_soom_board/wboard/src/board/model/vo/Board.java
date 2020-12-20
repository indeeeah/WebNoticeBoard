package board.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Board {

//	bno number,
//	bsubject varchar2(300),
//	bcontent varchar2(1800),
//	bdate TIMESTAMP(6),
//	bcnt number,
//	bwriter varchar2(60),
//	bwriter_snd varchar2(60),
//	bfilepath varchar2(1800),
//	bpwd varchar2(30),
//	bre_step number,
//	bre_level number);
	private int bno;
	private String bsubject;
	private String bcontent;
	private Timestamp bdate;
	private int bcnt;
	private String bwriter;
	private String bwriter_snd;
	private String bfilepath;
	private String bpwd;
	private int bre_step;
	private int bre_level;
	private int bref;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBsubject() {
		return bsubject;
	}

	public void setBsubject(String bsubject) {
		this.bsubject = bsubject;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public Timestamp getBdate() {
		return bdate;
	}

	public void setBdate(Timestamp bdate) {
		this.bdate = bdate;
	}

	public int getBcnt() {
		return bcnt;
	}

	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBwriter_snd() {
		return bwriter_snd;
	}

	public void setBwriter_snd(String bwriter_snd) {
		this.bwriter_snd = bwriter_snd;
	}

	public String getBfilepath() {
		return bfilepath;
	}

	public void setBfilepath(String bfilepath) {
		this.bfilepath = bfilepath;
	}

	public String getBpwd() {
		return bpwd;
	}

	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}

	public int getBre_step() {
		return bre_step;
	}

	public void setBre_step(int bre_step) {
		this.bre_step = bre_step;
	}

	public int getBre_level() {
		return bre_level;
	}

	public void setBre_level(int bre_level) {
		this.bre_level = bre_level;
	}

	public int getBref() {
		return bref;
	}

	public void setBref(int bref) {
		this.bref = bref;
	}
	
	

}