<?php 

 $con = mysqli_connect('localhost','root','','demo') or die('unable to connect to db');
 
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
 $name = $_POST['name'];

 $sql ="SELECT id FROM demo ORDER BY id ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id = 0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }
 
 $path = "uploads/image$id.png";
 
 $actualpath = "http://192.168.137.1/Volleyupload/$path";
 
 $sql = "UPDATE `demo` SET `photo`='$image' WHERE `name`='$name'";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }
 ?>
 