package colin.miniblog.core.dao;

import colin.miniblog.core.pojo.UserInfo;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/10
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.dao
 */
@Repository
public class UserDao {

    @Autowired
    @Qualifier(value="sqlManager")
    private SpringBeetlSql beetlSqlsql;

    /**
     * 保存用户对象
     * @param params
     * @return
     */
    public int insertUser(Map<String,Object> params){
        return beetlSqlsql.getSQLMananger().insert(UserInfo.class, params);
    }
}
