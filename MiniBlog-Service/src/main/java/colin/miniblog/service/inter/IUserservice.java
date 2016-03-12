package colin.miniblog.service.inter;

import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by joker on 16-3-12.
 */
public interface IUserservice {

    /**
     * 注册用户的信息
     * @param userInfoMap
     */
    public CommonResultMap<UserInfo> userRegisterInfo(Map<String,Object> userInfoMap);

    /**
     * 用户登录信息
     * @param userInfoMap
     * @return
     */
    public CommonResultMap<UserInfo> userLoginInfo(Map<String,Object> userInfoMap);

    /**
     * 更新用户的信息
     * @param userInfoMap
     * @return
     */
    public CommonResultMap<UserInfo> userUpdateInfo(Map<String,Object> userInfoMap);

    /**
     * 删除用户的信息
     * @param userInfoMap
     * @return
     */
    public CommonResultMap<UserInfo> userDeleteInfo(Map<String,Object> userInfoMap);

    /**
     * 查询用户列表
     * @param userInfoMap
     * @return
     */
    public CommonResultMap<List<UserInfo>> userListInfo(Map<String,Object> userInfoMap);
}
