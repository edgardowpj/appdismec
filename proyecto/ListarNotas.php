<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['notes']=array();
$select="SELECT * FROM notes";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['username']=$row['1'];
    $index['note']=$row['2'];
    $index['time']=$row['3'];
    $index['rank']=$row['4'];

    array_push($result['notes'],$index);
}

$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
