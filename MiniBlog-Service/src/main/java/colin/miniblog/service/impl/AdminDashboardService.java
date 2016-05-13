package colin.miniblog.service.impl;

import colin.miniblog.core.pojo.AdminInfo;
import colin.miniblog.service.inter.IAdminDashboardService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/13.
 */
@Service
public class AdminDashboardService implements IAdminDashboardService{
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
        return this.adminInfoLoginService(username, DigestUtils.md5Hex(psw));
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
