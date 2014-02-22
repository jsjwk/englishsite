<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath() %>/js/jquery.js" type="text/javascript" ></script>
<title>英语站点功能页面</title>
</head>
<body>

<h1>英语站点功能页面</h1>

<table bordercolor="red" border="1">
<thead><h3>tree</h3></thead>
<tr>
<td>
<input id="btPlay" type="button" value="播放" onclick="ManageSoundControl('play')" />
<input id="btStop" type="button" value="停止" onclick="ManageSoundControl('stop')" />
</td>
<td>
</td>
</tr>
<tr>
<td>
<h3>记得</h3>
</td>
<td>
<h3>忘记</h3>
</td>
</tr>
</table>

文字描述文字描述文字描述文字描述文字描述文字描述文字描述
文字描述文字描述文字描述文字描述文字描述文字描述文字描述


<script type="text/javascript">
function ManageSoundControl(action)
{
     if(action == "play")
   {
    	 $('embed').remove(); 
         $('body').append('<embed src="China[1].mp3" autostart="true" hidden="true" loop="false">');
     }
     if(action == "stop")
     {
     }
 }
 
</script>
</body>
</html>