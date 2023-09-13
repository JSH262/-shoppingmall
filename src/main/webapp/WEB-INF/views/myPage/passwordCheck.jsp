<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passwordCheck</title>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/passwordCheck.js"></script> <!-- ajax 구현 -->
<style>
	html,
	body {
	  height: 100%;
	}
	
	.form-signin {
	  max-width: 330px;
	  padding: 1rem;
	}
	
	.form-signin .form-floating:focus-within {
	  z-index: 2;
	}
	
	.form-signin input[type="email"] {
	  margin-bottom: -1px;
	  border-bottom-right-radius: 0;
	  border-bottom-left-radius: 0;
	}
	
	.form-signin input[type="password"] {
	  margin-bottom: 10px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}

      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }
      .bd-mode-toggle {
        z-index: 1500;
      }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-light" style="background-color: #f8f9fa;">

<%
	String action = request.getParameter("action");
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
%>

<main class="form-signin w-100 m-auto">
  <form>
    <!--	WARN  2023-09-13 15:03:32,595: org.springframework.web.servlet.PageNotFound(1116) - No mapping found for HTTP request with URI [/shoppingmall/myPage/images/logo.png] in DispatcherServlet with name 'appServlet'
     <img class="mb-4" src="./images/logo.png" alt="" width="72" height="57"> -->
    <h1 class="h3 mb-3 fw-normal">비밀번호 확인</h1>

    <div class="form-floating">
      <input type="password" class="form-control" id="password" placeholder="Password">
      <label for="floatingPassword">비밀번호를 입력하세요</label>
      <input type="hidden" id="userId" value="<%=userId %>"/>
      <input type="hidden" id="action" value="<%=action %>"/>
    </div>

    <button class="btn btn-primary w-100 py-2" type="button" value="확인" onclick="passwordCheck()" >비밀번호 확인</button>
    <p class="mt-5 mb-3 text-body-secondary">&copy; 2023–2023</p>
  </form>
</main>
<%-- 
<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>비밀번호 확인</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle;">비밀번호</th>
					<td>
						<input id="password" class="form-control" type="password" 
							name="password" placeholder="비밀번호를 입력하세요" 
							autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<!-- 오류 메시지가 출력될 영역 -->
						<h5 id="errorMessage" style="color: lime; font-weight: bold;">
							${messageType}: ${messageContent}
						</h5>
						<input class="hidden" id="userId" value="<%=userId %>"/>
						<input class="btn btn-primary" type="button" value="확인" onclick="passwordCheck()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
 --%>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>

<%-- 
<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div id="messageCheck">
                <div class="modal-header panel-heading">
                    <button class="close" type="button" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 id="messageType" class="modal-title">
                        ${messageType}
                    </h4>
                </div>
                <div id="messageContent" class="modal-body">
                    ${messageContent}
                </div>
                <div class="modal-footer">
                    	<button id="success" class="btn btn-primary" type="button" data-dismiss="modal" 
                        	    onclick="location.href='<%= request.getContextPath() %>/myPage/<%=action %>'">확인</button>
                        <button id="warning" class="btn btn-primary" type="button" data-dismiss="modal">돌아가기</button>
                </div>
            </div>
        </div>
    </div>
</div>
 --%>
 
</body>
</html>

