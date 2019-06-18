package cn.hnist.dao;

import cn.hnist.pojo.OrderGoods;
import cn.hnist.pojo.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    /**
     * 向order_info表中添加一条信息
     */
    @Insert("insert into order_info(order_id,addr_id,user_id,pay_method) " +
            "values(#{order_id},#{addr_id},#{user_id},#{pay_method})")
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * 向order_goods表中添加一条信息
     */
    @Insert("insert into order_goods(count,price,order_id,sku_id) " +
            "values(#{count}, #{price}, #{order_id}, #{sku_id})")
    void addOrderGoods(OrderGoods orderGoods);

    /**
     * 更新orderinfo中的总价格和总数目
     */
    @Update("update order_info set total_count=#{total_count},total_price=#{total_price} " +
            "where order_id=#{order_id}")
    void updateOrderInfoPAndC(OrderInfo orderInfo);

    /**
     * 根据user id 查询order_info内容
     */
    @Select("select * from order_info where user_id=#{id}")
    List<OrderInfo> findOrderInfoByUserId(Integer id);

    /**
     * 根据order id查询order_goods内容
     */
    @Select("select * from order_goods where order_id=#{order_id}")
    List<OrderGoods> findOrderGoodsByOrderId(String order_id);

    /**
     * 根据order_id查询order_info内容
     */
    @Select("select * from order_info where order_id=#{order_id}")
    OrderInfo findOrderInfoById(String order_id);

    /**
     * 更新order_info订单状态
     */
    @Update("update order_info set order_status=#{order_status} where order_id=#{order_id}")
    void updateOrderInfoStatus(OrderInfo order);
}
