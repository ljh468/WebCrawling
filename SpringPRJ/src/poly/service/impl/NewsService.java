package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.NewsDTO;
import poly.persistance.mapper.INewsMapper;
import poly.service.INewsService;
import poly.util.CmmUtil;


@Service("NewsService")
public class NewsService implements INewsService {

	@Resource(name = "NewsMapper")
	private INewsMapper newsMapper;
	
	// 로그파일 출력개체
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Jsoup 라이브러리를 통한 bbc영어 웹크롤링
	 */
	public int getNewsInfoFromWEB() throws Exception {

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB start!!");

		int res = 0; // 크롤링 결과 (0보다 크면 크롤링 성공)

		// 코리아헤럴드 사이트 (https:// 는 보안때문에 불가)
		String url = "http://www.bbc.com/news/world";

		// JSOUP 라이브러리를 통해 사이트에 접속되면, 그 사이트 전체의 HTML 소스를 저장할 변수
		Document doc = null;

		// 사이트 접속
		doc = Jsoup.connect(url).get();

		// 사이트에 접속하여 전체 기사 중 메인 기사를 찾아 들어가야 함.

		// 웹 페이지 전체 소스 중 일부 태그를 선택하기 위해 사용
		// 메인페이지의 url을 가져오기 위함
		Element element_urlGet = doc.select("a.gs-c-promo-heading").first();

		// element_urlGet 소스에 href를 가져옴
		String href = element_urlGet.attr("href");
		
		log.info("add : " + element_urlGet);
		log.info("href : " + href);
		
		// 기사 링크로 들어가기
		doc = Jsoup.connect("http://www.bbc.com"+href).get();

		// 뉴스의 제목
		Element element_title = doc.select("#main-heading").first();
		String news_title = CmmUtil.nvl(element_title.text().trim().toString());
		log.info(news_title);
		// <div class="view_tit_byline_l"><a
		// href="/search/list_name.php?byline=Ock+Hyun-ju">By Ock Hyun-ju</a></div>
//		// 뉴스의 기자
//		Elements element_authorGet = doc.select("div.view_tit_byline_l");
//		String element_author = element_authorGet.attr("a");
//
//		// <div class="view_tit_byline_r">Published : Aug 26, 2020 - 15:44
//		// &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Updated : Aug 26, 2020 - 20:36</div>z
//		// 뉴스의 날짜
//		Elements element_date = doc.select("dd.e1ojgjhb2");
//		// split으로 앞의 불필요한 문자들을 삭제 후 삽입할 것.

		// <div class="view_con_t"> 뉴스의 내용
		Elements element_contents = doc.select("div.e1xue1i82");
		String news_contents = CmmUtil.nvl(element_contents.next().text().trim().toString());
		log.info(news_contents);
		
		
		NewsDTO nDTO = null;
		
		// 수집된 데이터 DB에 저장
		nDTO = new NewsDTO();
		nDTO.setNews_no("1");
		nDTO.setNews_title(news_title);	
		nDTO.setNews_contents(news_contents);
		nDTO.setNews_date("");
		
		res += newsMapper.InsertNewsInfo(nDTO);
		log.info("nDTO : 1");
		log.info("nDTO : " + nDTO.getNews_title());
		log.info("nDTO : " + nDTO.getNews_contents());
		log.info("nDTO : date");
		log.info(this.getClass().getName() + ".getNewsInfoFromWEB end!");

	
		return res;
	}
}
