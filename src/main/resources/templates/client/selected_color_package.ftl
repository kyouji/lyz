<div class="colo_sec">
    <input type="hidden" id="unit_price" value="${unit_price!'0'}">
    <input type="hidden" id="goods_id" value="<#if goodsId??>${goodsId?c}</#if>">
    <input type="hidden" id="the_new_quantity" value="${quantity!'0'}">
    <div class="my_close">
        <dl>
            <dt>
                <span>颜色</span>
                <span>数量</span>
                <span>价格</span>
                <span>编辑</span>
            </dt>
            <#if select_colors??>
                <#list select_colors as item>
                    <dd id="colorPackage${item.id?c}">
                        <span>${item.number!''}</span>
                        <span>${item.quantity!''}</span>
                        <span>
                            <#if item.totalPrice??>
                                ${item.totalPrice?string("0.00")}
                            </#if>
                        </span>
                        <span>
                            <a href="javascript:deleteSelectedColorPackage(${item.id?c},${goodsId?c})">删除</a>
                        </span>
                    </dd>
                </#list>
            </#if>
        </dl>
        <a class="colo_clo">关闭</a>
    </div>
</div>