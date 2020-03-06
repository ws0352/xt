package cn.bjfu.ws.service.Impl;

import cn.bjfu.ws.dao.UserInfoMapper;
import cn.bjfu.ws.model.UserInfo;
import cn.bjfu.ws.model.UserInfoExample;
import cn.bjfu.ws.service.UserService;
import cn.bjfu.ws.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userMapper;


//    @Override
//    public UserInfo validateUser(String username, String password) {
//
////        userMapper.selectByExample();
//        return null;
//    }

    @Override
    public UserInfo queryUserByUsername(String  username) {
        UserInfoExample userExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<UserInfo> user1 = this.userMapper.selectByExample(userExample);
        if(user1.isEmpty() ||  user1 == null){
            return null;
        }
        return user1.get(0);
    }

    @Override
    public UserInfo addUser(UserInfo user) {
        Long id = IdWorker.getId();
        user.setId(id);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        user.setGmtCreate(d);
        user.setGmtModified(d);
        userMapper.insert(user);
        UserInfoExample userExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(user.getId());
        List<UserInfo> user1 = this.userMapper.selectByExample(userExample);

        return user1.get(0);
    }

    @Override
    public UserInfo updateUser(UserInfo user) {
        UserInfoExample userExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(user.getId());
        int num = userMapper.updateByExampleSelective(user, userExample);
        if(num == 0){
            return null;
        }
        return userMapper.selectByPrimaryKey(user.getId());
    }
}
