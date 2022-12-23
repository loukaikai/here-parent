package com.here.modules.oauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.oauth.entity.HereGoods;
import com.here.modules.oauth.mapper.HereGoodsMapper;
import com.here.modules.oauth.service.HereGoodsService;
import org.springframework.stereotype.Service;

@Service
public class HereGoodsServiceImpl  extends ServiceImpl<HereGoodsMapper, HereGoods> implements HereGoodsService {
}
