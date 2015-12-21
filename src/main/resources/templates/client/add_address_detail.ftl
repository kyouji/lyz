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
        <link rel="stylesheet" type="text/css" href="/client/css/x_account_settings.css"/>
        
        <script type="text/javascript" src="/client/js/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/client/js/add_address.js"></script>
    </head>
    <body class="bgc-f3f4f6">
        <#-- 引入警告提示样式 -->
        <#include "/client/common_warn.ftl">
        <#-- 引入等待提示样式 -->
        <#include "/client/common_wait.ftl">  
        <!-- 头部 -->
        <header>
            <a class="back" href="javascript:history.go(-1);"></a>
            <p>选择城区</p>
        </header>
        <!-- 头部 END -->
        
        <!-- 选择城区 -->
        <article class="add-shipping-address" id="region">
            <#if region_list??>
                <#list region_list as item>
                    <div class="select-city" onclick="theNextStep(${item.id?c});">
                        <span>${item.name!''}</span>
                        <em></em>
                    </div>
                </#list>
            </#if>
            <#-- 用于存储步骤编号 -->
            <input type="hidden" id="type" value="${type!'0'}">
        </article>
        <!-- 选择城区 END -->
    </body>
</html>