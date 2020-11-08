package poly.controller;

import static poly.util.CmmUtil.nvl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;


@Controller
public class Mailcontroller {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "MailService")
	private IMailService mailService;
	
	/**
	 *  메일 발송하기
	 */
	@RequestMapping(value="mail/mailForm")
	public String mailForm() {
		return "/mail/mailForm";
	}
	
	@RequestMapping(value="mail/sendMail", method = RequestMethod.POST)
	public String sendMail(HttpServletRequest request, ModelMap model)throws Exception {
		
		// 로그 찍기
		log.info(this.getClass().getName() + "mail.sendMail start!! ");
		
		// 웹 URL로 부터 전달받는 값들
		String toMail = nvl(request.getParameter("toMail")); // 받는사람
		String title = nvl(request.getParameter("title")); // 제목
		String contents = nvl(request.getParameter("contents")); // 내용
		
		// 메일 발송할 정보 넣기 위한 DTO객체 생성하기
		MailDTO pDTO = new MailDTO();
		
		// 웹에서 받은 값을 DTO에 넣기
		pDTO.setToMail(toMail); // 받는 사람을 DTO에 저장
		pDTO.setTitle(title); // 제목을 DTO에 저장
		pDTO.setContents(contents); // 내용을 DTO에 저장
		
		log.info("pDTO : "+ pDTO.getToMail());
		log.info("pDTO : "+ pDTO.getTitle());
		log.info("pDTO : "+ pDTO.getContents());
		
		// 메일 발송하기
		int res = mailService.doSendMail(pDTO);
		log.info("res : " + res);
		if(res == 1) { // 메일발송 성공
			log.info(this.getClass().getName() + "mail.sendMail success !!");
		}else { // 메일발송 실패
			log.info(this.getClass().getName() + "mail.sendMail fail !!");
		}
		
		// 메일 발송 결과를 JSP에 전달하기( 데이터 전달시, 숫자보단 문자열이 컨트롤하기 편리하기 때문에 강제로 숫자를 문자로 변환함)
		model.addAttribute("res", String.valueOf(res));
		
		// 로그 찍기
		log.info(this.getClass().getName() + "mail.sendMail end!! ");
		
		// 함수 처리가 끝나고 보여줄 JSP 파일명 (/WEB-INF/view/mail/sendMailResult.jsp)
		return "/mail/sendMailResult";
		
	}
	
}
