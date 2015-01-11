package guda.shop.common.fck;

import guda.shop.common.web.springmvc.MessageResolver;

import javax.servlet.http.HttpServletRequest;

public class LocalizedMessages {
    private static String _$1(HttpServletRequest paramHttpServletRequest, String paramString, Object[] paramArrayOfObject) {
        return MessageResolver.getMessage(paramHttpServletRequest, paramString, paramArrayOfObject);
    }

    public static String getCompatibleBrowserYes(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.editor.compatibleBrowser.yes", new Object[0]);
    }

    public static String getCompatibleBrowserNo(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.editor.compatibleBrowser.no", new Object[0]);
    }

    public static String getFileUploadEnabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.fileUpload.enabled", new Object[0]);
    }

    public static String getFileUploadDisabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.fileUpload.disabled", new Object[0]);
    }

    public static String getFileRenamedWarning(HttpServletRequest paramHttpServletRequest, String paramString) {
        return _$1(paramHttpServletRequest, "fck.connector.fileUpload.file_renamed_warning", new Object[]{paramString});
    }

    public static String getInvalidFileTypeSpecified(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.fileUpload.invalid_file_type_specified", new Object[0]);
    }

    public static String getFileUploadWriteError(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.fileUpload.write_error", new Object[0]);
    }

    public static String getGetResourcesEnabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.getResources.enabled", new Object[0]);
    }

    public static String getGetResourcesDisabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.getResources.disabled", new Object[0]);
    }

    public static String getGetResourcesReadError(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.getResources.read_error", new Object[0]);
    }

    public static String getCreateFolderEnabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.createFolder.enabled", new Object[0]);
    }

    public static String getCreateFolderDisabled(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.createFolder.disabled", new Object[0]);
    }

    public static String getInvalidCommandSpecified(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.invalid_command_specified", new Object[0]);
    }

    public static String getFolderAlreadyExistsError(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.createFolder.folder_already_exists_error", new Object[0]);
    }

    public static String getInvalidNewFolderNameSpecified(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.createFolder.invalid_new_folder_name_specified", new Object[0]);
    }

    public static String getCreateFolderWriteError(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.createFolder.write_error", new Object[0]);
    }

    public static String getInvalidResouceTypeSpecified(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.invalid_resource_type_specified", new Object[0]);
    }

    public static String getInvalidCurrentFolderSpecified(HttpServletRequest paramHttpServletRequest) {
        return _$1(paramHttpServletRequest, "fck.connector.invalid_current_folder_specified", new Object[0]);
    }
}

