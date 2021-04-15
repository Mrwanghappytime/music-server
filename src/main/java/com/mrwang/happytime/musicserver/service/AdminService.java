package com.mrwang.happytime.musicserver.service;

import com.mrwang.happytime.musicserver.po.Admin;
import com.mrwang.happytime.musicserver.vo.Result;

public interface AdminService {

    Result findAdmin(Admin admin);
}
