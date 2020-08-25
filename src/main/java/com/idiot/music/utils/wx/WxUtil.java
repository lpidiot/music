package com.idiot.music.utils.wx;
import com.alibaba.fastjson.JSONObject;
import com.idiot.music.utils.HttpRequest;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

public class WxUtil {
	private static String appid = "wx25f3f3a84e45348d";// 小程序唯一标识 (在微信小程序管理后台获取)
	private static String secret = "baca523e58ca5c4e58210c4187b418eb";// 小程序的 app secret (在微信小程序管理后台获取)
	
	/**
	 * 获取openid
	 * 
	 * @param code
	 * @return
	 */
	public static Map<String, Object> getUserData(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 登录凭证不能为空
		if (code == null || code.length() == 0) {
			map.put("status", 0);
			map.put("msg", "code 不能为空");
			return map;
		}
		// 登录凭证 code 获取 session_key 和 openid
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("secret", secret);
		params.put("js_code", code);
		params.put("grant_type", "authorizattion_code");
		// 发送请求
		String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
		// 解析相应内容（转换成json对象）
		// .fromObject(sr);
		JSONObject json = JSONObject.parseObject(sr);
		// 获取会话密钥（session_key）
		String session_key = json.get("session_key").toString();
		map.put("sessionkey", session_key);
		// 用户的唯一标识（openid）
		String openid = (String) json.get("openid");
		map.put("openId", openid);
		return map;
	}
	
	public static Object getPhone(String code, String encryptedData, String iv){
		String result = "";
		try {
			AES aes = new AES();
			Map<String, Object> map = getUserData(code);
			String sessionKey = map.get("sessionkey").toString();
		    byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));  
		    if(null != resultByte && resultByte.length > 0){
		        result = new String(WxPKCS7Encoder.decode(resultByte));
	        }  
		} catch (Exception e) {
			result = "";
			e.printStackTrace();
			return "error";
		}
		return result;
	}
}
