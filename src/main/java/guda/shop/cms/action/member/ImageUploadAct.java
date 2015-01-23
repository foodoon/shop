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
    private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
    private static final String RESULT_PAGE = "iframe_upload";
    private ServletContext servletContext;

    @RequestMapping({"/common/o_upload_image.htm"})
    public String execute(String fileName, Integer uploadNum, Integer zoomWidth, Integer zoomHeight, @RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validate(fileName, file, request);

        Website web = SiteUtils.getWeb(request);

        if (errors.hasErrors()) {

            model.addAttribute("error", errors.getErrors().get(0));

            return web.getTplSys("member",
                    MessageResolver.getMessage(request,
                            "iframe_upload", new Object[0])
            );
        }

        String real = this.servletContext.getRealPath(web.getUploadRel());

        String dateDir = FileNameUtils.genPathName();


        File root = new File(real, dateDir);

        if (!root.exists()) {

            root.mkdirs();
        }


        if (StringUtils.isBlank(fileName)) {

            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            fileName = FileNameUtils.genFileName(ext);
        } else {

            fileName = FilenameUtils.getName(fileName);
        }


        File tempFile = new File(root, fileName);


        String ctx = request.getContextPath();

        String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;

        model.addAttribute("zoomWidth", zoomWidth);

        model.addAttribute("zoomHeight", zoomHeight);
        try {

            file.transferTo(tempFile);

            model.addAttribute("uploadPath", relPath);


            model.addAttribute("uploadNum", uploadNum);
        } catch (IllegalStateException e) {

            model.addAttribute("error", e.getMessage());

            log.error("upload file error!", e);
        } catch (IOException e) {

            model.addAttribute("error", e.getMessage());

            log.error("upload file error!", e);
        }

        model.addAttribute("zoomWidth", zoomWidth);

        model.addAttribute("zoomHeight", zoomHeight);

        return web.getTplSys("member",
                MessageResolver.getMessage(request,
                        "iframe_upload", new Object[0])
        );
    }

    private WebErrors validate(String fileName, MultipartFile file, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (file == null) {

            errors.addErrorCode("imageupload.error.noFileToUpload");

            return errors;
        }

        if (StringUtils.isBlank(fileName)) {

            fileName = file.getOriginalFilename();
        }

        String ext = FilenameUtils.getExtension(fileName);

        if (!ImageUtils.isImage(ext)) {

            errors.addErrorCode("imageupload.error.notSupportExt", new Object[]{ext});

            return errors;
        }

        return errors;
    }

    public void setServletContext(ServletContext servletContext) {

        this.servletContext = servletContext;
    }
}

