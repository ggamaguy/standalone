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
<Script src="js/modalEvents.js"></Script>
<Script src="js/eqMasterAjax.js"></Script>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/eqMaster.css" rel="stylesheet">

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
					<li class=""><a href="baseline">���� ����</a></li>
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
						<td></td>
					</tr>
					<tr>
						<td><input type="hidden" id="site"> <input
							type="hidden" id="group"> <input type="hidden"
							id="subgroup">
							<div class="btn-group" id="siteSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown" id="selectedSite">
									<p id="default-site" class="defalt-msg selected-msg">����� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>

								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>����� ����</a></li>
									<li class="divider"></li>
									<c:if test="${sites.num == 0}">
										<li><a>�׸��� ���� ���� �ʽ��ϴ�</a></li>
									</c:if>
									<c:if test="${sites.num != 0}">
										<c:forEach items="${sites.content}" var="site">
											<li><a onclick="getUpperGroup(this.id);"
												id="${site.siteCode}/${site.siteName}"><c:out
														value="${site.siteName}" /></a></li>
										</c:forEach>
									</c:if>
								</ul>
							</div></td>
						<td><div class="btn-group" id="upperGroupSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown" id="selectedGroup">
									<p id="default-upperGroup" class="defalt-msg selected-msg">����
										�׷� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>���� �׷� ����</a></li>
								</ul>
							</div></td>
						<td><div class="btn-group" id="subGroupSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown" id="selectedSubGroup">
									<p id="default-subGroup" class="defalt-msg selected-msg">����
										�׷� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>���� �׷� ����</a></li>
								</ul>
							</div></td>
					</tr>
					<tr>
						<td><input type="hidden" id="eqType"> <input
							type="hidden" id="eqDetail"> <input type="hidden"
							id="eqUsingGroup">
							<div class="btn-group" id="eqTypeSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown">
									<p id="default-eqType" class="defalt-msg selected-msg">����
										���� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>

								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>���� ���� ����</a></li>
									<li class="divider"></li>
									<c:if test="${eqCategories.num == 0}">
										<li><a>�׸��� ���� ���� �ʽ��ϴ�</a></li>
									</c:if>
									<c:if test="${eqCategories.num != 0}">
										<c:set var="typeList"></c:set>
										<c:forEach items="${eqCategories.content}" var="eqCategory">
											<li><a onclick="getEqDetail(this.id);"
												id="${eqCategory.eqType}"><c:out
														value="${eqCategory.eqType}" /></a></li>
										</c:forEach>
									</c:if>
								</ul>
							</div></td>
						<td><div class="btn-group" id="eqDetailSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown" id="selectedGroup">
									<p id="default-eqDetail" class="defalt-msg selected-msg">����
										���� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>���� ���� ����</a></li>
								</ul>
							</div></td>
						<td><div class="btn-group" id="usingGroupSelection">
								<button type="button" class="comboBtn-width btn btn-default"
									data-toggle="dropdown">
									<p id="default-usingGroup" class="defalt-msg selected-msg">���
										�μ� ����</p>
								</button>
								<button type="button"
									class="btn btn-default dropdown-toggle height34"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li class="disabled"><a>��� �μ� ����</a></li>
								</ul>
							</div></td>
					</tr>
				</table>



				<table id="eqTable"
					class="table table-bordered pre-scrollable tableWidth">
					<thead>
						<tr>
							<th>ȸ�� �ڵ�</th>
							<th>�����</th>
							<th>����׷�</th>
							<th>�����ڵ�</th>
							<th>����</th>
							<th>����</th>
							<th>��ġ</th>
							<th>�����ġ</th>
							<th>����</th>
							<th>������1</th>
							<th>������2</th>
							<th>������3</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="addNewEq" onclick = "addNewEq();" colspan="12">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">��� ������ ����</h4>
				</div>
				<div class="modal-body">
					<table class="modalTable">
						<tr>
							<td>
							<input type="hidden" id="updateModalEqId">
							<form class="form-inline">
									<div class="form-group">
										<label for="companyCode">ȸ�� �ڵ�</label> 
										<select id="updateModalCompanyCode"  class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="siteCode">�����</label> 
										<select id="updateModalSiteCode" class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="upperGroup">���� �׷�</label> 
										<select	id="updateModalUpperGroup" class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="eqCode">���� �ڵ�</label> 
										<select id="updateModalEqCode" class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="eqDetail">���� ����</label>
										<input id="updateModalEqDetail" class="form-control" readonly>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="subGroup">����</label> 
										<select id="updateModalSubGroup" class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr><td><form class="form-inline">
									<div class="form-group">
										<label for="Loc">��ġ</label> 
										<select id="updateModalLoc" class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="funcLoc">��� ��ġ</label> 
										<select id="updateModalFuncLoc" class="form-control"></select>
									</div>
								</form></td>

						</tr>
						<tr>							<td><form class="form-inline">
									<div class="form-group">
										<label for="process">����</label> 
										<select id="updateModalProcess" class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="energy1">������1</label> 
										<select id="updateModalEnergy1" class="form-control"></select>
									</div>
								</form></td>
							
						</tr>
						<tr><td><form class="form-inline">
									<div class="form-group">
										<label for="energy2">������2</label> 
										<select id="updateModalEnergy2" class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="energy3">������3</label> 
										<select id="updateModalEnergy3" class="form-control"></select>
									</div>
								</form></td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">�ݱ�</button>
					<button type="button" class="btn btn-primary">���� ���� ����</button>
				</div>
			</div>
			<!-- ��� ������ -->
		</div>
		<!-- ��� ���̾�α� -->
	</div>
	<!-- ��� ��ü ������ -->
	<div class="modal fade" id="newEqModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">��� ������ ����</h4>
				</div>
				<div class="modal-body">
					<table class="modalTable">
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label class="modalLabel" for="companyCode">ȸ�� �ڵ�</label> <select
											class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="siteCode">�����</label> <select class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="upperGroup">���� �׷�</label> <select
											class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="eqCode">���� �ڵ�</label> <select class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="eqDetail">���� ����</label> <select
											class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="subGroup">����</label> <select class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="funcLoc">��� ��ġ</label> <select
											class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="process">����</label> <select class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="energy1">������1</label> <select class="form-control"></select>
									</div>
								</form></td>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="energy2">������2</label> <select class="form-control"></select>
									</div>
								</form></td>
						</tr>
						<tr>
							<td><form class="form-inline">
									<div class="form-group">
										<label for="energy3">������3</label> <select class="form-control"></select>
									</div>
								</form></td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">�ݱ�</button>
					<button type="button" class="btn btn-primary">���� ���� ����</button>
				</div>
			</div>
			<!-- ��� ������ -->
		</div>
		<!-- ��� ���̾�α� -->
	</div>
	<!-- ��� ��ü ������ -->
</body>
</html>