package colin.miniblog.core.dao.common;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/10
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.dao
 */

public class CommonDao {

  @Autowired
  @Qualifier("sqlManager")
  private SpringBeetlSql springBeetlSql;

  public SQLManager getSqlManager() {
    return springBeetlSql.getSQLMananger();
  }
}
