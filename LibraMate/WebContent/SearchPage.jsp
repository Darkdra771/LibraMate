<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<title>LibraMate</title>
	<style>
        
        body{
            background-color: deepskyblue;
        }
        
        #header {
            background-color:aquamarine;
            margin-top: -10px;
            margin-left: -8px;
            padding:5px;
            top:0;
            left:0;
            width: 100%;
            height: auto;
        }
        
        #container{
            padding-top: 130px;
            overflow-y: auto;
            padding-left: 30px;
            margin-left: 30px;
            display: inline-block;
            line-height: 17px;
        }
                
        #text{
            display:inline-block;
            font-family: "Arial", Times, serif;
            font-size: 45px;
            color: white;
            text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;  
        }
        
        #radio{
            padding-left: 30px;
        }
        
        
        /*for information page*/
        
        #largeImage{
        	position: absolute;
            padding-top: 100px;
            margin-left: 80px;
            padding-bottom: 30px;
        }
        
        #info{
        	position: absolute;
        	display: inline-block;
            margin-left: 670px;
            margin-top: 150px;
        }
        
        #description{
            font-family: "Arial", Times, serif;
            color: white;
            text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
            
        }
        
        
        /*for information page*/
        
        a, a:visited, a:hover, a:active {
  			color: inherit;
		}
        
        #element1{
            display:inline-block;
            margin-left: 50px;
        }
        
        #element2{
            display:inline-block;
        }
        
        #element3{
        display: inline-block;
        }
        
        #image{
        	margin-left: 30px;
        	border-radius: 50%;
        	width: 50px;
        	height: 50px;
        	padding:1px;
   			border:3px solid #021a40;
        }

        /* start of search styling */

        .topnav a {
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }


        .topnav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
            margin-left: 100px;
        }

        .topnav .search-container button {

            padding: 7px 10px;
            margin-top: 8px;
            margin-right: 9px;
            margin-left: -4px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }
        
        /* drop-down menu */
        .dropbtn {
            background-color: #3498DB;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .dropbtn:hover, .dropbtn:focus {
            background-color: #2980B9;
        }

        .dropdown {
            margin-top: 30px;
            margin-left: 100px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            overflow: auto;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {background-color: #ddd}

        .show {display:block;}
        
        /*.....*/
        
        
	</style>
	<script>
	
		window.onload = function(){
			
			//alert("in Search Page");
			var x = document.getElementById('dropdown');
			var req = new XMLHttpRequest();
			req.open("GET", "Update?user=active", false);
			req.send();
			
			if(req.responseText === "no"){
            	x.style.display = "none";
			}
			else if(req.responseText === "yes"){
            	document.getElementById('imageStyle').style.maxHeight = "600px";
            	x.style.display = "block";
			}
			
			var check = document.getElementById("search_icon").getAttribute("class");
			if(check === "fa fa-group"){
				document.getElementsByClassName('radio')[0].style.display = "none";
				document.getElementsByClassName('radio')[1].style.display = "none";
			}
			
			
		}
		
	</script>
	</head>
	
	<body>
		
        <div id="header">
            
            <div id="element1">
            	<a href="Home.jsp"><text id="text">LibraMate</text></a>
            </div>
            
            <!--end of libramate-->
           
            <!--form input-->

            <div id="element2">
                <form name="search_form"  method="POST" onsubmit="return search();" action="SearchPage.jsp" >
                
                    <div class="topnav">
                        <div class="search-container">
                            <input id="input" type="text" placeholder="Search..." name="input" value="${input}">
             				
             		
             				
             				 <button type="button" id="icon" onclick="change();">
             				 	<div id="search_icon" class=${ user } ></div>
             				 </button>
                                         
           					
                                            
                            <div id="radio" class="radio" style="display:inline-block;">

                            
                            <input type="radio" name="choice" value="Title" ${choice == "Title"? 'checked' : ''}><strong>Title</strong>
                            <br>
                            <input type="radio" name="choice" value="Author" ${choice == "Author"? 'checked' : ''}>
                            <strong>Author</strong>
                            </div>
                    
                            <div id="radio" class="radio" style="display:inline-block;">
                            <input type="radio" name="choice" value="ISBN" ${choice == "ISBN"? 'checked' : ''}><strong>ISBN</strong>
                            <br>
                            <input type="radio" name="choice" value="Genre" ${choice == "Genre"? 'checked' : ''}><strong>Genre</strong>
                            </div>
                            
                            
                        </div>
                    </div>
         
                </form>
            </div>
            
            <div id="element3">
            	<form action="UserServlet">
            		<input type="image" id="image" src="${userImage}">
            	</form>
            </div>
            
        </div>
        
        <!--end of header-->
        <!--take out the form put into string in servlet -->
        
        <div id="search_results">   
        	    ${images}
        </div>
   
        
    <script>
	
    function search(){
    	
    	var icon = document.getElementById('search_icon');
    	var x = new XMLHttpRequest();
    	if(hasClass(icon, 'fa fa-book') || hasClass(icon, 'fa fa-search')){
    		//document.search_form.action = "Input";
    		x.open("GET", "Input?input=" + document.search_form.input.value
    				+ "&choice=" + document.search_form.choice.value, false);
    		x.send();
    		if(x.responseText.trim().length > 0){
    			document.getElementById('search_results').innerHTML = x.responseText;
    			return false;
    		}
    	}
    	else{
    		//document.search_form.action = "Account";
    		x.open("GET", "Accounts?input=" + document.search_form.input.value, false);
    		x.send();
    		if(x.responseText.trim().length > 0){
    			document.getElementById('search_results').innerHTML = x.responseText;
    			return false;
    		}
    	}
    	
    	
    	document.getElementById('search_results').innerHTML = 
    		"<div id=\"text\" style=\"margin-left:450px;font-size:80px;"
    		+ "margin-top:200px;\">\n<center>No User Found!</center>\n"
    		+ "</div>\n";
    	
    	return false;
    }
    
    
	//changing the button
	function change(){
		
		
		var icon = document.getElementById('search_icon');
		if(hasClass(icon, 'fa fa-book')){
			icon.className = 'fa fa-group';
			//document.getElementById('radio').style.display = "none";
			document.getElementsByClassName('radio')[0].style.display = "none";
			document.getElementsByClassName('radio')[1].style.display = "none";
		}
		else{
			icon.className = 'fa fa-book';
			//document.getElementById('radio').style.display = "block";
			document.getElementsByClassName('radio')[0].style.display = "inline-block";
			document.getElementsByClassName('radio')[1].style.display = "inline-block";
		}
		
	}
	
	function hasClass(element, cls) {
	    return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
	}
	  

    /* When the user clicks on the button, 
    toggle between hiding and showing the dropdown content */
    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
      if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
      }
    }
    
    //store read
    function StoreRead(){
    	var x = new XMLHttpRequest();
    	var title = document.getElementsByTagName("h1")[0].innerHTML;
    	x.open("GET", "Update?user=read&title=" + title , false);
    	x.send();
    	document.getElementById('msg').innerHTML = x.responseText;
    	
    }
    
    //store favor
    function StoreFavor(){
    	var x = new XMLHttpRequest();
    	var title = document.getElementsByTagName("h1")[0].innerHTML;
    	x.open("GET", "Update?user=favor&title=" + title , false);
    	x.send();
    	document.getElementById('msg').innerHTML = x.responseText;
    	
    }
	
    </script>      
	</body>
</html>