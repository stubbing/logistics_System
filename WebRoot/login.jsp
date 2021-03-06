<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>登录</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/main.js"></script>
</head>
<body onload="today()">
	<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" name="date"></a>
		</div>
		<div class="collapse navbar-collapse menubar-left">
			<ul class="nav navbar-nav">
				<li>
					<a href="index.jsp">
						<span class="glyphicon glyphicon-home">首页</span>
					</a>
				</li>
				<li>
					<a href="index.jsp">
						<span class="glyphicon glyphicon-envelope">寄快递</span>
					</a>
				</li>
				<li>
					<a href="news.jsp">
						<span class="glyphicon glyphicon glyphicon-lock">网点查询</span>
					</a>
				</li>
				<li>
					<a href="info.jsp">
						<span class="glyphicon glyphicon-book">运费查询</span>
					</a>
				</li>
				<li>
					<a href="info.jsp">
						<span class="glyphicon glyphicon-th">关于我们</span>
					</a>
				</li>
				<li>
					<a href="warning.jsp">
						<span class="glyphicon glyphicon-star">注意事项</span>
					</a>
				</li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<div class="maincontent">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="panel panel-default panel-default1">
						<div class="panel-heading">
							<h3 style="text-align: center">欢迎登录</h3>
						</div>
						<hr>
						<s:actionerror />
						<s:actionmessage />
						<div class="panel-body">
							<form method="POST" class="form-horizontal" role="form" action="login">
								<div class="form-group">

									<div class="col-md-1"></div>
									<div class="col-md-10">
										<input type="mobile" class="form-control form-control1 form-control2" placeholder="账号："
											id="userid" name="userid" value="" required="required">
										<div class="help-block with-errors"></div>

									</div>
									<div class="col-md-1"></div>
								</div>

								<div class="form-group">
									<!--label class="col-md-4 control-label">密码</label-->
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<input type="password" class="form-control form-control1 form-control4" placeholder="密码："
											id="password" name="password" required="required">
										<div class="help-block with-errors"></div>
									</div>
									<div class="col-md-1"></div>
								</div>

								<div class="form-group text-center">
									<div>
										<label>
											<input type="radio" name="type" value="1" checked>
											区县营业点
										</label>
										<label>
											<input type="radio" name="type" value="2">
											省分拣中心
										</label>
										<label>
											<input type="radio" name="type" value="3">
											管理员
										</label>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-1 col-sm-10">
										<label class="checkbox-inline">
											<input type="submit" value="登录" class="btn btn-primary btn-block btn-btn1">
										</label>
										<label class="checkbox-inline">
											<input type="reset" value="重置" class="btn btn-primary btn-block btn-btn1">
										</label>
										<label class="checkbox-inline">
											<a href="index.jsp" style="color: black;">
												<input type="button" value="返回" class="btn btn-primary btn-block btn-btn1">
											</a>
										</label>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- jQuery (Bootstrap 插件需要引入) -->
	<script src="js/jquery.min.js"></script>
	<!-- 包含了所有编译插件 -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>
