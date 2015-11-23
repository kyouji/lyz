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
        <title>登录</title>
        
        <link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/other.css"/>
        
        <script type="text/javascript" src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>
        <script>
            var app = angular.module("app",[]);
            var ctrl = app.controller("ctrl",function($scope,$http){
                $scope.user = {
                    username : "",
                    password : ""
                };
                $scope.submitForm = function(){
                    $http.get("/login/check",{params:{
                        username:$scope.user.username,
                        password:$scope.user.password
                    }}).success(function(res){
                        if(0 == res.status){
                            <#-- 进行页面跳转 window.location.href = ""; -->
                            window.location.href = "/";
                        }else{
                           alert(res.message);
                        }
                    });
                }
                $scope.regist = function(){
                    window.location.href = "/regist"
                }
            });
        </script>
    </head>
    <script type="text/javascript">
        document.getElementsByTagName('html')[0].style.fontSize = window.screen.width/10+'px';
    </script>
    <body ng-app="app">
        <header class="top_head">
            <div class="head_back" style="background:none;"></div>
            <div class="head_title">登陆</div>
        </header>
        <section class="onload_content" ng-controller="ctrl">
            <div class="reg_logo"><img src="/client/images/big_logo.png" /></div>
            <form class="on_content" name="loginForm" ng-submit="submitForm()">               
                <dl>
                    <dt><input type="text" name="username" ng-model="user.username" ng-pattern="/^1\d{10}$/" placeholder="手机号码" ng-required="true" /></dt>
                    <dt><input type="password" name="password" ng-model="user.password" ng-minlength="6" placeholder="用户密码" ng-maxlength="20"  ng-required="true"/></dt>
                    <dd><input type="submit" ng-disabled="loginForm.$invalid" value="登陆" /></dd>
                    <dd><a>忘记密码</a><span></span></dd>
                    <dd><a href="/regist">注册</a></dd>
                </dl>                       
            </form>
        </section>
    </body>
</html>