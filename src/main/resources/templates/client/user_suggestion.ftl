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
        <link rel="stylesheet" type="text/css" href="/client/css/x_gu_sales.css"/>
        
        <script type="text/javascript" src="/client/js/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/client/js/rich_lee.js"></script>
        <script type="text/javascript" src="/client/js/user_suggestion.js"></script>
        <script type="text/javascript">
            $(function(){
                win_cla();
            });
    </script>
    </head>
    <body class="bgc-f3f4f6">
        <#-- 引入警告提示样式 -->
        <#include "/client/common_warn.ftl">
        <#-- 引入等待提示样式 -->
        <#include "/client/common_wait.ftl">  
        <!-- 头部 -->
        <header>
            <a class="back" href="#"></a>
            <p>投诉建议</p>
        </header>
        <!-- 头部 END -->
        
        <!-- 投诉建议 -->
        <article class="suggest">
            <div class="title">送货、退换货及咨询请联系<a href="http://wpa.qq.com/msgrd?V=1&uin=894559632&exe=qq&Site=qq&menu=yes">电话客服</a></div>
            <div class="title">
                <select style="padding:0 2%; width:96%;height:35px;-webkit-appearance:none;" placeholder="123">
                    <option>请选择咨询分类</option>
                </select>
            </div>
            <div class="textarea">
                <div class="headline">我们存在哪些不足</div>
                <textarea id="suggestion" name="suggestion"></textarea>
                <div class="headline">您的联系电话</div>
                <input class="phone-num-textarea" id="phone" type="text" name="phone" value="${username!''}">
            </div>
            <input class="btn-submit-save" id="button" type="button" onclick="submitTheSuggestion();" value="提交">
        </article>
        <!-- 投诉建议 END -->
        
        <div class="clear h66"></div>
        
        <!-- 底部 -->
        <#include "/client/common_footer.ftl">
        <!-- 底部 END -->
    </body>
</html>