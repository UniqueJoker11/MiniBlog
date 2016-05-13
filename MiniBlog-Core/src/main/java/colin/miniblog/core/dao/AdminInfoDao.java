package colin.miniblog.core.dao;

import colin.miniblog.core.dao.common.CommonDao;
import colin.miniblog.core.pojo.AdminInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.db.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joker on 16-4-21.
 */
@Repository
public class AdminInfoDao extends CommonDao{

    /**
     * 增加管理员信息
     * @param adminName
     * @param psw
     * @return
     */
    public boolean addAdminInfo(String adminName,String psw){
        AdminInfo adminInfo=this.initAdminInfo(null,adminName,psw);
        return super.getSqlManager().insert(AdminInfo.class,adminInfo)==1?true:false;
    }

    /**
     * 修改管理员信息
     * @param id
     * @param adminName
     * @param psw
     * @return
     */
    public boolean updateAdminInfo(Integer id,String adminName,String psw){
        AdminInfo adminInfo=this.initAdminInfo(id,adminName,psw);
        return super.getSqlManager().updateById(adminInfo)==1?true:false;
    }

    /**
     * 根据主键删除管理员
     * @param id
     * @return
     */
    public boolean delAdminInfo(Integer id){
        return super.getSqlManager().deleteById(AdminInfo.class, id)==1?true:false;
    }

    /**
     * 查询管理员信息
     * @param adminName
     * @param psw
     * @return
     */
    public AdminInfo searchAdminInfo(String adminName,String psw){
        List<AdminInfo> adminInfoList=this.getSqlManager().select("adminInfo.searchAdminInfo",AdminInfo.class,initAdminInfo(null,adminName,psw));
        return adminInfoList.isEmpty()?null:adminInfoList.get(0);
    }

    /**
     * 初始化用户对象信息
     * @param id
     * @param adminName
     * @param psw
     * @return
     */
    private AdminInfo initAdminInfo(Integer id,String adminName,String psw){
        AdminInfo adminInfo=new AdminInfo();
        if (id!=null&&StringUtils.isNumeric(String.valueOf(id))){
            adminInfo.setId(id);
        }
        if(adminName!=null&& !StringUtils.isBlank(adminName)){
            adminInfo.setAdminname(adminName);
        }
        if(adminInfo!=null&&!StringUtils.isBlank(adminName)){
            adminInfo.setAdminpwd(psw);
        }
        return adminInfo;
    }
}
