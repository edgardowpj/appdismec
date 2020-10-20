<?php

    $connection=mysqli_connect("localhost","root","","login");
    
    
     $username = $_POST["username"];
     $nameofcourse = $_POST["email"];
    
     
     $sql = "INSERT INTO course(username,nameofcourse) VALUES ('$username','$nameofcourse')";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Inserted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
          
    
    


?>