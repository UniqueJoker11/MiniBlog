package colin.miniblog.core.dao;

import colin.miniblog.core.dao.common.CommonDao;
import colin.miniblog.core.pojo.AdminMenu;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2016/5/13.
 */
@Repository
public class AdminMenuDao extends CommonDao {

    /**
     * 获取所有的菜单信息
     *
     * @return
     */
    public List<AdminMenu> getAllAdminMenu() {
        return super.getSqlManager().all(AdminMenu.class);
    }

    /**
     * 更新菜单信息
     * @param id
     * @param name
     * @param url
     * @param pid
     * @param fsort
     * @return
     */
    public boolean updateAdminMenu(Integer id, String name, String url, Integer pid, Integer fsort) {
        if (ObjectUtils.NULL.equals(id)) {
            return false;
        }
        return super.getSqlManager().updateById(this.initAdminMenu(id, name, url, pid, fsort)) == 1 ? true : false;
    }

    /**
     * 初始化菜单类
     *
     * @param id
     * @param name
     * @param url
     * @param pid
     * @param fsort
     * @return
     */
    private AdminMenu initAdminMenu(Integer id, String name, String url, Integer pid, Integer fsort) {
        AdminMenu menu = new AdminMenu();
        menu.setId(id);
        if (!StringUtils.isBlank(name)) {
            menu.setName(name);
        }
        if (!StringUtils.isBlank(url)) {
            menu.setName(url);
        }
        if (!ObjectUtils.NULL.equals(pid) && StringUtils.isNumeric(pid.toString())) {
            menu.setPid(pid);
        }
        if (!ObjectUtils.NULL.equals(fsort) && StringUtils.isNumeric(fsort.toString())) {
            menu.setFsort(fsort);
        }
        return menu;
    }
}
