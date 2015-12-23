function operate(operation, type, id) {
	if (0 == type) {
		var elementId = "#goods" + id;
		var inventoryId = "#goods" + id + "quantity";
		var priceId = "#goods" + id + "price";
	}
	if (1 == type) {
		var elementId = "#color" + id;
		var inventoryId = "#color" + id + "quantity";
		var priceId = "#color" + id + "price";
	}

	var quantity = $(elementId).val();
	var inventory = $(inventoryId).val();
	var priceElement = $(priceId);

	if (0 == operation) {
		if (0 == quantity) {
			warning("亲，不能再少了");
			return;
		}
	}

	if (1 == operation) {
		if (inventory == quantity) {
			warning("亲，库存只有这么多啦");
			return;
		}
	}

	// 开启等待图标
	wait();
	// 发送异步请求
	$.ajax({
		url : "/user/selected/change/quantity",
		type : "post",
		timeout : 10000,
		data : {
			operation : operation,
			type : type,
			id : id
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			close(100);
			warning("亲，您的网速不给力啊");
		},
		success : function(res) {
			//关闭等待图标
			close(100);
			$("#my_selected").html(res);
		}
	});
}