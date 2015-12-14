window.onload = function() {
	win_cla();// 分类弹窗
	my_hei();// 百分比高设置
	banner_scroll();// 首页banner滑动
	scroll_news();// 滚动新闻
    my_select();
	footer();
};

function my_select() {
	$('.reg_content dt select').select(function() {
		var optionvalue = $(this).find('option:selected').text();
		$('#my_box').text(optionvalue);
	});
}

function turn_hei(obj,my_math){
	obj.height((obj.width())*my_math);
};

function my_hei() {
	turn_hei($('.index_banner'), 0.5)// 首页
	// turn_hei($('.index_banner img'),0.5)//首页图片

	turn_hei($('.index_nav li a'), 1)// nav导航

	turn_hei($('.index_goods01 dl dd'), 0.44)// 活动促销
	turn_hei($('.index_goods02'), 1)// nav

	turn_hei($('.index_goods03 ul li a'), 1.6)// nav
};

function banner_scroll() {

	var go = window.screen.width;
	var timer = null;
	var iNow = 0; // 记录 索引
	var iScroll = -2 * go; // 滑动的距离 每次滑动的距离 相加 储存在myX里面
	var straX = 0; // 最开始的坐标位子
	var myX = 0;// 用来储存滑动的距离
	// 移除 冒泡 和 默认
	/*
	 * $(document).bind('touchmove',function(){ return false; })
	 */

	$('.scroll_box').bind('touchstart', function() {
		$('.scroll_box').css({
			WebkitTransition : '0'
		})
		straX = event.changedTouches[0].pageX;
		myX = iScroll;
	});

	$('.scroll_box').bind('touchmove', function() {

		var disX = event.changedTouches[0].pageX - straX;
		iScroll = myX + disX;

		// $('.box').css({WebkitTransform:'translateX('+iScroll+'px)'})
		$('.scroll_box').animate({
			left : '' + iScroll + 'px'
		}, 0)
	});

	$('.scroll_box').bind(
			'touchend',
			function() {

				var disX = event.changedTouches[0].pageX - straX;
				// iScroll = myX + disX;
				iScroll = myX + disX;
				iNow = iScroll / go;
				iNow = Math.round(iNow)
				iScroll = iNow * go;

				if (!$('.scroll_box').is(':animated')) {
					$('.scroll_box').animate(
							{
								left : '' + iScroll + 'px'
							},
							400,
							function() {
								if (iScroll <= -3 * go) {
									$('.scroll_box img:first').insertAfter(
											$('.scroll_box img:last'))
									$('.scroll_box').css({
										left : '-200%'
									})
									myX = iScroll = -2 * go;

								} else if (iScroll >= -go) {
									$('.scroll_box img:last').insertBefore(
											$('.scroll_box img:first'))
									$('.scroll_box').css({
										left : '-200%'
									})
									myX = iScroll = -2 * go;
								}
								;
								console.log(iNow)
							})
				}
				;
			});
	// 自动走
};

function scroll_news() {
	var timer = setInterval(function() {
		$('.scroll_newsbox').animate(
				{
					top : '-40px'
				},
				2000,
				function() {
					$('.scroll_newsbox a:first').insertAfter(
							$('.scroll_newsbox a:last'));
					$('.scroll_newsbox').css({
						top : '0'
					});
				});
	}, 5000);

};

function win_colo_temp() {
	$('.colo_choice').height($(window).height())
	$('.colo_box').height($(window).height() - 150)
	$('.colo_box li').height($('.colo_box li').width() * 1)
	$('.colo_sec').height($(window).height() - 50)
	var hei = $(window).height() - 100
	$('.colo_sec dl').css({
		maxHeight : hei
	})

	$('.lei_box01 dl dt a').click(function() {
//		$('.colo_choice').css({
//			left : '0px'
//		})
	})
	$('.colo_back').click(function() {
		$('.colo_choice').css({
			left : '100%'
		})
	})

	$('.colo_test').click(function() {
		$('.colo_choice').css({
			left : '100%'
		})
	})

	$('.colo_title a').click(function() {
		$('.colo_sec').slideDown()
	})
	$('.colo_clo').click(function() {
		$('.colo_sec').slideUp()
	})
}

function win_colo(obj,goodsId){
	obj.click(function(){
		//console.log(0)
		var quantityElementId = "#quantity"+goodsId;
		var quantity = $(quantityElementId).val();
		$.post("/goods/get/color",{
			"goodsId" : goodsId,
			"quantity" : quantity
		},function(res){
			//进行局部刷新
			$("#color_package_select").html(res);
			$('.colo_choice').height($(window).height())
			$('.colo_box').height($(window).height()-150)
			$('.colo_box li').height($('.colo_box li').width()*1)
			$('.colo_sec').height($(window).height()-50)
			var hei = $(window).height()-100
			$('.colo_sec dl').css({maxHeight:hei})
			$('.colo_choice').css({left:'0px'})	
			$('.colo_back').click(function(){
				$('.colo_choice').css({left:'100%'})	
			})
			
			$('.colo_test').click(function(){
				$('.colo_choice').css({left:'100%'})	
			})
			
			$('.colo_title a').click(function(){
				if($('.colo_sec').css('display') == 'none'){
					$('.colo_sec').slideDown()	
				}else if($('.colo_sec').css('display') == 'block'){
					$('.colo_sec').slideUp()
				};			
			})
			$('.colo_clo').click(function(){
				$('.colo_sec').slideUp()	
			})
		});
	});
};


function my_check() {
	// var my_num = [];
	$('.my_black').hide();
	$('.my_checkbox').each(function(i) {
		$(this).click(function() {
			if ($('.my_black').eq(i).css('display') == 'none') {
				$('.my_black').eq(i).show();
			} else {
				$('.my_black').eq(i).hide();
			}
			;
			/*
			 * if($('.my_black').css('display') == 'block'){
			 * $('.go_buy').animate({height:'50px',bottom:'60px'})
			 * 
			 * }else if($('.my_black').css('display') == 'none'){
			 * $('.go_buy').animate({height:'0px',bottom:'0px'}) }
			 */
			// ///////////娱乐 统计数据
			/*
			 * if($('.my_black').eq(i).css('display') == 'block'){
			 * my_num.push($('.my_black').eq(i)) }else
			 * if($('.my_black').css('display') == 'none'){
			 * my_num.shift($('.my_black').eq(i))); } $('.go_buy
			 * span').text(my_num.length); //console.log(my_num.length)
			 */
		})
	})
};

function win_out(){
	//$('.index_head div').click(function(){
		//console.log(0)
		$('.win_cla').css({left:'0px'});			
		$('.win_cla dd a').css({WebkitTransform:'translateX(0px)'});
		$('.win_cla dd a').each(function(i){
			$(this).css({WebkitTransition:'1s ' + i*200 +'ms'});				
		});												
	//});
	//console.log(0)
};



function win_cla(){
	$('.win_cla').height($(window).height())
	

	$('.win_cla dt span').click(function(){
		$('.win_cla').css({left:'100%'});
		$('.win_cla dd a').css({WebkitTransform:'translateX(100%)'});
		$('.win_cla dd a').each(function(i){
			$(this).css({WebkitTransition:'1s'});				
		});				
	});	
	
	var hei = ($(window).height() - $('.win_cla dt span').height())/2
	//console.log(hei)
	$('.win_cla dt span').css({marginTop:''+hei+'px'})
	$('.win_cla dt span').height($('.win_cla dt span').width())
};


function footer(){
	var le = ($(window).width() - $('.footer_act').width())/2;
	$('.footer_act').css({left:le});
};

