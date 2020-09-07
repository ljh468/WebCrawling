package poly.dto;

/**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 */
public class NewsDTO {

	private String news_title; // 뉴스제목
	private String news_contents; // 뉴스내용
	
	
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_contents() {
		return news_contents;
	}
	public void setNews_contents(String news_contents) {
		this.news_contents = news_contents;
	}
	

}
