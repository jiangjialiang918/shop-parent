<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <img alt="验证码" src="<%=basePath%>imageCode/code"/>
 --%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理中心</title>
	  <%  
	    // http://
	    String urlPrefix = request.getScheme() + "://";
	    //获取项目名  
	    String path = request.getContextPath();  
	    //http://localhost:8080/项目名/  
	    String basePath = request.getScheme()
	     + "://" + request.getServerName() 
	     + ":" + request.getServerPort() + path + "/";  
	 %>
	<link rel="stylesheet" href="<%=basePath%>css/login/style.css">
	<link href="<%=basePath%>css/login/popup-box.css" rel="stylesheet" type="text/css" media="all" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Sign In And Sign Up Forms  Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	</script><script src="<%=basePath%>js/login/jquery.min.js"></script>
	<script src="<%=basePath%>js/login/jquery.magnific-popup.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>js/login/modernizr.custom.53451.js"></script> 
    <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
</script>	
		
</head>
<body>
	<h1>后台中心登录</h1>
	<div class="w3layouts">
		<div class="signup-agileinfo">
			<h2></h2>
			<form action="<%=basePath%>index/index" method="post">
				<input type="text" name="name" class="name" placeholder="用户名" >
				<input type="password" name="password" class="password" placeholder="密码" >
				<ul>
					<li>
						<input type="checkbox" id="brand1" value="">
						<label for="brand1"><span></span>记住密码</label>
					</li>
				</ul>
				<a href="#">忘记密码?</a><br>
				<div class="clear"></div>
				<input type="submit" value="登录">
			</form>
		</div>
	
<body>
</html>