package test.colin.miniblog.service;

import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserservice;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.CommonSerciceTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joker on 16-3-12.
 */
public class UserServiceTest extends CommonSerciceTest{

    @Autowired
    private IUserservice userservice;

    private Map<String,Object> userMap;
    @Before
    public void initUserMap(){
        userMap=new HashMap();
        userMap.put("username","colin");
        userMap.put("password","1234");
    }
    @Test
    public void testUserRegisterInfo(){
        CommonResultMap<UserInfo> userinfo=userservice.userRegisterInfo(userMap);
        System.out.println(userinfo.isSuccess());
    }
}
