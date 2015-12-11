today=151211;
lweek=151207;
lmonth=151107;
lang_mapping={"1":"Java",
		 		"2":"Python",
		 		"3":"PHP",
		 		"4":"Ruby",
		 		"5":"Javascript",
		 		"6":"HTML",
		 		"7":"CSS",
		 		"8":"C",
		 		"9":"CPP"};
name_mapping={
		"1":"Scott Zhang",
		"2":"Ellen Wen",
		"3":"Patel",
		"4":"Joe Smith",
		"5":"James Bond"
}

var ws = new WebSocket("ws://localhost:8080/Devemeter/WsHandler");
   ws.onopen = function () {
   };

      ws.onmessage = function(message) {
    	//document.getElementById("msgArea").textContent += message.data + "\n";
    	if (typeof(genChart) !="undefined") {
    		genChart.options.data[0].dataPoints=jsonConversion(message.data);
    		genChart.render();
    	}
      };

      function postToServer() {
    ws.send(document.getElementById("msg").value);
    document.getElementById("msg").value = "";

   }

   function closeConnect() {
    ws.close();
   }



 function jsonConversion(str) {
	 var dp=[];
	 //var generalChart=str;
     var generalChart=JSON.parse(str);
	 for (var lang in generalChart["general"]) {
	       	dp.push({label:lang_mapping[lang], y:generalChart["general"][lang]});

	       }
	 return dp;
 }

function trendJsonConversion(str) {
	var lineData=[];
	var trendChart=JSON.parse(str);
	for (var lang in trendChart["trend"]) {
		var dp=[];
		for (var dt in trendChart["trend"][lang]) {
			var y=parseInt(dt.substring(0,2));
			var m=parseInt(dt.substring(2,4))-1;
			var d=parseInt(dt.substring(4,6));
			dp.push({x: new Date(y,m,d), y:trendChart["trend"][lang][dt]});
		}
		var line={type:"line", showInLegend: true, lineThickness: 2, name: lang_mapping[lang], dataPoints:dp};
		lineData.push(line);
	}
	return lineData;
}

function leaderboardJsonConversion(str) {
	var data=JSON.parse(str);
	var dp=[]
	for (var uid in data) {
		var name=name_mapping[uid];
		var num=data[uid];
		dp.push({y:num, indexLabel:name});
	}
	dp.sort(function(a,b) {
		return a.y-b.y;
	})
	for (var i=0; i<dp.length; i++) {
		dp[i]["label"]=dp.length-i;
	}
	return dp;
}

 function requestGeneralChart(uid, date1, date2, dataType, t) {
    $.ajax({
         url: "http://localhost:8080/Devemeter/GetGeneralData",
         type: "POST",
         data: {"uid":uid, "date1":date1, "date2":date2, "dataType":dataType},
         beforeSend: function() {
        	 $("#spinner").show();
         },
         success: function(data) {
           var dp=[];
           dp=jsonConversion(data);
           var max=100;
           if (t=="Week")
        	   max=500;
           else if (t=="Month")
        	   max=1000;
           var titleText="Lines of code written in a "+t;
           genChart = new CanvasJS.Chart("generalChart",
                {
        	   	  animationEnabled:true,
                  axisY: {
                    maximum: max
                  },
                  title: {
                	  text: titleText
                  },
                  data: [
                      {
                        type: "column",
                        dataPoints: dp
                      }
                  ]
                });

            genChart.render();
            $("#spinner").hide();
         },
         error: function() {
           alert("error ");
         }
        });
 }
 
 function requestTrendChart(uid, date1, date2, range, dataType) {
	    $.ajax({
	         url: "http://localhost:8080/Devemeter/GetTrendData",
	         type: "GET",
	         data: {"uid":uid, "date1":date1, "date2":date2, "dataType":dataType},
	         success: function(data) {
	           var chartData=[];
	           chartData=trendJsonConversion(data);
	           //console.log(chartData);
	           trendChart = new CanvasJS.Chart("trendChart",
	                {
	        	   	  animationEnabled:true,
	        	   	  axisX: {
	        	   		gridColor: "Silver",
	        	   		tickColor:"silver",
	        	   		valueFormatString: "DD/MMM"
	        	   	  },
	                  axisY: {
	                	gridColor: "Silver",
		        	   	tickColor:"silver"
	                  //  maximum: 20
	                  },
	                  data: chartData
	                });

	            trendChart.render();
	         },
	         error: function() {
	           alert("error ");
	         }
	        });
 }
 
 function requestLeaderboardChart() {
	 $.ajax({
		 url: "http://localhost:8080/Devemeter/GetLeadershipData",
		 type: "GET",
		 success: function(data) {
			 var leadData=leaderboardJsonConversion(data);
			 
			 leadChart=new CanvasJS.Chart("leadChart", {
				title: {
					text: "Leaderboard",
					fontSize: 28,
					margin: 30
				},
				animationEnabled: true,
				 axisY: {
		                tickThickness: 0,
		                lineThickness: 0,
		                labelFontSize:30,
		                valueFormatString: " ",
		                gridThickness: 0                    
		            },
		            axisX: {
		                tickThickness: 0,
		                lineThickness: 0,
		                labelFontSize: 30,
		                labelFontColor: "Peru"

		            },
				data: [{
				       indexLabelFontSize:23,
				       toolTipContent: "<span style='\"'color: {color};'\"'><strong>{indexLabel}</strong></span><span style='\"'font-size: 20px; color:peru '\"'><strong>{y}</strong></span>",

				       indexLabelPlacement: "inside",
		               indexLabelFontColor: "white",
		               indexLabelFontWeight: 600,
		               indexLabelFontFamily: "Verdana",
		               color: "#62C9C3",
		               type: "bar",
		               dataPoints:leadData
				}]
			 });
			 leadChart.render();
		 }
	 })
 }

 function showLead() {
	$("#intro").show();
	$("#about").hide();
 	$("#services").hide();
 	$("#contact").hide();
 }
 
 function hideLead() {
	 $("#intro").hide();
	 $("#about").show();
	 $("#services").show();
	 $("#contact").show();
 }
 
 window.onload = function () {
        requestGeneralChart("1",today, today, "line", "Day");
        requestTrendChart("1", today-5, today, "line");
        //binding tasks
        $(".about-section").css("padding-top", "50px");
        $(".contact-section").css("padding-top", "50px");
        $(".contact-section").css("margin-top", "120px");
        $(".contact-section").css("margin-bottom", "700px");
        
        $("#gen_day").click(function(){
                requestGeneralChart("1",today, today, "line", "Day");
        });

        $("#gen_week").click(function(){
                requestGeneralChart("1",lweek, today, "line", "Week");
        });

        $("#gen_month").click(function(){
                requestGeneralChart("1",lmonth, today, "line", "Month");
        });
        
        $("trend_week").click(function(){
        	$("#trend_title").html("Weekly Trend");
        	requestTrendChart("1", today-5, today, "line");
        });
        
        $("#trend_month").click(function(){
        	$("#trend_title").html("Monthly Trend");
        	requestTrendChart("1", 151111, today, "line");
        });
        
        $("#trend_year").click(function(){
        	$("#trend_title").html("Yearly Trend");
        	requestTrendChart("1", 141211, today, "line");
        });
        
        $("#genSwitch").click(function(){
        	if (genChart.options.data[0].type=="pie") {
        		genChart.options.data[0].type="column";
        	} else {
        		genChart.options.data[0].type="pie";
        	}
        	genChart.render();
        });
        
        gauge=new JustGage({
        	id:"productivity",
        	value:60,
        	min:0,
        	max:100,
        	title: "Productivity Index"
        });
        
        $("#zlk").click(function(){
        	requestGeneralChart("1",today, today, "line", "Day");
        	requestTrendChart("1", today-12, today, "line");
        	hideLead();
        });
        
        $("#wwq").click(function(){
        	requestGeneralChart("2",today, today, "line", "Day");
        	requestTrendChart("2", today-12, today, "line");
        	hideLead();
        })
        
        $("#u3").click(function(){
        	requestGeneralChart("3",today, today, "line", "Day");
        	requestTrendChart("3", today-12, today, "line");
        	hideLead();
        });
        
        $("#u4").click(function(){
        	requestGeneralChart("4",today, today, "line", "Day");
        	requestTrendChart("4", today-12, today, "line");
        });
        
        $("#u5").click(function(){
        	requestGeneralChart("5",today, today, "line", "Day");
        	requestTrendChart("5", today-12, today, "line");
        });
        
        $("#leaderboard").click(function(){
        	showLead();
        	
        	requestLeaderboardChart();
        });
        
        
        
}


