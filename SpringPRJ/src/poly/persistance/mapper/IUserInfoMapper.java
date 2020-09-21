package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("IUserInfoMapper")
public interface IUserInfoMapper {
	
	// 회원가입 하기(회원정보 등록하기)
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;

	// 회원가입 전 중복체크하기(DB조회하기)
	UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

}
