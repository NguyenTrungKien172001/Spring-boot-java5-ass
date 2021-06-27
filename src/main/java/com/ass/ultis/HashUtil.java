package com.ass.ultis;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
	//ma hoa pw
	public static String hash(String plain) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(plain, salt);
	}
	
	
	//check pw
	public static boolean verify(String plain, String hashed) {
		return BCrypt.checkpw(plain, hashed);
	}
}
