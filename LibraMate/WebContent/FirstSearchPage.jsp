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
        
       
        
	</style>

	</head>
	
	<body>

        <div id="header">
            
            <div id="element1">
            	<a href="Home.jsp"><text id="text">LibraMate</text></a>
            </div>
            
            <!--end of libramate-->
           
            <!--form input-->

            <div id="element2">
                <form name="search"  method="POST" action="Input">
                
                    <div class="topnav">
                        <div class="search-container">
                            <input id="input" type="text" placeholder="Search..." name="input" value="${input}">
                            <button id="button" type="submit"><i class="fa fa-search"></i></button>     
                            
                                                
                                                
                            <div id="radio" style="display:inline-block;">
                            <input type="radio" name="choice" value="Title" ${choice == "Title"? 'checked' : ''}><strong>Title</strong>
                            <br>
                            <input type="radio" name="choice" value="Author" ${choice == "Author"? 'checked' : ''}>
                            <strong>Author</strong>
                            </div>
                    
                            <div id="radio" style="display:inline-block;">
                            <input type="radio" name="choice" value="ISBN" ${choice == "ISBN"? 'checked' : ''}><strong>ISBN</strong>
                            <br>
                            <input type="radio" name="choice" value="Genre" ${choice == "Genre"? 'checked' : ''}><strong>Genre</strong>
                            </div>
                            
                        </div>
                    </div>
         
                </form>
            </div>    
            
        </div>
        
        <!--end of header-->
        <!--take out the form put into string in servlet -->
        
        <div >   
        	  ${images}
        </div>
            

	</body>
</html>