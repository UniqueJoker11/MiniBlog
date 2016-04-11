package colin.miniblog.service.impl;

import colin.miniblog.core.dao.UserDao;
import colin.miniblog.service.inter.IUserService;
import colin.miniblog.utils.ColinDateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/14
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.service.inter
 */
@Service
public class UserService implements IUserService{
  @Autowired
  private UserDao userDao;
  /**
   * 驗證用戶登錄
   * @param username
   * @param password
   * @return
   */
  @Override
  public User userLoginService(String username,String password) {
    User user=new User();
    user.setUsername(username);
    user.setPwd(DigestUtils.md5Hex(password));
    return userDao.valdiateUser(user);
  }

  /**
   * 用戶註冊
   *
   * @param userInfo
   * @return
   */
  @Override
  public User userRegisterService(String username,String password) {
    User user=new User();
    user.setUsername(username);
    user.setPwd(DigestUtils.md5Hex(password));
    user.setMtime(ColinDateUtils.getCurrentDate());
    return userDao.insertUser(user);
  }

  /**
   * 用戶信息更新
   *
   * @param userInfo
   * @return
   */
  @Override
  public int userUpdateService(Map<String, Object> userInfo) {
    return 0;
  }

  /**
   * 根據id刪除用戶
   *
   * @param id
   * @return
   */
  @Override
  public int userDeleteService(int id) {
    return 0;
  }
}
