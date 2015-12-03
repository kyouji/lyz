<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            html,body{width:100%;height: 100%;}
        </style>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="copyright" content="" />
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta charset="utf-8">
        <title>我的订单</title>
        
        <link rel="stylesheet" type="text/css" href="/client/css/my_base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/other.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/xiuxiu.css"/>
        <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
        <script src="/client/js/rich_lee.js" type="text/javascript"></script>
        <script src="/client/js/user_order.js" type="text/javascript"></script>
    </head>
    <body style="background: #f3f4f6;>
        <div">
            <div class="sec_header">
                <a href="javascript:history.go(-1);"></a>
                <p>我的订单</p>				
            </div>
            
            <section class="my_order">
                <!-- 搜索框 -->
                <input id="typeId" type="hidden" value="${typeId!'0'}">
                <div class="searchbox bgc-f3f4f6 bdt"><input type="text"><a href="#"></a></div>			
                <!-- 订单管理 -->
                <ul class="order-nav">
                    <li id="all"><a href="#">全部</a></li>
                    <li id="unpayed"><a href="#">待付款</a></li>
                    <li id="unsignin"><a href="#">待收货</a></li>
                    <li id="uncommend"><a href="#">待评价</a></li>
                </ul>
                
                <!-- 订单分类 -->
                <article class="orders-species"> 
                    <!-- 订单列表 -->
                    <#if unpayed_order_list??>
                        <div id="all_orders" class="some_orders">
                            <#list unpayed_order_list as item>
                                <ol class="order-list">
                                    <li class="li1">
                                        <label>订单号：<span>${item.orderNumber!''}</span></label>
                                        <div class="species">
                                            <#if item.statusId??>
                                                <#switch item.statusId>
                                                    <#case 2>待付款<#break>
                                                    <#case 3>待发货<#break>
                                                    <#case 4>待签收<#break>
                                                    <#case 5>待评价<#break>
                                                    <#case 6>已完成<#break>
                                                    <#case 7>已取消<#break>
                                                </#switch>
                                            </#if>
                                        </div>
                                    </li>
                                    <#if item.orderGoodsList??>
                                        <#list item.orderGoodsList as goods>
                                            <li class="li2">
                                                <div class="img"><img src="${goods.goodsCoverImageUri!''}" alt="产品图片"></div>
                                                <div class="product-info">
                                                    <div class="div1">${goods.goodsTitle!''}</div>
                                                    <div class="div2">￥<span><#if goods.price??>${goods.price?string("0.00")}<#else>0.00</#if></span>&nbsp;&nbsp;<label>数量：<span>${goods.quantity!'0'}</span></label></div>
                                                </div>
                                            </li>
                                            <div class="li3">
                                                <#if item.statusId??>
                                                    <#switch item.statusId>
                                                        <#case 2>
                                                            <a href="">订单详情</a>
                                                            <a href="">取消订单</a>
                                                            <a href="" style="border: #cc1421 1px solid; color: #cc1421;">去支付</a>
                                                        <#break>
                                                        <#case 3>
                                                            <a href="">订单详情</a>
                                                        <#break>
                                                        <#case 4>
                                                            <a href="">订单详情</a>
                                                            <a href="">物流详情</a>
                                                            <a href="">确认收货</a>
                                                        <#break>
                                                        <#case 5>
                                                            <a href="">订单详情</a>
                                                            <a href="">立即评价</a>
                                                        <#break>
                                                        <#case 6>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                        <#case 7>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                    </#switch>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </ol>
                            </#list>
                        </div>
                    </#if>
                    
                    <#if unpayed_order_list??>
                        <div id="unpayed_orders"  class="some_orders">
                            <#list unpayed_order_list as item>
                                <ol class="order-list">
                                    <li class="li1">
                                        <label>订单号：<span>${item.orderNumber!''}</span></label>
                                        <div class="species">
                                            <#if item.statusId??>
                                                <#switch item.statusId>
                                                    <#case 2>待付款<#break>
                                                    <#case 3>待发货<#break>
                                                    <#case 4>待签收<#break>
                                                    <#case 5>待评价<#break>
                                                    <#case 6>已完成<#break>
                                                    <#case 7>已取消<#break>
                                                </#switch>
                                            </#if>
                                        </div>
                                    </li>
                                    <#if item.orderGoodsList??>
                                        <#list item.orderGoodsList as goods>
                                            <li class="li2">
                                                <div class="img"><img src="${goods.goodsCoverImageUri!''}" alt="产品图片"></div>
                                                <div class="product-info">
                                                    <div class="div1">${goods.goodsTitle!''}</div>
                                                    <div class="div2">￥<span><#if goods.price??>${goods.price?string("0.00")}<#else>0.00</#if></span>&nbsp;&nbsp;<label>数量：<span>${goods.quantity!'0'}</span></label></div>
                                                </div>
                                            </li>
                                            <div class="li3">
                                                <#if item.statusId??>
                                                    <#switch item.statusId>
                                                        <#case 2>
                                                            <a href="">订单详情</a>
                                                            <a href="">取消订单</a>
                                                            <a href="" style="border: #cc1421 1px solid; color: #cc1421;">去支付</a>
                                                        <#break>
                                                        <#case 3>
                                                            <a href="">订单详情</a>
                                                        <#break>
                                                        <#case 4>
                                                            <a href="">订单详情</a>
                                                            <a href="">物流详情</a>
                                                            <a href="">确认收货</a>
                                                        <#break>
                                                        <#case 5>
                                                            <a href="">订单详情</a>
                                                            <a href="">立即评价</a>
                                                        <#break>
                                                        <#case 6>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                        <#case 7>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                    </#switch>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </ol>
                            </#list>
                        </div>
                    </#if>
                    
                    <#if unsignin_order_list??>
                        <div class="unsignin_orders"  class="some_orders">
                            <#list unsignin_order_list as item>
                                <ol class="order-list">
                                    <li class="li1">
                                        <label>订单号：<span>${item.orderNumber!''}</span></label>
                                        <div class="species">
                                            <#if item.statusId??>
                                                <#switch item.statusId>
                                                    <#case 2>待付款<#break>
                                                    <#case 3>待发货<#break>
                                                    <#case 4>待签收<#break>
                                                    <#case 5>待评价<#break>
                                                    <#case 6>已完成<#break>
                                                    <#case 7>已取消<#break>
                                                </#switch>
                                            </#if>
                                        </div>
                                    </li>
                                    <#if item.orderGoodsList??>
                                        <#list item.orderGoodsList as goods>
                                            <li class="li2">
                                                <div class="img"><img src="${goods.goodsCoverImageUri!''}" alt="产品图片"></div>
                                                <div class="product-info">
                                                    <div class="div1">${goods.goodsTitle!''}</div>
                                                    <div class="div2">￥<span><#if goods.price??>${goods.price?string("0.00")}<#else>0.00</#if></span>&nbsp;&nbsp;<label>数量：<span>${goods.quantity!'0'}</span></label></div>
                                                </div>
                                            </li>
                                            <div class="li3">
                                                <#if item.statusId??>
                                                    <#switch item.statusId>
                                                        <#case 2>
                                                            <a href="">订单详情</a>
                                                            <a href="">取消订单</a>
                                                            <a href="" style="border: #cc1421 1px solid; color: #cc1421;">去支付</a>
                                                        <#break>
                                                        <#case 3>
                                                            <a href="">订单详情</a>
                                                        <#break>
                                                        <#case 4>
                                                            <a href="">订单详情</a>
                                                            <a href="">物流详情</a>
                                                            <a href="">确认收货</a>
                                                        <#break>
                                                        <#case 5>
                                                            <a href="">订单详情</a>
                                                            <a href="">立即评价</a>
                                                        <#break>
                                                        <#case 6>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                        <#case 7>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                    </#switch>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </ol>
                            </#list>
                        </div>
                    </#if>
                    
                    <#if uncomment_order_list??>
                        <div id="uncomment_orders"  class="some_orders">
                            <#list uncomment_order_list as item>
                                <ol class="order-list">
                                    <li class="li1">
                                        <label>订单号：<span>${item.orderNumber!''}</span></label>
                                        <div class="species">
                                            <#if item.statusId??>
                                                <#switch item.statusId>
                                                    <#case 2>待付款<#break>
                                                    <#case 3>待发货<#break>
                                                    <#case 4>待签收<#break>
                                                    <#case 5>待评价<#break>
                                                    <#case 6>已完成<#break>
                                                    <#case 7>已取消<#break>
                                                </#switch>
                                            </#if>
                                        </div>
                                    </li>
                                    <#if item.orderGoodsList??>
                                        <#list item.orderGoodsList as goods>
                                            <li class="li2">
                                                <div class="img"><img src="${goods.goodsCoverImageUri!''}" alt="产品图片"></div>
                                                <div class="product-info">
                                                    <div class="div1">${goods.goodsTitle!''}</div>
                                                    <div class="div2">￥<span><#if goods.price??>${goods.price?string("0.00")}<#else>0.00</#if></span>&nbsp;&nbsp;<label>数量：<span>${goods.quantity!'0'}</span></label></div>
                                                </div>
                                            </li>
                                            <div class="li3">
                                                <#if item.statusId??>
                                                    <#switch item.statusId>
                                                        <#case 2>
                                                            <a href="">订单详情</a>
                                                            <a href="">取消订单</a>
                                                            <a href="" style="border: #cc1421 1px solid; color: #cc1421;">去支付</a>
                                                        <#break>
                                                        <#case 3>
                                                            <a href="">订单详情</a>
                                                        <#break>
                                                        <#case 4>
                                                            <a href="">订单详情</a>
                                                            <a href="">物流详情</a>
                                                            <a href="">确认收货</a>
                                                        <#break>
                                                        <#case 5>
                                                            <a href="">订单详情</a>
                                                            <a href="">立即评价</a>
                                                        <#break>
                                                        <#case 6>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                        <#case 7>
                                                            <a href="">订单详情</a>
                                                            <a href="">删除订单</a>
                                                        <#break>
                                                    </#switch>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </ol>
                            </#list>
                        </div>
                    </#if>
                </article>
                <!-- 用户订单 END -->							
            </section>
            
            <div class="index_test_box02"></div>
            <#include "/client/common_footer.ftl">
        </div>		
    </body>
</html>
