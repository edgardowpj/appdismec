<?php
    include 'db_config.php';

    $con = mysqli_connect($HOST,$USER,$PASSWORD,$DB_NAME);


    $encodePDF = $_POST['PY'];


    $sql="SELECT id FROM documentsPDF ORDER BY id ASC";
    $res=mysqli_query($con,$sql);
    $id=0;

    while($row=mysqli_fetch_array($res)){
        $id = $row['id'];
        }


    $pdfTitle = "MyPY";
    $pdfLocation = "PDFS/$id.py";

    $sqlQuery = "INSERT INTO `documentsPDF`(`title`, `location`) VALUES ('$pdfTitle','$pdfLocation')";

    if(mysqli_query($con,$sqlQuery)){

        file_put_contents($pdfLocation, base64_decode($encodePDF));

        $result["status"] = TRUE;
        $result["remarks"] = "document uploaded successfully";

    }else{

        $result["status"] = FALSE;
        $result["remarks"] = "document uploading Failed";

    }

    mysqli_close($con);

    print(json_encode($result));

?>
