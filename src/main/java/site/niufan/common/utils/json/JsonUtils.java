package site.niufan.common.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import site.niufan.common.utils.log.LogUtils;
import site.niufan.common.utils.springframework.ApplicationContextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON 工具
 * @author Fan Niu
 * @since 2018/6/1
 */
public class JsonUtils {

    private static volatile ObjectMapper objectMapper;
    private static final Object objectMapperMonitor = new Object();

    private JsonUtils(){}

    /**
     * 将一个对象转为JSON字符串
     * @param object 对象，可以是集合
     * @return String JSON字符串
     */
    public static String toJson(Object object){
        String result;
        try {
            result = getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 将JSON字符串转为目标对象
     * @param json JSON字符串，必须为JSON Object的字符串，不能是JSON ARRAY的字符串
     * @param clazz 目标对象字节码
     * @param <T> 目标对象类型
     * @return 目标对象
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        T result;
        try {
            result = getObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 将JSON字符串转为MAP
     * @param json JSON字符串，必须为JSON Object的字符串，不能是JSON ARRAY的字符串
     * @return Map
     */
    public static Map fromJsonToMap(String json){
        Map result;
        try {
            result = getObjectMapper().readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 将JSON字符串转为目标对象
     * @param json JSON字符串，必须为 JSON ARRAY 的字符串，不能是 JSON Object 的字符串
     * @param clazz 目标对象字节码
     * @param <T> 目标对象类型
     * @return 目标对象List集合
     */
    public static <T> List<T> fromJsonToArrayList(String json, Class<T> clazz){
        List<T> result;
        try {
            List list = getObjectMapper().readValue(json, List.class);
            result = new ArrayList<T>();
            for(Object object : list){
                result.add(getObjectMapper().readValue(toJson(object), clazz));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 返回jackson的ObjectMapper
     * @return com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper getObjectMapper(){
        if(objectMapper == null){
            synchronized (objectMapperMonitor) {
                if(objectMapper == null){
                    initObjectMapper();
                }
            }
        }
        return objectMapper;
    }

    private static void initObjectMapper() {
        try {
            objectMapper = ApplicationContextUtils.getBean(ObjectMapper.class);
        } catch (Exception e) {
            LogUtils.warn(LogUtils.getLogger(JsonUtils.class), "Get Spring ObjectMapper error.");
        }
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }
}
