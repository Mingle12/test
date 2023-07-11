package com.Mingle.server.impl;

import com.Mingle.dao.GoodsDao;
import com.Mingle.person.Goods;
import com.Mingle.server.GoodsServer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class GoodsServerImpl extends ServiceImpl<GoodsDao,Goods> implements GoodsServer {

}
