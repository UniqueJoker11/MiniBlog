package colin.miniblog.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joker on 16-3-14.
 */
public class ColinCollectionsUtils {
    /**
     * 获取对象的Map对象格式
     * @param args
     * @return
     */
    public static Map<String,Object> initParamsMap(String[] args){
        Map<String,Object> params=new HashMap<String,Object>();
        for(String arg:args){
            try{
                String[] argArray=arg.split("\\.");
                params.put(argArray[0],argArray[1]);
            }catch (Exception e){
              e.printStackTrace();
              throw new IllegalArgumentException("传入的参数格式不正确");
            }
        }
        return params;
    }

    /**
     * 打印出对象的json格式
     * @param obj
     */
    public static void showObjContent(Object obj) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            objectMapper.writeValue(System.out,obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
