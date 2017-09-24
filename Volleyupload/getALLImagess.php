<?php


 $con = mysqli_connect('localhost','root','','demo') or die('unable to connect to db');
 
 $sql = "select photo from demo";
 

 $result = array();
if($res = mysqli_query($con,$sql))
{	
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('url'=>$row['photo']));
	$photo = $row['photo'];
  }
 
 echo json_encode(array("result"=>$result));
}
 mysqli_close($con);
 ?>