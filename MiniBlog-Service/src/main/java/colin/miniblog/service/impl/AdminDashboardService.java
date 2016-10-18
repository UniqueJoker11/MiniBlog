package colin.miniblog.service.impl;

import colin.miniblog.core.dao.AdminInfoDao;
import colin.miniblog.core.dao.AdminMenuDao;
import colin.miniblog.core.pojo.AdminInfo;
import colin.miniblog.core.pojo.AdminMenu;
import colin.miniblog.service.inter.IAdminDashboardService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
@Service
public class AdminDashboardService implements IAdminDashboardService{

    @Autowired
    private AdminInfoDao adminInfoDao;
    @Autowired
    private AdminMenuDao adminMenuDao;
    /**
     * 管理员登录服务
     *
     * @param username
     * @param psw
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NEVER )
    public AdminInfo adminInfoLoginService(String username, String psw) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(psw)){
            return null;
        }
        return this.adminInfoDao.searchAdminInfo(username, DigestUtils.md5Hex(psw));
    }

    public List<AdminMenu> listAllAdminMenus(){
        return  adminMenuDao.getAllAdminMenu();
    }
    /**
     * 验证用户登录
     *
     * @param username
     * @param psw
     * @return
     */
    @Override
    public Boolean validateAdminIngoLogin(String username, String psw) {
        return this.adminInfoLoginService(username,psw)==null?false:true;
    }
}
