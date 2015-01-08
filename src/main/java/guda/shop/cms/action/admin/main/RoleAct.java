package guda.shop.cms.action.admin.main;

import guda.shop.core.entity.Role;
import guda.shop.core.manager.RoleMng;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RoleAct {

    @Autowired
    private RoleMng manager;

    @RequestMapping({"/role/v_list.do"})
    public String list(HttpServletRequest request, ModelMap model) {
/* 26 */
        List list = this.manager.getList();
/* 27 */
        model.addAttribute("list", list);
/* 28 */
        return "role/list";
    }

    @RequestMapping({"/role/v_add.do"})
    public String add(ModelMap model) {
/* 33 */
        return "role/add";
    }

    @RequestMapping({"/role/v_edit.do"})
    public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 38 */
        model.addAttribute("role", this.manager.findById(id));
/* 39 */
        return "role/edit";
    }

    @RequestMapping({"/role/o_save.do"})
    public String save(Role bean, String[] perms, HttpServletRequest request, ModelMap model) {
/* 45 */
        bean = this.manager.save(bean, splitPerms(perms));
/* 46 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/role/o_update.do"})
    public String update(Role bean, String[] perms, HttpServletRequest request, ModelMap model) {
/* 52 */
        bean = this.manager.update(bean, splitPerms(perms));
/* 53 */
        return list(request, model);
    }

    @RequestMapping({"/role/o_delete.do"})
    public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/* 59 */
        Role[] beans = this.manager.deleteByIds(ids);
/* 60 */
        return list(request, model);
    }

    private Set<String> splitPerms(String[] perms) {
/* 64 */
        Set set = new HashSet();
/* 65 */
        if (perms != null) {
/* 66 */
            for (String perm : perms) {
/* 67 */
                for (String p : StringUtils.split(perm, ',')) {
/* 68 */
                    if (!StringUtils.isBlank(p)) {
/* 69 */
                        set.add(p);
                    }
                }
            }
        }
/* 74 */
        return set;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.RoleAct
 * JD-Core Version:    0.6.2
 */