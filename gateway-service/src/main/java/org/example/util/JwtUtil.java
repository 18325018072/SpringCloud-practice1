package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import java.util.Map;

public class JwtUtil {

	private static final String SECRET = "suib2w";

	public static boolean verifyJwt(String json) {

		Jwt jwt = JwtHelper.decodeAndVerify(json.substring("bearer".length()).trim(), new MacSigner(SECRET));
		try {
			Map claimsMap = new ObjectMapper().readValue(jwt.getClaims(), Map.class);
			System.out.println(claimsMap.get("exp"));
			System.out.println(claimsMap.get("iat"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
