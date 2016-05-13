package test.colin.miniblog.core;

import colin.miniblog.core.dao.UserDao;
import colin.miniblog.core.pojo.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.beetl.ext.fn.Json;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/10
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.dao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-datasource.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    Map<String,Object> params=new HashMap<String,Object>();

    @Before
    public void initUser(){
        params.put("username", "colin");
        params.put("pwd", DigestUtils.md5Hex("1234"));
        params.put("sign","我的签名");
        params.put("role",1);
        params.put("daren_tree","hello");
        params.put("nickname", "colin");
        params.put("sex",0);
        params.put("email_validate",1);
        params.put("is_del",0);
        params.put("face","wode touxiang");
        params.put("coin",200);
        params.put("cur_area",10);
        params.put("mtime", Timestamp.valueOf("2016-03-12 12:23:23"));
    }
   /* @Test
    public void testInsertUser(){

    }*/
    @Test
    public void testValidateUserInfo(){
      List<UserInfo> result= userDao.validateUserInfo(params);
        ObjectMapper om=new ObjectMapper();
        try {
            System.out.println(om.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
