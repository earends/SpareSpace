<?php
    $con = mysqli_connect("mysql13.000webhost.com", "a8457156_Sspace", "discord_space123", "a8457156_Sspace");
    
    $username = $_POST["username"];
    $title = $_POST["title"];
    $description = $_POST["description"];
    $location = $_POST["location"];
    $cost = $_POST["cost"];
    $obo = $_POST["obo"];
    $dimmension = $_POST["dimmension"];
    $phone = $_POST["phone"];
    $email = $_POST["email"];
    $image = $_POST["image"];
    $image2 = $_POST["image2"];
    $statement = mysqli_prepare($con, "INSERT INTO Postings (username,title,description,location,cost,obo,dimmension,phone,email,image,image2) VALUES (?, ?, ?, ?,?, ?, ?, ?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssssssssss", $username,$title,$description, $location, $cost, $obo,$dimmension,$phone,$email,$image,$image2);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>