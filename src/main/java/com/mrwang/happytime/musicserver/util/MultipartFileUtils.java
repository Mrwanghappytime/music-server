package com.mrwang.happytime.musicserver.util;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtils {
    public static String getFileType(MultipartFile file) {
        String name = file.getOriginalFilename();
        return name.substring(name.lastIndexOf(".")+1);
    }
}
