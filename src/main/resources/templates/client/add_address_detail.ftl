<header>
    <a class="back" href="javascript:windowHide();" id="back"></a>
    <p>选择城区</p>
</header>
<!-- 头部 END -->

<!-- 选择城区 -->
<article class="add-shipping-address" id="region" class="colo_sec">
    <#if region_list??>
        <#list region_list as item>
            <div class="select-city" onclick="getRegion(${item.id?c},'${item.name!''}',${status!'0'});">
                <span>${item.name!''}</span>
                <em></em>
            </div>
        </#list>
    </#if>
</article>
