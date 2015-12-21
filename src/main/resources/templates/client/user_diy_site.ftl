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
    </head>
    <body class="bgc-f3f4f6">
        
        <!-- 头部 -->
        <header>
            <a class="back" href="javascript:history.go(-1);"></a>
            <p>门店归属</p>
        </header>
        <!-- 头部 END -->
        
        <!-- 门店归属 -->
        <article class="home-stores">
            <#if user??>
                <div>当前所属门店：<span>${user.diyName!''}</span></div>
            </#if>
            <#--
                <div class="title">选择其他门店</div>
            -->
            <select>
                <#if district_list??>
                    <#list district_list as item>
                        <option>${item.name!''}</option>
                    </#list>
                </#if>
            </select>
            <#if subdistrict_list??>
                <#list subdistrict_list as item>
                    <div class="stores-list">${item.name!''}</div>
                </#list>
            </#if>
        </article>
        <!-- 门店归属 END -->
        
    </body>
</html>