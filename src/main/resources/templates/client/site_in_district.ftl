<#if all_site??>
    <#list all_site as item>
        <div class="stores-list" onclick="selectDiySite(${item.id?c});">${item.title!''}</div>
    </#list>
</#if>