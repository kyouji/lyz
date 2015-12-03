<!DOCTYPE html>
<html>
    <head>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="copyright" content="" />
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta charset="utf-8">
        <title>乐易装首页</title>
        
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
        <div style="background: #fafafa;">
            <div class="sec_header">
                <a href="javascript:history.go(-1);"></a>
                <p>消息中心</p>
                <span></span>
            </div>
            <section class="new_center">
                <#if all_message_list??>
                    <#list all_message_list as item>
                    	<dl>
                            <dt>
                                <span><#if item.createTime??>${item.createTime?string("yyyy-MM-dd")}</#if></span>
                            </dt>
                            <dd>
                                <h3>消息通知</h3>
                                <div>
                                    <#if item.imgUri??>
                                        <img src="${item.imgUri!''}"/>
                                    </#if>
                                </div>
                                <p><#if item.content??>${item.content!''}...</#if></p>
                                <a href="#">查看详情 ></a>
                            </dd>
                        </dl>
                    </#list>
                </#if>
            </section>
        </div>		
    </body>
</html>
