<?php
    $con = mysqli_connect("mysql13.000webhost.com", "a8457156_Sspace", "discord_space123", "a8457156_Sspace");
    
    $name = $_POST["name"];
    $age = $_POST["username"];
    $username = $_POST["password"];
    $password = $_POST["age"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $password, $age);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>