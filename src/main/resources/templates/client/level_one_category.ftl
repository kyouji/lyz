<#if level_one_categories??>
    <div class="fen_testleft">
        <ul>
            <#list level_one_categories as item>
                <li><a href="#">${item.title!''}</a></li>
            </#list>
        </ul>
    </div>
</#if>