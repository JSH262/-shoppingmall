<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	UsersVO vo = AttributeName.getUserData(request);
	String userId = vo.getId();
%>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/passwordCheck.js"></script>


	<style>
		.feature-icon {
			width:2em;
			height:2em;
			border-radius: 0.5em;
		}
	</style>


</head>
<body>
	<%@ include file="/WEB-INF/component/header.jsp" %>
	

<div class="container px-4 py-5" id="featured-3">
    <h2 class="pb-2 border-bottom">My Page</h2>
    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
      <div class="feature col">
        <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
		    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
	 	    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
		  </svg>
        </div>
        <h3 class="fs-2">회원정보 수정</h3>
       	<ul class="list-unstyled mt-3 mb-4">
              <li>이름 변경</li>
              <li>이메일 변경</li>
              <li>전화번호 변경</li>
            </ul>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=userUpdate'">회원정보 수정</button>
      </div>
      <div class="feature col">
        <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
		    <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
		    <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
		  </svg>
        </div>
        <h3 class="fs-2">비밀번호 변경</h3>
        <p>비밀번호 변경</p>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=passwordUpdate'">비밀번호 변경</button>
      </div>
      <div class="feature col">
        <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
		    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7ZM11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1Zm0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"/>
		    <path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
		  </svg>
        </div>
        <h3 class="fs-2">회원 탈퇴</h3>
        <p>회원 탈퇴</p>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=unregister'">회원 탈퇴</button>
      </div>
    </div>
    <div>
      <div class="feature col" style="max-width: 285px">
        <div class="feature-icon d-inline-flex align-items-center justify-content-center text-bg-primary bg-gradient fs-2 mb-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-layout-text-sidebar-reverse" viewBox="0 0 16 16">
		    <path d="M12.5 3a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1h5zm0 3a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1h5zm.5 3.5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h5a.5.5 0 0 0 .5-.5zm-.5 2.5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1h5z"/>
		    <path d="M16 2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2zM4 1v14H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h2zm1 0h9a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H5V1z"/>
		  </svg>
        </div>
        <h3 class="fs-2">내가 작성한 리뷰</h3>
        <p>내가 적성한 리뷰 목록</p>
        <button type="button" class="btn btn-primary"  onclick="location.href='<%= request.getContextPath() %>/reviewList.do?userId=<%=userId %>'">내가 작성한 리뷰</button>
      </div>
      <div class="feature col"> </div>
      <div class="feature col"> </div>
    </div>
  </div>

	<%@ include file="/WEB-INF/component/footer.jsp" %>	


</body>
</html>
