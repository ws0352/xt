package cn.bjfu.ws.controller;

import cn.bjfu.ws.BO.UserBO;
import cn.bjfu.ws.exception.Result;
import cn.bjfu.ws.exception.exception.error.CommonErrorCode;
import cn.bjfu.ws.model.UserInfo;
import cn.bjfu.ws.model.UserToken;
import cn.bjfu.ws.service.Impl.UserServiceImpl;
import cn.bjfu.ws.service.UserTokenService;
import cn.bjfu.ws.utils.IdWorker;
import cn.bjfu.ws.utils.MD5Util;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserTokenService userTokenService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public Result getRegister(@RequestParam String username, @RequestParam String password){
        if("".equals(username) || "".equals(password)){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
        UserInfo userInfo = userService.queryUserByUsername(username);
        if(userInfo != null){
            return Result.ofFail(CommonErrorCode.ALREADY_EXISTED);
        }
        UserInfo user = new UserInfo();
        String pwd=	MD5Util.string2MD5(password);
        user.setPassword(pwd);
        user.setUserName(username);

        UserInfo result = userService.addUser(user);
        logger.info("用户注册"+"账号："+username+ "密码: "+password);
        return Result.ofSuccess(result);
    }

    @GetMapping("/login")
    public Result getLogin(@RequestParam String username, @RequestParam String password){
        if("".equals(username) || "".equals(password)){
            return Result.ofFail(CommonErrorCode.ERROR_FORMAT);
        }
        UserInfo userInfo = userService.queryUserByUsername(username);
        String pwd = MD5Util.string2MD5(password);

        if (!pwd.equals(userInfo.getPassword())){
            return Result.ofFail(CommonErrorCode.PARAM_ERROR);
        }
        UserBO userBO = new UserBO();
        userBO.setId(userInfo.getId());
        userBO.setUserName(userInfo.getUserName());
        UserToken userToken =  userTokenService.queryTokenByUid(userInfo.getId());
        //为生成Token准备
        String TokenStr = "";
        Date date = new Date();
        Timestamp nowtime = new Timestamp(date.getTime() / 1000);
        //生成Token

        TokenStr = creatToken(userInfo, date);
        System.out.println(TokenStr);
        if (null == userToken) {
            //第一次登陆
            userToken = new UserToken();
            userToken.setToken(TokenStr);
            userToken.setUid(userInfo.getId());
            UserToken userToken1 = userTokenService.addToken(userToken);
            userBO.setToken(userToken1.getToken());
            userBO.getBuildTime(userToken1.getGmtCreate());
        }else{
            //登陆就更新Token信息
            TokenStr = creatToken(userInfo, date);
            userToken.setToken(TokenStr);
            userToken.setGmtCreate(nowtime);
            UserToken userToken2 = userTokenService.updateToken(userToken);
            userBO.setToken(userToken2.getToken());
            userBO.getBuildTime(userToken2.getGmtCreate());
        }
        logger.info("用户"+userInfo.getUserName()+ "登录");
        return Result.ofSuccess(userBO);
    }

    //生成Token信息方法（根据有效的用户信息）
    private String creatToken(UserInfo user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("id",String.valueOf(user.getId()) ) // 设置内容
                .setIssuer("Vincent")// 设置签发人
                .signWith(signatureAlgorithm, "wangsheng"); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }
}
