package cn.hnist.service;

import cn.hnist.dao.GoodsDao;
import cn.hnist.pojo.CartVo;
import cn.hnist.pojo.GoodsSKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("redisService")
public class RedisService {

    Jedis jedis = null;

    @Autowired
    GoodsDao goodsDao;

    @PostConstruct
    public void init() {
        try {
            jedis = new Jedis("47.107.251.224",6379);
            jedis.auth("JanvySun");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("无法连接到redis数据库");
        }
    }

    @PreDestroy
    public void destory() {
        jedis.close();
    }

    /**
     * 获取购物车数量
     * @param id : 用户id
     * @return : 购物车数量
     */
    public Integer getCount(Integer id) {
        return Math.toIntExact(jedis.hlen(String.format("cart_%d", id)));
    }

    /**
     * 添加用户历史浏览记录
     */
    public void addHistory(Integer user_id, Integer goods_id) {
        String historyKey = String.format("history_%d", user_id);
        // 移除该列表的goods_id
        jedis.lrem(historyKey, 0, Integer.toString(goods_id));
        // 把goods_id插入列表左侧
        jedis.lpush(historyKey, Integer.toString(goods_id));
        // 只保留5条信息
        jedis.ltrim(historyKey, 0, 4);
    }

    /**
     * 获取用户历史浏览记录
     */
    public List<GoodsSKU> getUserHistory(Integer user_id) {
        String historyKey = String.format("history_%d", user_id);
        List<String> sku_ids = jedis.lrange(historyKey, 0, 4);
        List<GoodsSKU> skus = new ArrayList<>();
        for (String sku_id : sku_ids) {
            skus.add(goodsDao.findGoodsSKUById(Integer.parseInt(sku_id)));
        }
        return skus;
    }

    /**
     * 添加购物车
     * @param id : 用户id
     * @param skuId : 商品skuid
     * @param c : 商品数目
     */
    public void addCartCount(Integer id, Integer skuId, Integer c) {
        int count = c;
        String cartKey = String.format("cart_%d", id);
        String cart_count = jedis.hget(cartKey, Integer.toString(skuId));
        if(cart_count != null){
            count += Integer.parseInt(cart_count);
        }
        // 设置hash中对应sku_id的值
        jedis.hset(cartKey, Integer.toString(skuId), Integer.toString(count));
    }

    /**
     * 获取用户所有购物车信息
     */
    public List<CartVo> getAllCartInfo(Integer id) {
        List<CartVo> carts = new ArrayList<>();
        String cartKey = String.format("cart_%d", id);
        Map<String, String> cart_info = jedis.hgetAll(cartKey);
        // map中的键为sku id
        Set<String> keys = cart_info.keySet();
        for (String key : keys) {
            // map中的值为数量
            String value = cart_info.get(key);
            Integer count = Integer.valueOf(value);

            CartVo vo = new CartVo();
            vo.setCount(count);
            GoodsSKU sku = goodsDao.findGoodsSKUById(Integer.valueOf(key));
            vo.setSku(sku);
            vo.setAmount(sku.getPrice()*count);
            carts.add(vo);
        }

        return carts;
    }

    /**
     * 更新购物车信息
     */
    public void updataCart(Integer id, Integer sku_id, Integer count) {
        String cartKey = String.format("cart_%d", id);
        jedis.hset(cartKey, String.valueOf(sku_id), String.valueOf(count));
    }

    /**
     * 获取用户购物车总记录数
     */
    public Integer getAllCartCount(Integer id) {
        String cartKey = String.format("cart_%d", id);
        Integer count = 0;
        List<String> counts = jedis.hvals(cartKey);
        for (String s : counts) {
            count += Integer.parseInt(s);
        }
        return count;
    }

    /**
     * 删除购物车中某个商品
     */
    public void delCart(Integer id, Integer skuId) {
        String cartKey = String.format("cart_%d", id);
        jedis.hdel(cartKey, String.valueOf(skuId));
    }
}
