package com.herohuang.weixin.service.impl;

import com.herohuang.weixin.service.IWeiXinPayService;
import com.herohuang.weixin.dao.IWeiXinPayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Acheron on 2015/10/29.
 */
@Service
public class WeiXinPayServiceImpl implements IWeiXinPayService {
    @Autowired
    IWeiXinPayDao dao;

}
