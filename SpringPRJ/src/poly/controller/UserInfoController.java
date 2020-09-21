package poly.controller;

import static poly.util.CmmUtil.nvl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserInfoDTO;
import poly.service.IUserInfoService;
import poly.util.EncryptUtill;

/* 
 * Contoroller 선언해야만 Spring 프레임워크에서 Controller인지 인식가능
 * 자바 서블릿 역할 수행
 */
@Controller
public class UserInfoController {
	private Logger log = Logger.getLogger(this.getClass());
	
	/*
	 * 비즈닛 로직( 중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 */
	@Resource(name="UserInfoService")
	private IUserInfoService userInfoService;
	
	/**
	 * 회원가입 화면으로 이동
	 * 
	 */
	@RequestMapping(value="user/userRegForm")
	public String userRegForm() {
		log.info(this.getClass() + "/user/uesrRegForm ok!");
		
		return "/user/UserRegForm";
	}
	
	@RequestMapping(value="user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception{
		
		log.info(this.getClass().getName() + "/insertUserInfo start!");;
		
		//회원가입 결과에 대한 메시지를 전달할 변수
		String msg ="";
		
		// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;
		
		try {
			/*
			 * #####################################
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * #####################################
			 */
			String user_id = nvl(request.getParameter("user_id")); // 아이디
			String user_name = nvl(request.getParameter("user_name")); // 이름
			String password = nvl(request.getParameter("password")); // 비밀번호
			String email = nvl(request.getParameter("email")); // 이메일
			String addr1 = nvl(request.getParameter("addr1")); // 주소
			String addr2 = nvl(request.getParameter("addr2")); // 상세주소
			/*
			 * #####################################
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * #####################################
			 */
			log.info("user_id : " + user_id);
			log.info("user_name : " + user_name);
			log.info("password : " + password);
			log.info("email : " + email);
			log.info("addr1 : " + addr1);
			log.info("addr2 : " + addr2);
			
			/*
			 * ####################################
			 * 웹(회원정보 입력화면) 에서 받는 정보를 DTO에 저장하기 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 함
			 */
			
			// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수 메모리에 올리기
			pDTO = new UserInfoDTO();
			
			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			pDTO.setPassword(EncryptUtill.encHashSHA256(password));
			// 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
			pDTO.setEmail(EncryptUtill.encAES128CBC(email));
			// 민감 정보인 이메일은 AES-128-CBC로 암호화함
			pDTO.setAddr1(addr1);
			pDTO.setAddr2(addr2);
			
			log.info("pDTO0 : " + pDTO.getUser_id());
			log.info("user_name : " + pDTO.getUser_name());
			log.info("password : " + pDTO.getPassword());
			log.info("email : " + pDTO.getEmail());
			log.info("addr1 : " + pDTO.getAddr1());
			log.info("addr2 : " + pDTO.getAddr2());
			/*
			 * ####################################
			 * 웹(회원정보 입력화면) 에서 받는 정보를 DTO에 저장하기 끝!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 함
			 */
			
			/*
			 * 회원가입
			 */
			int res = userInfoService.inserUserInfo(pDTO);
			
			log.info("res : " + res);
			if(res==1) {
				msg = "회원가입되었습니다.";
				
			// 추후 회원가입 입력화면에서 ajax를 활용해서 아이디중복, 이메일 중복을 체크하길 바람
			}else if(res==2) {
				msg = "이미 가입된 이메일 주소입니다.";
			}else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
			}
		}catch(Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			msg= "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		}finally {
			log.info(this.getClass().getName() + ".insertUserInfo end !! ");
			
			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("msg",msg);
			
			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("pDTO", pDTO);
			
			// 변수 초기화 (메모리 효율화 시키기 위해 사용함)
			pDTO = null;
		}
		
		return "/user/Msg";
	}
	
}
