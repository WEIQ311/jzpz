/**
 * Created by weiQiang on 15:44.
 */
var dom = document.getElementById('userClock');
var numberClock = document.getElementById('numberClock');
var ctx = dom.getContext('2d');
var width = ctx.canvas.width;
var height = ctx.canvas.height;
var r = width / 2;
//防止画布变大结构不变
var rem = width / 200 ;
//绘制背景
function drawBackGround() {
    ctx.save();
    //重置圆心
    ctx.translate(r,r);
    ctx.beginPath();
    ctx.lineWidth = 8*rem;
    ctx.arc(0,0,r-ctx.lineWidth/2,0,2*Math.PI,false);
    ctx.stroke();
    var hourNumbers = [3,4,5,6,7,8,9,10,11,12,1,2];
    ctx.font = 18*rem+'px Arial';
    ctx.textAlign='center';
    ctx.textBaseline = 'middle';
    //绘制时刻
    hourNumbers.forEach(function (number,i) {
        var rad = 2*Math.PI/12*i;
        var x = Math.cos(rad)*(r-30*rem);
        var y = Math.sin(rad)*(r-30*rem);
        ctx.fillText(number,x,y);
    });
   //绘制时刻线
    for(var i = 0;i<60;i++){
        var rad = 2*Math.PI/60*i;
        var x = Math.cos(rad)*(r-18*rem);
        var y = Math.sin(rad)*(r-18*rem);
        ctx.beginPath();
        if(i%5===0){
            ctx.fillStyle = '#000';
            ctx.arc(x,y,2*rem,0,2*Math.PI,false);
        }else{
            ctx.fillStyle = '#ccc';
            ctx.arc(x,y,2*rem,0,2*Math.PI,false);
        }
        ctx.fill();
    }
}
//绘制小时
function  drawHour(hour,minute) {
    ctx.save();
    ctx.beginPath();
    var hRad = 2*Math.PI/12*hour;
    var mRad = 2*Math.PI/12/60*minute;
    ctx.rotate(hRad+mRad);
    ctx.lineWidth = 6*rem;
    ctx.lineCap = 'round';
    ctx.moveTo(0,10*rem);
    ctx.lineTo(0,-r/2);
    ctx.stroke();
    ctx.restore();
}
//绘制分钟
function drawMinute(minute,second) {
    ctx.save();
    ctx.beginPath();
    var rad = 2*Math.PI/60*minute;
    var sRad = 2*Math.PI/60/60*second;
    ctx.rotate(rad+sRad);
    ctx.lineWidth = 3*rem;
    ctx.lineCap = 'round';
    ctx.moveTo(0,10*rem);
    ctx.lineTo(0,-r+30*rem);
    ctx.stroke();
    ctx.restore();
}
//绘制秒
function drawSeconds(second) {
    ctx.save();
    ctx.beginPath();
    ctx.fillStyle = '#c14543';
    var rad = 2*Math.PI/60*second;
    ctx.rotate(rad);
    ctx.moveTo(-2*rem,20*rem);
    ctx.lineTo(2*rem,20*rem);
    ctx.lineTo(1,-r+18*rem);
    ctx.lineTo(-1,-r+18*rem);
    ctx.fill();
    ctx.restore();
}
//绘制圆心
function  drawDot() {
    ctx.beginPath();
    ctx.fillStyle = '#fff';
    ctx.arc(0,0,3*rem,0,2*Math.PI,false);
    ctx.fill();
}

function draw() {
    //恢复到坐标点
    ctx.clearRect(0,0,width,height);
    var now = new Date();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    drawBackGround();
    drawHour(hour,minute);
    drawMinute(minute,second);
    drawSeconds(second);
    drawDot();
    ctx.restore();
    //设置数字显示屏
    numberClock.innerHTML=(hour<10?'0'+hour:hour)+':'+(minute<10?'0'+minute:minute)+':'+'<font color="#c14543">'+(second<10?'0'+second:second)+'</font>';
}
draw();
setInterval(draw,1000);