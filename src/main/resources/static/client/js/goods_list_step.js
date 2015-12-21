function clickLevelTwo(elementId) {
	// 点击左变切换
	$('.fen_testtop ul li a').css({
		background : '#e8e8e8',
		color : '#333333'
	});
	// 是被点击的元素变颜色
	$("#" + elementId).css({
		background : '#ffaa00',
		color : 'white'
	});

	// 显示出正确的商品栏
	$(".ctrlGoods").css("display", "none");
	$("#goods" + elementId).css("display", "block");
}