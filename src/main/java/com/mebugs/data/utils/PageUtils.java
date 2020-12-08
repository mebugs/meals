package com.mebugs.data.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页处理方法
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-20
 */
public class PageUtils {

    /**
     * 处理排序Page快速初始化 不适用既有正序又有倒序的排序
     * @param page 原Page对象
     * @param order 支持传入多列 逗号隔开 建议通过界面传如
     * @param desc true倒序 false正序
     * @return
     */
    public static Page setPageOrder(Page page, String order, boolean desc)
    {
        List<OrderItem> orders = new ArrayList<>();
        if(StringUtils.isNotBlank(order))
        {
            String[] orderArray = order.split(",");
            for(String ord : orderArray)
            {
                if(desc)
                {
                    orders.add(OrderItem.desc(ord));
                }else{
                    orders.add(OrderItem.asc(ord));
                }
            }
        }else{
            //默认ID倒序
            orders.add(OrderItem.desc("id"));
        }
        page.setOrders(orders);
        return page;
    }
}
