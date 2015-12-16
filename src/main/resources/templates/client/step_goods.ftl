<div class="fen_goodbox">                   
    <#if level_one_categories??>
        <#list level_one_categories as level_one>
            <#if ("level_two_categories"+level_one_index)?eval??>
                <#list ("level_two_categories"+level_one_index)?eval as level_two>
                    <#if ("goods_list"+level_one_index+level_two_index)?eval??>
                        <div class="ctrlGoods" id="goods${level_one.id?c}_${level_two.id?c}" <#if !(level_one_index==0&&level_two_index==0)>style="display:none;"</#if>>
                            <#list ("goods_list"+level_one_index+level_two_index)?eval as goods>
                                <dl>
                                    <dt>
                                        <a>
                                            <#if goods??&&goods.coverImageUri??>
                                                <#-- 商品的图片，点击可跳转到详情页 -->
                                                <img onclick="window.location.href='/goods/detail/${goods.id?c}'" src="${goods.coverImageUri!''}" />
                                            </#if>
                                        </a>
                                    </dt>
                                    <dd>
                                        <#-- 用户存储指定商品的库存 -->
                                        <input type="hidden" id="inventory${goods.id?c}" value="<#if goods.leftNumber??>${goods.leftNumber?c}<#else>0</#if>">
                                        <#-- 商品的标题，点击可跳转到详情页 -->
                                        <p onclick="window.location.href='/goods/detail/${goods.id?c}'">${goods.title!''}</p>
                                        <div class="fen_div01">
                                            <#-- 判断指定商品在该地区是否参与促销 -->
                                            <#if ("priceListItem"+level_one_index+level_two_index+goods_index)?eval??>
                                                <#if ("priceListItem"+level_one_index+level_two_index+goods_index)?eval.isPromotion??>
                                                    <#if ("priceListItem"+level_one_index+level_two_index+goods_index)?eval.isPromotion>
                                                        <span>促销</span>
                                                    </#if>
                                                </#if>
                                            </#if>
                                            <a href="javascript:changeQuantity(${goods.id?c},'add');">+</a>
                                            <input readonly="true" class="goodsSelectedQuantity" type="number" id="quantity${goods.id?c}" value="0">
                                            <a href="javascript:changeQuantity(${goods.id?c},'delete');">-</a>
                                        </div>
                                        <div class="fen_div02">
                                            <#-- 显示指定商品在该地区的价格 -->
                                            <#if ("priceListItem"+level_one_index+level_two_index+goods_index)?eval??>
                                                <#if ("priceListItem"+level_one_index+level_two_index+goods_index)?eval.salePrice??>
                                                    <a>￥${("priceListItem"+level_one_index+level_two_index+goods_index)?eval.salePrice?string("0.00")}</a>
                                                </#if>
                                            </#if>
                                            <#-- 判断是否属于调色产品 -->
                                            <#if goods.isColorful??&&goods.isColorful>
                                                <span onclick="changeColor(${goods.id?c});">调色</span>
                                            </#if>
                                        </div>
                                    </dd>
                                </dl>   
                                <div class="index_test_box"></div>
                            </#list>
                        </div>
                    </#if>
                </#list>
            </#if>
        </#list>
    </#if>
</div>