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
    </head>
    <script type="text/javascript">
        $(function(){
            win_cla();
        });
    </script>
    <body
        <div>
            <dl class="win_cla">
                <dt>
                    <span></span>
                </dt>
                <dd>
                    <a href="/goods/normal/list"><div><span>一键下单</span></div></a>
                    <a href="/goods/step/list"><div><span>步骤下单</span></div></a>
                    <a href="#"><div><span>面积下单</span></div></a>                                
                </dd>
            </dl>
            
            
            <header class="index_head">
                <span><#if user??>${user.cityName!''}</#if></span>
                <input type="" name="" id="" placeholder="快速搜索商品" />
                <div onclick="window.location.href='/message'"></div>
            </header>
            <#if circle_ad_list??>
                <div class="index_banner">
                    <div class="scroll_box">
                        <#list circle_ad_list as item>
                            <img onclick="window.locatioin.href='${item.linkUri!'#'}'" src="${item.fileUri!''}"/>
                        </#list>
                    </div>
                    <div class="scroll_btn">
                        <ul>
                            <#list circle_ad_list as item>
                                <li></li>
                            </#list>
                        </ul>
                    </div>              
                </div>
            </#if>
            <#if navi_bar_list??>
                <ul class="index_nav">
                    <li>
                        <#list navi_bar_list as item>
                            <#if item_index lt 4>
                                <a href="${item.linkUri!'#'}">
                                    <img src="${item.iconUri!''}" />
                                    <span>${item.title!''}</span>
                                </a>
                            </#if>
                        </#list>
                    </li>
                    <li>
                        <#list navi_bar_list as item>
                            <#if item_index gt 3&&item_index lt 8>
                                <a href="${item.linkUri!'#'}">
                                    <img src="${item.iconUri!''}" />
                                    <span>${item.title!''}</span>
                                </a>
                            </#if>
                        </#list>
                    </li>
                </ul>
            </#if>
            
            <section class="index_content">
                <#if headline_list??>
                    <dl class="index_news">
                        <dt><img src="/client/images/scroll_news.png" /></dt>
                        <dd>
                            <div class="scroll_newsbox">
                                <#list headline_list as item>
                                    <a href="/article/${item.id?c}">${item.title!''}</a>
                                </#list>
                            </div>
                        </dd>               
                    </dl>
                </#if>
                <div class="index_test_box"></div>
                <div class="index_goods01">
                    <dl>
                        <dt>
                            <p>活动促销<span>· 优惠多多</span></p>                          
                            <a href="#"><img src="/client/images/index_guide_right.png" /></a>
                        </dt>
                        <dd>
                            <div class="index_scroll_goods">
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                                <a href="#">
                                    <div>
                                        <img src="/client/images/index_banner02.png"  />
                                        <span>满减</span>
                                        <span>满赠</span>
                                    </div>
                                    <p>￥1700.00</p>                                 
                                </a>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="index_goods02">
                    <dl>
                        <dt>
                            <a class="my_indexgood" href="#">
                                <div class="rich_box">
                                    <h3>乳胶漆 </h3><div class="guide"></div>
                                    <p>油漆涂料辅料 调色色浆</p>
                                    <span>￥39.00</span>
                                </div>
                                <div><img src="/client/images/index_goods_img01.png" /></div>
                            </a>
                        </dt>
                        <dd>
                            <a class="my_indexgood02" href="#">
                                <div class="rich_box">
                                    <h3>乳胶漆 </h3><div class="guide"></div>
                                    <p>油漆涂料辅料 调色色浆</p>
                                    <span>￥39.00</span>
                                </div>
                                <div><img src="/client/images/index_goods_img02.png" /></div>                              
                            </a>
                        </dd>
                        <dd>
                            <a class="my_indexgood02" href="#">
                                <div class="rich_box">
                                    <h3>乳胶漆 </h3><div class="guide"></div>
                                    <p>油漆涂料辅料 调色色浆</p>
                                    <span>￥39.00</span>
                                </div>
                                <div><img src="/client/images/index_goods_img03.png" /></div>                              
                            </a>
                        </dd>
                    </dl>
                    <ul>
                        <li>
                            <a class="my_indexgood03" href="#">
                                <h3>乳胶漆 </h3><div class="guide"></div>
                                <p>油漆涂料辅料 调色色浆</p>
                                <span>￥39.00</span>
                                <div><img src="/client/images/index_goods_img04.png" /></div>
                            </a>
                        </li>
                        <li>
                            <a class="my_indexgood03" href="#">
                                <h3>乳胶漆 </h3><div class="guide"></div>
                                <p>油漆涂料辅料 调色色浆</p>
                                <span>￥39.00</span>
                                <div><img src="/client/images/index_goods_img05.png" /></div>
                            </a>
                        </li>
                        <li>
                            <a class="my_indexgood03" href="#">
                                <h3>乳胶漆 </h3><div class="guide"></div>
                                <p>油漆涂料辅料 调色色浆</p>
                                <span>￥39.00</span>
                                <div><img src="/client/images/index_goods_img06.png" /></div>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="index_test_box"></div>
                <div class="index_goods03">
                    <#if index_center??>
                        <div class="goods03_title">
                           <img onclick="window.location.href='${index_center.linkUri!'#'}'" src="${index_center.fileUri!''}"/>
                        </div>
                    </#if>
                    <#if index_recommend_list??>
                        <ul>
                            <li>
                                <#list index_recommend_list as item>
                                    <a class="good03_box" href="#">
                                        <div>
                                            <img src="${item.coverImageUri!''}" />
                                            <span>促销</span>
                                        </div>
                                        <p>${item.title!''}</p>
                                        <span class="box03_pri">￥<#if item.salePrice??>${item.salePrice?string("0.00")}<#else>0.00</#if></span>
                                    </a>
                                </#list>
                            </li>
                        </ul>
                    </#if>
                </div>
            </section>
            <div class="index_test_box02"></div>
            <#include "/client/common_footer.ftl">
    </body>
</html>
