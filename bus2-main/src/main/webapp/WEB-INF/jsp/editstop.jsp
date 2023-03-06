<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ page import="java.util.LinkedHashMap" %>
			<%@ page import="java.util.List" %>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="ISO-8859-1">
					<title>Insert title here</title>
					<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
					</script>
		
					<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js">
					</script>
		
					<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
					</script>
				</head>

				<script>
					document.addEventListener("DOMContentLoaded", () => {
						console.log("loaded");
						var info = document.getElementById('data');


						var request = new XMLHttpRequest()
						var stops;

						request.open('GET', "http://localhost:8080/stop/getall");
						request.onload = function () {
							stops = JSON.parse(request.responseText);
							console.log(window.stops);
							var retVal = '<form onsubmit="return false">'
								+ '<table align="CENTER">' + '<tr>' + '<td>stop:'
								+ '<select id="select-stop" name="stop">'
							for (var stop of stops) {
								console.log(stop);
								retVal += '<option id="stop-selected" value=' + stop.sid + '>'
									+ stop?.name + '</option>';
							}
							retVal +=`	</select>
									</tr>
									<tr>
										<td>
											<input type="text" id="input-name">
										</td>
									</tr>
									<tr>
										<td>
											<button onclick="addStop()" >UPDATE</button>
										</td>
										<td>
										<button onclick="gomanage()" >BACK</button>
									</td>
									</tr>
							</form>`;
							info.innerHTML += retVal; 
						}
						request.send();
						// stops.forEach((stop) => {
						// });
					});

					function addStop() {
						var url = '/stop/update';
						console.log($( "#select-stop option:selected" ).attr("value"));
						var stop = {
							sid: Number($( "#select-stop option:selected" ).attr("value")),
							name: document.getElementById("input-name").value
						};
	

						console.log(stop);
						alert("Click Ok to update");

						$.ajax({
							headers: {
								Accept: 'application/json', //imp
							},
							type: "PUT",
							url: url,
							data: JSON.stringify(stop), // imp
							dataType: "json",
							contentType: "application/json", //imp
							success: function (response) {
								console.log("added", response);
							},
							error: function () {
								alert('Error occured');
							}

						});
						 window.location.href = "/stop/managing";
					}
				   function gomanage(){
					   window.location.href = "/stop/managing";
				   }

				</script>

				<body>



					<div id="data"></div>


				</body>

				</html>