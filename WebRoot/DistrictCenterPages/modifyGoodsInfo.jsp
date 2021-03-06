<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="bean.Goods"%>
<%
	Goods goods = (Goods) session.getAttribute("getGoodsByID");
	if (goods == null) {
		goods = new Goods();
	}
%>
<%!public String change(String s) {
		if (s != null) {
			return s;
		}
		return "";
	}%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改订单</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/bootstrap.css">
</head>
<script type="text/javascript">
function myFun()
{
alert("修改成功");

}
</script>
<body onload="today()">
	<jsp:include page="../ToolsPage/header.jsp"></jsp:include>
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-2 column">
					<jsp:include page="sidebar.jsp"></jsp:include>
				</div>

				<div class="col-xs-10 column">
					<form class="form-inline" role="form" action="searchGoodsByIDModify">
						<div class="form-group">
							<label class="col-sm-4 control-label">单号：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="searchGoodsId" required="required">
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-default">提交</button>
						</div>
						<s:actionerror />
						<s:actionmessage />
					</form>
					<form class="form-horizontal" role="form" method="post" action="modifyGoodsinfo"
						id="modifyGoodsinfo">
						<hr style="border-top: 1px ridge rgba(6, 3, 9, 0.47)">
						<div class="form-group">
							<label class="col-sm-4 control-label">单号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" required="required"
									value="<%=change(goods.getGoodsId())%>" disabled="disabled">
								<input type="hidden" class="form-control" name="goodsId" required="required"
									value="<%=change(goods.getGoodsId())%>">
							</div>
						</div>
						<h3>收件人信息</h3>
						<hr style="border-top: 1px ridge rgba(6, 3, 9, 0.47)">
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="receiverName" required="required"
									value="<%=change(goods.getReceiverName())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人电话</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="receiverPhone" required="required"
									value="<%=change(goods.getReceiverPhone())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人省份</label>
							<div>
								<label class="checkbox-inline">
									<select class="form-control" id="school" name="receiverProvince" id="receiverProvince">
										<jsp:include page="../ToolsPage/provinceList.jsp"></jsp:include>
									</select>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人城市</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="receiverCity" required="required"
									value="<%=change(goods.getReceiverCity())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人区县</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="receiverDistrict" required="required"
									value="<%=change(goods.getReceiverDistrict())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">收件人地址</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="receiverAddress" required="required"
									value="<%=change(goods.getReceiverAddress())%>">
							</div>
						</div>
						<h3>寄件人信息</h3>
						<hr style="border-top: 1px ridge rgba(6, 3, 9, 0.47)">
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="senderName" required="required"
									value="<%=change(goods.getSenderName())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人电话</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="senderPhone" required="required"
									value="<%=change(goods.getSenderPhone())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人省份</label>
							<div>
								<label class="checkbox-inline">
									<select class="form-control" id="school" name="senderProvince" id="senderProvince">
										<jsp:include page="../ToolsPage/provinceList.jsp"></jsp:include>
									</select>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人城市</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="senderCity" required="required"
									value="<%=change(goods.getSenderCity())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人区县</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="senderDistrict" required="required"
									value="<%=change(goods.getSenderDistrict())%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">寄件人地址</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="senderAddress" required="required"
									value="<%=change(goods.getSenderAddress())%>">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<label class="checkbox-inline">
									<input type="submit" value="提交" class="btn btn-default" onclick="myFun()">
								</label>
								<label class="checkbox-inline">
									<input type="reset" value="重置" class="btn btn-default">
								</label>
								<%
									session.removeAttribute("getGoodsByID");
								%>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="../js/jquery.min.js"></script>
	<script type="text/javascript">
    $(function() {
    $("#senderProvince option[value='<%=goods.getSenderProvince()%>']").attr("selected","selected");
    $("#receiverProvince option[value='<%=goods.getReceiverProvince()%>']").attr("selected", "selected");
	});
    </script>
	<!-- 包含了所有编译插件 -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/main.js"></script>
</body>
</html>