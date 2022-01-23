<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cybersoft.javabackend.game.doanso.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDateTime" %>
<!doctype html>
<html>
  <head>
    <title>Ranking Board</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  </head>
  <body>
  <div>
      <h1 style="width:80%;float:left;display:inline-block">Ranking Board</h1>
      <form class="mt-2" style="width:20%;float:left" action="<%=request.getContextPath() + UrlConst.PLAYER_LOGIN %>" method="get">
	  <button type="submit" class="btn btn-primary">Login</button>
	  </form>
  </div>  
      <div class="container h-100 d-flex justify-content-center align-content-center">
        <table class="table table-striped">
          <thead>
            <th>Rank</th>
            <th>Username</th>
            <th>Point</th>
            <th>Timestamp</th>
            <th>Record ID</th>
          </thead>
          <tbody>
            <c:forEach var="ranks" items="${ranks}" varStatus="loop">
            	<tr>
            		<td>${loop.index + 1}</td>
		            <td>${ranks.player.username}</td>
		            <td>${ranks.point}</td>
		            <td>${ranks.finishDate}</td>
		            <td>${ranks.id}</td>
            	</tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>