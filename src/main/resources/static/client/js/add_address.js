/**
 * 初步进入选择行政区划，行政街道的方法
 * 
 * @author dengxiao
 */
function selectDetail() {
	// 获取所有的项的内容
	var receiver = $("#receiver").val();
	var receiverMobile = $("#receiverMobile").val();
	var detailAddress = $("#detailAddress").val();
	// 开启等待图标
	wait();
	window.location.href = "/user/address/add/1" + "?receiver=" + receiver
			+ "&receiverMobile=" + receiverMobile + "&detailAddress="
			+ detailAddress;
}

/**
 * 在选择行政区划，行政街道的页面中进入下一步的方法
 * 
 * @author dengxiao
 */
function theNextStep(id) {
	// 获取步骤编号
	var type = $("#type").val();
	// 开启等待图标
	wait();
	window.location.href = "/user/address/add/" + type + "?id=" + id;
}

/**
 * 提交保存新的收货地址的方法
 * 
 * @author dengxiao
 */
function saveAddress() {
	// 获取所有的项的内容
	var receiver = $("#receiver").val();
	var receiverMobile = $("#receiverMobile").val();
	var detailAddress = $("#detailAddress").val();

	// 开启等待图标
	wait();

	// 发送异步请求
	$.ajax({
		url : "/user/address/add/save",
		type : "post",
		timeout : 10000,
		data : {
			receiver : receiver,
			receiverMobile : receiverMobile,
			detailAddress : detailAddress
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 关闭等待图标
			close(1);
			warning("亲，您的网速不给力啊");
		},
		success : function(res) {
			// 未登陆的情况下直接跳转到登陆页面
			if (-2 == res.status) {
				window.location.href = "/login";
				return;
			}

			// 关闭等待图标
			close(100);
			if (0 == res.status) {
				warning("收货地址已经添加");
				setTimeout(function() {
					window.location.href = "/user/address";
				}, 1000);
			} else {
				warning(res.message);
			}
		}
	});
}
