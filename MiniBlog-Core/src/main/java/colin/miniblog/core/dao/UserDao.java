package colin.miniblog.core.dao;

import colin.miniblog.core.dao.common.CommonDao;
import colin.miniblog.core.pojo.UserInfo;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/10
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.dao
 */
@Repository
public class UserDao extends CommonDao {

    /**
     * 保存用户对象
     *
     * @param userInfo
     * @return
     */
    public int insertUser(UserInfo userInfo) {
        return super.getSqlManager().insert(UserInfo.class, userInfo);
    }

    /**
     * 根据用户名和密码验证用户信息,返回用戶信息
     *
     * @param userInfoMap
     * @return
     */
    public List<UserInfo> validateUserInfo(Map<String, Object> userInfoMap) {
        return super.getSqlManager().select("user.validateLogin", UserInfo.class, userInfoMap);
    }

    /**
     * 更新用戶的信息
     *
     * @param userInfo
     * @return
     */
    public int updateUserInfo(Map<String,Object> userInfo) {
        return super.getSqlManager().update("user.update", userInfo);
    }

    /**
     * 删除对象通过id
     * @param id
     * @return
     */
    public int delUser(int id){
       return this.getSqlManager().deleteById(UserInfo.class,id);
    }
}
