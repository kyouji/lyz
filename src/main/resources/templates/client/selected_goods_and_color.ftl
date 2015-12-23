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
                            <#if ("goods"+goods_index)?eval??>
                                <input type="hidden" id="goods${goods.goodsId?c}quantity" value="${("goods"+goods_index)?eval}">
                            </#if>
                            <!-- 数量选择 -->
                            <div class="numbers">
                                <a class="less" href="javascript:operate(0,0,${goods.goodsId?c});">-</a>
                                <input type="text" id="goods${goods.goodsId?c}" <#if ("goods"+goods_index)?eval??&&("goods"+goods_index)?eval lt goods.quantity>value="${("goods"+goods_index)?eval}"<#else>value="${goods.quantity!'0'}"</#if>>
                                <a class="add" href="javascript:operate(1,0,${goods.goodsId?c});">+</a>
                            </div>
                            <div class="price" id="goods${goods.goodsId?c}price">￥
                                <span>
                                    <#if goods.price??&&goods.quantity??>
                                        ${(goods.price*goods.quantity)?eval?string("0.00")}
                                    </#if>
                                </span>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </#list>
    </article>
</#if>

<#if selected_colors??&&selected_colors?size gt 0>
    <article class="my-selected">
        <!-- 已选列表 -->
        <#list selected_colors as item>
            <div class="selected-list">
                <section class="sec1">
                    <div class="img">
                        <img src="${item.imageUri!''}" alt="产品图片">
                    </div>
                    <div class="product-info">
                        <div class="descript">${item.number!''}</div>
                        <div class="choose-num">
                            <#if ("color"+item_index)?eval??>
                                <input type="hidden" id="color${item.goodsId?c}quantity" value="${("color"+item_index)?eval}">
                            </#if>
                            <!-- 数量选择 -->
                            <div class="numbers">
                                <a class="less" href="javascript:operate(0,1,${item.goodsId?c});">-</a>
                                <input type="text" id="color${item.goodsId?c}" <#if ("color"+item_index)?eval??&&("color"+item_index)?eval lt item.quantity>value="${("color"+item_index)?eval!'0'}"<#else>value="${item.quantity!'0'}"</#if>>
                                <a class="add" href="javascript:operate(1,1,${item.goodsId?c});">+</a>
                            </div>
                            <div class="price" id="color${item.goodsId?c}price">￥
                                <span>
                                    <#if item.price??&&item.quantity??>
                                        ${(item.price*item.quantity)?eval?string("0.00")}
                                    </#if>
                                </span>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </#list>
    </article>
</#if>