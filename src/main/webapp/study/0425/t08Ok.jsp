<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String img = request.getParameter("img");
	String[] img2 = request.getParameterValues("img2");
	
	System.out.println("img: "+ img);
	System.out.println("img2: "+ img2);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>t08Ok.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 	<script>
		String img2 = "";
		if(img2s != null) {
			for(String i : img2s) {
				img2 += m + "/";
			}
			mountain = mountain.substring(0, mountain.length()-1);
		}
	</script> -->
</head>
<body>
<p><br/></p>
<div class="container">
	<h3>전송된 그림</h3>
	<div>
	  <div class="mb-3">
    	그림파일 : <%=img %>
  	</div>
  	<p><%=img %> <img src="<%=request.getContextPath() %>/images/<%=img %>.jpg" width="200px" />	
	</div>
  <div>
  	<p>
  		<!-- 
  			배열	.length
				문자열	.length()
				ArrayList	.size()
			-->
  		<%
   			for(int i=0; i<img2.length; i++) {
  				out.println("<div><img src='"+request.getContextPath()+"/images/"+img2[i]+".jpg' width='200px' /></div>");
  			}
  		%>
  	</p>
  </div>
  <div>
    <a href="t08_이미지숙제.jsp" class="btn btn-warning">돌아가기</a> 
  </div>
</div>
<p><br/></p>
</body>
</html>