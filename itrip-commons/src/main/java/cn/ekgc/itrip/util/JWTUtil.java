package cn.ekgc.itrip.util;

import cn.ekgc.itrip.util.constant.SystemConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>JWT工具类，用于生成令牌和校验</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class JWTUtil {

	private static Algorithm algorithm = Algorithm.HMAC256(SystemConstant.SECRET_KEY);

	/**
	 *<b>生成令牌</b>
	 * @param id 当前用户主键
	 * @return
	 * @throws Exception
	 */
	public static String createToken(Long id)throws Exception{
		//创建JWTCreator.builder对象，用于创建token
		JWTCreator.Builder builder = JWT.create();

		//创建一个map集合，用于存储JWT的头部信息
		Map<String,Object> header = new HashMap<String,Object>();
		//设定加密算法
		header.put("alg","HS256");
		//设定token类型
		header.put("type", "JWT");
		builder.withHeader(header);
		//设定有效载荷
		builder.withClaim("id", id);
		//为前端设置过期时间30分钟
		Long expire = new Date().getTime() + (30 * 60 * 1000);
		builder.withClaim("expire", new Date(expire));
		//使用算法进行签名，生成最终的字符串
		return builder.sign(algorithm);
	}

	/**
	 * <b>解密token令牌</b>
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Long validateToken(String token)throws Exception{
		if(token != null && !"".equals(token)){
			try {
				//通过加密算法解密
				JWTVerifier verifier = JWT.require(algorithm).build();
				//进行解密校验
				DecodedJWT decodedJWT = verifier.verify(token);
				//当没有异常信息产生时，说明token备成功识别，获得相关数据
				return decodedJWT.getClaim("id").asLong();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return -1L;
	}

}
