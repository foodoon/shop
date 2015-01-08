package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Poster;
import guda.shop.cms.manager.PosterMng;
import guda.shop.common.web.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class PosterAct {

    @Autowired
    private PosterMng manager;

    @RequestMapping({"/poster/v_list.do"})
    public String list(HttpServletRequest request, ModelMap model) {
/* 28 */
        List listPoster = this.manager.getPage();
/* 29 */
        model.addAttribute("listPoster", listPoster);
/* 30 */
        return "poster/list";
    }

    @RequestMapping({"/poster/o_updateCare.do"})
    public String o_updateCare(String val, Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 36 */
        Poster bean = this.manager.findById(id);
/* 37 */
        bean.setInterUrl(val);
/* 38 */
        this.manager.update(bean);
/* 39 */
        ResponseUtils.renderJson(response, "success");
/* 40 */
        return null;
    }

    @RequestMapping({"/poster/o_update.do"})
    public String edit(String[] picUrl, String[] interUrl, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 46 */
        if ((picUrl != null) && (picUrl.length > 0)) {
/* 47 */
            for (int i = 0; i < picUrl.length; i++) {
/* 48 */
                Poster p = new Poster();
/* 49 */
                p.setTime(new Date());
/* 50 */
                p.setPicUrl(picUrl[i]);
/* 51 */
                p.setInterUrl(interUrl[i]);
/* 52 */
                this.manager.saveOrUpdate(p);
            }
        }

/* 56 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/poster/o_delPoster.do"})
    public String delete(Integer id, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 62 */
        this.manager.deleteById(id);
/* 63 */
        ResponseUtils.renderJson(response, "success");
/* 64 */
        return null;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.PosterAct
 * JD-Core Version:    0.6.2
 */