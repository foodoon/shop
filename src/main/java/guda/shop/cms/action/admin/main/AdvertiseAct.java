package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Advertise;
import guda.shop.cms.manager.AdspaceMng;
import guda.shop.cms.manager.AdvertiseMng;
import guda.shop.cms.web.RequestUtils1;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Controller
public class AdvertiseAct {
       private static final Logger log = LoggerFactory.getLogger(AdvertiseAct.class);

    @Autowired
    private AdspaceMng adspaceMng;

    @Autowired
    private AdvertiseMng manager;


    @RequestMapping({"/advertise/v_list.do"})
    public String list(Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(queryAdspaceId,
       queryEnabled, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));

        if (queryAdspaceId != null) {

            model.addAttribute("queryAdspaceId", queryAdspaceId);
        }

        if (queryEnabled != null) {

            model.addAttribute("queryEnabled", queryEnabled);
        }

        return "advertise/list";
    }

    @RequestMapping({"/advertise/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {

        List adspaceList = this.adspaceMng.getList();

        model.addAttribute("adspaceList", adspaceList);

        return "advertise/add";
    }

    @RequestMapping({"/advertise/v_edit.do"})
    public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Advertise advertise = this.manager.findById(id);

        model.addAttribute("advertise", advertise);

        model.addAttribute("attr", advertise.getAttr());

        model.addAttribute("adspaceList", this.adspaceMng.getList());

        model.addAttribute("pageNo", pageNo);

        return "advertise/edit";
    }

    @RequestMapping({"/advertise/o_save.do"})
    public String save(Advertise bean, Integer adspaceId, HttpServletRequest request, ModelMap model) {

        Map<String, String> attr = RequestUtils1.getRequestMap(request, "attr_");


        Set<String> toRemove = new HashSet<String>();

        for (Entry entry : attr.entrySet()) {

            if (StringUtils.isBlank((String) entry.getValue())) {

                toRemove.add((String) entry.getKey());
            }
        }

        for (String key : toRemove) {

            attr.remove(key);
        }

        bean = this.manager.save(bean, adspaceId, attr);

        log.info("save advertise id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/advertise/o_update.do"})
    public String update(Integer queryAdspaceId, Boolean queryEnabled, Advertise bean, Integer adspaceId, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Map<String, String> attr = RequestUtils1.getRequestMap(request, "attr_");


        Set<String> toRemove = new HashSet<String>();

        for (Entry entry : attr.entrySet()) {

            if (StringUtils.isBlank((String) entry.getValue())) {

                toRemove.add((String) entry.getKey());
            }
        }

        for (String key : toRemove) {

            attr.remove(key);
        }

        bean = this.manager.update(bean, adspaceId, attr);

        log.info("update advertise id={}.", bean.getId());

        return list(queryAdspaceId, queryEnabled, pageNo, request, model);
    }

    @RequestMapping({"/advertise/o_delete.do"})
    public String delete(Integer[] ids, Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Advertise[] beans = this.manager.deleteByIds(ids);

        for (Advertise bean : beans) {

            log.info("delete advertise id={}", bean.getId());
        }

        return list(queryAdspaceId, queryEnabled, pageNo, request, model);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AdvertiseAct
 * JD-Core Version:    0.6.2
 */