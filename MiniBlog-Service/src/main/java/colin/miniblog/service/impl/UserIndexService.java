package colin.miniblog.service.impl;

import colin.miniblog.core.dao.UserDao;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserIndexService;
import colin.miniblog.utils.ColinDateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joker on 16-3-12.
 */
@Service
public class UserIndexService implements IUserIndexService {

    @Autowired
    private UserDao userDao;

    /**
     * 驗證用戶登錄
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    @Cacheable(value = "customCache",key = "'login'+#username")
    public UserInfo userLoginService(String username, String password) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("username",username);
        params.put("pwd",DigestUtils.md5Hex(password));
        List<UserInfo> userInfos= userDao.validateUserInfo(params);
        if (userInfos!=null&&userInfos.size()==1){
            return userInfos.get(0);
        }else {
            return null;
        }

    }

    /**
     * 用戶註冊
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    @CacheEvict(value = "customCache",allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public UserInfo userRegisterService(String username, String password) {
        if(validateUserLogin(username,password)){
            return null;
        }
        //初始化用户的信息
        UserInfo userInfo=initUserRegisterInfo(username,password);
        //插入用户的信息
        int result= userDao.insertUser(userInfo);
        if(result==1){
            return userInfo;
        }else{
            return null;
        }
    }

    /**
     * 初始化用户信息
     * @param username
     * @param password
     * @return
     */
    private UserInfo initUserRegisterInfo(String username,String password){
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
        userInfo.setPwd(DigestUtils.md5Hex(password));
        //设定用户名
        userInfo.setUsername(username);
        //设定用户角色
        userInfo.setRole(0);
        return userInfo;
    }
    /**
     * 用戶信息更新
     *
     * @param userInfo
     * @return
     */
    @Override
    @CacheEvict(key = "customCache",allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean userUpdateService(Map<String, Object> userInfo) {
        if (userInfo==null||userInfo.isEmpty()||userInfo.get("id")==null){
            return false;
        }else{
           int result= userDao.updateUserInfo(userInfo);
            if (result==1){
                return true;
            }else {
                return false;
            }
        }

    }

    /**
     * 根據id刪除用戶
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(key = "customCache",allEntries = true)
    public boolean userDeleteService(int id) {
        if (id<=0){
            return false;
        }else {
            int result=userDao.delUser(id);
            if (result==1){
                return true;
            }else{
                return false;
            }

        }

    }

    /**
     * 验证用户的登录信息
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean validateUserLogin(String username, String password) {
        UserInfo userInfo=this.userLoginService(username,password);
        return userInfo!=null?true:false;
    }
}
