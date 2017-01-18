package cn.geekc.ssm.cache.utils;

/**
 * 
 * @author geekc
 *
 * 2016年7月11日上午10:45:20
 */
public final class MemcachedUtils {
	
	/**
	 * Memcached存储永久有效exp为0
	 */
	public final static int PERMANENT = 0;
	
	/**
	 * key的最大长度为250
	 */
	public final static int MAX_KEY_LENGTH = 250;
	
	/**
	 * 构造函数保护，不提供MemcachedUtils的实例
	 */
	protected MemcachedUtils() {
		
	}

}
