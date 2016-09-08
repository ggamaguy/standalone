<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<Script src="js/jquery-3.1.0.min.js"></Script>
<Script src="js/bootstrap.min.js"></Script>
<Script src="js/bootstrap-combobox.js"></Script>
<Script src="js/ajax.js"></Script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.combobox').combobox();
	});
</script>


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/eqMaster.css" rel="stylesheet">
<link href="css/bootstrap-combobox.css" rel="stylesheet">

<title>���� ���� �ý���</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">���� ���� �ý���</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="#">Help</a></li>
			</ul>
			<!-- <form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form> -->
		</div>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="tq">�ڵ�����</li>
					<li class=""><a href="#">���� ����</a></li>
					<li class=""><a href="#">���� �ڵ� ����</a></li>
					<li class=""><a href="#">���� ���� Pattern</a></li>
					<li class="tq">����͸�</li>
					<li class=""><a href="#">���� ����͸�</a></li>
					<li class=""><a href="#">������ ���� ��</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h3 class="page-header">��� ������</h3>
				<form>
					<input type="button" value="�ű�" class="btn btn-primary pull-right" />
					<input type="button" value="����" class="btn btn-primary pull-right" />
					<input type="button" value="����" class="btn btn-primary pull-right" />
				</form>
				<table class="eqMasterTable">
					<tr>
						<td></td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						<select class="combobox height30" id="site" onchange="getUpperGroup(this.value);">
								<option value="" disabled selected>����� ����</option>
								<c:if test="${sites.num == 0}">
									<option value="noElement">�׸��� ���� ���� �ʽ��ϴ�</option>
								</c:if>
								<c:if test="${sites.num != 0}">
									<c:forEach items="${sites.content}" var="site">
										<option value="${site.siteCode}"><c:out
												value="${site.siteName}" /></option>
									</c:forEach>
								</c:if>
						</select></td>
						<td><select class="combobox height30" id="upperGroup">
								<option value="" disabled selected>���� �׷� ����</option>
						</select></td>
						<td><select class="combobox height30" id=subGroup">
								<option value="" disabled selected>���� �׷� ����</option>
						</select></td>
					</tr>
					<tr>
						<td><select class="combobox height30" id="eqType">
								<option value="" disabled selected>���� ���� ����</option>
						</select></td>
						<td><select class="combobox height30" id="eqDetail">
								<option value="" disabled selected>���� ���� ����</option>
						</select></td>
						<td><select class="combobox height30" id="usingGroup">
								<option value="" disabled selected>���μ� ����</option>
						</select></td>
					</tr>
					<tr>
						<td>
						<table>
						���⿡ ���� ��� ���
						</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>