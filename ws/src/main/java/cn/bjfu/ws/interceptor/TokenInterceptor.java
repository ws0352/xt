package cn.bjfu.ws.interceptor;

import cn.bjfu.ws.dao.UserTokenMapper;
import cn.bjfu.ws.exception.exception.category.BusinessException;
import cn.bjfu.ws.model.UserToken;
import cn.bjfu.ws.service.UserTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;


@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserTokenService userTokenService;
    //提供查询
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {}
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {}
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/v1/register".equals(arg0.getRequestURI()) || "/v1/login".equals(arg0.getRequestURI())) {
            return true;}

        //权限路径拦截
        arg1.setCharacterEncoding("UTF-8");
//        PrintWriter resultWriter = null;
       //解析Token信息
        try {

//            resultWriter=arg1.getWriter();
            final String headerToken=arg0.getHeader("XW-Token");
            //判断请求信息
            if(null==headerToken||headerToken.trim().equals("")){
//                resultWriter.write("你没有token,需要登录");
                throw new BusinessException("-1", "你没有token,需要登录");
            }
            Claims claims = Jwts.parser().setSigningKey("wangsheng").parseClaimsJws(headerToken).getBody();

            String tokenUserId=(String)claims.get("id");
            Long itokenUserId=Long.parseLong(tokenUserId);
//            System.out.println(itokenUserId);
            //根据客户Token查找数据库Token
            UserToken myToken=userTokenService.queryTokenByUid(itokenUserId);

            //数据库没有Token记录
             if(null==myToken) {
//                 resultWriter.write("我没有你的token？,需要登录");
                 throw new BusinessException("-1", "我没有你的token？,需要登录");
             }
             //数据库Token与客户Token比较
             if( !headerToken.equals(myToken.getToken()) ){
//                 resultWriter.write("你的token修改过？,需要登录");
                 throw new BusinessException("-1", "你的token修改过？,需要登录");

             }
             //判断Token过期
             Date tokenDate=(Date)claims.getExpiration();
             int overdue=(int)(new Date().getTime()-tokenDate.getTime())/1000;
             if(overdue>60*60*24*3){
                 throw new BusinessException("-1", "你的token过期了？,需要登录");
//                 resultWriter.write("你的token过期了？,需要登录");
             }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("-1", "反正token不对,需要登录");
//            resultWriter.write("反正token不对,需要登录");
        }finally {


        }

        //最后才放行
        return true;
    }
}
