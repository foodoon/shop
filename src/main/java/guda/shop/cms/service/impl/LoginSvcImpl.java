package guda.shop.cms.service.impl;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.entity.ShopConfig;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.ShopAdminMng;
import guda.shop.cms.manager.ShopConfigMng;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.service.ShoppingSvc;
import guda.shop.common.security.BadCredentialsException;
import guda.shop.common.security.UserNotAcitveException;
import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.common.security.encoder.PwdEncoder;
import guda.shop.common.security.rememberme.CookieTheftException;
import guda.shop.common.security.rememberme.RememberMeService;
import guda.shop.common.web.session.SessionProvider;
import guda.shop.core.entity.Member;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.MemberMng;
import guda.shop.core.manager.UserMng;
import guda.shop.core.security.UserNotInWebsiteException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginSvcImpl
        implements LoginSvc {
    private static final Logger _$10 = LoggerFactory.getLogger(LoginSvcImpl.class);
    private ShopMemberMng _$9;
    private ShopAdminMng _$8;
    private UserMng _$7;
    private MemberMng _$6;
    private ShopConfigMng _$5;
    private PwdEncoder _$4;
    private RememberMeService _$3;
    private ShoppingSvc _$2;
    private SessionProvider _$1;

    public ShopMember memberLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite, String paramString1, String paramString2)
            throws UsernameNotFoundException, BadCredentialsException, UserNotInWebsiteException, UserNotAcitveException {
        Long localLong = paramWebsite.getId();
        logout(paramHttpServletRequest, paramHttpServletResponse);
        User localUser = _$1(paramString1, paramString2);
        ShopMember localShopMember = this._$9.getByUserId(localLong, localUser.getId());
        if (localShopMember == null) {
            ShopConfig localShopConfig = this._$5.findById(localLong);
            if (localShopConfig.getRegisterAuto().booleanValue())
                localShopMember = this._$9.join(localUser, localLong, localShopConfig.getRegisterGroup());
            else
                throw new UserNotInWebsiteException("user '" + localUser.getUsername() + "' not in Website '" + localLong + "'");
        } else if (!localShopMember.getMember().getActive().booleanValue()) {
            throw new UserNotAcitveException("user '" + localUser.getUsername() + "' not Active '" + localLong + "'");
        }
        this._$7.updateLoginInfo(localUser.getId(), paramHttpServletRequest.getRemoteAddr());
        this._$3.loginSuccess(paramHttpServletRequest, paramHttpServletResponse, localShopMember.getMember());
        this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_user_id_key", localUser.getId());
        this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_member_id_key", localShopMember.getId());
        _$1(localShopMember.getUsername(), null, null, paramHttpServletRequest, paramHttpServletResponse);
        this._$2.addCookie(localShopMember, paramHttpServletRequest, paramHttpServletResponse);
        return localShopMember;
    }

    public ShopMember getMember(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite) {
        ShopMember localShopMember = _$1(paramHttpServletRequest, paramHttpServletResponse, paramWebsite);
        return localShopMember != null ? localShopMember : _$2(paramHttpServletRequest, paramHttpServletResponse, paramWebsite);
    }

    private ShopMember _$2(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite) {
        Member localMember;
        try {
            localMember = (Member) this._$3.autoLogin(paramHttpServletRequest, paramHttpServletResponse);
            if (localMember == null)
                return null;
        } catch (CookieTheftException localCookieTheftException) {
            _$10.warn("remember me cookie theft: {}", localCookieTheftException.getMessage());
            return null;
        }
        if (localMember == null)
            return null;
        Long localLong1 = paramWebsite.getId();
        Long localLong2 = localMember.getUser().getId();
        ShopMember localShopMember = null;
        int i = 0;
        if (!localMember.getWebsite().getId().equals(localLong1)) {
            localMember = this._$6.getByUserId(localLong1, localLong2);
            i = 1;
        }
        if (localMember == null) {
            ShopConfig localShopConfig = this._$5.findById(localLong1);
            if (localShopConfig.getRegisterAuto().booleanValue()) {
                localShopMember = this._$9.join(localLong2, localLong1, localShopConfig.getRegisterGroup());
                _$10.debug("shop member auto login. username= {}", localShopMember.getUsername());
            } else {
                _$10.debug("shop member not allow auto login.");
            }
        } else {
            localShopMember = this._$9.findById(localMember.getId());
            if (localShopMember == null)
                throw new IllegalStateException("This is JspGou's BUG, ShopMember here should not be null.");
        }
        if (localShopMember != null) {
            this._$7.updateLoginInfo(localLong2, paramHttpServletRequest.getRemoteAddr());
            this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_user_id_key", localShopMember.getMember().getUser().getId());
            this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_member_id_key", localShopMember.getMember().getId());
            _$1(localShopMember.getUsername(), null, null, paramHttpServletRequest, paramHttpServletResponse);
            if (i == 0) ;
        }
        return localShopMember;
    }

    private ShopMember _$1(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite) {
        Long localLong1 = (Long) this._$1.getAttribute(paramHttpServletRequest, "_member_id_key");
        ShopMember localShopMember = null;
        Long localLong2 = paramWebsite.getId();
        if (localLong1 != null) {
            localShopMember = this._$9.findById(localLong1);
            if ((localShopMember != null) && (localShopMember.getWebsite().getId().equals(localLong2)))
                return localShopMember;
        }
        Long localLong3 = (Long) this._$1.getAttribute(paramHttpServletRequest, "_user_id_key");
        if (localLong3 != null) {
            localShopMember = this._$9.getByUserId(localLong2, localLong3);
            if (localShopMember == null) {
                ShopConfig localShopConfig = this._$5.findById(localLong2);
                if (localShopConfig.getRegisterAuto().booleanValue())
                    localShopMember = this._$9.join(localLong3, localLong2, localShopConfig.getRegisterGroup());
            }
            if (localShopMember != null)
                this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_member_id_key", localShopMember.getId());
        }
        return localShopMember;
    }

    private void _$1(String paramString1, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        StringBuilder localStringBuilder1 = new StringBuilder(new String(Base64.encodeBase64(paramString1.getBytes())));
        while (localStringBuilder1.charAt(localStringBuilder1.length() - 1) == '=')
            localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1);
        paramHttpServletResponse.addCookie(_$1("username", localStringBuilder1.toString(), paramHttpServletRequest));
        StringBuilder localStringBuilder2 = new StringBuilder();
        if (!StringUtils.isBlank(paramString2))
            localStringBuilder2.append(paramString2);
        localStringBuilder2.append(":");
        if (!StringUtils.isBlank(paramString3))
            localStringBuilder2.append(paramString3);
        String str = localStringBuilder2.toString();
        localStringBuilder2 = new StringBuilder(new String(Base64.encodeBase64(str.getBytes())));
        while (localStringBuilder2.charAt(localStringBuilder2.length() - 1) == '=')
            localStringBuilder2.deleteCharAt(localStringBuilder2.length() - 1);
        Cookie localCookie = _$1("fullname", localStringBuilder2.toString(), paramHttpServletRequest);
        localCookie.setMaxAge(2147483647);
        paramHttpServletResponse.addCookie(localCookie);
    }

    private void _$1(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        Cookie localCookie1 = _$1("username", null, paramHttpServletRequest);
        localCookie1.setMaxAge(0);
        Cookie localCookie2 = _$1("fullname", null, paramHttpServletRequest);
        localCookie2.setMaxAge(0);
        paramHttpServletResponse.addCookie(localCookie1);
        paramHttpServletResponse.addCookie(localCookie2);
    }

    private Cookie _$1(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest) {
        Cookie localCookie = new Cookie(paramString1, paramString2);
        String str = paramHttpServletRequest.getContextPath();
        localCookie.setPath(StringUtils.isBlank(str) ? "/" : str);
        return localCookie;
    }

    public void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        this._$3.logout(paramHttpServletRequest, paramHttpServletResponse);
        this._$1.logout(paramHttpServletRequest, paramHttpServletResponse);
        clearCookie(paramHttpServletRequest, paramHttpServletResponse);
    }

    public void clearCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        this._$2.clearCookie(paramHttpServletRequest, paramHttpServletResponse);
        _$1(paramHttpServletRequest, paramHttpServletResponse);
    }

    public ShopAdmin adminLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite, String paramString1, String paramString2)
            throws UsernameNotFoundException, BadCredentialsException, UserNotInWebsiteException {
        Long localLong = paramWebsite.getId();
        logout(paramHttpServletRequest, paramHttpServletResponse);
        User localUser = _$1(paramString1, paramString2);
        ShopAdmin localShopAdmin = this._$8.getByUserId(localUser.getId(), localLong);
        if (localShopAdmin == null)
            throw new UserNotInWebsiteException("user '" + localUser.getUsername() + "' not in Website '" + localLong + "'");
        this._$7.updateLoginInfo(localUser.getId(), paramHttpServletRequest.getRemoteAddr());
        this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_user_id_key", localUser.getId());
        this._$1.setAttribute(paramHttpServletRequest, paramHttpServletResponse, "_admin_id_key", localShopAdmin.getId());
        _$1(localShopAdmin.getUsername(), null, null, paramHttpServletRequest, paramHttpServletResponse);
        return localShopAdmin;
    }

    public ShopAdmin getAdmin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite) {
        Long localLong1 = paramWebsite.getId();
        Long localLong2 = (Long) this._$1.getAttribute(paramHttpServletRequest, "_admin_id_key");
        if (localLong2 != null) {
            ShopAdmin localShopAdmin = this._$8.findById(localLong2);
            if ((localShopAdmin != null) && (localShopAdmin.getWebsite().getId().equals(localLong1)))
                return localShopAdmin;
            Long localLong3 = localShopAdmin.getAdmin().getUser().getId();
            localShopAdmin = this._$8.getByUserId(localLong3, localLong1);
            return localShopAdmin;
        }
        return null;
    }

    private User _$1(String paramString1, String paramString2)
            throws UsernameNotFoundException, BadCredentialsException {
        User localUser = this._$7.getByUsername(paramString1);
        if (localUser == null)
            throw new UsernameNotFoundException("username not found: " + paramString1);
        if (!this._$4.isPasswordValid(localUser.getPassword(), paramString2))
            throw new BadCredentialsException("password invalid!");
        return localUser;
    }

    @Autowired
    public void setShopMemberMng(ShopMemberMng paramShopMemberMng) {
        this._$9 = paramShopMemberMng;
    }

    @Autowired
    public void setShopConfigMng(ShopConfigMng paramShopConfigMng) {
        this._$5 = paramShopConfigMng;
    }

    @Autowired
    public void setShopAdminMng(ShopAdminMng paramShopAdminMng) {
        this._$8 = paramShopAdminMng;
    }

    @Autowired
    public void setUserMng(UserMng paramUserMng) {
        this._$7 = paramUserMng;
    }

    @Autowired
    public void setPwdEncoder(PwdEncoder paramPwdEncoder) {
        this._$4 = paramPwdEncoder;
    }

    @Autowired
    public void setRememberMeService(RememberMeService paramRememberMeService) {
        this._$3 = paramRememberMeService;
    }

    @Autowired
    public void setMemberMng(MemberMng paramMemberMng) {
        this._$6 = paramMemberMng;
    }

    @Autowired
    public void setSessionProvider(SessionProvider paramSessionProvider) {
        this._$1 = paramSessionProvider;
    }

    @Autowired
    public void setShoppingSvc(ShoppingSvc paramShoppingSvc) {
        this._$2 = paramShoppingSvc;
    }
}

