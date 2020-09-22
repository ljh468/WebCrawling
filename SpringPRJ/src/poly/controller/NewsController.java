package poly.controller;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.stanford.nlp.pipeline.CoreSentence;
import poly.dto.NewsDTO;
import poly.service.INewsService;
import poly.util.NLPUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class NewsController {

	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name = "NewsService")
	private INewsService newsService;

	/**
	 * 뉴스 수집 URL 호출
	 */

	@RequestMapping(value = "/Today/clawlingNews")
	public String getNewsInfoFromWEB(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB Start!");

		int res = newsService.getNewsInfoFromWEB();
		
		// 뉴스 결과 넣어주기
		model.addAttribute("res", String.valueOf(res));

		log.info(this.getClass().getName() + ".getNewsInfoFromWEB End!");

		return "/Today/clawlingNews";

	}
	
	@RequestMapping(value="/Today/viewNews")
	public String getNewsInfoFromDB(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	throws Exception {
		
		log.info(this.getClass().getName() + "./Today/viewNews Start!");
		
		String news_no = "1";
		
		NewsDTO nDTO = new NewsDTO();
		
		nDTO.setNews_no(news_no);
		
		nDTO = newsService.getNewsInfoFromDB(nDTO);
		
		Iterator<CoreSentence> it = NLPUtil.sentence(nDTO.getNews_contents());
		
		while(it.hasNext()) {
			
			CoreSentence sent = it.next();
			
			log.info(sent.text());
			log.info(sent.tokens().get(0).originalText());
			log.info(sent.tokens().get(0).index());
			log.info(sent.lemmas());
			
		}
		
		log.info(this.getClass().getName() + "/Today/viewNews End!");
		
		return "/Today/viewNews";
		
	}
}
