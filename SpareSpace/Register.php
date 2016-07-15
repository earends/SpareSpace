<?php
    $con = mysqli_connect("mysql13.000webhost.com", "a8457156_Sspace", "discord_space123", "a8457156_Sspace");
    
    $name = $_POST["name"];
    $username = $_POST["username"];
    $age = $_POST["age"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, age, username, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name,$age, $username, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>