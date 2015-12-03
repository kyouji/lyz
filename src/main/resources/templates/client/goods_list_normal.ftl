<!DOCTYPE html>
<html>
    <head>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="copyright" content="" />
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta charset="utf-8">
        <title>乐易装首页</title>
        
        <link rel="stylesheet" type="text/css" href="/client/css/my_base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/other.css"/>
        
        <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
        <script src="/client/js/rich_lee.js" type="text/javascript"></script>
        <script src="/client/js/goods_list_normal.js" type="text/javascript"></script>
    </head>
    <script type="text/javascript">
        window.onload = function(){
                win_colo_temp();//颜色调理窗口窗口
                win_cla();//分类弹窗        
                my_check();//模拟checkbox
        };
    </script>
    <body>
        <#include "/client/wait_img.ftl">    
        <div>
            <dl class="win_cla">
                <dt>
                    <span></span>
                </dt>
                <dd>
                    <a href="#"><div><span>一键下单</span></div></a>
                    <a href="#"><div><span>步骤下单</span></div></a>
                    <a href="#"><div><span>面积下单</span></div></a>                                
                </dd>
                
            </dl>
            <!--
                作者：rich
                描述：侧边栏 滑动
            -->
            <div id="color_package_select">
                <#include "/client/color_package.ftl">
            </div>
            <!--
                描述：调颜色
            -->
            <header class="index_head">
                <span><#if user??&&user.cityName??>${user.cityName!''}</#if></span>
                <input type="" name="" id="" value="快速搜索商品" />
                <div class="menu"></div>
            </header>
            <!--
                作者：rich
                描述：头部结束
            -->
            <#if level_one_categories??>
                <#-- 遍历一级分类 -->
                <#list level_one_categories as level_one>
                    <section class="lei_box">
                        <h3 class="lei_title">${level_one.title!''}</h3>
                        <#if ("level_two_categories"+level_one_index)?eval??>
                            <#-- 遍历二级分类 -->
                            <#list ("level_two_categories"+level_one_index)?eval as level_two>
                                <div class="lei_box01">
                                    <div class="box01_title">${level_two.title!''}</div>
                                    <#if ("goods_list"+level_one_index+level_two_index)?eval??>
                                        <#-- 遍历二级分类下的所有商品 -->
                                        <#list ("goods_list"+level_one_index+level_two_index)?eval as goods>
                                            <dl>
                                                <dt>
                                                    <#--
                                                        <span class="my_checkbox" onclick = "selectGoods(${goods.id?c})">
                                                            <div id="${goods.id?c}" class="my_black"></div>
                                                        </span>
                                                    -->
                                                    <input type="hidden" id="inventory${goods.id?c}" value="">
                                                    <h3 onclick="getGoodsDetail(${goods.id?c})">${goods.title!''}</h3>
                                                    <#-- 后期会判断该商品是不是属于调色商品 -->
                                                    <a id="color${goods.id?c}" href="javascript:changeColor(${goods.id?c});">调色></a>
                                                </dt>
                                                <dd>
                                                    <p>￥958.00</p>
                                                    <a>促销</a>
                                                    <div>
                                                        <span onclick="changeQuantity(${goods.id?c},'delete')">-</span>
                                                        <input readonly="true" type="number" id="quantity${goods.id?c}" value="0">
                                                        <span onclick="changeQuantity(${goods.id?c},'add')">+</span>
                                                    </div>
                                                </dd>
                                            </dl>
                                        </#list>
                                    </#if>
                                </div>
                            </#list>
                        </#if>
                    </section>
                </#list>
            </#if>
            <div class="index_test_box03"></div>
            
            
            <div class="go_buy">
                <p>查看已选(<span id="select_num">${selected_goods_number!'0'}</span>)</p>
                <a href="#">去结算</a>
            </div>
            
            <div class="index_footer">
                <ul>
                    <li>
                        <a href="#">
                            <div></div>
                            <span>首页</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div></div>
                            <span>下单</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div></div>
                            <span>我的</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div></div>
                            <span>已选</span>
                        </a>
                    </li>
                </ul>
                <div class="footer_act">
                    <a href="#"></a>
                </div>
            </div>
        </div>      
    </body>
</html>
