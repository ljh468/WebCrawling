package poly.persistance.mapper;

import config.Mapper;
import poly.dto.NewsDTO;

@Mapper("NewsMapper")
public interface INewsMapper {
	
	// 수집된 내용 DB에 등록
	int InsertNewsInfo(NewsDTO nDTO) throws Exception;
	
}
