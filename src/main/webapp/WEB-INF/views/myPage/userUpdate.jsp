<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userUpdate</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/myPage/myPage.js"></script> <!-- ajax 구현 -->
<style>
	#form {
          display: inline-block;
          text-align: center;
        }

	
</style>
</head>
<body class="d-flex align-items-center py-4 bg-light" style="background-color: #f8f9fa;">
<%
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
	String name = user.getName();
	String email = user.getEmail();
	String phone = user.getPhone();
%>

<div class="container">
  <main>
    <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="./images/logo.png" alt="" width="72" height="57">
      <h2>회원정보 수정</h2>
    </div>

      <div class="col-md-12 text-center">
        <h4 class="mb-3">회원정보 입력</h4>
        <form class="text-center">
          <div class="row g-3 align-items-center">
			<div class="col-4"></div>
            <div class="col-6" style="padding-top: 12px; max-width: 250px;">
              <label for="address" class="form-label">이름</label>
              <input id="name" class="form-control" type="text" name="name"
							value="<%=name %>"/>
            </div>
            <div class="col-4"></div>
          </div>
          <div class="row g-3">
            <div class="col-4"></div>
            <div class="col-6" style="padding-top: 12px; max-width: 250px;">
              <label for="address" class="form-label">이메일</label>
              <input id="email" class="form-control" type="email" name="email" 
							value="<%=email %>"/>
            </div>
            <div class="col-4"></div>
          </div>
          <div class="row g-3">
            <div class="col-4"></div>
            <div class="col-6" style="padding-top: 12px; max-width: 250px;">
              <label for="address" class="form-label">전화번호</label>
              <input id="phone" class="form-control" type="text" name="phone" 
							value="<%=phone %>"/>
            </div>
            <div class="col-4"></div>
          </div>

				<input type="hidden" id="userId" value="<%=userId %>">
				<input class="btn btn-primary" type="button" value="적용하기" 	onclick="userUpdate()"/>
        </form>
      </div>
  </main>
</div>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>

<%-- <div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>회워정보 수정</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle;">이름</th>
					<td colspan="2">
						<input id="name" class="form-control" type="text" name="name" 
							value="<%=name %>"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">이메일</th>
					<td colspan="2">
						<input id="email" class="form-control" type="email" name="email" 
							value="<%=email %>"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">전화번호</th>
					<td colspan="2">
						<input id="phone" class="form-control" type="text" name="phone" 
							value="<%=phone %>"/>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<input class="hidden" id="userId" value="<%=userId %>">
						<input class="btn btn-primary" type="button" value="적용하기" 
							onclick="userUpdate()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
 --%>
<%-- 
<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center">
			<!-- 모달 창의 종류(색상)를 설정한다. -->
			<!-- messageCheck라는 id를 추가하고 class를 제거한다. -->
			<div id="messageCheck">
				<div class="modal-header panel-heading">
					<button class="close" type="button" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<!-- messageType이라는 id를 추가한다. -->
					<h4 id="messageType" class="modal-title">
						${messageType}
					</h4>
				</div>
				<!-- messageContent라는 id를 추가한다. -->
				<div id="messageContent" class="modal-body">
					${messageContent}
				</div>
				<div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" 
				        onclick="location.href='<%=request.getContextPath() %>/myPage'">확인</button>
                </div>
			</div>
		</div>
	</div>
</div>
 --%>


</body>
</html>