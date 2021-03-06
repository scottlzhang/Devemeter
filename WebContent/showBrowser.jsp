<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Devemeter</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Siderbar CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

     <!-- Scrolling CSS -->
    <link href="css/scrolling-nav.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/canvasjs/canvasjs.min.js"></script>
	<script type="text/javascript" src="js/canvasjs/jquery.canvasjs.min.js"></script>
	<script type="text/javascript" src="js/jgauge/justgage-1.1.0.min.js"></script>
	<script type="text/javascript" src="js/jgauge/raphael-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
    	.range-btn {
    		text-align:left;
    		margin-top:30px;
    		margin-bottom:30px;
    	}
    	.range-btn a {
    		font-size:20px;
    		cursor:pointer;
    	}
    	#sidebar-wrapper a {
    		cursor:pointer;
    	}
    	
    	.profile-pic {
    		float:left;
    		margin-left:15px;
    		margin-top:5px;
    	}
    	
    	.sidebar-nav li {
    		line-height:50px;
    	}
    </style>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li id="sidebar-firstline">
                    <a id="sidebar-team" href="#" style="display:inline-block;margin-left:-20px">Team</a>
                    <a id="menu-toggle" href="#menu-toggle"  style="display:inline-block;margin-left:77px">
                        <img src="http://www.afrolinked.com/app/templates/default/css/collapse-menu-01-256.png"></img>
                    </a>
                </li>
                <li>
                    <a id="zlk"><img class="profile-pic" src="pic/p1.png" width="40px"></img>Scott Zhang</a>
                    
                </li>
                <li>
                    <a id="wwq">Ellen Wen<img class="profile-pic" src="pic/p2.png" width="40px"></img></a>
                </li>
                <li>
                    <a id="u3">Johnnie Norton<img class="profile-pic" src="pic/p3.jpg" width="40px"></img></a>
                </li>
                <li>
                    <a id="u4">Josh Koch<img class="profile-pic" src="pic/p4.jpg" width="40px"></a>
                </li>
                <li>
                    <a id="u5">Emilia Lepisto<img class="profile-pic" src="pic/p5.jpg" width="40px"></a>
                </li>
                <li>
                	<a id="leaderboard">Leaderboard</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->


        <!-- Page Content -->
        <div id="page-content-wrapper">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="container" style="margin-left:15px">
                    <div id="logo" class="navbar-header page-scroll">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand page-scroll" href="#page-top">Devemeter</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav" style="margin-left:30px ">
                            <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                            <li class="hidden">
                                <a class="page-scroll" href="#page-top"></a>
                            </li>
                            <li>
                                <a class="page-scroll" href="#about">Overview</a>
                            </li>
                            <li>
                                <a class="page-scroll" href="#services">Trend</a>
                            </li>
                            <li>
                                <a class="page-scroll" href="#contact">Productivity</a>
                            </li>
                        </ul>
                    </div>
                <!-- /.navbar-collapse -->
                </div>
            <!-- /.container -->
            </nav>
            
		<div id='spinner' style="position:fixed;margin-top:26%; margin-left:35%; z-index:10; display:none"><img src="spinner.gif" alt="waiting..." ></div>
		
        <!-- Intro Section -->
        <section id="intro" class="intro-section" style="display:none">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div id="leadChart" style="width:70%"></div>
                    </div>
                </div>
            </div>
        </section>
		
        <!-- About Section -->
        <section id="about" class="about-section" style="padding-top:130px">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <button id="genSwitch" style="display:block;margin-bottom:20px"]>Switch Chart</button>
       <div id="generalChart" style="height: 600px; width: 80%; display:block"></div>
       <div class="range-btn">
	       <a id="gen_day">1 day</a><span> | </span>
	       <a id="gen_week">1 week</a><span> | </span>
	       <a id="gen_month">1 month</a>
       </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Services Section -->
        <section id="services" class="services-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 id="trend_title" style="margin-left:-180px">Weekly Trend</h1>
                          <div id="trendChart" style="height: 500px; width: 80%; display:block"></div>
       <div class="range-btn">
	       <a id="trend_week">Week</a><span> | </span>
	       <a id="trend_month">Month</a><span> | </span>
	       <a id="trend_year">Year</a>
       </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Contact Section -->
        <section id="contact" class="contact-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                         <div id="productivity" style="width:400px; height:500px; margin-left:35%"></div>
                    </div>
                </div>
            </div>
        </section>

        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <div id="toggle-hidden">
        <a href="#menu-toggle" id="menu-toggle2">
            <img src="http://www.afrolinked.com/app/templates/default/css/collapse-menu-01-256.png"></img>
        </a>
    </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Scrolling Nav JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle, #menu-toggle2").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>
