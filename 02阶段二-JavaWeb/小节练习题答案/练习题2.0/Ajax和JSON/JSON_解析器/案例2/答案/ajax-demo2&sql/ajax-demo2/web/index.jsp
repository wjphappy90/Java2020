<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js" ></script>
<script type="text/javascript">
function findUser(){
	//获得文本框中的名称
	var name = $("#name").val();
	//发送ajax请求并将参数提交
	$.post("${pageContext.request.contextPath}/FindUserServlet", { name: name },
			   function(data){
					//判断如果查询不到数据就提示
					if(data==null || data.length==0){
						alert("没有查询到用户数据!");
						return;
					}
					//晴空表格,删除除表头行以外的行
					$("#list tbody tr").eq(0).nextAll().remove();
					//遍历返回的user列表数据
					$.each(data, function(i, json){
						//每次遍历都新建一行
						var tr = $("<tr></tr>");
						//创建对应的td对象
						var idTd = $("<td>"+json['id']+"</td>");
						var nameTd = $("<td>"+json['name']+"</td>");
						var ageTd = $("<td>"+json['age']+"</td>");
						var eduTd = $("<td>"+json['edu']+"</td>");
						var genderTd = $("<td>"+json['gender']+"</td>");
						//将td加入tr
						tr.append(idTd);
						tr.append(nameTd);
						tr.append(ageTd);
						tr.append(eduTd);
						tr.append(genderTd);
						//将tr放入表格
						$("#list").append(tr);
						});

			   }, "json");
}
</script>

</head>
<body>
用户名:<input id="name" /><input type="button" value="查询" onclick="findUser()" /><hr>

<table id="list" border=1 >
	<tr>
		<td>id</td>
		<td>用户名</td>
		<td>年龄</td>
		<td>学历</td>
		<td>性别</td>
	</tr>
</table>
</body>
</html>