<?php

    $connection=mysqli_connect("localhost","root","","login");
    
    
     $username = $_POST["username"];
     $email = $_POST["email"];
    
     
     $sql = "INSERT INTO course(username,email) VALUES ('$username','$email')";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Inserted";
        
     }
     else{
         echo "Failed, Because the username is register";
     }
     mysqli_close($connection);
     
          
    
    


?>