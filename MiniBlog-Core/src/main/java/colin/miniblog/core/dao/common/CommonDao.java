package colin.miniblog.core.dao.common;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by joker on 16-3-12.
 */
public class CommonDao {
    @Autowired
    @Qualifier(value = "sqlManager")
    private SpringBeetlSql beetlSqlsql;

    public SQLManager getSqlManager() {
        return beetlSqlsql.getSQLMananger();
    }
}
