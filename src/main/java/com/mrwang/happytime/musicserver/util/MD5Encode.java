package com.mrwang.happytime.musicserver.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Encode {

    public static String encode(String salt,String password){
        String hashAlgorithName = "MD5";
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash obj = new SimpleHash(hashAlgorithName,password,byteSource,1024);
        return obj.toHex();
    }


}
