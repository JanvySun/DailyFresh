package test;

import cn.hnist.dao.GoodsDao;
import cn.hnist.pojo.GoodsType;
import cn.hnist.pojo.IndexPromotionBanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class GoodsDaoTest {

    @Autowired
    GoodsDao goodsDao;

    @Test
    public void test01() {
        List<GoodsType> types = goodsDao.findAllGoodsType();
        for (GoodsType type : types) {
            System.out.println(type);
        }
    }

    @Test
    public void test02() {
        GoodsType type = new GoodsType();
        type.setName("速冻食品");
        type.setLogo("ice");
        type.setImage("file:///E:/Desktop/Pictures/DailyFresh/banner06.jpg");
        goodsDao.addType(type);
    }

    @Test
    public void test03() {
        List<IndexPromotionBanner> lists = goodsDao.findAllPromotionBanner();
        for (IndexPromotionBanner list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test04() {
        IndexPromotionBanner ipb = new IndexPromotionBanner();
        ipb.setName("活动2");
        ipb.setOd(1);
        ipb.setUrl("javascript;");
        ipb.setImage("file:///E:/Desktop/Pictures/DailyFresh/adv02.jpg");
        goodsDao.addPromotionBanner(ipb);
    }

}
