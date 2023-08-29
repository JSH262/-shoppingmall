<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	UsersVO userInfo = AttributeName.getUserData(request);

	if(userInfo != null)
	{
		String userType = userInfo.getType();
		if(userType == UsersType.SELLER)
		{	
%>
		    <%-- 판매자의 채팅용 버튼 --%>
			<div style="position: fixed; bottom:30px; right: 30px;">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-info" id="btnStartChatting">
					<i class="bi bi-chat-right-text-fill"></i>
				</button>
			</div>
			
			<script type="text/javascript">
				$(() => {
					$("#btnStartChatting").bind('click', () => {
						
						let url = getContextPath() + '/chatting';
						window.open(url, '채팅', "width=995,height=850,resizable=no");
					});			
				});
			</script>
<%
		}
	}
%>