package cn.hnist.dao;

import cn.hnist.pojo.GoodsType;
import cn.hnist.pojo.IndexGoodsBanner;
import cn.hnist.pojo.IndexPromotionBanner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {

    // ------------ 表 goods_type CRUD ------------
    /**
     * 查询所有type
     * @return : GoodsType结果集
     */
    @Select("select * from goods_type")
    List<GoodsType> findAllGoodsType();

    /**
     * 向goods_type表中添加一条数据
     * @param type : GoodsType对象
     */
    @Insert("insert into goods_type(name,logo,image) values(#{name},#{logo},#{image})")
    void addType(GoodsType type);
    // ===========================================


    // ------------ 表 index_banner CRUD ------------
    /**
     * 查询index_banner(首页轮播商品)中信息
     * @return : 结果集
     */
    @Select("select * from index_banner")
    List<IndexGoodsBanner> findAllIndexBanner();
    // =============================================


    // ------------ 表 index_promotion CRUD ------------
    /**
     * 查询index_promotion(首页促销活动)中所有信息
     * @return : 查询结果集
     */
    @Select("select * from index_promotion order by od")
    List<IndexPromotionBanner> findAllPromotionBanner();

    /**
     * 添加index_promotion表记录
     */
    @Insert("insert into index_promotion(name,url,image,od) values(#{name},#{url},#{image},${od})")
    void addPromotionBanner(IndexPromotionBanner promotionBanner);
    // ================================================
}
