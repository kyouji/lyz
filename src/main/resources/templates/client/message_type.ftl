<!DOCTYPE html>
<html>
    <head>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="copyright" content="" />
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta charset="utf-8">
        <title>乐易装-消息中心</title>
        
        <link rel="stylesheet" type="text/css" href="/client/css/my_base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/other.css"/>
        
        <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
        <script src="/client/js/rich_lee.js" type="text/javascript"></script>
    </head>
    <script type="text/javascript">
        $(function(){
        	turn_hei($('.det_banner'),0.8) 
        	$('.det_banner img').width($(window).width())
        })
    </script>
    <body>
        <div>
            <div class="sec_header">
                <a href="javascript:history.go(-1);"></a>
                <p>消息中心</p>
                <span></span>
            </div>
            <section class="new_list">
                <#if all_type??>
                    <#list all_type as item>
                        <a href="/message/list/${item.id?c}">
                            <dl>
                                <dt><img src="${item.imgUrl!''}"></dt>
                                <dd>
                                    <h3>${item.name!''}</h3>
                                    <#if ("content"+item_index)?eval??>
                                        <span>${("content"+item_index)?eval!''}</span>
                                    <#else>
                                        <span></span>
                                    </#if>
                                </dd>
                            </dl>
                        </a>
                    </#list>
                </#if>
            </section>
        </div>		
    </body>
</html>
