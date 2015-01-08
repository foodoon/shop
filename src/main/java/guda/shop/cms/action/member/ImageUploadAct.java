package guda.shop.cms.action.member;

import guda.shop.cms.web.SiteUtils;
import guda.shop.common.file.FileNameUtils;
import guda.shop.common.image.ImageUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class ImageUploadAct
        implements ServletContextAware {
    public static final String ERROR = "error";
    /*  38 */   private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
    private static final String RESULT_PAGE = "iframe_upload";
    private ServletContext servletContext;

    @RequestMapping({"/common/o_upload_image.jspx"})
    public String execute(String fileName, Integer uploadNum, Integer zoomWidth, Integer zoomHeight, @RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
/*  56 */
        WebErrors errors = validate(fileName, file, request);
/*  57 */
        Website web = SiteUtils.getWeb(request);
/*  58 */
        if (errors.hasErrors()) {
/*  59 */
            model.addAttribute("error", errors.getErrors().get(0));
/*  60 */
            return web.getTplSys("member",
/*  61 */         MessageResolver.getMessage(request, 
/*  61 */         "iframe_upload", new Object[0]));
        }
/*  63 */
        String real = this.servletContext.getRealPath(web.getUploadRel());
/*  64 */
        String dateDir = FileNameUtils.genPathName();

/*  66 */
        File root = new File(real, dateDir);
/*  67 */
        if (!root.exists()) {
/*  68 */
            root.mkdirs();
        }

/*  71 */
        if (StringUtils.isBlank(fileName)) {
/*  72 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/*  73 */
            fileName = FileNameUtils.genFileName(ext);
        } else {
/*  75 */
            fileName = FilenameUtils.getName(fileName);
        }

/*  78 */
        File tempFile = new File(root, fileName);

/*  80 */
        String ctx = request.getContextPath();
/*  81 */
        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
/*  82 */
        model.addAttribute("zoomWidth", zoomWidth);
/*  83 */
        model.addAttribute("zoomHeight", zoomHeight);
        try {
/*  85 */
            file.transferTo(tempFile);
/*  86 */
            model.addAttribute("uploadPath", relPath);

/*  88 */
            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {
/*  90 */
            model.addAttribute("error", e.getMessage());
/*  91 */
            log.error("upload file error!", e);
        } catch (IOException e) {
/*  93 */
            model.addAttribute("error", e.getMessage());
/*  94 */
            log.error("upload file error!", e);
        }
/*  96 */
        model.addAttribute("zoomWidth", zoomWidth);
/*  97 */
        model.addAttribute("zoomHeight", zoomHeight);
/*  98 */
        return web.getTplSys("member",
/*  99 */       MessageResolver.getMessage(request, 
/*  99 */       "iframe_upload", new Object[0]));
    }

    private WebErrors validate(String fileName, MultipartFile file, HttpServletRequest request) {
/* 105 */
        WebErrors errors = WebErrors.create(request);
/* 106 */
        if (file == null) {
/* 107 */
            errors.addErrorCode("imageupload.error.noFileToUpload");
/* 108 */
            return errors;
        }
/* 110 */
        if (StringUtils.isBlank(fileName)) {
/* 111 */
            fileName = file.getOriginalFilename();
        }
/* 113 */
        String ext = FilenameUtils.getExtension(fileName);
/* 114 */
        if (!ImageUtils.isImage(ext)) {
/* 115 */
            errors.addErrorCode("imageupload.error.notSupportExt", new Object[]{ext});
/* 116 */
            return errors;
        }
/* 118 */
        return errors;
    }

    public void setServletContext(ServletContext servletContext) {
/* 124 */
        this.servletContext = servletContext;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ImageUploadAct
 * JD-Core Version:    0.6.2
 */