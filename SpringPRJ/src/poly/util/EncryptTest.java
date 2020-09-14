package poly.util;

public class EncryptTest {
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("-----------------------------");
		System.out.println("해시 암호화 알고리즘");
		// 암호화 문자열 
		String str = "2020110020_이재훈";
		
		// 복호화가 불가능한 해시암호화 알고리즘 실행
		String hashEnc = EncryptUtill.encHashSHA256(str);
		
		// 해기 암호화 알고리즘 결과 출력
		System.out.println("hashEnc : " + hashEnc);
		
		System.out.println("----------------------------");
	}
}
