<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();

    //初始化编辑器
    var editor = KindEditor.create('.editor', {
        width: '98%',
        height: '350px',
        resizeType: 1,
        uploadJson: '/Verwalter/editor/upload?action=EditorFile',
        fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
        allowFileManager: true
    });
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
    $(".upload-show360").each(function () {
        $(this).InitSWFUpload_show360({ 
            btntext: "批量上传", 
            btnwidth: 66, 
            single: false, 
            water: true, 
            thumbnail: true, 
            filesize: "5120", 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf", 
            filetypes: "*.jpg;*.jpge;*.png;*.gif;" 
        });
    });

    //（缩略图）
    var txtPic = $("#txtImgUrl").val();
    if (txtPic == "" || txtPic == null) {
        $("#thumb_ImgUrl_show1").hide();
    }
    else {
        $("#thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show1").show();
    }

    $("#txtImgUrl").blur(function () {
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $("#thumb_ImgUrl_show1").hide();
        }
        else {
            $("#thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show1").show();
        }
    });
    
    //（团购缩略图）
    var groupPic = $("#groupSaleImage").val();
    if (groupPic == "" || groupPic == null) {
        $("#thumb_ImgUrl_show2").hide();
    }
    else {
        $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + groupPic + "' bigsrc='" + groupPic + "' /></div></li></ul>");
        $("#thumb_ImgUrl_show2").show();
    }

    $("#groupSaleImage").blur(function () {
        var groupPic = $("#groupSaleImage").val();
        if (groupPic == "" || groupPic == null) {
            $("#thumb_ImgUrl_show2").hide();
        }
        else {
            $("#thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='" + groupPic + "' bigsrc='" + groupPic + "' /></div></li></ul>");
            $("#thumb_ImgUrl_show2").show();
        }
    });
    
    //设置封面图片的样式
    $(".photo-list ul li .img-box img").each(function () {
        if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
            $(this).parent().addClass("selected");
        }
    });
    
    // 根据类型载入参数
    $("#categoryId").change(function(){
        $.ajax({
            url : '/Verwalter/goods/edit/parameter/'+$(this).val(),
            type : 'POST',
            success : function(res) {
                $("#id-param-sec").html(res);
            }
        });
    });
    
    // 添加赠品
    $("#addGift").click(function(){
        showDialogGift();
    });
    
    // 添加组合
    $("#addCombination").click(function(){
        showDialogCombination();
    });
    
    //创建促销赠品窗口
    function showDialogGift(obj) {
        var objNum = arguments.length;
        
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "赠品",
            content: 'url:/Verwalter/goods/list/dialog/gift?total=' + $("#var_box_gift").children("tr").length,
            width: 800,
            height: 350
        });
        
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            giftDialog.data = obj;
        }
    }
    
    //删除促销赠品节点
    function delGiftNode(obj) {
        $(obj).parent().parent().remove();
    }
    
    //创建商品组合窗口
    function showDialogCombination(obj) {
        var objNum = arguments.length;
        
        var combinationDialog = $.dialog({
            id: 'combinationDialogId',
            lock: true,
            max: false,
            min: false,
            title: "商品组合",
            content: 'url:/Verwalter/goods/list/dialog/comb?total=' + $("#var_box_comb").children("tr").length,
            width: 800,
            height: 550
        });
        
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            combinationDialog.data = obj;
        }
    }
    
    //删除商品组合节点
    function delCombinationNode(obj) {
        $(obj).parent().parent().remove();
    }
    
    // 自动计算销售价
    $("#outFactoryPrice").change(function(){
        var p1 = $.trim($('#outFactoryPrice').val());
        var p2 = $.trim($('#returnPrice').val())
        if (isNaN(p1) || p1=="") { p1 = 0 }
        if (isNaN(p2) || p2== "") { p2 = 0 }
        
        $("#idComputeSalePrice").val((parseFloat(p1) + parseFloat(p2)));
    });
    
    $("#returnPrice").change(function(){
        var p1 = $.trim($('#outFactoryPrice').val());
        var p2 = $.trim($('#returnPrice').val())
        if (isNaN(p1) || p1=="") { p1 = 0 }
        if (isNaN(p2) || p2== "") { p2 = 0 }
        
        $("#idComputeSalePrice").val((parseFloat(p1) + parseFloat(p2)));
    });
    
    // 判断积分购买限额不能大于最高返现额
    $("#pointLimited").change(function(){
        var point = $.trim($('#pointLimited').val());
        var price = $.trim($('#returnPrice').val())
        if (isNaN(point) || point=="") { p1 = 0 }
        if (isNaN(price) || price== "") { p2 = 0 }
        
        if (parseFloat(price) < parseFloat(point))
        {
            alert("购买积分限额不能大于最高返现额!");
            $(this).val("0");
        }
    });
});

//创建促销赠品窗口
function show_goods_gift_dialog(obj) {
    var objNum = arguments.length;
    var zengpinDialog = $.dialog({
        id: 'zengpinhDialogId',
        lock: true,
        max: false,
        min: false,
        title: "促销赠品",
        content: 'url:/Verwalter/goods/list/dialog/gift',
        width: 800,
        height: 550
    });
    //如果是修改状态，将对象传进去
    if (objNum == 1) {
        zengpinDialog.data = obj;
    }
}

//创建商品组合窗口
function show_goods_comb_dialog(obj) {
    var objNum = arguments.length;
    var zengpinDialog = $.dialog({
        id: 'zengpinhDialogId',
        lock: true,
        max: false,
        min: false,
        title: "促销赠品",
        content: 'url:/Verwalter/goods/list/dialog/comb',
        width: 800,
        height: 550
    });
    //如果是修改状态，将对象传进去
    if (objNum == 1) {
        zengpinDialog.data = obj;
    }
}
    
//删除促销商品节点
function del_goods_gift(obj) {
    $(obj).parent().parent().remove();
    $("#totalGift").val(parseInt($("#totalGift").val())-1);
}

//删除商品组合节点
function del_goods_comb(obj) {
    $(obj).parent().parent().remove();
    $("#totalComb").val(parseInt($("#totalComb").val())-1);
}

</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/goods/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="menuId" type="text" value='${mid!""}' style="display:none;">
<input name="channelId" type="text" value='${cid!""}' style="display:none">
<input name="id" type="text" value='<#if goods??>${goods.id?c}</#if>' style="display:none">
<!--导航栏-->
<div class="location">
    <a href="/Verwalter/goods/list" class="back"><i></i><span>
        返回列表页</span></a> 
    <a href="/Verwalter/center" class="home">
    <i></i><span>首页</span></a>
    <i class="arrow"></i>
    <span>编辑信息</span>
</div>
<div class="line10">
</div>
<!--/导航栏-->
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap" >
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">扩展选项</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">详细描述</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">价格与库存</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">促销</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">赠品</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">组合商品</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">SEO选项</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="id-first-tab" class="tab-content" style="display: block;">
        <dl>
            <dt>所属类别</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="categoryId" id="categoryId" datatype="*" sucmsg=" ">
                        <#if !goods??>
                        <option value="">请选择类别...</option>
                        </#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id!""}" <#if goods?? && goods.categoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>显示状态</dt>
            <dd>
                <div class="rule-multi-radio multi-radio">
                    <span>
                        <input type="radio" name="isOnSale" value="1" <#if goods??==false || goods.isOnSale==true>checked="checked"</#if>>
                        <label>上架</label>
                        <input type="radio" name="isOnSale" value="0" <#if goods?? && goods.isOnSale?? && goods.isOnSale==false>checked="checked"</#if>>
                        <label>下架</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>推荐类型</dt>
            <dd>
                <div class="rule-multi-checkbox multi-checkbox">
                    <span>
                        <input id="cblItem_0" type="checkbox" name="isRecommendIndex" <#if goods?? && goods.isRecommendIndex?? && goods.isRecommendIndex==true>checked="checked"</#if>>
                        <label for="cblItem_0">首页推荐</label>
                        <input id="cblItem_1" type="checkbox" name="isRecommendType" <#if goods?? && goods.isRecommendType?? && goods.isRecommendType==true>checked="checked"</#if>>
                        <label for="cblItem_1">分类推荐</label>
                        <input id="cblItem_2" type="checkbox" name="isHot" <#if goods?? && goods.isHot?? && goods.isHot==true>checked="checked"</#if>>
                        <label for="cblItem_2">热销</label>
                        <input id="cblItem_3" type="checkbox" name="isNew" <#if goods?? && goods.isNew?? && goods.isNew==true>checked="checked"</#if>>
                        <label for="cblItem_3">新品</label>
                        <input id="cblItem_4" type="checkbox" name="isSpecialPrice" <#if goods?? && goods.isSpecialPrice?? && goods.isSpecialPrice==true>checked="checked"</#if>>
                        <label for="cblItem_4">特价</label>
                    </span>
                </div>
            </dd>
        </dl>
        
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if goods??>${goods.sortId!""}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        
        <dl>
            <dt>封面图片</dt>
            <dd>
                <input id="txtImgUrl" name="coverImageUri" type="text" value="<#if goods??>${goods.coverImageUri!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <div id="thumb_ImgUrl_show1" class="photo-list thumb_ImgUrl_show">
                </div>
            </dd>
        </dl>
        
        <div id="id-param-sec">
            <#if goods??>
                <#include "/site_mag/goods_category_param_list.ftl" />
            </#if>
        </div>
    </div>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>商品名称</dt>
            <dd>
                <input name="name" type="text" value="<#if goods??>${goods.name!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
                <span class="Validform_checktip">*标题最多100个字符</span>
            </dd>
        </dl>
        
        <dl>
            <dt>商品标题</dt>
            <dd>
                <input name="title" type="text" value="<#if goods??>${goods.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" ">
                <span class="Validform_checktip">*标题最多100个字符</span>
            </dd>
        </dl>
        
        <dl>
            <dt>商品副标题</dt>
            <dd>
                <input name="subTitle" type="text" value="<#if goods??>${goods.subTitle!""}</#if>" class="input normal" datatype="*2-255" sucmsg=" ">
                <span class="Validform_checktip">*标题最多255个字符</span>
            </dd>
        </dl>
        <dl>
            <dt>商品编码</dt>
            <dd>
                <input name="code" type="text" value="<#if goods??>${goods.code!""}</#if>" class="input normal" datatype="*0-7" sucmsg=" ">
                <span class="Validform_checktip">*编码最多7个字符</span>
            </dd>
        </dl>
        <dl>
            <dt>服务</dt>
            <dd>
                <textarea name="service" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.service!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>促销</dt>
            <dd>
                <textarea name="promotion" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.promotion!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>配置</dt>
            <dd>
                <textarea name="configuration" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.configuration!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>上架时间</dt>
            <dd>
                <div class="input-date">
                    <input name="onSaleTime" type="text" value="<#if goods??>${goods.onSaleTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认为当前时间</span>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        <dl id="div_show360_container">
            <dt>展示图片</dt>
            <dd>
                <div class="upload-box upload-show360"></div>
                <div class="photo-list_show360">
                    <ul>
                        <#if goods?? && goods.showPictures??>
                            <#list goods.showPictures?split(",") as uri>
                                <#if uri != "">
                                <li>
                                    <input type="hidden" name="hid_photo_name_show360" value="0|${uri!""}|${uri!""}">
                                    <div class="img-box">
                                        <img src="${uri!""}" bigsrc="${uri!""}">
                                    </div>
                                    <a href="javascript:;" onclick="delImg(this);">删除</a>
                                </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>详细描述</dt>
            <dd>
                <textarea name="detail" class="editor"><#if goods??>${goods.detail!""}</#if></textarea>
            </dd>
        </dl>
        <dl>
            <dt>售后服务</dt>
            <dd>
                <textarea name="afterMarketService" class="editor"><#if goods??>${goods.afterMarketService!""}</#if></textarea>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        
        <dl>
            <dt>成本价</dt>
            <dd>
                <input name="costPrice" type="text" value="<#if goods?? && goods.costPrice??>${goods.costPrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">成本价，不在前台显示</span>
            </dd>
        </dl>
        <dl>
            <dt>市场价</dt>
            <dd>
                <input name="marketPrice" type="text" value="<#if goods?? && goods.marketPrice??>${goods.marketPrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">市场价格</span>
            </dd>
        </dl>
        <#--
        <dl>
            <dt>包含费用</dt>
            <dd>
                <input name="includePrice" type="text" value="<#if goods?? && goods.includePrice??>${goods.includePrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">电话卡销售时包含的话费</span>
            </dd>
        </dl>
        <dl>
            <dt>组合价</dt>
            <dd>
                <input name="combPrice" type="text" value="<#if goods?? && goods.combPrice??>${goods.combPrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">商品组合销售时的价格</span>
            </dd>
        </dl>
        -->
        <dl>
            <dt>出厂价</dt>
            <dd>
                <input id="outFactoryPrice" name="outFactoryPrice" type="text" value="<#if goods?? && goods.outFactoryPrice??>${goods.outFactoryPrice?string("0.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">*商品出厂价</span>
            </dd>
        </dl>
        <dl>
            <dt>最高返现额</dt>
            <dd>
                <input id="returnPrice" name="returnPrice" type="text" value="<#if goods?? && goods.returnPrice??>${goods.returnPrice?string("0.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">*返还分销商的最高可能金额，不填时为0</span>
            </dd>
        </dl>
        <dl>
            <dt>销售价</dt>
            <dd>
                <input id="idComputeSalePrice" name="salePrice" type="text" disabled="disabled" value="<#if goods?? && goods.salePrice??>${goods.salePrice?string("0.00")}<#else>0</#if>" class="input normal" sucmsg="" style="background: #EEEEEE;">
                <span class="Validform_checktip">系统自动计算 (销售价 = 出厂价 + 最高返现额)</span>
            </dd>
        </dl>
        <dl>
            <dt>积分购买限额</dt>
            <dd>
                <input id="pointLimited" name="pointLimited" type="text" value="<#if goods?? && goods.pointLimited??>${goods.pointLimited?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">购买时可使用的积分限额，必须小于最高返现额</span>
            </dd>
        </dl>
        <dl>
            <dt>赠送积分</dt>
            <dd>
                <input name="returnPoints" type="text" value="<#if goods?? && goods.returnPoints??>${goods.returnPoints?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">购买该商品赠送的积分</span>
            </dd>
        </dl>
        <#if warehouse_list??>
        <dl>
            <dt>所在仓库</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="warehouseId" datatype="*0-100" sucmsg=" ">
                        <option value="" <#if !warehouse_list??>selected="selected"</#if>>请选择类别...</option>
                        <#list warehouse_list as w>
                            <option value="${w.id!""}" <#if goods.warehouseId?? && goods.warehouseId==w.id>selected="selected"</#if>>${w.title!""}</option>
                        </#list>
                    </select>
                </div>
            </dd>
        </dl>
        </#if>
        <dl>
            <dt>库存余量</dt>
            <dd>
                <input name="leftNumber" type="text" value="<#if goods?? && goods.leftNumber??>${goods.leftNumber?c!"1"}<#else>1</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">库存为0时显示为缺货</span>
            </dd>
        </dl>
        <dl>
            <dt>销量</dt>
            <dd>
                <input name="soldNumber" type="text" value="<#if goods?? && goods.soldNumber??>${goods.soldNumber?c!"0"}<#else>0</#if>" class="input normal" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">商品已销售数量</span>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        
        <dl>
            <dt>支持限时抢购</dt>
            <dd>
                <div class="rule-multi-radio multi-radio">
                    <span>
                        <input type="radio" name="isFlashSale" value="1" <#if goods?? && goods.isFlashSale?? && goods.isFlashSale==true>checked="checked"</#if> >
                        <label>是</label>
                        <input type="radio" name="isFlashSale" value="0" <#if goods??==false || goods.isFlashSale??==false || goods.isFlashSale==false>checked="checked"</#if>>
                        <label>否</label>
                    </span>
                </div>
            </dd>
        </dl>
        
        <dl>
            <dt>开始时间</dt>
            <dd>
                <div class="input-date">
                    <input name="flashSaleStartTime" type="text" value="<#if goods??>${goods.flashSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认为当前时间</span>
            </dd>
        </dl>
        
        <dl>
            <dt>结束时间</dt>
            <dd>
                <div class="input-date">
                    <input name="flashSaleStopTime" type="text" value="<#if goods??>${goods.flashSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认为当前时间</span>
            </dd>
        </dl>
        
        <dl>
            <dt>限时抢购价</dt>
            <dd>
                <input name="flashSalePrice" type="text" value="<#if goods?? && goods.flashSalePrice??>${goods.flashSalePrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">商品限时抢购价格</span>
            </dd>
        </dl>
        
        <dl>
            <dt>限时抢购剩余数量</dt>
            <dd>
                <input name="flashSaleLeftNumber" type="text" value="<#if goods??>${goods.flashSaleLeftNumber!''}<#else>0</#if>" class="input normal" datatype="n0-10" sucmsg=" ">
                <span class="Validform_checktip">为0时抢购结束</span>
            </dd>
        </dl>
        <dl>
            <dt>限时抢购销量</dt>
            <dd>
                <input name="flashSaleSoldNumber" type="text" value="<#if goods??>${goods.flashSaleSoldNumber!''}<#else>0</#if>" class="input normal" datatype="n0-10" sucmsg=" ">
                <span class="Validform_checktip">已抢购商品数量</span>
            </dd>
        </dl>
        
        
        <dl>
            <dt>支持团购</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span>
                        <input type="radio" name="isGroupSale" value="1" <#if goods?? && goods.isGroupSale?? && goods.isGroupSale==true>checked="checked"</#if>>
                        <label>是</label>
                        <input type="radio" name="isGroupSale" value="0" <#if goods??==false || goods.isGroupSale??==false || goods.isGroupSale==false>checked="checked"</#if>>
                        <label>否</label>
                    </span>
                </div>
            </dd>
        </dl>
        <dl>
            <dt>开始时间</dt>
            <dd>
                <div class="input-date">
                    <input name="groupSaleStartTime" type="text" value="<#if goods??>${goods.groupSaleStartTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认为当前时间</span>
            </dd>
        </dl>
        
        <dl>
            <dt>结束时间</dt>
            <dd>
                <div class="input-date">
                    <input name="groupSaleStopTime" type="text" value="<#if goods??>${goods.groupSaleStopTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认为当前时间</span>
            </dd>
        </dl>
        <dl>
            <dt>闪购团购展示图片</dt>
            <dd>
                <input id="groupSaleImage" name="groupSaleImage" type="text" value="<#if goods??>${goods.groupSaleImage!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <div id="thumb_ImgUrl_show2" class="photo-list thumb_ImgUrl_show">
                </div>
            </dd>
        </dl>
        <dl>
            <dt>团购价</dt>
            <dd>
                <input name="groupSalePrice" type="text" value="<#if goods?? && goods.groupSalePrice??>${goods.groupSalePrice?string("#.##")}<#else>0</#if>" class="input normal" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" sucmsg=" ">
                <span class="Validform_checktip">商品团购价格</span>
            </dd>
        </dl>
        <dl>
            <dt>团购剩余数量</dt>
            <dd>
                <input name="groupSaleLeftNumber" type="text" value="<#if goods?? && goods.groupSaleLeftNumber??>${goods.groupSaleLeftNumber?c}<#else>0</#if>" class="input normal" datatype="n0-10" sucmsg=" ">
                <span class="Validform_checktip">为0时团购结束</span>
            </dd>
        </dl>
        <dl>
            <dt>团购销量</dt>
            <dd>
                <input name="groupSaleSoldNumber" type="text" value="<#if goods?? && goods.groupSaleSoldNumber??>${goods.groupSaleSoldNumber?c}<#else>0</#if>" class="input normal" datatype="n0-10" sucmsg=" ">
                <span class="Validform_checktip">团购已售商品数量</span>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>赠品</dt>
            <dd>
                <a id="addGift" class="icon-btn add"><i></i><span>添加赠品</span></a>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt></dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="6%">
                                排序
                            </th>
                            <th width="10%">
                                商品ID
                            </th>
                            <th width="38%">
                                赠品标题
                            </th>
                            <th width="10%">
                                原价
                            </th>
                            <th width="6%">
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody id="var_box_gift">
                        <input type="hidden" id="totalGift" name="totalGift" value="<#if goods??>${goods.totalGift!'0'}</#if>" />
                        <#if goods?? && goods.giftList??>
                            <#list goods.giftList as gift>
                                <tr class="td_c">
                                    <td>
                                        <input name="giftList[${gift_index}].id" type="hidden" value="${gift.id!''}">
                                        <input name="giftList[${gift_index}].coverImageUri" type="hidden" value="${gift.coverImageUri!''}">
                                        <input type="text" name="giftList[${gift_index}].sortId" class="td-input" value="${gift.sortId!''}" style="width:90%;">
                                    </td>
                                    <td><input type="text" id="id" name="giftList[${gift_index}].goodsId" class="td-input" value="${gift.goodsId!''}" style="width:90%;"></td>
                                    <td>
                                        <input type="text" id="title" name="giftList[${gift_index}].goodsTitle" class="td-input" value="${gift.goodsTitle!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="price" name="giftList[${gift_index}].goodsPrice" class="td-input" value="${gift.goodsPrice?string("0.00")}" style="width:90%;">
                                    </td>
                                    <td>
                                        <i class="icon"></i>
                                        <a title="编辑" class="img-btn edit operator" onclick="show_goods_gift_dialog(this);">编辑</a>
                                        <a title="删除" class="img-btn del operator" onclick="del_goods_gift(this);">删除</a>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>商品组合</dt>
            <dd>
                <a id="addCombination" class="icon-btn add"><i></i><span>添加组合</span></a>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt></dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="6%">
                                排序
                            </th>
                            <th width="10%">
                                商品ID
                            </th>
                            <th width="38%">
                                赠品标题
                            </th>
                            <th width="10%">
                                原价
                            </th>
                            <th width="10%">
                                组合价
                            </th>
                            <th width="6%">
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody id="var_box_comb">
                        <input type="hidden" id="totalComb" name="totalComb" value="<#if goods??>${goods.totalComb!'0'}</#if>" />
                        <#if goods?? && goods.combList??>
                            <#list goods.combList as item>
                                <tr class="td_c">
                                    <td>
                                        <input name="combList[${item_index}].id" type="hidden" value="${item.id?c!''}">
                                        <input name="combList[${item_index}].coverImageUri" type="hidden" value="${item.coverImageUri!''}">
                                        <input type="text" name="combList[${item_index}].sortId" class="td-input" value="${item.sortId!''}" style="width:90%;">
                                    </td>
                                    <td><input type="text" id="id" name="combList[${item_index}].goodsId" class="td-input" value="${item.goodsId!''}" style="width:90%;"></td>
                                    <td>
                                        <input type="text" id="title" name="combList[${item_index}].goodsTitle" class="td-input" value="${item.goodsTitle!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="price" name="combList[${item_index}].goodsPrice" class="td-input" value="${item.goodsPrice?string("0.00")}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="currentPrice" name="combList[${item_index}].currentPrice" class="td-input" value="${item.currentPrice?string("0.00")}" style="width:90%;">
                                    </td>
                                    <td>
                                        <i class="icon"></i>
                                        <a title="编辑" class="img-btn edit operator" onclick="show_goods_comb_dialog(this);">编辑</a>
                                        <a title="删除" class="img-btn del operator" onclick="del_goods_comb(this);">删除</a>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if goods??>${goods.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" id="txtSeoKeywords" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if goods??>${goods.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
    </div>
    
    
    
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>
</body>
</html>