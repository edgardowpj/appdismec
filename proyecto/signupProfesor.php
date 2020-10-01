<?php
require "DataBaseProfesor.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['fullname'])&& isset($_POST['course'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("teacher", $_POST['username'], $_POST['email'], $_POST['password'], $_POST['fullname'], $_POST['course'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
