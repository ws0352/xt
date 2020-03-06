package cn.bjfu.ws.service.Impl;

import cn.bjfu.ws.dao.UserTokenMapper;
import cn.bjfu.ws.model.UserToken;
import cn.bjfu.ws.model.UserTokenExample;
import cn.bjfu.ws.service.UserTokenService;
import cn.bjfu.ws.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public UserToken queryTokenByUid(Long uid) {
        UserTokenExample example = new UserTokenExample();
        UserTokenExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<UserToken> userTokens = this.userTokenMapper.selectByExample(example);
        if(userTokens.isEmpty() ||  userTokens == null){
            return null;
        }
        return userTokens.get(0);
    }

    @Override
    public UserToken addToken(UserToken token) {
        Long id = IdWorker.getId();
        token.setId(id);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        token.setGmtCreate(d);
        token.setGmtModified(d);
        userTokenMapper.insert(token);
        return userTokenMapper.selectByPrimaryKey(token.getId());
    }

    @Override
    public UserToken updateToken(UserToken token) {
        userTokenMapper.updateByPrimaryKey(token);
        return userTokenMapper.selectByPrimaryKey(token.getId());
    }
}
