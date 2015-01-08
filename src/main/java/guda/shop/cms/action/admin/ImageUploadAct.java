package guda.shop.cms.action.admin;

import guda.shop.cms.web.SiteUtils;
import guda.shop.common.file.FileNameUtils;
import guda.shop.common.image.ImageUtils;
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
    /*  35 */   private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
    private static final String RESULT_PAGE = "/common/iframe_upload";
    private static final String RESULT_SWITCH_PAGE = "/common/iframe_switch_upload";
    private static final String RESULT_BIG_PAGE = "/common/iframe_big_upload";
    private static final String RESULT_AMP_PAGE = "/common/iframe_amp_upload";
    private ServletContext servletContext;

    @RequestMapping({"/common/o_upload_image.do"})
    public String execute(String fileName, Integer uploadNum, Integer zoomWidth, Integer zoomHeight, @RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
/*  60 */
        WebErrors errors = validate(fileName, file, request);
/*  61 */
        if (errors.hasErrors()) {
/*  62 */
            model.addAttribute("error", errors.getErrors().get(0));
/*  63 */
            return "/common/iframe_upload";
        }
/*  65 */
        Website web = SiteUtils.getWeb(request);
/*  66 */
        String real = this.servletContext.getRealPath(web.getUploadRel());
/*  67 */
        String dateDir = FileNameUtils.genPathName();

/*  69 */
        File root = new File(real, dateDir);
/*  70 */
        if (!root.exists()) {
/*  71 */
            root.mkdirs();
        }

/*  74 */
        if (StringUtils.isBlank(fileName)) {
/*  75 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/*  76 */
            fileName = FileNameUtils.genFileName(ext);
        } else {
/*  78 */
            fileName = FilenameUtils.getName(fileName);
        }

/*  81 */
        File tempFile = new File(root, fileName);

/*  83 */
        String ctx = request.getContextPath();
/*  84 */
        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
/*  85 */
        model.addAttribute("zoomWidth", zoomWidth);
/*  86 */
        model.addAttribute("zoomHeight", zoomHeight);
        try {
/*  88 */
            file.transferTo(tempFile);
/*  89 */
            model.addAttribute("uploadPath", relPath);
/*  90 */
            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {
/*  92 */
            model.addAttribute("error", e.getMessage());
/*  93 */
            log.error("upload file error!", e);
        } catch (IOException e) {
/*  95 */
            model.addAttribute("error", e.getMessage());
/*  96 */
            log.error("upload file error!", e);
        }
/*  98 */
        return "/common/iframe_upload";
    }

    @RequestMapping({"/common/o_upload_switch_image.do"})
    public String executeSwitch(String fileName, Integer uploadNum, @RequestParam(value = "uploadFileSwitch", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
/* 108 */
        WebErrors errors = validate(fileName, file, request);
/* 109 */
        if (errors.hasErrors()) {
/* 110 */
            model.addAttribute("error", errors.getErrors().get(0));
/* 111 */
            return "/common/iframe_switch_upload";
        }
/* 113 */
        Website web = SiteUtils.getWeb(request);
/* 114 */
        String real = this.servletContext.getRealPath(web.getUploadRel());
/* 115 */
        String dateDir = FileNameUtils.genPathName();

/* 117 */
        File root = new File(real, dateDir);
/* 118 */
        if (!root.exists()) {
/* 119 */
            root.mkdirs();
        }

/* 122 */
        if (StringUtils.isBlank(fileName)) {
/* 123 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/* 124 */
            fileName = FileNameUtils.genFileName(ext);
        } else {
/* 126 */
            fileName = FilenameUtils.getName(fileName);
        }

/* 129 */
        File tempFile = new File(root, fileName);

/* 131 */
        String ctx = request.getContextPath();
/* 132 */
        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
        try {
/* 135 */
            file.transferTo(tempFile);
/* 136 */
            model.addAttribute("uploadPath", relPath);
/* 137 */
            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {
/* 139 */
            model.addAttribute("error", e.getMessage());
/* 140 */
            log.error("upload file error!", e);
        } catch (IOException e) {
/* 142 */
            model.addAttribute("error", e.getMessage());
/* 143 */
            log.error("upload file error!", e);
        }
/* 145 */
        return "/common/iframe_switch_upload";
    }

    @RequestMapping({"/common/o_upload_big_image.do"})
    public String executeBig(String fileName, Integer uploadNum, @RequestParam(value = "uploadFileBig", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
/* 155 */
        WebErrors errors = validate(fileName, file, request);
/* 156 */
        if (errors.hasErrors()) {
/* 157 */
            model.addAttribute("error", errors.getErrors().get(0));
/* 158 */
            return "/common/iframe_big_upload";
        }
/* 160 */
        Website web = SiteUtils.getWeb(request);
/* 161 */
        String real = this.servletContext.getRealPath(web.getUploadRel());
/* 162 */
        String dateDir = FileNameUtils.genPathName();

/* 164 */
        File root = new File(real, dateDir);
/* 165 */
        if (!root.exists()) {
/* 166 */
            root.mkdirs();
        }

/* 169 */
        if (StringUtils.isBlank(fileName)) {
/* 170 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/* 171 */
            fileName = FileNameUtils.genFileName(ext);
        } else {
/* 173 */
            fileName = FilenameUtils.getName(fileName);
        }

/* 176 */
        File tempFile = new File(root, fileName);

/* 178 */
        String ctx = request.getContextPath();
/* 179 */
        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
        try {
/* 181 */
            file.transferTo(tempFile);
/* 182 */
            model.addAttribute("uploadPath", relPath);
/* 183 */
            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {
/* 185 */
            model.addAttribute("error", e.getMessage());
/* 186 */
            log.error("upload file error!", e);
        } catch (IOException e) {
/* 188 */
            model.addAttribute("error", e.getMessage());
/* 189 */
            log.error("upload file error!", e);
        }
/* 191 */
        return "/common/iframe_big_upload";
    }

    @RequestMapping({"/common/o_upload_amp_image.do"})
    public String executeAmp(String fileName, Integer uploadNum, @RequestParam(value = "uploadFileAmp", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
/* 201 */
        WebErrors errors = validate(fileName, file, request);
/* 202 */
        if (errors.hasErrors()) {
/* 203 */
            model.addAttribute("error", errors.getErrors().get(0));
/* 204 */
            return "/common/iframe_amp_upload";
        }
/* 206 */
        Website web = SiteUtils.getWeb(request);
/* 207 */
        String real = this.servletContext.getRealPath(web.getUploadRel());
/* 208 */
        String dateDir = FileNameUtils.genPathName();

/* 210 */
        File root = new File(real, dateDir);
/* 211 */
        if (!root.exists()) {
/* 212 */
            root.mkdirs();
        }

/* 215 */
        if (StringUtils.isBlank(fileName)) {
/* 216 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/* 217 */
            fileName = FileNameUtils.genFileName(ext);
        } else {
/* 219 */
            fileName = FilenameUtils.getName(fileName);
        }

/* 222 */
        File tempFile = new File(root, fileName);

/* 224 */
        String ctx = request.getContextPath();
/* 225 */
        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
        try {
/* 227 */
            file.transferTo(tempFile);
/* 228 */
            model.addAttribute("uploadPath", relPath);
/* 229 */
            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {
/* 231 */
            model.addAttribute("error", e.getMessage());
/* 232 */
            log.error("upload file error!", e);
        } catch (IOException e) {
/* 234 */
            model.addAttribute("error", e.getMessage());
/* 235 */
            log.error("upload file error!", e);
        }
/* 237 */
        return "/common/iframe_amp_upload";
    }

    private WebErrors validate(String fileName, MultipartFile file, HttpServletRequest request) {
/* 242 */
        WebErrors errors = WebErrors.create(request);
/* 243 */
        if (file == null) {
/* 244 */
            errors.addErrorCode("imageupload.error.noFileToUpload");
/* 245 */
            return errors;
        }
/* 247 */
        if (StringUtils.isBlank(fileName)) {
/* 248 */
            fileName = file.getOriginalFilename();
        }
/* 250 */
        String ext = FilenameUtils.getExtension(fileName);
/* 251 */
        if (!ImageUtils.isImage(ext)) {
/* 252 */
            errors.addErrorCode("imageupload.error.notSupportExt", new Object[]{ext});
/* 253 */
            return errors;
        }
/* 255 */
        return errors;
    }

    public void setServletContext(ServletContext servletContext) {
/* 261 */
        this.servletContext = servletContext;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.ImageUploadAct
 * JD-Core Version:    0.6.2
 */