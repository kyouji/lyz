window.onload = function(){
	my_select();
		
};



function my_select(){
	
	//alert()
		
//		$('.my_sele').html($('.my_box a').eq(0).html());
		$('.my_box a').eq(0).addClass('bd');
		
		$('.my_sele').click(function(){
			if($('.my_box').css('display') == 'none'){
				$('.my_box').slideDown(200);
			}else if($('.my_box').css('display') == 'block'){
				$('.my_box').slideUp(200);
			};
			
	})
		
		$('.my_box a').each(function(i){
			
			$(this).click(function(){
				$('.my_sele').html($(this).html())	
				$('.my_box').hide();
				$('.my_box a').removeClass('bd');
				$(this).addClass('bd');
			})
			
			
			
			
			
			
		})
}		
		
		
		
