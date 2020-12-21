<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['documentspdf']=array();
$select="SELECT * FROM documentspdf";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['title']=$row['1'];
    $index['location']=$row['2'];

    array_push($result['documentspdf'],$index);
}

$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
