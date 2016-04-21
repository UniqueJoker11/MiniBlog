package colin.miniblog.service.impl;

import colin.miniblog.core.dao.UserDao;
import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserservice;
import colin.miniblog.utils.ColinDateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by joker on 16-3-12.
 */
@Service
public class UserService implements IUserservice{

    @Autowired
    private UserDao userDao;
    /**
     * 注册用户的信息
     *
     * @param userInfoMap
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public CommonResultMap<UserInfo> userRegisterInfo(Map<String, Object> userInfoMap) {
        //初始化用户的信息
        UserInfo userInfo=initUserRegisterInfo(userInfoMap);
        //插入用户的信息
        int result= userDao.insertUser(userInfo);
        //初始化返回對象
        CommonResultMap<UserInfo> commonResultMap=null;
        if(result==1){
            commonResultMap=new CommonResultMap<>(true,userInfo);
        }else{
            commonResultMap=new CommonResultMap<>(false,"插入用戶失敗");
        }
        return commonResultMap;
    }
    public UserInfo initUserRegisterInfo(Map<String,Object> userInfoMap){
        //构建userInfo对象
        UserInfo userInfo=new UserInfo();
        //默认0个金币
        userInfo.setCoin(0);
        //设定当前的地域信息（需要结合网络信息返回当前用户的地域信息）
        userInfo.setCur_area(3);
        //用户活跃度初始化值为0
        userInfo.setDaren_tree(0);
        //设定邮箱是否有被验证（未被验证为0）
        userInfo.setEmail_validate(0);
        //暂无头像
        userInfo.setFace("");
        //初始化创建时间
        userInfo.setMtime(ColinDateUtils.generateCurrentTimestamp());
        //设定最近一次登陆时间
        userInfo.setLast_login_time(ColinDateUtils.generateCurrentDate());
        //初始化密码
        userInfo.setPwd(DigestUtils.md5Hex(userInfoMap.get("pwd").toString()));
        //设定用户名
        userInfo.setUsername(userInfoMap.get("username").toString());
        //设定用户角色
        userInfo.setRole(0);
        return userInfo;
    }
    /**
     * 用户登录信息
     *
     * @param userInfoMap
     * @return
     */
    @Override
    public CommonResultMap<UserInfo> userLoginInfo(Map<String, Object> userInfoMap) {
        List<UserInfo> userInfos=userDao.validateUserInfo(userInfoMap);
        if(userInfos!=null&&!userInfos.isEmpty()){
            return new CommonResultMap<UserInfo>(true,userInfos.get(0));
        }else {
            return new CommonResultMap<UserInfo>(false,"用户信息不正确");
        }
    }

    /**
     * 验证用户的登录信息是否正确
     *
     * @param userInfoMap
     * @return
     */
    @Override
    public boolean validateUserLogin(Map<String, Object> userInfoMap) {
        List<UserInfo> userInfos=userDao.validateUserInfo(userInfoMap);
        if(userInfos!=null&&!userInfos.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 更新用户的信息
     *
     * @param userInfoMap
     * @return
     */
    @Override
    public CommonResultMap<UserInfo> userUpdateInfo(Map<String, Object> userInfoMap) {
        return null;
    }

    /**
     * 删除用户的信息
     *
     * @param userInfoMap
     * @return
     */
    @Override
    public CommonResultMap<UserInfo> userDeleteInfo(Map<String, Object> userInfoMap) {
        return null;
    }

    /**
     * 查询用户列表
     *
     * @param userInfoMap
     * @return
     */
    @Override
    public CommonResultMap<List<UserInfo>> userListInfo(Map<String, Object> userInfoMap) {
        return null;
    }
}
