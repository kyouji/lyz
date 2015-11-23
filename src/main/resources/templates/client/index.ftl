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

	<body>
		<div>
			<header class="index_head">
				<span><#if user??>${user.cityName!''}</#if></span>
				<input type="" name="" id="" placeholder="快速搜索商品" />
				<div ></div>
			</header>
		    <#if circle_ad_list??>
    			<div class="index_banner">
    				<div class="scroll_box">
    				    <#list circle_ad_list as item>
        					<a href="${item.linkUri!'#'}"><img src="${item.fileUri!''}" /></a>
    					</#list>
    				</div>
    				<div class="scroll_btn">
    					<ul>
					        <#list circle_ad_list as item>
        						<li></li>
    						</#list>
    					</ul>
    				</div>				
    			</div>
			</#if>
			<#if navi_bar_list??>
    			<ul class="index_nav">
    				<li>
    				    <#list navi_bar_list as item>
    				        <#if item_index lt 4>
            					<a href="${item.linkUri!'#'}">
            						<img src="${item.iconUri!''}" />
            						<span>${item.title!''}</span>
            					</a>
        					</#if>
    					</#list>
    				</li>
    				<li>
    				    <#list navi_bar_list as item>
                            <#if item_index gt 3&&item_index lt 8>
            					<a href="${item.linkUri!'#'}">
                                    <img src="${item.iconUri!''}" />
                                    <span>${item.title!''}</span>
                                </a>
        					</#if>
    					</#list>
    				</li>
    			</ul>
			</#if>
			<section class="index_content">
        		<#if headline_list??>
    				<dl class="index_news">
    					<dt><img src="/client/images/scroll_news.png" /></dt>
    					<dd>
    						<div class="scroll_newsbox">
						        <#list headline_list as item>
						            <a href="/article/${item.id?c}">${item.title!''}</a>
    							</#list>
    						</div>
    					</dd>				
    				</dl>
				</#if>
				<div class="index_test_box"></div>
				<div class="index_goods01">
					<dl>
						<dt>
							<p>活动促销<span>· 优惠多多</span></p>							
							<a href="#"><img src="/client/images/index_guide_right.png" /></a>
						</dt>
						<dd>
							
							<div class="index_scroll_goods">
								<a href="#">
									<div>
										<img src="/client/images/index_banner02.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner03.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner04.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner02.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner02.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner02.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner02.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								<a href="#">
									<div>
										<img src="/client/images/index_banner03.png"  />
									</div>
									<p>￥1700.00</p>
									<span>满减</span>
									<span>满赠</span>
								</a>
								
							</div>
						</dd>
					</dl>
				</div>
				<div class="index_goods02">
					<dl>
						<dt>
							<a class="my_indexgood" href="#">
								<h3>乳胶漆 </h3><div class="guide"></div>
								<p>油漆涂料辅料 调色色浆</p>
								<span>￥39.00</span>
								<div><img src="/client/images/index_goods_img01.png" /></div>
							</a>
						</dt>
						<dd>
							<a class="my_indexgood02" href="#">
								<div class="rich_box">
									<h3>乳胶漆 </h3><div class="guide"></div>
									<p>油漆涂料辅料 调色色浆</p>
									<span>￥39.00</span>
								</div>
								<div><img src="/client/images/index_goods_img02.png" /></div>								
							</a>
						</dd>
						<dd>
							<a class="my_indexgood02" href="#">
								<div class="rich_box">
									<h3>乳胶漆 </h3><div class="guide"></div>
									<p>油漆涂料辅料 调色色浆</p>
									<span>￥39.00</span>
								</div>
								<div><img src="/client/images/index_goods_img03.png" /></div>								
							</a>
						</dd>
					</dl>
					<ul>
						<li>
							<a class="my_indexgood03" href="#">
								<h3>乳胶漆 </h3><div class="guide"></div>
								<p>油漆涂料辅料 调色色浆</p>
								<span>￥39.00</span>
								<div><img src="/client/images/index_goods_img04.png" /></div>
							</a>
						</li>
						<li>
							<a class="my_indexgood03" href="#">
								<h3>乳胶漆 </h3><div class="guide"></div>
								<p>油漆涂料辅料 调色色浆</p>
								<span>￥39.00</span>
								<div><img src="/client/images/index_goods_img05.png" /></div>
							</a>
						</li>
						<li>
							<a class="my_indexgood03" href="#">
								<h3>乳胶漆 </h3><div class="guide"></div>
								<p>油漆涂料辅料 调色色浆</p>
								<span>￥39.00</span>
								<div><img src="/client/images/index_goods_img06.png" /></div>
							</a>
						</li>
					</ul>
				</div>
				<div class="index_test_box"></div>
				<div class="index_goods03">
				    <#if index_center??>
    					<div class="goods03_title">
    					   <a href= "${index_center.linkUri!'#'}"><img src="${index_center.fileUri!''}"/></a>
    					</div>
					</#if>
					<#if index_recommend_list??>
    					<ul>
    					    <#list index_recommend_list as item>
    					       <#if item_index==0||(item_index)%2==0>
        						    <li>
						       </#if>
        							<a class="good03_box" href="#">
        								<div>
        									<img src="/client/images/index_goods_img07.png" />
        									<span>满减</span>
        									<span>满减</span>
        								</div>
        								<p>华润漆 净味惠涂易内墙乳胶漆RS901-18L 油漆涂料 内</p>
        								<span class="box03_pri">￥700.00</span>
        							</a>
        							<a class="good03_box" href="#">
        								<div>
        									<img src="/client/images/index_goods_img07.png" />
        									<span>满减</span>
        									<span>满减</span>
        								</div>
        								<p>华润漆 净味惠涂易内墙乳胶漆RS901-18L 油漆涂料 内</p>
        								<span class="box03_pri">￥700.00</span>
        							</a>
    							<#if item_index==1||item_index==(index_recommend_list?size-1)||(item_index+1)%2==0>
        						    </li>
        						</#if>
    						</#list>
    					</ul>
					</#if>
				</div>
			</section>
			<div class="index_test_box02"></div>
			<div class="index_footer">
				<ul>
					<li>
						<a href="/">
							<div></div>
							<span>首页</span>
						</a>
					</li>
					<li>
						<a href="#">
							<div></div>
							<span>下单</span>
						</a>
					</li>
					<li>
						<a href="#">
							<div></div>
							<span>我的</span>
						</a>
					</li>
					<li>
						<a href="#">
							<div></div>
							<span>已选</span>
						</a>
					</li>
				</ul>
				<div class="footer_act">
					<a href="#"></a>
				</div>
			</div>
		</div>		
	</body>
</html>
