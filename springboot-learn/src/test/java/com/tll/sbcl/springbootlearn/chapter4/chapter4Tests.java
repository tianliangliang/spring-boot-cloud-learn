package com.tll.sbcl.springbootlearn.chapter4;

import com.tll.sbcl.springbootlearn.chapter4.springDataJps.User;
import com.tll.sbcl.springbootlearn.chapter4.springDataJps.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tll on 08/11/2017 12:56
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class chapter4Tests {
//    @Autowired
//    private UserService userSerivce;
//
//    @Before
//    public void setUp() {
//        // 准备，清空user表
//        //userSerivce.deleteAllUsers();
//    }
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void test() throws Exception {
//        // 插入5个用户
//        userSerivce.create("a", 1);
//        userSerivce.create("b", 2);
//        userSerivce.create("c", 3);
//        userSerivce.create("d", 4);
//        userSerivce.create("e", 5);
//        // 查数据库，应该有5个用户
//        Assert.assertEquals(5, userSerivce.getAllUsers().intValue());
//        // 删除两个用户
//        userSerivce.deleteByName("a");
//        userSerivce.deleteByName("e");
//        // 查数据库，应该有5个用户
//        Assert.assertEquals(3, userSerivce.getAllUsers().intValue());
//    }
//
//
//    @Test
//    public void test1() throws Exception {
//        // 创建10条记录
//        userRepository.save(new User("AAA", 10));
//        userRepository.save(new User("BBB", 20));
//        userRepository.save(new User("CCC", 30));
//        userRepository.save(new User("DDD", 40));
//        userRepository.save(new User("EEE", 50));
//        userRepository.save(new User("FFF", 60));
//        userRepository.save(new User("GGG", 70));
//        userRepository.save(new User("HHH", 80));
//        userRepository.save(new User("III", 90));
//        userRepository.save(new User("JJJ", 100));
//
//        List<User> list= userRepository.findAll();
//        // 测试findAll, 查询所有记录
//        Assert.assertEquals(10, userRepository.findAll().size());
//        // 测试findByName, 查询姓名为FFF的User
//        Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());
//        // 测试findUser, 查询姓名为FFF的User
//        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());
//        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
//        Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());
//        // 测试删除姓名为AAA的User
//        userRepository.delete(userRepository.findByName("AAA"));
//        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
//        Assert.assertEquals(9, userRepository.findAll().size());
//    }

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;
    @Before
    public void setUp() {
        jdbcTemplate1.update("DELETE  FROM  USER ");
        jdbcTemplate2.update("DELETE  FROM  USER ");
    }
    @Test
    public void test() throws Exception {
        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);
        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));
        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
    }
}
