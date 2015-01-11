package guda.shop.common.security.rememberme;

import guda.shop.common.security.AccountStatusException;
import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.common.security.userdetails.AccountStatusUserDetailsChecker;
import guda.shop.common.security.userdetails.UserDetails;
import guda.shop.common.security.userdetails.UserDetailsChecker;
import guda.shop.common.security.userdetails.UserDetailsService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractRememberMeServices
        implements RememberMeService, InitializingBean {
    public static final String REMEMBER_ME_COOKIE_KEY = "remember_me_cookie";
    public static final String DEFAULT_PARAMETER = "remember_me";
    public static final int TWO_WEEKS_S = 1209600;
    private static final String _$9 = ":";
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private String _$8 = "remember_me_cookie";
    private String _$7 = "remember_me";
    private int _$6 = 1209600;
    private boolean _$5;
    private boolean _$4;
    private String _$3;
    private UserDetailsChecker _$2 = new AccountStatusUserDetailsChecker();
    private UserDetailsService _$1;

    public final UserDetails autoLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
            throws CookieTheftException {
        String str = extractRememberMeCookie(paramHttpServletRequest);
        if (str == null)
            return null;
        this.logger.debug("Remember-me cookie detected");
        UserDetails localUserDetails = null;
        try {
            String[] arrayOfString = decodeCookie(str);
            localUserDetails = processAutoLoginCookie(arrayOfString, paramHttpServletRequest, paramHttpServletResponse);
            this._$2.check(localUserDetails);
        } catch (CookieTheftException localCookieTheftException) {
            cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
            throw localCookieTheftException;
        } catch (UsernameNotFoundException localUsernameNotFoundException) {
            cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
            this.logger.debug("Remember-me login was valid but corresponding user not found.", localUsernameNotFoundException);
            return null;
        } catch (InvalidCookieException localInvalidCookieException) {
            cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
            this.logger.debug("Invalid remember-me cookie: " + localInvalidCookieException.getMessage());
            return null;
        } catch (AccountStatusException localAccountStatusException) {
            cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
            this.logger.debug("Invalid UserDetails: " + localAccountStatusException.getMessage());
            return null;
        } catch (RememberMeAuthenticationException localRememberMeAuthenticationException) {
            cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
            this.logger.debug(localRememberMeAuthenticationException.getMessage());
            return null;
        }
        this.logger.debug("Remember-me cookie accepted");
        return localUserDetails;
    }

    public final boolean loginSuccess(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserDetails paramUserDetails) {
        if (!rememberMeRequested(paramHttpServletRequest, this._$7)) {
            this.logger.debug("Remember-me login not requested.");
            return false;
        }
        return onLoginSuccess(paramHttpServletRequest, paramHttpServletResponse, paramUserDetails);
    }

    public final void loginFail(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        this.logger.debug("Interactive login attempt was unsuccessful.");
        cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
        onLoginFail(paramHttpServletRequest, paramHttpServletResponse);
    }

    public void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        this.logger.debug("Remember-me logout.");
        cancelCookie(paramHttpServletRequest, paramHttpServletResponse);
        onLogout(paramHttpServletRequest, paramHttpServletResponse);
    }

    protected String extractRememberMeCookie(HttpServletRequest paramHttpServletRequest) {
        Cookie[] arrayOfCookie = paramHttpServletRequest.getCookies();
        if ((arrayOfCookie == null) || (arrayOfCookie.length == 0))
            return null;
        for (int i = 0; i < arrayOfCookie.length; i++)
            if (this._$8.equals(arrayOfCookie[i].getName()))
                return arrayOfCookie[i].getValue();
        return null;
    }

    protected boolean rememberMeRequested(HttpServletRequest paramHttpServletRequest, String paramString) {
        if (this._$5)
            return true;
        String str = paramHttpServletRequest.getParameter(paramString);
        if ((str != null) && ((str.equalsIgnoreCase("true")) || (str.equalsIgnoreCase("on")) || (str.equalsIgnoreCase("yes")) || (str.equals("1"))))
            return true;
        if (this.logger.isDebugEnabled())
            this.logger.debug("Did not send remember-me cookie (principal did not set parameter '" + paramString + "')");
        return false;
    }

    protected void setCookie(String[] paramArrayOfString, int paramInt, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        String str1 = encodeCookie(paramArrayOfString);
        Cookie localCookie = new Cookie(this._$8, str1);
        String str2 = paramHttpServletRequest.getContextPath();
        localCookie.setPath(StringUtils.hasText(str2) ? str2 : "/");
        localCookie.setMaxAge(paramInt);
        paramHttpServletResponse.addCookie(localCookie);
    }

    protected void cancelCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        this.logger.debug("Cancelling cookie");
        Cookie localCookie = new Cookie(this._$8, null);
        String str = paramHttpServletRequest.getContextPath();
        localCookie.setPath(StringUtils.hasText(str) ? str : "/");
        localCookie.setMaxAge(0);
        paramHttpServletResponse.addCookie(localCookie);
    }

    protected String[] decodeCookie(String paramString)
            throws InvalidCookieException {
        StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 3).append(paramString);
        for (int i = 0; i < localStringBuilder.length() % 4; i++)
            localStringBuilder.append("=");
        paramString = localStringBuilder.toString();
        if (!Base64.isArrayByteBase64(paramString.getBytes()))
            throw new InvalidCookieException("Cookie token was not Base64 encoded; value was '" + paramString + "'");
        String str = new String(Base64.decodeBase64(paramString.getBytes()));
        return StringUtils.delimitedListToStringArray(str, ":");
    }

    protected String encodeCookie(String[] paramArrayOfString) {
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < paramArrayOfString.length; i++) {
            localStringBuilder.append(paramArrayOfString[i]);
            if (i < paramArrayOfString.length - 1)
                localStringBuilder.append(":");
        }
        String str = localStringBuilder.toString();
        localStringBuilder = new StringBuilder(new String(Base64.encodeBase64(str.getBytes())));
        while (localStringBuilder.charAt(localStringBuilder.length() - 1) == '=')
            localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        return localStringBuilder.toString();
    }

    protected abstract UserDetails processAutoLoginCookie(String[] paramArrayOfString, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
            throws RememberMeAuthenticationException, UsernameNotFoundException;

    protected abstract boolean onLoginSuccess(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserDetails paramUserDetails);

    protected void onLoginFail(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
    }

    protected void onLogout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
    }

    public void afterPropertiesSet()
            throws Exception {
        Assert.hasLength(this._$3);
        Assert.hasLength(this._$7);
        Assert.hasLength(this._$8);
        Assert.notNull(this._$1);
    }

    protected String getCookieName() {
        return this._$8;
    }

    public void setCookieName(String paramString) {
        this._$8 = paramString;
    }

    public boolean isAlwaysRemember() {
        return this._$5;
    }

    public void setAlwaysRemember(boolean paramBoolean) {
        this._$5 = paramBoolean;
    }

    public String getParameter() {
        return this._$7;
    }

    public void setParameter(String paramString) {
        this._$7 = paramString;
    }

    public UserDetailsService getUserDetailsService() {
        return this._$1;
    }

    public void setUserDetailsService(UserDetailsService paramUserDetailsService) {
        this._$1 = paramUserDetailsService;
    }

    public String getKey() {
        return this._$3;
    }

    public void setKey(String paramString) {
        this._$3 = paramString;
    }

    protected int getTokenValiditySeconds() {
        return this._$6;
    }

    public void setTokenValiditySeconds(int paramInt) {
        this._$6 = paramInt;
    }

    public boolean isAlwaysRememberCookie() {
        return this._$4;
    }

    public void setAlwaysRememberCookie(boolean paramBoolean) {
        this._$4 = paramBoolean;
    }
}

