<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page import="java.net.URLEncoder, java.net.URLDecoder" %>
<%@ page import="java.net.URL, java.util.Iterator" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="org.json.simple.*, org.json.simple.parser.*" %>
<%
	String clientId = "cPcjssrfqOgC3aNWJygc";//애플리케이션 클라이언트 아이디값";
	String clientSecret = "i6ud20ZNH8";//애플리케이션 클라이언트 시크릿값";
	String code = request.getParameter("code");
	String state = request.getParameter("state");
	String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	apiURL += "client_id=" + clientId;
	apiURL += "&client_secret=" + clientSecret;
	apiURL += "&code=" + code;
	apiURL += "&state=" + state;
	String accessTokenString = getAccessToken(apiURL);
	String result = getResult(accessTokenString);
	JSONParser jsonParser = new JSONParser();
	JSONObject obj = (JSONObject)jsonParser.parse(result);
	obj = (JSONObject)obj.get("response");
	out.println("email = " + obj.get("email") + "<br />");
	out.println("name = " + URLDecoder.decode((String)obj.get("name"), "utf-8"));
%>
<%!
	private String getAccessToken(String apiURL) throws Exception{
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		BufferedReader br = null;
		if(responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} 
		String result = null;
		StringBuffer res = new StringBuffer();
		while ((result = br.readLine()) != null) {
			res.append(result);
		}
		br.close();
		return res.toString();
	}
%>
<%!
	private String getResult(String result) throws Exception{
		JSONParser jsonParser = new JSONParser();
		JSONObject obj = (JSONObject)jsonParser.parse(result);
		String token = (String)obj.get("access_token"); // 네이버 로그인 접근 토큰;
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", header);
		int responseCode = con.getResponseCode();
		BufferedReader br = null;
		if(responseCode==200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} 
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		return response.toString();
	} 
%>