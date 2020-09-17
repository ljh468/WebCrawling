package poly.util;

public class EncryptTest {
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("-----------------------------");
		System.out.println("해시 암호화 알고리즘");
		// 암호화 문자열 
		String str = "2020110020_이재훈";
		System.out.println("암호화 할 문자열 : " + str);
		// 복호화가 불가능한 해시암호화 알고리즘 실행
		String hashEnc = EncryptUtill.encHashSHA256(str);
		
		// 해기 암호화 알고리즘 결과 출력
		System.out.println("hashEnc : " + hashEnc);
		
		System.out.println("----------------------------");
		System.out.println("AES128-CBC 암, 복호화 알고리즘");
		System.out.println("암호화 할 문자열 : " + str);
		// AES128-CBC 암호화 알고리즘 실행
		String enc = EncryptUtill.encAES128CBC(str);
		
		// AES128-CBC 암호화 알고리즘 결과 출력
		System.out.println("enc : " + enc);
		
		// AES128-CBC 복호화 알고리즘 실행
		String dec = EncryptUtill.decAES128CBC(enc);
		
		// AES128-CBC 복호화 알고리즘 결과 출력
		System.out.println("dec : " + dec);
		
		System.out.println("----------------------------");
	}
}
