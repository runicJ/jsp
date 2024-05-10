package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	public String encryptSHA256(String str){  // 암호화 하겠다 encryptSHA256
    String sha = "";
    try{
       MessageDigest sh = MessageDigest.getInstance("SHA-256");  // 자리수에 관계없음  // sha-256은 복호화 불가
//       int salt = (int)(Math.random()*100) + 1;  // salt key(DB에 저장) | 비밀번호 => salt키가 같으면 항상 같은 값이 나옴 // 100~999
//       str += salt;
       
       sh.update(str.getBytes());
       byte byteData[] = sh.digest();
       StringBuffer sb = new StringBuffer();
       for(int i = 0 ; i < byteData.length ; i++){
           sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
       }
       sha = sb.toString();
   }catch(NoSuchAlgorithmException e){
       System.out.println("Encrypt Error - NoSuchAlgorithmException");
       sha = null;
   }
   return sha;
 } 
}
