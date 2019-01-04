const https = require('https')
const http = require('http')
var weather = new Array(5);

setInterval(()=>{
    https.get('https://free-api.heweather.com/s6/weather/forecast?key=2d78e95f0c3b44dba6c5d75b6cf9ee99&lang=en&location=CN101210101',(res)=>{
        //console.log(res.statusCode);
        var json = "";
        res.on('data',(data)=>{
            json += data;
        })
        res.on('end',()=>{
            //console.log(json.indexOf("high"))
            //var temp = parseInt(json.substr(json.indexOf("high")+'high":"高温 '.length,3));
            json = JSON.parse(json);
            weather[0] = '$';
            weather[1] = json.HeWeather6[0]["daily_forecast"][1]["cond_txt_d"];
            weather[2] = json.HeWeather6[0]["daily_forecast"][1]["tmp_max"];
            weather[3] = json.HeWeather6[0]["daily_forecast"][1]["tmp_min"];
            weather[4] = '&';
            // console.log(weather.toString());
        })
    })
},5*60*1000);

const server_hardware = http.createServer();
server_hardware.listen(8808);
server_hardware.on('request',(req,res) => {
	res.statusCode = 200;
	if(req.url == '/weather'){
        res.end(weather.toString());
    }else{
        res.end("temp and hum\n");
    }
})

const server_html = http.createServer();
server_html.listen(80);
server_html.on('request',(req,res) => {
	res.statusCode = 200;
	res.end("<html>         \
    <head>                  \
        <title>             \
        </title>            \
    </head>                 \
    <body>                  \
        <h1>aaaa</h1>       \
        <p>user:</p>                   \
        <p>passwd:</p>                   \
    </body>                            \
</html>                                \
    ");
})






