<?php

$link = mysqli_connect('localhost' , 'root' , '' ,'demo');

$query = "select name from demo";

$query_run  =  mysqli_query($link , $query);


$output = array();

while($row = mysqli_fetch_assoc($query_run))
{
	$output[] = $row;
}
echo json_encode($output);

?>