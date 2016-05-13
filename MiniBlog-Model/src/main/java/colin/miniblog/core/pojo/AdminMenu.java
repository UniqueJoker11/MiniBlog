package colin.miniblog.core.pojo;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * Created by Administrator on 2016/5/13.
 */
@Table(name = "admin_menu")
public class AdminMenu {
    @AutoID
    private int id;
    private String name;
    private String url;
    private int pid;
    private int fsort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getFsort() {
        return fsort;
    }

    public void setFsort(int fsort) {
        this.fsort = fsort;
    }
}
