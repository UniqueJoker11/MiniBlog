package colin.miniblog.service.inter;

import colin.miniblog.core.pojo.AdminInfo;

/**
 * Created by Administrator on 2016/5/13.
 */
public interface IAdminDashboardService {

    /**
     * 管理员登录服务
     * @param username
     * @param psw
     * @return
     */
    public AdminInfo adminInfoLoginService(String username,String psw);

    /**
     * 验证用户登录
     * @param username
     * @param psw
     * @return
     */
    public Boolean validateAdminIngoLogin(String username,String psw);
}
