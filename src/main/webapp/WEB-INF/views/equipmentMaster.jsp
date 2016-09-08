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

<title>공정 개선 시스템</title>
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
			<a class="navbar-brand" href="#">공정 개선 시스템</a>
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
					<li class="tq">자동제어</li>
					<li class=""><a href="#">기준 정보</a></li>
					<li class=""><a href="#">전력 자동 제어</a></li>
					<li class=""><a href="#">최적 운전 Pattern</a></li>
					<li class="tq">모니터링</li>
					<li class=""><a href="#">전력 모니터링</a></li>
					<li class=""><a href="#">에너지 성과 평가</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h3 class="page-header">장비 마스터</h3>
				<form>
					<input type="button" value="신규" class="btn btn-primary pull-right" />
					<input type="button" value="저장" class="btn btn-primary pull-right" />
					<input type="button" value="삭제" class="btn btn-primary pull-right" />
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
								<option value="" disabled selected>사업장 선택</option>
								<c:if test="${sites.num == 0}">
									<option value="noElement">항목이 존재 하지 않습니다</option>
								</c:if>
								<c:if test="${sites.num != 0}">
									<c:forEach items="${sites.content}" var="site">
										<option value="${site.siteCode}"><c:out
												value="${site.siteName}" /></option>
									</c:forEach>
								</c:if>
						</select></td>
						<td><select class="combobox height30" id="upperGroup">
								<option value="" disabled selected>상위 그룹 선택</option>
						</select></td>
						<td><select class="combobox height30" id=subGroup">
								<option value="" disabled selected>하위 그룹 선택</option>
						</select></td>
					</tr>
					<tr>
						<td><select class="combobox height30" id="eqType">
								<option value="" disabled selected>설비 유형 선택</option>
						</select></td>
						<td><select class="combobox height30" id="eqDetail">
								<option value="" disabled selected>설비 내역 선택</option>
						</select></td>
						<td><select class="combobox height30" id="usingGroup">
								<option value="" disabled selected>사용부서 선택</option>
						</select></td>
					</tr>
					<tr>
						<td>
						<table>
						여기에 쿼리 결과 띄움
						</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>