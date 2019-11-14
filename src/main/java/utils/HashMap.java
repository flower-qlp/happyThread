package utils;

import java.util.Collections;
import java.util.Map;

/***
 * @author happy
 * 线程安全使用hashmap
 * */
public class HashMap {

    /**
     * 使用map数据的时候需要先获取该map的锁,并发高会导致其他的线程阻塞
     **/
    public static Map m = Collections.synchronizedMap((Map) new HashMap());

    public static void main(String[] args) {


    }


}
