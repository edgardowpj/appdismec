<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['users']=array();
$select="SELECT * FROM users";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['fullname']=$row['1'];
    $index['username']=$row['2'];
    $index['email']=$row['4'];
    $index['tipe']=$row['5'];

    array_push($result['users'],$index);
}

$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
