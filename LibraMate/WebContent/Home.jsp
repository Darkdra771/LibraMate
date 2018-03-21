<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>LibraMate</title>
	<style>
        
        body{
            margin: 0;
			background-color: #2D9CDB;
			width: 100%;
        }
        
        #header {
            position:absolute;
            background-color: #66cc9a;
            padding:50px;
            top:0;
            left:0;
            width: 100%;
        }

		
        #footer {
            position:absolute;
            padding:50px;
            bottom:0;
            left:0;
            width:100%; 
            background-color: #66cc9a;
        }
        
        div.container {
            height: 10em;
            position: relative 
        }
        
        div.container {
            margin-top: 200px;
            margin-left: 730px;
            position: absolute;
            margin-right: -50%;
            transform: translate(-50%, -50%);
        }

        #text{
            position: absolute;
			font-family: 'Roboto', sans-serif;
			color: white;
			-webkit-text-stroke: 3.5px black;
			font-weight: bold;
			font-size: 4em;
        }
        
       
        /* start of search styling */
        .topnav a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav .search-container {
            float: right;
            margin-left: 30px;
            padding-top: 100px;
        }

        .topnav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            float: right;
            padding: 7px 10px;
            margin-top: 8px;
            margin-right: 9px;
            margin-left: -4px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }
        
        
        /*login button*/
    	.login{
        	padding-top: 250px;
    		margin-left: 70px;
   		 }    
    
   		 .login-button{
       		width: 150px;
        	height: 50px;
        	background-color: darkslategray;
        	color: white;
        	border: hidden;
        	font-size: 30px;
    	}    
    
	/*end of login button*/
       
        
	</style>
	<script type="text/javascript" >
		window.onload = function(){
			
			var xml = new XMLHttpRequest();
			xml.open("GET", "LoadJson", false);
			xml.send();
				
			
		}
	
	</script>
	</head>
	
	<body>
		

        <div id="header"></div>
        
        <div class="container"> 
            
            <center id="text">LibraMate</center>
        
            <div class="topnav">
                <div class="search-container">
                <form method="POST" action="Input">
                

              		 <font color="red"><strong>${SearchError}</strong></font><br>
                    
                    <input type="text" placeholder="Search..." name="input">
                    <button type="submit"><i class="fa fa-search"></i></button><br>
                    
                            <div id="radio" style="display:inline-block;">
                            <input type="radio" name="choice" value="Title" checked><strong>Title</strong>
                            <br>
                            <input type="radio" name="choice" value="Author" ><strong>Author</strong>
                            </div>
                    
                            <div id="radio" style="display:inline-block;margin-left: 60px;">
                            <input type="radio" name="choice" value="ISBN"><strong>ISBN</strong>
                            <br>
                            <input type="radio" name="choice" value="Genre"><strong>Genre</strong>
                            </div>
                    
                </form>
                
                </div>
            </div>
                <div class="login">
                	<form method="GET" action="Login.jsp">
                		<input class="login-button" type="submit" value="Login" >
                	</form>
                	<br><br>
                	<form method="GET" action="SignUp.jsp">
                		<input class="login-button" type="submit" Value="SignUp">
                	</form>
                </div>
            
        </div>
            

        <div id="footer"></div>

	</body>
</html>