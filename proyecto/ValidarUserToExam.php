<?php
require "DataBaseToExam.php";
$db = new DataBase();
if (isset($_POST['username'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("course", $_POST['username'])) {
            echo "Login Success";
        } else echo "Username wrong or you have no exams to do";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
