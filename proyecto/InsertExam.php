<?php

    $connection=mysqli_connect("localhost","root","","login");

    if($_SERVER['REQUEST_METHOD']=='POST'){
        $textexam=$_POST['textexam'];
        $a=$_POST['a'];
        $b=$_POST['b'];
        $c=$_POST['c'];
        $d=$_POST['d'];
        $img=$_POST['img'];
        $correctAnswer=$_POST['correctAnswer'];

        $sql="SELECT id FROM exams ORDER BY id ASC";
        $res=mysqli_query($connection,$sql);
        $id=0;

        while($row=mysqli_fetch_array($res)){
            $id = $row['id'];
            }

        $path="uploads/$id.png";
        $actualpath="http://192.168.1.55/proyecto/uploads/$path";

        $sql="INSERT INTO exams (textexam,a,b,c,d,correctAnswer,img) VALUES ('$textexam','$a','$b','$c','$d','$correctAnswer','$actualpath')";

        if(mysqli_query($connection,$sql)){
            file_put_contents($path,base64_decode($img));
            echo "Upload correct";
        }

        mysqli_close($connection);
    }else{
        echo"Error";
    }

     
          
    
    


?>