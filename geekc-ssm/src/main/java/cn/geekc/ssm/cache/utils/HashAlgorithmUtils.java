package cn.geekc.ssm.cache.utils;


import net.spy.memcached.DefaultHashAlgorithm;

/**
 * Hash算法工具类
 * @author geekc
 *
 * 2016年7月11日上午10:45:44
 */
public final class HashAlgorithmUtils {

	/**
	 * 构造函数保护，不提供HashAlgorithmUtils的实例
	 */
	protected HashAlgorithmUtils() {

	}

	/**
	 * 用KETAMA_HASH算法计算出key的hash值并返回
	 * 
	 * @param key
	 * @return
	 */
	public static String hash(String key) {
		long rv = 0;
		byte[] bKey = DefaultHashAlgorithm.computeMd5(key);
		rv = ((long) (bKey[3] & 0xFF) << 24) 
				| ((long) (bKey[2] & 0xFF) << 16) 
				| ((long) (bKey[1] & 0xFF) << 8)
				| (bKey[0] & 0xFF);
		return String.valueOf(rv & 0xffffffffL);
	}

}
