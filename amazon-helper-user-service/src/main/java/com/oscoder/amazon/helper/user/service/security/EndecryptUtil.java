package com.oscoder.amazon.helper.user.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author
 * @create 2020-04-30 01:03
 **/

public class EndecryptUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(EndecryptUtil.class);
	
	private static final String CHARSET_NAME = "utf-8";
	
	private static final String KEY_ALGORITHM = "AES";
	/**
	 * 默认的加密算法
	 * */
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	
	/**
	 * AES 加密操作
	 *
	 * @param content 待加密内容
	 * @param key 加密密钥
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			
			byte[] byteContent = content.getBytes(CHARSET_NAME);
			
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
			
			byte[] result = cipher.doFinal(byteContent);
			//通过Base64转码返回
			return Base64Utils.encodeToString(result);
		} catch (Exception ex) {
			LOGGER.error("encrypt error for {}",content, ex);
		}
		return null;
	}
	
	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param key
	 * @return
	 */
	public static String decrypt(String content, String key) {
		try {
			//实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			
			//使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
			
			//执行操作
			byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
			
			return new String(result, CHARSET_NAME);
		}catch (Exception e){
			LOGGER.error("decrypt error",e);
		}
		return null;
	}
	
	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String key) {
		//返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			//AES 要求密钥长度为 128
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(key.getBytes());
			kg.init(128, secureRandom);
			//生成一个密钥
			SecretKey secretKey = kg.generateKey();
			// 转换为AES专用密钥
			return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		} catch (Exception ex) {
			LOGGER.error("getSecretKey error for {}",key,ex);
		}
		
		return null;
	}
}
