$(function(){
    $(".some_orders").css("display","none");
    $(".order-nav li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        var li_id = $(this).attr("id");
        
        if("all"==li_id){
            $(".some_orders").css("display","none");
            $("#all_orders").css("display","none");
        }else if("unpayed"==li_id){
            $(".some_orders").css("display","none");
            $("unpayed_orders").css("display","none");
        }else if("unsignin"==li_id){
            $(".some_orders").css("display","none");
            $("unsignin_orders").css("display","none");
        }else if("uncommend"==li_id){
            $(".some_orders").css("display","none");
            $("uncommend_orders").css("display","none");
        }
    });
    var init_id = $("#typeId").val();
    if(0==init_id){
    	
    }else if (1 == init_id){
    	
    }else if (2 == init_id){
    	
    }else if (3 ==  init_id){
    	
    }
    $("#all").click();
});
