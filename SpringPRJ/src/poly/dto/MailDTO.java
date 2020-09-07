package poly.dto;

/**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 */
public class MailDTO {

	String toMail; // 받는사람
	String title; // 보내는 메일 제목
	String contents; // 보내는 메일 내용
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String content) {
		this.contents = content;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	

}
