package poly.service;

import java.util.Map;

import poly.dto.AccStatDTO;

public interface IGetAccStatService {
	
	/**
	 * OpenAPI 서버로 부터 전달받는 JSON 데이터
	 * 
	 * 교통사고건수 가져오기
	 */
	Map<String, Object> getAccStatForJSON(AccStatDTO pDTO) throws Exception;
}
