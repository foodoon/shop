package guda.shop.core.manager.impl;

import guda.shop.common.page.Pagination;
import guda.shop.common.security.encoder.PwdEncoder;
import guda.shop.core.dao.UserDao;
import guda.shop.core.entity.EmailSender;
import guda.shop.core.entity.MessageTemplate;
import guda.shop.core.entity.User;
import guda.shop.core.manager.UserMng;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class UserMngImpl
        implements UserMng {
    private PwdEncoder _$2;
    private UserDao _$1;

    public User passwordForgotten(Long paramLong, String paramString, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate) {
        User localUser = findById(paramLong);
        String str1 = StringUtils.remove(UUID.randomUUID().toString(), '-');
        localUser.setResetKey(str1);
        String str2 = RandomStringUtils.randomNumeric(10);
        localUser.setResetPwd(str2);
        senderEmail(localUser.getId(), localUser.getUsername(), paramString, localUser.getEmail(), localUser.getResetKey(), localUser.getResetPwd(), paramEmailSender, paramMessageTemplate);
        return localUser;
    }

    public void senderActiveEmail(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final EmailSender paramEmailSender, final MessageTemplate paramMessageTemplate)
            throws MailException {
        JavaMailSenderImpl localJavaMailSenderImpl = new JavaMailSenderImpl();
        localJavaMailSenderImpl.setHost(paramEmailSender.getHost());
        localJavaMailSenderImpl.setUsername(paramEmailSender.getUsername());
        localJavaMailSenderImpl.setPassword(paramEmailSender.getPassword());
        localJavaMailSenderImpl.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage paramAnonymousMimeMessage)
                    throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper localMimeMessageHelper = new MimeMessageHelper(paramAnonymousMimeMessage, false, paramEmailSender.getEncoding());
                localMimeMessageHelper.setSubject(paramMessageTemplate.getActiveTitle());
                localMimeMessageHelper.setTo(paramString3);
                localMimeMessageHelper.setFrom(paramEmailSender.getUsername(), paramEmailSender.getPersonal());
                String str = paramMessageTemplate.getActiveTxt();
                str = StringUtils.replace(str, "${userName}", paramString1);
                str = StringUtils.replace(str, "${activationCode}", paramString4);
                str = StringUtils.replace(str, "${base}", paramString2);
                localMimeMessageHelper.setText(str, true);
            }
        });
    }

    public void senderEmail(final Long paramLong, final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final EmailSender paramEmailSender, final MessageTemplate paramMessageTemplate)
            throws MailException {
        JavaMailSenderImpl localJavaMailSenderImpl = new JavaMailSenderImpl();
        localJavaMailSenderImpl.setHost(paramEmailSender.getHost());
        localJavaMailSenderImpl.setUsername(paramEmailSender.getUsername());
        localJavaMailSenderImpl.setPassword(paramEmailSender.getPassword());
        localJavaMailSenderImpl.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage paramAnonymousMimeMessage)
                    throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper localMimeMessageHelper = new MimeMessageHelper(paramAnonymousMimeMessage, false, paramEmailSender.getEncoding());
                localMimeMessageHelper.setSubject(paramMessageTemplate.getSubject());
                localMimeMessageHelper.setTo(paramString3);
                localMimeMessageHelper.setFrom(paramEmailSender.getUsername(), paramEmailSender.getPersonal());
                String str = paramMessageTemplate.getText();
                str = StringUtils.replace(str, "${uid}", String.valueOf(paramLong));
                str = StringUtils.replace(str, "${username}", paramString1);
                str = StringUtils.replace(str, "${activationCode}", paramString4);
                str = StringUtils.replace(str, "${resetPwd}", paramString5);
                str = StringUtils.replace(str, "${base}", paramString2);
                localMimeMessageHelper.setText(str, true);
            }
        });
    }

    public User resetPassword(Long paramLong) {
        User localUser = findById(paramLong);
        localUser.setPassword(this._$2.encodePassword(localUser.getResetPwd()));
        localUser.setResetKey(null);
        localUser.setResetPwd(null);
        return localUser;
    }

    public boolean isPasswordValid(Long paramLong, String paramString) {
        User localUser = findById(paramLong);
        return this._$2.isPasswordValid(localUser.getPassword(), paramString);
    }

    public boolean usernameExist(String paramString) {
        return getByUsername(paramString) != null;
    }

    public boolean emailExist(String paramString) {
        return getByEmail(paramString) != null;
    }

    public User getByUsername(String paramString) {
        return this._$1.getByUsername(paramString);
    }

    public User getByEmail(String paramString) {
        return this._$1.getByEmail(paramString);
    }

    public void updateLoginInfo(Long paramLong, String paramString) {
        User localUser = findById(paramLong);
        localUser.setLoginCount(Long.valueOf(localUser.getLoginCount().longValue() + 1L));
        String str = localUser.getCurrentLoginIp();
        Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isBlank(str)) {
            localUser.setLastLoginIp(paramString);
            localUser.setLastLoginTime(localTimestamp);
        } else {
            localUser.setLastLoginIp(localUser.getCurrentLoginIp());
            localUser.setLastLoginTime(localUser.getCurrentLoginTime());
        }
        localUser.setCurrentLoginIp(paramString);
        localUser.setCurrentLoginTime(localTimestamp);
    }

    public User register(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate) {
        User localUser = new User();
        localUser.setUsername(paramString1);
        localUser.setPassword(paramString2);
        localUser.setEmail(paramString3);
        localUser.setRegisterIp(paramString4);
        if (paramDate != null)
            localUser.setCreateTime(paramDate);
        return save(localUser);
    }

    public User register(String paramString1, String paramString2, String paramString3, String paramString4) {
        return register(paramString1, paramString2, paramString3, paramString4, new Date());
    }

    public Pagination getPage(int paramInt1, int paramInt2) {
        return this._$1.getPage(paramInt1, paramInt2);
    }

    public User findById(Long paramLong) {
        return this._$1.findById(paramLong);
    }

    public User save(User paramUser) {
        String str = this._$2.encodePassword(paramUser.getPassword());
        paramUser.setPassword(str);
        paramUser.init();
        return this._$1.save(paramUser);
    }

    public User updateUser(Long paramLong, String paramString1, String paramString2) {
        User localUser = findById(paramLong);
        if (!StringUtils.isBlank(paramString1))
            localUser.setPassword(this._$2.encodePassword(paramString1));
        if (!StringUtils.isBlank(paramString2))
            localUser.setEmail(paramString2);
        return localUser;
    }

    public User deleteById(Long paramLong) {
        return this._$1.deleteById(paramLong);
    }

    public User[] deleteByIds(Long[] paramArrayOfLong) {
        User[] arrayOfUser = new User[paramArrayOfLong.length];
        for (int i = 0; i < paramArrayOfLong.length; i++)
            arrayOfUser[i] = deleteById(paramArrayOfLong[i]);
        return arrayOfUser;
    }

    @Autowired
    public void setPwdEncoder(PwdEncoder paramPwdEncoder) {
        this._$2 = paramPwdEncoder;
    }

    @Autowired
    public void setDao(UserDao paramUserDao) {
        this._$1 = paramUserDao;
    }
}

