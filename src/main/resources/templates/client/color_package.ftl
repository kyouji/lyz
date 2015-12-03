<div class="colo_choice">
    <div class="colo_test"></div>
        <div class="win_colo">
            <!--描述：定位颜色选择第二层-->
            <div id="select_colors_by_dx">
                <#include "/client/selected_color_package.ftl">
            </div>
                
            <div class="colo_title">
                <div class="colo_back"><img src="/client/images/colo_back.png"></div>
                <p>调色选择</p>
                <a><img src="/client/images/colo_choic.png"/></a>
            </div>
            <ul class="colo_box">
                <#if color_package_list??>
                    <#list color_package_list as item>
                        <li onclick="getColor('${item.number!''}','<#if item.price??>${item.price?string('0.00')}<#else>0.00</#if>')"><img src="${item.imageUri!''}"></li>
                    </#list>
                </#if>
            </ul>
            <div class="colo_num">
                <p id="color_name">
                    <#if color_package_list??>
                        <#list color_package_list as item>
                            <#if item_index==0>
                                ${item.number!''}
                            </#if>
                        </#list>
                    </#if>
                </p>
                <div class="colo_add">
                    <span onclick="changeColorNum('delete');">-</span>
                    <input type="text" readonly="true" style="text-align:center;" id="select_color_quantity" value="0" />
                    <span onclick="changeColorNum('add');">+</span>
                </div>
                <p id="color_price">￥0</p>
            </div>
            <div class="down_buy" onclick="addColor()">确定</div>
        </div>
    </div>