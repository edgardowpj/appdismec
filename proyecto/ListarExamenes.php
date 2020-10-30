<?php

$conexion=mysqli_connect("localhost","root","","login");

$result=array();
$result['exams']=array();
$select="SELECT * FROM exams";
$show= mysqli_query($conexion,$select);

while($row = mysqli_fetch_array($show)){
    $index['id']=$row['0'];
    $index['textexam']=$row['1'];
    $index['a']=$row['2'];
    $index['b']=$row['3'];
    $index['c']=$row['4'];
    $index['d']=$row['5'];
    $index['correctAnswer']=$row['6'];
    $index['img']=base64_encode($row['7']);

    array_push($result['exams'],$index);
}
header('Content-type: application/json');
$result['succes']="1";
echo json_encode($result);
mysqli_close($conexion);

?>
