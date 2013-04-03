(function(){
	var is_breath_in = true;
	var air = Math.random()/10;
	setInterval(
			function(){var op = parseFloat($("#breath_o").css("opacity"));
			if(air > 0.08){ air-=0.02};
			if(air < 0.03){ air+=0.03};				
			op = is_breath_in ?　op + air　: op - air;	
			if(op > 1) {
				is_breath_in = false;
				air = Math.random()/10;
			}else if(op < 0){
				is_breath_in = true;
				air = Math.random()/10;					
			}
			$("#breath_o").css("opacity",op);},
			100);
	
	Window.prototype.alert = function(info, needReload){
			$(".alert").remove();
			var alert_div = $("<div class=\"alert hide\"><a class=\"close\" data-dismiss=\"alert\">×</a><span>"+info+"</span></div>");
			$("#content").prepend(alert_div);		
			$(".alert").show(200);
			if(needReload){
				setTimeout("$(\".alert\").hide(200); window.location.reload();",2000);			
			}else{
				setTimeout("$(\".alert\").hide(200);",2000);
			}
	};
})();