window.onload = function(){
	my_select();
};



function my_select(){
	$('.reg_content dt select').change(function(){
		var optionvalue=$(this).find('option:selected').text();
		$('#my_box').text(optionvalue);
	});
}		
		
		
		
