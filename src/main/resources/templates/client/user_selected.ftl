<!DOCTYPE html>
<html lang="zh-CN" class="bgc-f3f4f6">
    <head>
        <meta charset="UTF-8">
        <meta name="keywords" content="">
        <meta name="copyright" content="" />
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <title>乐易装</title>
        <!-- css -->
        <link rel="stylesheet" type="text/css" href="/client/css/my_base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/x_common.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/x_order_manage.css"/>
        <!-- js -->
        <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
    </head>
    <body class="bgc-f3f4f6">
        <!-- 头部 -->
        <header>
            <a class="back" href="#"></a>
            <p>我的已选</p>
        </header>
        <!-- 头部 END -->
        
        <!-- 我的已选 -->
        <#if all_selected??&&all_selected?size gt 0>
            <article class="my-selected">
                <!-- 已选列表 -->
                <#list all_selected as goods>
                    <div class="selected-list">
                        <section class="sec1">
                            <div class="img">
                                <img src="${goods.goodsCoverImageUri!''}" alt="产品图片">
                            </div>
                            <div class="product-info">
                                <div class="descript">${goods.goodsTitle!''}</div>
                                <div class="choose-num">
                                    <!-- 数量选择 -->
                                    <div class="numbers">
                                        <a class="less">-</a>
                                        <input type="text" value="${goods.quantity!'0'}">
                                        <a class="add">+</a>
                                    </div>
                                    <div class="price">￥
                                        <span>
                                            <#if goods.price??&&goods.quantity??>
                                                ${(goods.price*goods.quantity)?eval?string("0.00")}
                                            </#if>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <#if ("goods_colors"+goods_index)?eval??&&("goods_colors"+goods_index)?eval?size gt 0>
                            <#list ("goods_colors"+goods_index)?eval as color>
                                <section class="sec1">
                                    <div class="colortap"></div>
                                    <div class="product-info">
                                    <div class="descript">调色包：<span>${color.number!''}</span></div>
                                        <div class="choose-num">
                                            <!-- 数量选择 -->
                                            <div class="numbers">
                                                <a class="less">-</a>
                                                <input type="text" value="${color.quantity!'0'}">
                                                <a class="add">+</a>
                                            </div>
                                            <div class="price">￥
                                                <span>
                                                    <#if color.salePrice??&&color.quantity??>
                                                        ${(color.salePrice*color.quantity)?eval?string("0.00")}
                                                    </#if>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </#list>
                        </#if>
                    </div>
                </#list>
            </article>
        </#if>
        <!-- 我的已选 END -->
        <div class="clear h50"></div>
        <!-- 底部 -->
        <footer>
            <a class="btn-clearing" href="#">去结算</a>
        </footer>
        <!-- 底部 END -->
    </body>
</html>