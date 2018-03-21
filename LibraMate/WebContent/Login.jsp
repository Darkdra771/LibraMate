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
        margin-left: -145px;
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

	#nameError, #passwordError{
		font-weigth: bold;
		font-size: 20px;
		color: red;
		position: absolute;
		background-color: #fff;
    	background-color: rgba(255,255,255,0.5);
	}


/*end of error field*/
    
</style>
<script>

	function validate(){
		
		//alert("validate");
		var username = document.login_form.userName.value;
		var password = document.login_form.password.value;
		var error = false;
		
		//check username
		if(username === ""){
			alert("empty1");
			document.getElementById("nameError").innerHTML = "Invalid Name";
			error = true;
		}else{
			document.getElementById("nameError").innerHTML = "";
		}	
		
		//check password
		if(password === ""){
			alert("empty2");
			document.getElementById("passwordError").innerHTML = "Entry Cannot be Empty";
			error = true;
		}
		else{
			document.getElementById("passwordError").innerHTML = "";
		}
		
		if(error){
			return false;
		}else{
			
			var txt = "";
			var x = new XMLHttpRequest();
			x.open("GET", "File.json", false);
			x.onreadystatechange = function(){
				if(x.readyState === 4 && x.status === 200){
					txt = x.responseText;
				}
			
			}
	
			x.send();
			
			//var myObj = JSON.parse(sessionStorage.getItem('JsonString'));
			var myObj = JSON.parse(txt);
			var length = myObj.users.length;
			//alert("hello " + myObj.users[0].imageURL);
			
			for(var i = 0; i < length; i++){
				//alert(i);
				if(username === myObj.users[i].username){
					if(password === myObj.users[i].password){
						//success
						sessionStorage.setItem('index', i);
						return true;
					}
					else{
						error = true;
						document.getElementById("passwordError").innerHTML = "Password is wrong";
						return false;
					}
				}
			}
			
			document.getElementById("nameError").innerHTML = "username not found";
			return false;
			
		}
		
		
	}
</script>
</head>
<body>
	<div id="top">
				<div id="element1">
            	<a href="Home.jsp">LibraMate</a>
            </div>
	</div>
	<div id="search-container">
		<h1>Login</h1>

        <form name="login_form" method="POST" onsubmit="return validate();"   action="Input">
     
      		<input type="hidden" name="forward" value="forward">
            <text>Username</text><br>
            <input style="display:inline-block" class="form-bar" type="text" name="userName" >
            <div style="display:inline-block;" id="nameError"></div>
            <br>
            <text>Password</text><br>
            <input style="display:inline-block" class="form-bar" type="text" name="password" >
            <div style="display:inline-block;" id="passwordError"></div>
            <br>
            

                <div class="login">
                <input class="login-button" type="submit" value="Submit">
                </div>
        </form>
	</div>
    

	<div id = "bottom"></div>
	
</body>
</html>