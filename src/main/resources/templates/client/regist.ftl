<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            html,body{width:100%;height: 100%;}
        </style>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="copyright" content="" />
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta charset="utf-8">
        <title>注册</title>
        
        <link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/other.css"/>
        
        <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
        <script src="/client/js/rich_lee.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=bdd6b0736678f88ed49be498bff86754"></script>
        <script type="text/javascript" src="/client/js/map.js"></script>
        <script type="text/javascript" src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>
        <script>
            $(function(){
                showCityInfo();
            });
            //创建一个包含所有城市信息的数组
            var cities = [
                <#if regions??>
                    <#list regions as item>
                        "${item.cityName!''}",
                    </#list>
                </#if>
            ];        
            var app = angular.module("app",[]);
            var ctrl = app.controller("ctrl",function($scope,$http){
                $scope.regions = [
                    <#if regions??>
                        <#list regions as item>
                            {name:"${item.cityName!''}"},
                        </#list>
                    </#if>
                ];
                $scope.infos = {
                    phone:"",
                    code:"",
                    password:"",
                    repassword:"",
                    referPhone:"",
                    diySiteName:"归属门店：默认门店"
                }
                
                $scope.checkRefer = function(){
                    var check = /^1\d{10}$/;
                    var cityInfo = $("#my_city").html();
                    if(check.test($scope.infos.referPhone)){
                        $http.get("/regist/refer/check",{params:{
                            referPhone:$scope.infos.referPhone,
                            cityInfo:cityInfo
                        }}).success(function(res){
                            if(0 == res.status){
                                $scope.infos.diySiteName = "归属门店："+res.message;
                            }
                        });
                    }
                }
                
                $scope.login = function(){
                    window.location.href = "/";
                }
                
                $scope.submitForm = function(){
                    var cityInfo = $("#my_city").html();
                    $http.get("/regist/save",{params:{
                        cityInfo:cityInfo,
                        phone:$scope.infos.phone,
                        code:$scope.infos.code,
                        password:$scope.infos.password,
                        repassword:$scope.infos.repassword,
                        referPhone:$scope.infos.referPhone,
                        diySiteName:$scope.infos.diySiteName
                    }}).success(function(res){
                        alert("成功！");
                    });
                }
            });
        </script>
    </head>
    <script type="text/javascript">
        document.getElementsByTagName('html')[0].style.fontSize = window.screen.width/10+'px';
    </script>
    <body ng-app="app">
        <header class="top_head">注册</header>
        <div id="map" style="display:block"></div>
        <section class="onload_content" ng-controller="ctrl">
            <div class="reg_logo"><img src="/client/images/big_logo.png" /></div>
            <form class="reg_content" name="registForm" ng-submit="submitForm()">              
                <dl>
                    <dt >
                        <span class="my_sele" id="my_city">城市</span>
                        <span class="my_box">
                            <a ng-repeat="region in regions">{{region.name}}</a>
                        </span>
                    </dt>
                    <dd><input type="text" name="phone" ng-model="infos.phone" ng-pattern="/^1\d{10}$/" placeholder="手机号码" ng-required="true"/></dd>
                    <dt>
                        <input type="text" name="code" ng-model="infos.code" ng-minlength="4" ng-maxlength="4" placeholder="手机验证码" ng-required="true"/>
                        <input type="button" ng-disabled="registForm.phone.$invalid" value="发送验证码" />
                    </dt>
                    <dd><input type="password" name="password" ng-model="infos.password" placeholder="设置密码" ng-minlength="6" ng-maxlength="20" ng-required="true"/></dd>
                    <dd><input type="password" name="repassword" ng-model="infos.repassword" placeholder="确认密码" ng-minlength="6" ng-maxlength="20" ng-required="true"/></dd>
                    <dd><input type="text" name="referPhone" ng-model="infos.referPhone" ng-change="checkRefer();" placeholder="推荐人电话" /></dd>
                    <dd><input type="text" name="diySiteName" ng-model="infos.diySiteName" placeholder="服务门店" disabled="true" /></dd>
                    <dd><input type="submit" ng-disabled="registForm.$invalid" value="注册" /></dd>
                    <dd><input type="button" ng-click="login();" value="登录" /></dd>
                </dl>                       
            </form>
        </section>
    </body>
</html>
        
