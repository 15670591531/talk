package org.docryze.talk.talkutil.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Json 工具类
 */
public class JsonUtil {

    /**
     * 解析json字符串为JSONObject对象
     * @param jsonStr
     * @return
     */
    public static JSONObject paser(String jsonStr){
        return JSON.parseObject(jsonStr);
    }

    /**
     * 将对象转化为JSon字符串
     * @param o
     * @return
     */
    public static String toJsonString(Object o){
        return JSON.toJSONString(o);
    }
}
