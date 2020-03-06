package cn.bjfu.ws.service;

import cn.bjfu.ws.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    UserInfo validateUser(String username, String password, String token);
    UserInfo queryUserByUsername(String  username);
    UserInfo addUser(UserInfo user);
    UserInfo updateUser(UserInfo user);
}
