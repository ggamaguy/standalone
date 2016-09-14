<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<Script src="js/baseline.js"></Script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/tab.css" rel="stylesheet">
<link href="css/content.css" rel="stylesheet">
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
			
				<h3 class="page-header">기준 정보</h3>
				<div class="tab" role="tabpanel">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#Section1"
							aria-controls="home" role="tab" data-toggle="tab">기준 정보</a></li>
						<li role="presentation"><a href="#Section2"
							aria-controls="profile" role="tab" data-toggle="tab">설비 정보</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="Section1">
							<table class="whole_table">
								<tr>
									<td class="table_align_top table_width150">
										<table class="table table-hover">
											<thead>
												<th>No</th>
												<th>메뉴</th>
											</thead>
											<tr id="1-row" onclick="">
												<td>1</td>
												<td><a href="baseline">사업장 정보</a></td>
											</tr>
											<tr id="2-row" onclick="">
												<td>2</td>
												<td>조직구분정보</td>
											</tr>
											<tr id="3-row" onclick="">
												<td>3</td>
												<td><a href="uppergroup">상위조직정보</a></td>
											</tr>
											<tr id="4-row" onclick="" class="active">
												<td>4</td>
												<td><a href="subgroup">하위조직정보</a></td>
											</tr>
											<tr id="5-row" onclick="">
												<td>5</td>
												<td>에너지원정보</td>
											</tr>
											<tr id="6-row" onclick="">
												<td>6</td>
												<td>에너지이용정보</td>
											</tr>
											<tr id="7-row" onclick="">
												<td>7</td>
												<td>공정정보</td>
											</tr>
											<tr id="8-row" onclick="">
												<td>8</td>
												<td>Sub공정정보</td>
											</tr>
											<tr id="9-row" onclick="">
												<td>9</td>
												<td>설비범주</td>
											</tr>
											<tr id="10-row" onclick="">
												<td>10</td>
												<td>설비그룹</td>
											</tr>
											<tr id="11-row" onclick="">
												<td>11</td>
												<td>위치구분정보</td>
											</tr>
											<tr id="12-row" onclick="">
												<td>12</td>
												<td>상위위치정보</td>
											</tr>
											<tr id="13-row" onclick="">
												<td>13</td>
												<td>하위위치정보</td>
											</tr>
										</table>
									</td>
									<td class="content">
										<table class="table_content">
											<tr id="table_menu">
												<td>
													<form id="dbIO">
														<input type="button" value="신규"
															class="btn btn-primary pull-right" onclick="newSubGroup()" />
														<input type="button" value="저장"
															class="btn btn-primary pull-right"
															onclick="saveSubGroup()" /> <input type="button"
															value="삭제" class="btn btn-primary pull-right"
															onclcik="deleteSubGroup()" />
													</form>
												<td>
											</tr>
											<tr>
												<td id="inner-content" class="table_align_top">
													<div>
														<form role="form" class="form-inline" id="inputform">
															<div class="btn-group">
																<button type="button" id="upperGroupNameInput"
																	class="btn btn-default dropdown-toggle"
																	data-toggle="dropdown">
																	<p>본부 코드</p><span class="caret"></span>
																</button>
																<ul class="dropdown-menu" role="menu">
														<c:forEach items="${subgroup.content}" var="subGroup">
															<li>${subGroup.upperGroupName}</li>
														</c:forEach>
																</ul>
															</div>
															<div class="form-group">
																<label for="subGroup" class="sr-only">조직 코드</label> <input
																	id="subGroupCodeInput" type="text" class="form-control"
																	placeholder="조직 코드" readonly>
															</div>
															<input type="hidden" id="subGroupCodeHiden">
															<input type="hidden" id="upperGroupCodeHiden">
															<div class="form-group">
																<label for="subName" class="sr-only">조직 명</label> <input
																	id="subGroupNameInput" type="text" class="form-control"
																	placeholder="조직 명">
															</div>
														</form>
													</div>
													<table class="table">
														<thead>
															<tr>
																<th>No</th>
																<th>본부</th>
																<th>조직 코드</th>
																<th>조직 명</th>
															</tr>
														</thead>
														<tbody>
															<%
																int i = 1;
															%>
															<c:forEach items="${subgroup.content}" var="subGroup">
																<tr id="row<%=i%>"
																	onclick="subGroupDataTableClick(this.id);">
																	<td><%=i++%></td>
																	<td><label class="upperGroupName"
																		value="${subGroup.upperGroupName}">${subGroup.upperGroupName}</label></td>
																	<td><label class="groupCode"
																		value="${subGroup.upperGroupCode}/${subGroup.subGroupCode}">${subGroup.upperGroupCode}${subGroup.subGroupCode}</label></td>
																	<td><label class="groupName"
																		value="${subGroup.subGroupName}">${subGroup.subGroupName}</label></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="Section2">
							<h3>Section 2</h3>
							<p>2 - Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Sed pellentesque iaculis sagittis. Cras et convallis
								risus, nec maximus justo. Mauris dignissim tellus eu lorem
								efficitur faucibus. Etiam diam risus, molestie eu porta sit
								amet, bibendum nec nisi. Ut iaculis pulvinar dapibus. Cras diam
								turpis, tincidunt ac efficitur id, dignissim at.</p>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<Script src="js/jquery-3.1.0.min.js"></Script>
	<Script src="js/bootstrap.min.js"></Script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>