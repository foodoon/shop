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

    @RequestMapping({"/poster/list.do"})
    public String list(HttpServletRequest request, ModelMap model) {

        List listPoster = this.manager.getPage();

        model.addAttribute("listPoster", listPoster);

        return "poster/list";
    }

    @RequestMapping({"/poster/o_updateCare.do"})
    public String o_updateCare(String val, Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Poster bean = this.manager.findById(id);

        bean.setInterUrl(val);

        this.manager.update(bean);

        ResponseUtils.renderJsonString(response, "success");

        return null;
    }

    @RequestMapping({"/poster/o_update.do"})
    public String edit(String[] picUrl, String[] interUrl, Integer pageNo, HttpServletRequest request, ModelMap model) {

        if ((picUrl != null) && (picUrl.length > 0)) {

            for (int i = 0; i < picUrl.length; i++) {

                Poster p = new Poster();

                p.setTime(new Date());

                p.setPicUrl(picUrl[i]);

                p.setInterUrl(interUrl[i]);

                this.manager.saveOrUpdate(p);
            }
        }


        return "redirect:list.do";
    }

    @RequestMapping({"/poster/o_delPoster.do"})
    public String delete(Integer id, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        this.manager.deleteById(id);

        ResponseUtils.renderJsonString(response, "success");

        return null;
    }
}

