// 改变商品数量的方法
function changeQuantity(goodsId, operation) {
	// 获取指定商品显示数量的输入框的id
	var quantityElementId = "#quantity" + goodsId;
	// 获取当前指定商品选择的数量
	var quantity = $(quantityElementId).val();
	// 获取当前已选商品的数量
	var selectNumber = $("#select_num").html();

	// 如果是减少当前商品的数量
	if ("delete" == operation) {
		// 如果当前商品的数量已经是0了就不做任何处理
		if (0 == quantity) {
			return;
		}
		// 如果当前商品的数量是1，则减1后，代表该件商品没有被选中
		if (1 == quantity) {
			selectNumber = parseInt(selectNumber) - 1;
		}
		// 正常减少数量
		quantity = parseInt(quantity) - 1;
	}

	// 如果是增加商品数量的情况
	if ("add" == operation) {
		// 如果当前商品的的数量是0，则加1之后，代表这件商品被选中
		if (0 == quantity) {
			selectNumber = parseInt(selectNumber) + 1;
		}
		// 正常增加数量
		quantity = parseInt(quantity) + 1;
	}

	// 把更新后的商品信息和已选数量显示到页面上
	$(quantityElementId).val(quantity);
	$("#select_num").html(selectNumber);
}

// 选择调色包的方法
function changeColor(goodsId) {
	// 获取当前商品的数量
	var quantityElementId = "#quantity" + goodsId;
	var quantity = $(quantityElementId).val();

	// 开启等待响应的图标
	wait();

	// ajax获取指定商品的所有调色包
	$.post("/goods/get/color", {
		"goodsId" : goodsId,
		"quantity" : quantity
	}, function(res) {
		$("#color_package_select").html(res);

		// 关闭等待响应的图标
		close(1000);

		$('.colo_choice').height($(window).height())
		$('.colo_box').height($(window).height() - 150)
		$('.colo_box li').height($('.colo_box li').width() * 1)
		$('.colo_sec').height($(window).height() - 50)
		var hei = $(window).height() - 100
		$('.colo_sec dl').css({
			maxHeight : hei
		});
		$('.colo_choice').animate({
			left : '0px'
		});
		$('.colo_back').click(function() {
			$('.colo_choice').css({
				left : '100%'
			});
		});

		$('.colo_test').click(function() {
			$('.colo_choice').css({
				left : '100%'
			});
		});

		$('.colo_title a').click(function() {
			$('.colo_sec').slideDown()
		});
		$('.colo_clo').click(function() {
			$('.colo_sec').slideUp()
		});
	});
}

// 选择指定调色包的方法
function getColor(colorNum, colorPrice) {
	$("#color_name").html(colorNum);
	var nowNum = $("#select_color_quantity").val();
	var totalPrice = nowNum * colorPrice;
	$("#color_price").html("￥" + totalPrice);
	$("#unit_price").val(colorPrice);
}

// 改变选中调色包的数量的方法
function changeColorNum(operation) {
	var the_new_quantity = $("#the_new_quantity").val();
	var nowNum = $("#select_color_quantity").val();
	// 增加数量的情况
	if ("add" == operation) {
		$("#select_color_quantity").val(parseInt(nowNum) + 1);
		var unit_price = $("#unit_price").val();
		var colorPrice = unit_price * (parseInt(nowNum) + 1);
		$("#color_price").html("￥" + colorPrice);
	}
	// 减少数量的情况
	if ("delete" == operation) {
		if (0 == nowNum) {
			return;
		}
		$("#select_color_quantity").val(parseInt(nowNum) - 1);
		var unit_price = $("#unit_price").val();
		var colorPrice = unit_price * (parseInt(nowNum) - 1);
		$("#color_price").html("￥" + colorPrice);
	}
}

// 确定调色的方法
function addColor() {
	// 获取当前数量
	var nowNum = $("#select_color_quantity").val();
	// 如果数量为1，不进行任何操作
	if (0 == nowNum) {
		return;
	}
	// 获取当前的商品id
	var goodsId = $("#goods_id").val();
	// 获取当前的颜色编号
	var colorName = $("#color_name").html();

	// 开启等待响应的图标
	wait();

	// 进行异步请求，存储数据
	$.ajax({
		url : "/goods/color/add",
		timeout : 10000,
		method : "post",
		data : {
			"colorName" : colorName,
			"goodsId" : goodsId,
			"quantity" : nowNum
		},
		success : function(res) {
			$("#select_colors_by_dx").html(res);

			// 关闭等待响应的图标
			close();

			var the_new_quantity = $("#the_new_quantity").val();
			$("#select_color_quantity").val(the_new_quantity);
			var unit_price = $("#unit_price").val();
			var colorPrice = unit_price * the_new_quantity;
			$("#color_price").html("￥" + colorPrice);

			$('.colo_choice').height($(window).height())
			$('.colo_box').height($(window).height() - 150)
			$('.colo_box li').height($('.colo_box li').width() * 1)
			$('.colo_sec').height($(window).height() - 50)
			var hei = $(window).height() - 100
			$('.colo_sec dl').css({
				maxHeight : hei
			});
			$('.colo_choice').animate({
				left : '0px'
			});
			$('.colo_back').click(function() {
				$('.colo_choice').css({
					left : '100%'
				});
			});

			$('.colo_test').click(function() {
				$('.colo_choice').css({
					left : '100%'
				});
			});

			$('.colo_title a').click(function() {
				$('.colo_sec').slideDown()
			});
			$('.colo_clo').click(function() {
				$('.colo_sec').slideUp()
			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.debug(textStatus);
			// 关闭等待响应的图标
			close(1);
			alert("亲，您的网速不给力啊！");
		}
	});
}

function deleteSelectedColorPackage(id, goodsId) {
	// 获取节点元素的id
	var docElementId = "#colorPackage" + id;
	// 获取节点元素
	var ele = $(docElementId);
	$.ajax({
		url : "/goods/delete/color",
		method : "post",
		data : {
			"colorPackageId" : id,
			"goodsId" : goodsId
		},
		success : function(res) {
			ele.remove();
		}
	});
}

// window.location.href='/goods/detail/${goods.id?c}'
function getGoodsDetail(goodsId) {
	// 获取指定商品显示数量的输入框的id
	var quantityElementId = "#quantity" + goodsId;
	// 获取当前指定商品选择的数量
	var quantity = $(quantityElementId).val();

	window.location.href = "/goods/detail/" + goodsId + "?quantity="
			+ quantity;

}