<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['course']=array();
$select="SELECT * FROM course";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['username']=$row['1'];
    $index['email']=$row['2'];


    array_push($result['course'],$index);
}

$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
