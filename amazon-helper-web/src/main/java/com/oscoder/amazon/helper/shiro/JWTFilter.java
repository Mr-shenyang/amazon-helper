package com.oscoder.amazon.helper.shiro;

import com.oscoder.amazon.helper.jwt.JWTToken;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
        /*** 对跨域提供支持 */
        this.fillCorsHeader(WebUtils.toHttp(request), WebUtils.toHttp(response));
    }

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        if(this.isLoginRequest(request, response))
            return true;
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch(IllegalStateException e){ //not found any token
            responseError(response, e.getMessage());
        }catch (Exception e) {
            responseError(response, e.getMessage());
        }
        return allowed || super.isPermissive(mappedValue);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = getAuthzHeader(request);
        JWTToken jwtToken = new JWTToken(token);
        // 提交给 realm 进行登入，如果错误，会抛出异常并捕获
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String jwtToken = getAuthzHeader(servletRequest);
        if(StringUtils.isNotBlank(jwtToken)&&!JWTUtil.isTokenExpired(jwtToken)){
            return new JWTToken(jwtToken);
        }
        return null;
    }


    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String newToken = null;
        if(token instanceof JWTToken){
            JWTToken jwtToken = (JWTToken)token;
            UserPwdDTO user = (UserPwdDTO) subject.getPrincipal();
            boolean shouldRefresh = JWTUtil.shouldTokenRefresh(jwtToken.getToken());
            if(shouldRefresh) {
                newToken = JWTUtil.sign(user.getName(),user.getId());
            }
        }
        if(StringUtils.isNotBlank(newToken)){
            httpResponse.setHeader("token", newToken);
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        responseError(response,"msg");
        return false;
    }

    /**
     * 将非法请求跳转到登录页
     */
    private void responseError(ServletResponse response, String message) {
        try {
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            //设置编码，否则中文字符在重定向时会变为空字符串
//            message = URLEncoder.encode(message, "UTF-8");
            ((HttpServletResponse) response).sendRedirect("/#login" );
//            httpServletResponse.sendRedirect("/#login" );
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // 不使用*，自动适配跨域域名，避免携带Cookie时失效
        String origin = httpServletRequest.getHeader("Origin");
        if(StringUtils.isNotBlank(origin)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        }

        // 自适应所有自定义头
        String headers = httpServletRequest.getHeader("Access-Control-Request-Headers");
        if(StringUtils.isNotBlank(headers)) {
            httpServletResponse.setHeader("Access-Control-Allow-Headers", headers);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", headers);
        }

        // 允许跨域的请求方法类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        // 预检命令（OPTIONS）缓存时间，单位：秒
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        // 明确许可客户端发送Cookie，不允许删除字段即可
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
