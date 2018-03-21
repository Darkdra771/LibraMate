<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<style>
@charset "ISO-8859-1";

body {
		margin: 0;
		background-color: #2D9CDB;
		width: 100%;
}

	/* LIBRA */

   	   #element1{
            display:inline-block;
            margin-left: 10px;
            font-size: 70px;
    		text-decoration: none;
    		color: white;
            text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;  
        }
     /*end*/
    
/*form*/    
    text{
        margin-left: -155px;
        font-size: 25px;
        font-weight: bold;

    }
    
    .form-bar{
        width: 250px;
        height: 25px;
    }
/*end of form*/
    
    
h1 {
	position: relative;
	font-family: 'Roboto', sans-serif;
	color: white;
	-webkit-text-stroke: 3.5px black;
	font-weight: bold;
	font-size: 4em;
}

#top {
	width: 100%;
	height: 100px;
	background-color: #66cc9a;
	margin: 0;
	
}

#search-container {
	height: 200px;
	text-align:center;
	background-color: #2b9ddb;
}


#title {
	font-size: 8.5em;
	margin-top: 200px;
	margin-bottom: 25px;
}

    
/*login button*/
    .login{
        position: relative;
        padding-top: 80px;
    
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
    
label {
	font-weight: bold;
	font-size: 1.5em;
	font-family: 'Roboto', sans-serif;
}
    
#bottom {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100px;
	background-color: #6FCF97;
	margin: 0;
	padding: 0;
	display:inline-block;
}
.clearfloat {
	clear: both;
}    
    
/*error field*/

	#nameError, #passwordError, #UrlError {
		font-weigth: bold;
		font-size: 20px;
		color: red;
		position: absolute;
		background-color: #fff;
    	background-color: rgba(255,255,255,0.5);
	}


/*end of error field*/
    
    
</style>

</head>
<body>
	<div id="top">
			<div id="element1">
            	<a href="Home.jsp">LibraMate</a>
            </div>
	</div>
	<div id="search-container">
		<h1>Sign Up</h1>

		<text id="suc" style="margin-left:30px;"></text>
        <form name="login_form" method="POST" onsubmit="return validateForm();" action="Home.jsp">
      
            <text>Username</text><br>
            <input style="display:inline-block;" class="form-bar" type="text" name="userName">
            <div style="display:inline-block;" id="nameError"></div>
            <br>
            <text>Password</text><br>
            <input style="display:inline-block;" class="form-bar" type="text" name="password">
            <div style="display:inline-block;" id="passwordError"></div>
            <br>
            <text>Image Url</text><br>
            <input style="display:inline-block;" class="form-bar" type="text" name="imageUrl">
            <div style="display:inline-block;" id="UrlError"></div>
            

            <div class="login">
            <input class="login-button" type="submit" name="submit">
            </div>
        </form>
	</div>
    

	<div id="bottom"></div>
	
	<script >
	function validateForm(){
		//var myObj = JSON.parse('File.json');

		var error = false;
		var userName = document.login_form.userName.value;
		var password = document.login_form.password.value;
		var url = document.login_form.imageUrl.value;
		//alert("validate");
			//check userName
			if(userName === ""){
				//alert("empty1");
				document.getElementById("nameError").innerHTML = "Invalid Name";
				error = true;
			}else{
				document.getElementById("nameError").innerHTML = "";
			}	
			
			//check password
			if(password === ""){
				//alert("empty2");
				document.getElementById("passwordError").innerHTML = "Entry Cannot be Empty";
				error = true;
			}
			else{
				document.getElementById("passwordError").innerHTML = "";
			}
			
			//check url
			if(url === ""){
				//alert("empty3");
				document.getElementById("UrlError").innerHTML = "Entry Cannot be Empty";
				error = true;
			}
			else{
				document.getElementById("UrlError").innerHTML = "";
			}
		
		
		if(!error){

			//writes to json file
			if(!error){
				var x = new XMLHttpRequest();
				x.open("Get", "WriteJson?userName=" + userName + "&password=" + password + "&url=" + url);
				x.send();
				if(x.responseText.trim().length > 0){
					document.getElementById("nameError").innerHTML = "Username Taken";
					//alert("yes there is an error");
					return false;
				}
				
				//alert("SEND TO WRITE");
				document.getElementById("suc").innerHTML = "Sign Up successful!";
				return false;
				
			}
		}
		
		
		return false; 
	}
</script>
	
	
</body>
</html>