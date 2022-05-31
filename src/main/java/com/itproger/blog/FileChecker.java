package com.itproger.blog;

import java.io.File;

public class FileChecker {
    public static int CheckLargestId(File dir) {
        int result = 0;

        String[] listFiles = dir.list();
        for (String file : listFiles){
            int dotIndex = file.lastIndexOf('.');
            String fileWOindex = (dotIndex == -1) ? file : file.substring(0, dotIndex);
            int intFile = Integer.parseInt(fileWOindex);
            if(intFile > result) {
                result = intFile;
            }
        }
        System.out.println("Самый большой айди у нас: " + result);
        return result;
    }

    public static File DirCreator(String path) {
        File file = new File(path);
        if (file.exists() != true) {
            System.out.println("Папки не существует :(");
            file.mkdir();
        }
        return file;
    }
}
