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
            <p>收货地址</p>
            <a class="save fz2" href="/user/address/add/0">+</a>
        </header>
        <!-- 头部 END -->
        
        <!-- 收货地址 -->
        <dl class="shipping-address">
            <!-- 默认地址 -->
            <#if default??>
                <dt>
                    <div class="address">
                        <div class="div1">
                            <span class="c-fd9c11">（默认）</span>
                            <span>${default.receiverName!''}</span>
                            <span>${default.receiverMobile!''}</span>
                        </div>
                        <div class="div2 c999">${default.detailAddress!''}/div>
                    </div>
                    <div class="editable">
                        <a class="a2" href="#">修改</a>
                        <a class="a3" href="#">删除</a>
                    </div>
                </dt>
            </#if>
            <#if address_list??>
                <#list address_list as item>
                    <dd>
                        <div class="address">
                            <div class="div1">
                                <span>${item.receiverName!''}</span>
                                <span>${item.receiverMobile!''}</span>
                            </div>
                            <div class="div2 c999">${item.detailAddress!''}</div>
                        </div>
                        <div class="editable">
                            <a class="a1" href="#">设为默认</a>
                            <a class="a2" href="#">修改</a>
                            <a class="a3" href="#">删除</a>
                        </div>
                    </dd>
                </#list>
            </#if>
        </dl>
        <!-- 收货地址 END -->
    </body>
</html>