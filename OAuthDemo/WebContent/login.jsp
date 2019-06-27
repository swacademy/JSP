<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="js/jquery-1.12.4.js"></script>
		<script>
			$(function(){
				$('#naver').click(function(){
					var clientId = "cPcjssrfqOgC3aNWJygc";//애플리케이션 클라이언트 아이디값";
					var redirectURI = "<%=encodedURL()%>";
					var apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
					apiURL += "&client_id=" + clientId;
					apiURL += "&redirect_uri=" + redirectURI;
					apiURL += "&state=<%=getState()%>";
					location.href = apiURL;
				});
			});
		</script>
	</head>
<body>
	<input type="image" src="http://static.nid.naver.com/oauth/small_g_in.PNG" id="naver" />
</body>
</html>
<%!
	private String getState() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString();
	}

	private String encodedURL() throws Exception {
		return URLEncoder.encode("http://localhost:8080/OAuthDemo/navercallback.jsp", "UTF-8");
	}
%>
