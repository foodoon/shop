package guda.shop.core.manager;

import guda.shop.common.page.Pagination;
iimport guda.shop.ore.entity.EmailSender;
imimport guda.shop.re.entity.MessageTemplate;
import guda.shop.core.entity.User;
import java.util.Date;
import org.springframework.mail.MailException;

public abstract interface UserMng
{
  public abstract User passwordForgotten(Long paramLong, String paramString, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate);

  public abstract void senderEmail(Long paramLong, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate)
    throws MailException;

  public abstract void senderActiveEmail(String paramString1, String paramString2, String paramString3, String paramString4, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate)
    throws MailException;

  public abstract User resetPassword(Long paramLong);

  public abstract boolean isPasswordValid(Long paramLong, String paramString);

  public abstract boolean usernameExist(String paramString);

  public abstract boolean emailExist(String paramString);

  public abstract User getByUsername(String paramString);

  public abstract User getByEmail(String paramString);

  public abstract void updateLoginInfo(Long paramLong, String paramString);

  public abstract User register(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate);

  public abstract User register(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract User findById(Long paramLong);

  public abstract User save(User paramUser);

  public abstract User updateUser(Long paramLong, String paramString1, String paramString2);

  public abstract User deleteById(Long paramLong);

  public abstract User[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.UserMng
 * JD-Core Version:    0.6.2
 */