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
        

        
        /*for information page*/
    
        
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
        	margin-left: 100px;
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
        
       /*start profile styling*/
        #profileImage{
            border-radius: 50%;
            width: 200px;
            height: 200px;
            margin-top: 50px;
            margin-left: 50px;
        }
        
        #profile_name{
            background-color: #f1f1f1;
        }
        
        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
        
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
            background-color: antiquewhite;
        }
        
        
        /*second tab start*/
        .tabcontent2{
            color: white;
            display: none;
            padding: 50px;
            text-align: center;
        }
        
        .tablink {
            background-color: #555;
            color: white;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            font-size: 17px;
            width: 25%;
        }

        .tablink:hover {
            background-color: #777;
        }
        
        #Read {background-color: green;}
        #Favorites {background-color: green;}
        
        /*second tab ends */

        /*right-side*/
        .Library{
            vertical-align: top;
            margin: 60px 0px 0px 100px;
        }
        
        .second_tab{
            width: 50%;
        }
        
        #favorites{
            background-color: antiquewhite;
            height: 65%;
            width: 100%;
        }
        
        #follow_button{
			background-color: black;
			border: none;
        }
        
	</style>

	</head>
	
	<body>
		 
		
        <div id="header">
            
            <div id="element1">
            	<a href="Home.jsp"><text id="text">LibraMate</text></a>
            </div>
            
            <div id="element2">
                <form name="search_form"  method="POST" onsubmit="return search();" action="InputFromUserPage" >
                
                    <div class="topnav">
                        <div class="search-container">
                            <input id="input" type="text" placeholder="Search..." name="input" value="${input}">
             				
             				
             				 <button type="button" id="icon" onclick="change();">
             				 	<div id="search_icon" class="fa fa-group" ></div>
             				 </button>
                                        
                            <div id="radio" class="radio" style="display:none;">

                            
                            <input type="radio" name="choice" value="Title" ${choice == "Title"? 'checked' : ''}><strong>Title</strong>
                            <br>
                            <input type="radio" name="choice" value="Author" ${choice == "Author"? 'checked' : ''}>
                            <strong>Author</strong>
                            </div>
                    
                            <div id="radio" class="radio" style="display:none;">
                            <input type="radio" name="choice" value="ISBN" ${choice == "ISBN"? 'checked' : ''}><strong>ISBN</strong>
                            <br>
                            <input type="radio" name="choice" value="Genre" ${choice == "Genre"? 'checked' : ''}><strong>Genre</strong>
                            </div>          
                               
                            
                        </div>
                    </div>
         
                </form>
               </div>
       
                
                <div id="element3">
            		<img id="image" src="${userImage}">
           		</div>
    
    		<!-- change this -->
            
        </div>
        
        <!--end of header-->
        <!--take out the form put into string in servlet -->
        <div style="display: inline-block;">
            <div>
                <img id="profileImage" src="${imageURL}" border="3">
            </div>
            
            <div style="margin: 30px 0px 0px 85px; ">
                <text id="text" class="username" style="font-size:30px;">@${username}</text>
            </div>
            
            <div style="margin: 10px 0px 0px 50px;">
                <div class="tab" id="follower_tab">
                    <button class="tablinks" onclick="openCity(event,'Following')">Following</button>
                    <button class="tablinks" onclick="openCity(event, 'Followers')">Followers</button>
                </div>


                <div id="Following" class="tabcontent">
                	${Following}
                </div>

                <div id="Followers" class="tabcontent" style="display:none;">
                	${Followers}
                </div>
            </div>
        </div>
        
        
        
        <div style="display: inline-block;" class="Library">
            <div id="text" style="display: inline-block;">
                Your Library
            </div>
            
           <div>
                
                <button class="tablink" onclick="tab('Read', this, 'gray')" id="defaultOpen">Read</button>
                <button class="tablink" onclick="tab('Favorites', this, 'gray')">Favorites</button>

                <div id="Read" class="tabcontent2" >
                  ${read}
                </div>

                <div id="Favorites" class="tabcontent2">
                  ${images}
                </div>
            
            </div>
    
        </div>
        
             <div style="display:inline-block; vertical-align: top; 
       			margin-left:100px;margin-top: 80px;"
             id="follow_button">
             	<form method="GET"  action="Logout">
                	<input type="submit" id="text" style="background-color:black;position:absolute;" value="Logout">
                </form>
            </div>
        
        
        <script>
        
        
        function search(){
        	
        	var icon = document.getElementById('search_icon');
        	if(hasClass(icon, 'fa fa-book') || hasClass(icon, 'fa fa-search')){
        		//document.search_form.action = "Input";
        		if(document.search_form.choice.value === null){
        			document.search_form.choice.value = 'Title';
        		}
				document.search_form.action = "InputFromUserPage";
				//alert("HAPPED");
				return true;
        	}
        	else{
        		//document.search_form.action = "Account";
        		document.search_form.action = "AccountSearchFromUserPage"
				return true;
        	}
        	
        	
        	document.getElementById('search_results').innerHTML = 
        		"<div id=\"text\" style=\"margin-left:450px;font-size:80px;"
        		+ "margin-top:200px;\">\n<center>No User Found!</center>\n"
        		+ "</div>\n";
        	
        	return false;
        }
        
            function openCity(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }
            
            function tab(cityName,elmnt,color) {
                var i, tabcontent2, tablinks;
                tabcontent2 = document.getElementsByClassName("tabcontent2");
                for (i = 0; i < tabcontent2.length; i++) {
                    tabcontent2[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablink");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].style.backgroundColor = "";
                }
                document.getElementById(cityName).style.display = "block";
                elmnt.style.backgroundColor = color;

            }
            // Get the element with id="defaultOpen" and click on it
            document.getElementById("defaultOpen").click();
            
            
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
        	
</script>
      
	</body>
</html>