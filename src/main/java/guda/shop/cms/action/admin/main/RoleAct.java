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

        List list = this.manager.getList();

        model.addAttribute("list", list);

        return "role/list";
    }

    @RequestMapping({"/role/v_add.do"})
    public String add(ModelMap model) {

        return "role/add";
    }

    @RequestMapping({"/role/v_edit.do"})
    public String edit(Integer id, HttpServletRequest request, ModelMap model) {

        model.addAttribute("role", this.manager.findById(id));

        return "role/edit";
    }

    @RequestMapping({"/role/o_save.do"})
    public String save(Role bean, String[] perms, HttpServletRequest request, ModelMap model) {

        bean = this.manager.save(bean, splitPerms(perms));

        return "redirect:v_list.do";
    }

    @RequestMapping({"/role/o_update.do"})
    public String update(Role bean, String[] perms, HttpServletRequest request, ModelMap model) {

        bean = this.manager.update(bean, splitPerms(perms));

        return list(request, model);
    }

    @RequestMapping({"/role/o_delete.do"})
    public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {

        Role[] beans = this.manager.deleteByIds(ids);

        return list(request, model);
    }

    private Set<String> splitPerms(String[] perms) {

        Set set = new HashSet();

        if (perms != null) {

            for (String perm : perms) {

                for (String p : StringUtils.split(perm, ',')) {

                    if (!StringUtils.isBlank(p)) {

                        set.add(p);
                    }
                }
            }
        }

        return set;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.RoleAct
 * JD-Core Version:    0.6.2
 */