<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js" ></script>
<script type="text/javascript">
//处理ajax登陆逻辑
	function login(){
		//1获得用户名
		var name = $("#name").val();
		//2发送ajax请求,访问LoginServlet
		$.post("${pageContext.request.contextPath}/LoginServlet", { name: name },
			   function(json){
			//3接受后台处理结果
			if(json.type==1){
			//成功=>将页面中的登陆表单替换为欢迎信息
				$("#info").empty();
				$("#info").append($("<span>"+json.msg+"</span>"));
			}else{
			//失败=>提示
				alert(json.msg);
			}
			
			   }, "json");
	}
</script>
</head>
<body>
<h2>XXX购物商城</h2>
<div id="info" align="right" >
	用户名:<input type="text" id="name"  />&nbsp;&nbsp;&nbsp;
	密码:<input type="password" id="password" />&nbsp;&nbsp;&nbsp;
	<input type="button" onclick="login();" value="登陆">
</div>
<hr>
</body>
</html>