package guda.shop.common.fck;

import java.util.HashMap;
import java.util.Map;

public class Command {
    public static final Command GET_FOLDERS = new Command("GetFolders");
    public static final Command GET_FOLDERS_AND_FILES = new Command("GetFoldersAndFiles");
    public static final Command CREATE_FOLDER = new Command("CreateFolder");
    public static final Command FILE_UPLOAD = new Command("FileUpload");
    public static final Command QUICK_UPLOAD = new Command("QuickUpload");
    private static final Map<String, Command> _$2 = new HashMap(3);
    private static final Map<String, Command> _$1 = new HashMap(2);
    private String _$3;

    private Command(String paramString) {
        this._$3 = paramString;
    }

    public static Command valueOf(String paramString) {
        if (Utils.isEmpty(paramString))
            throw new NullPointerException("Name is null or empty");
        Command localCommand = (Command) _$2.get(paramString);
        if (localCommand == null)
            localCommand = (Command) _$1.get(paramString);
        if (localCommand == null)
            throw new IllegalArgumentException("No command const " + paramString);
        return localCommand;
    }

    public static boolean isValidForGet(String paramString) {
        return _$2.containsKey(paramString);
    }

    public static boolean isValidForPost(String paramString) {
        return _$1.containsKey(paramString);
    }

    public static Command getCommand(String paramString) {
        try {
            return valueOf(paramString);
        } catch (Exception localException) {
        }
        return null;
    }

    public String getName() {
        return this._$3;
    }

    public boolean equals(Object paramObject) {
        if (this == paramObject)
            return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass()))
            return false;
        Command localCommand = (Command) paramObject;
        return this._$3.equals(localCommand.getName());
    }

    public int hashCode() {
        return this._$3.hashCode();
    }

    public String toString() {
        return this._$3;
    }

    static {
        _$2.put(GET_FOLDERS.getName(), GET_FOLDERS);
        _$2.put(GET_FOLDERS_AND_FILES.getName(), GET_FOLDERS_AND_FILES);
        _$2.put(CREATE_FOLDER.getName(), CREATE_FOLDER);
        _$1.put(FILE_UPLOAD.getName(), FILE_UPLOAD);
        _$1.put(QUICK_UPLOAD.getName(), QUICK_UPLOAD);
    }
}

