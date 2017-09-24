<?php

$link = mysqli_connect('localhost','root','','demo');

if(isset($_REQUEST['uname'])&&!empty($_REQUEST['uname']))
{
echo		$unum =	$_REQUEST['uname'];	
	

	
}
else
{
	echo "2";
}
?>