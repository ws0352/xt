package cn.bjfu.ws.service;

import cn.bjfu.ws.dao.UserTokenMapper;
import cn.bjfu.ws.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    UserToken queryTokenByUid(Long uid);
    UserToken addToken(UserToken token);
    UserToken updateToken(UserToken token);
}
