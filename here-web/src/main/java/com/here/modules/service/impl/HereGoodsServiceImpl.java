package com.here.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.entity.HereGoods;
import com.here.modules.mapper.HereGoodsMapper;
import com.here.modules.service.HereGoodsService;
import org.springframework.stereotype.Service;

@Service
public class HereGoodsServiceImpl  extends ServiceImpl<HereGoodsMapper, HereGoods> implements HereGoodsService {
}
