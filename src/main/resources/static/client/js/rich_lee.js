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
// $('.colo_choice').css({
// left : '0px'
// })
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
		// 开启等待图标
		wait();
		$.ajax({
			url:"/goods/get/color",
			method:"post",
			timeout:10000,
			data:{
				"goodsId" : goodsId
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				close(1);
				warning("亲，您的网速不给力啊");
			},
			success:function(res){
				// 关闭等待图标
				close(1000);
				// 进行局部刷新
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
			}
		})
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
	// $('.index_head div').click(function(){
		// console.log(0)
		$('.win_cla').css({left:'0px'});			
		$('.win_cla dd a').css({WebkitTransform:'translateX(0px)'});
		$('.win_cla dd a').each(function(i){
			$(this).css({WebkitTransition:'1s ' + i*200 +'ms'});				
		});												
	// });
	// console.log(0)
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
	// console.log(hei)
	$('.win_cla dt span').css({marginTop:''+hei+'px'})
	$('.win_cla dt span').height($('.win_cla dt span').width())
};


function footer(){
	var le = ($(window).width() - $('.footer_act').width())/2;
	$('.footer_act').css({left:le});
};


function fen_scroll(){
	$('.fen_testleft').height($(window).height());
	var che = $('.fen_goodbox dl dt .my_checkbox');
	var dlb = $('.fen_goodbox dl dt')
	var hei = (dlb.height() - che.height())/2
	che.css({marginTop:hei})
	$('.fen_goodbox').height($(window).height()-150)
	/////////////
	var all_li = 0;
	var all_ul = $('.fen_testtop ul');
	//////循环ul
	all_ul.each(function(i){
		var len = all_ul.eq(i).children().size()*82;
		all_ul.eq(i).width(len);
		/*my_draf($('.fen_testtop ul').eq(i),$('.fen_testtop'),'left')*/
	});
	all_ul.css({display:'none'});
	all_ul.eq(0).css({display:'block'});
	$('.fen_testleft ul li a').eq(0).css({background:'#cc1421',color:'white' })
	$('.fen_testleft ul li').each(function(i){
		$('.fen_testleft ul li').eq(i).click(function(){
			all_ul.css({display:'none'});
			all_ul.eq(i).css({display:'block'});
			$('.fen_testleft ul li a').css({background:'#f8f8f8',color:'#333333' })
			$('.fen_testleft ul li a').eq(i).css({background:'#cc1421',color:'white' })
		});
	});
};

function my_draf(obj,obj_child,guide,page){
	var go = window.screen.width;
	var timer = null;
	var iNow = 0 ;    // 记录 索引
	var iScroll = 0;  // 滑动的距离 每次滑动的距离 相加 储存在myX里面
	var straX = 0;  // 最开始的坐标位子
	var myX = 0;// 用来储存滑动的距离
	var len = -obj.width()+obj_child.width();
	
	obj.bind('touchstart',function(){
		straX = event.changedTouches[0].pageX;				
		myX	= iScroll;
	});
	obj.bind('touchmove',function(){
		var disX =event.changedTouches[0].pageX - straX;				
		iScroll= myX + disX;										
		obj.animate({[guide]:''+iScroll+'px'},0)
	});
	obj.bind('touchend',function(){
		var disX =event.changedTouches[0].pageX - straX;				
		iScroll= myX + disX;
		if(iScroll>0){
			iScroll=0;
		}else if(iScroll<len){
			iScroll = len;
		}
		obj.animate({[guide]:''+iScroll+'px'},400)
	});
};
