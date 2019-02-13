import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loan.supermarket.dao.UserDao;
import com.loan.supermarket.mapper.User;
import com.loan.supermarket.web.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MyBatisTest {

//    @Autowired
//    private UserDao userDao;
//
//    /**
//     * 新增用户
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testAddUser() throws Exception {
////        User user = new User();
////        user.setId(820L);
////        user.setName("zhangsan21");
////        user.setAge(12);
////        userDao.addUser(user);
//    }
//
//    /**
//     * 删除用户
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testDelUser() throws Exception {
//        userDao.deleteUserById(889L);
//    }
//
//    /**
//     * 修改用户信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testUpdUser() throws Exception {
//        User user = new User();
//        user.setId(2L);
//        user.setName("zhangsan99");
//        user.setAge(122);
//        userDao.updateUserById(user);
//    }
//
//    /**
//     * 查询用户
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testQueryUser() throws Exception {
//        User user = userDao.queryUserById(2L);
//        if (null != user) {
//            System.out.println(user.getName());
//        }
//        System.out.println("null");
//    }
//
//    /**
//     * 查询所有用户
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testQueryUserList() throws Exception {
//        int pageNum = 1;
//        int pageSize = 1;
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> userList = userDao.queryUserList();
//        PageInfo<User> pageInfo = new PageInfo<>(userList);
//        System.out.println("总共条数：" + pageInfo.getTotal());
//        for (User user : pageInfo.getList()) {
//            System.out.println(user.getName());
//        }
//    }
}