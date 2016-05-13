package test.colin.miniblog.core;

import colin.miniblog.core.dao.AdminInfoDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-datasource.xml"})
public class AdminDaoTest {

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Test
    public void testAddAdminInfo(){
        String username="colin",psw="admin123";
        Assert.assertEquals(true, adminInfoDao.addAdminInfo(username, DigestUtils.md5Hex(psw)));
    }
    @Test
    public void testSearchAdminInfo(){
        String username="colin",psw="admin123";
        Assert.assertNotEquals(null, adminInfoDao.searchAdminInfo(username, DigestUtils.md5Hex(psw)));
    }
}
