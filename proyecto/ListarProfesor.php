<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['teacher']=array();
$select="SELECT * FROM teacher";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['username']=$row['1'];
    $index['email']=$row['2'];
    $index['fullname']=$row['4'];
    $index['course']=$row['5'];
    $index['tipe']=$row['6'];
    array_push($result['teacher'],$index);
}

$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
