package cn.hnist.dao;

import cn.hnist.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {

    /**
     * 查询所有type
     */
    @Select("select * from goods_type")
    List<GoodsType> findAllGoodsType();

    /**
     * 向goods_type表中添加一条数据
     */
    @Insert("insert into goods_type(name,logo,image) values(#{name},#{logo},#{image})")
    void addType(GoodsType type);

    /**
     * 查询index_banner(首页轮播商品)中信息
     */
    @Select("select * from index_banner order by od")
    List<IndexGoodsBanner> findAllIndexBanner();

    /**
     * 查询index_promotion(首页促销活动)中所有信息
     */
    @Select("select * from index_promotion order by od")
    List<IndexPromotionBanner> findAllPromotionBanner();

    /**
     * 添加index_promotion表记录
     */
    @Insert("insert into index_promotion(name,url,image,od) values(#{name},#{url},#{image},${od})")
    void addPromotionBanner(IndexPromotionBanner promotionBanner);

    /**
     * 根据id查询sku表
     */
    @Select("select * from goods_sku where id=#{id}")
    GoodsSKU findGoodsSKUById(Integer id);

    /**
     * 根据typeID查询表index_type_banner内容
     * 并获取其对应sku的内容
     */
    @Select("select * from index_type_goods where type_id=#{typeId} and display_type=1 order by od")
    List<IndexTypeBanner> findImageIndexTypeByTypeId(Integer typeId);

    /**
     * 根据typeID查询表index_type_banner内容
     * 并获取其对应sku内容
     */
    @Select("select * from index_type_goods where type_id=#{typeId} and display_type=0 order by od")
    List<IndexTypeBanner> findTitleIndexTypeByTypeId(Integer typeId);

    /**
     * 根据skuid查询order_goods表内容
     */
    @Select("select * from order_goods where sku_id=#{id}")
    List<OrderGoods> findOrderGoodsBySkuId(Integer id);

    /**
     * 根据商品种类查询sku
     */
    @Select("select * from goods_sku where type_id=#{id} order by id desc")
    List<GoodsSKU> findAllGoodsSKUByType(Integer id);

    /**
     * 根据查询goods_type
     */
    @Select("select * from goods_type where id=#{id}")
    GoodsType findGoodsTypeById(Integer id);

    /**
     * 根据id查询goods
     */
    @Select("select * from goods where id=#{id}")
    Goods findGoodsById(Integer goods_id);

    /**
     * 按种类获取sku并按价格排序
     */
    @Select("select * from goods_sku where type_id=#{id} order by price")
    List<GoodsSKU> findAllGoodsSKUByTypeAndSortPrice(Integer id);

    /**
     * 按种类获取SKu并按销量排序
     */
    @Select("select * from goods_sku where type_id=#{id} order by sales desc")
    List<GoodsSKU> findAllGoodsSKUByTypeAndSortSales(Integer id);

    /**
     * 根据商品种类查询sku
     */
    @Select("select * from goods_sku where type_id=#{id} order by id")
    List<GoodsSKU> findGoodsSKUByType(Integer id);

    /**
     * 根据名称模糊查询sku
     */
    @Select("select * from goods_sku where name like concat('%', #{name}, '%')")
    List<GoodsSKU> findGoodsSKUByName(String name);

    /**
     * 更新商品sku库存和销量
     */
    @Update("update goods_sku set stock=#{stock},sales=#{sales} where id=#{id}")
    void updataSkuStokeAndSales(GoodsSKU sku);
}
