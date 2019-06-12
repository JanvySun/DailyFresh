package cn.hnist.service.impl;

import cn.hnist.dao.GoodsDao;
import cn.hnist.pojo.GoodsType;
import cn.hnist.pojo.IndexGoodsBanner;
import cn.hnist.pojo.IndexPromotionBanner;
import cn.hnist.pojo.IndexTypeBanner;
import cn.hnist.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsType> findAllGoodsType() {
        return goodsDao.findAllGoodsType();
    }

    @Override
    public List<IndexGoodsBanner> findAllIndexBanner() {
        return goodsDao.findAllIndexBanner();
    }

    @Override
    public List<IndexPromotionBanner> findAllPromotionBanner() {
        return goodsDao.findAllPromotionBanner();
    }

    @Override
    public List<IndexTypeBanner> findAllTypeGoodsBanner() {
        return null;
    }
}
