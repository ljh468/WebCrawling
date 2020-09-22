package poly.service;

import poly.dto.NewsDTO;

public interface INewsService {
	
		// 사이트에서 정보 가져오기.
		int getNewsInfoFromWEB() throws Exception;
		
		// 저장된 뉴스 가져오기
		NewsDTO getNewsInfoFromDB(NewsDTO nDTO);


}
