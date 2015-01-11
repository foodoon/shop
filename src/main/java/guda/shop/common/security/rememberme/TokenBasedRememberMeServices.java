package guda.shop.common.security.rememberme;

import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.common.security.userdetails.UserDetails;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

public class TokenBasedRememberMeServices extends AbstractRememberMeServices {
    protected UserDetails processAutoLoginCookie(String[] paramArrayOfString, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
            throws DataAccessException, UsernameNotFoundException, InvalidCookieException {
        if (paramArrayOfString.length != 4)
            throw new InvalidCookieException("Cookie token did not contain 4 tokens, but contained '" + Arrays.asList(paramArrayOfString) + "'");
        long l;
        try {
            l = new Long(paramArrayOfString[1]).longValue();
        } catch (NumberFormatException localNumberFormatException1) {
            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" + paramArrayOfString[1] + "')");
        }
        if (isTokenExpired(l))
            throw new InvalidCookieException("Cookie token[1] has expired (expired on '" + new Date(l) + "'; current time is '" + new Date() + "')");
        Long localLong;
        try {
            localLong = new Long(paramArrayOfString[3]);
        } catch (NumberFormatException localNumberFormatException2) {
            throw new InvalidCookieException("Cookie token[3] did not contain a valid number (contained '" + paramArrayOfString[3] + "')");
        }
        UserDetails localUserDetails = getUserDetailsService().loadUser(localLong, paramArrayOfString[0]);
        String str = makeTokenSignature(l, localUserDetails.getUsername(), localUserDetails.getPassword(), localUserDetails.getId());
        if (!str.equals(paramArrayOfString[2]))
            throw new InvalidCookieException("Cookie token[2] contained signature '" + paramArrayOfString[2] + "' but expected '" + str + "'");
        return localUserDetails;
    }

    protected String makeTokenSignature(long paramLong, String paramString1, String paramString2, Long paramLong1) {
        return DigestUtils.md5Hex(paramString1 + ":" + paramLong + ":" + paramString2 + ":" + getKey() + ":" + paramLong1);
    }

    protected boolean isTokenExpired(long paramLong) {
        return paramLong < System.currentTimeMillis();
    }

    public boolean onLoginSuccess(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserDetails paramUserDetails) {
        String str1 = paramUserDetails.getUsername();
        String str2 = paramUserDetails.getPassword();
        if ((!StringUtils.hasLength(str1)) || (!StringUtils.hasLength(str2)))
            return false;
        int i = calculateLoginLifetime(paramHttpServletRequest, paramUserDetails);
        long l = System.currentTimeMillis();
        l += 1000L * (i < 0 ? 1209600 : i);
        String str3 = makeTokenSignature(l, str1, str2, paramUserDetails.getId());
        setCookie(new String[]{str1, Long.toString(l), str3, paramUserDetails.getId().toString()}, i, paramHttpServletRequest, paramHttpServletResponse);
        if (this.logger.isDebugEnabled())
            this.logger.debug("Added remember-me cookie for user '" + str1 + "', expiry: '" + new Date(l) + "'");
        return true;
    }

    protected int calculateLoginLifetime(HttpServletRequest paramHttpServletRequest, UserDetails paramUserDetails) {
        return getTokenValiditySeconds();
    }
}

