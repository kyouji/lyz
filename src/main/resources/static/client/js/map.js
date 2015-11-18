//地图初始化
var map = new AMap.Map("mapContainer", {
	resizeEnable : true,
	// 二维地图显示视口
	// 地图中心点
	center : [ 116.397428, 39.90923 ],
	// 地图显示的缩放级别
	zoom : 13
});
// 获取用户所在城市信息
function showCityInfo() {
	// 加载城市查询插件
	AMap.service([ "AMap.CitySearch" ], function() {
		// 实例化城市查询类
		var citysearch = new AMap.CitySearch();
		// 自动获取用户IP，返回当前城市
		citysearch.getLocalCity(function(status, result) {
			if (status === 'complete' && result.info === 'OK') {
				if (result && result.city && result.bounds) {
					var cityinfo = result.city;
					alert("您当前所在城市：" + cityinfo + "");
					if (!cities.toString().indexOf(cityinfo) > -1) {
						$('.my_box').append('<a>'+cityinfo+'</a>');
					}
					$("#my_city").html(cityinfo);
				}
			}
		});
	});
}