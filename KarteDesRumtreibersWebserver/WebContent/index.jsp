
<!DOCTYPE html>
<html>
<%
	//Objekt holen
	//sortieren
	
	

	int anzahl= 4;
	//Ticket[] test;
	for(int i=0; i<anzahl; i++){
		// test[i]= new Ticket();
	}

	String[] test = new String[anzahl];
	test[1]="jsp added";
	int id =1;
 	
%>
<head>
	
    <meta charset="UTF-8">
    <title>Ticketsystem</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="style.css" type="text/css" rel="stylesheet">
</head>

<body>
	<div id="headNavigation">
		Sortieren nach Status: 
		<form action="">
			<input type="radio" name="status" value="bearbeitet">Erfolgreich Bearbeitet<br>
			<input type="radio" name="status" value="wirdBearbeitet">Wird Bearbeitet<br>
			<input type="radio" name="status" value="unBearbeitet">Unbearbeitet<br>
			<input type="submit" name="submit" value="Sortieren">
		</form>
	</div>	
		<ul id="ulTicketsContainer">
		<% for(int i=0; i<anzahl; i++){  %>
			<li id="<%=id%>" class="liTicket"><!--  ein ticket-->
			<!-- jeweils ein ticket hier rein-->
				<ul class="ulTicket">
					<li class="ticketTitle"> Name:	<%=test[1] %> id: 123
					</li>
					<li class="ticketStatus"> Status:	Ticket unbearbeitet
						<div class="statusUnBearbeitet status">
						</div>
					</li>
					<li class="ticketBody">Text:		In Raum 234 ist ein stuhl kaputt <br>
											gesendet von jeremy ries 
					</li>
					<li class="ticketActions"> 
						<form action="">
							<input type="radio" name="status" value="bearbeitet">Erfolgreich Bearbeitet<br>
							<input type="radio" name="status" value="wirdBearbeitet">Wird Bearbeitet<br>
							<input type="submit" name="submit" value="Best&auml;tigen">
						</form>
					</li>
				</ul>
			</li>
		<%}%>
			<li class="liTicket"><!--  ein ticket-->
			<!-- jeweils ein ticket hier rein-->
				<ul class="ulTicket">
					<li class="ticketTitle"> Name:	Test ticket id: 221
					</li>
					<li class="ticketStatus"> Status:	Ticket bearbeitet
						<div class="statusBearbeitet status">
						</div>
					</li>
					<li class="ticketBody">Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
					Text:		In Raum 234 ist ein stuhl kaputt <br>
											gesendet von jeremy ries 
					</li>
					<li class="ticketActions"> 
						<form action="">
							<input type="radio" name="status" value="bearbeitet">Erfolgreich Bearbeitet<br>
							<input type="radio" name="status" value="wirdBearbeitet">Wird Bearbeitet<br>
							<input type="submit" name="submit" value="Best&auml;tigen">
						</form>
					</li>
				</ul>
			</li>
		</ul>
	<script>
		$( document ).ready(function() {
			//$("#1").hide(); 
			var test =$("#1").find(".status").removeClass("statusUnBearbeitet");
			test.addClass("statusBearbeitet");
		});
	</script>
</body>
</html>