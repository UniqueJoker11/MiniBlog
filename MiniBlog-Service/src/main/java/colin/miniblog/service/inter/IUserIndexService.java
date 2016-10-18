package colin.miniblog.service.inter;

import colin.miniblog.core.pojo.UserInfo;

import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/14
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.service.impl
 */
public interface IUserIndexService {
    /**
     * 驗證用戶登錄
     * @param username
     * @param password
     * @return
     */
  public UserInfo userLoginService(String username,String password);

    /**
     * 用戶註冊
     * @param username
     * @param password
     * @return
     */
  public UserInfo userRegisterService(String username,String password);

    /**
     * 用戶信息更新
     * @param userInfo
     * @return
     */
  public boolean userUpdateService(Map<String,Object> userInfo);

    /**
     * 根據id刪除用戶
     * @param id
     * @return
     */
  public boolean userDeleteService(int id);

  /**
   * 验证用户的登录信息
   * @param username
   * @param password
   * @return
     */
  public boolean validateUserLogin(String username,String password);
}
