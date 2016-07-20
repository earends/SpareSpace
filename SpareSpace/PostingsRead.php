
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
    
    $statement = mysqli_prepare($con, "SELECT username,title,description,location,cost,obo,dimmension,phone,email,image,image2 FROM Postings");
    
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $username,$title,$description,$location,$cost,$obo,$dimmension,$phone,$email,$image,$image2);
    
    $response = array();
    $titles = array();
   
    while(mysqli_stmt_fetch($statement)){  
        $response[] = $username;
        $response[] = $title;
        $response[] = $description;
        $response[] = $location;
        $response[] = $cost;
        $response[] = $obo;
        $response[] = $dimmension;
        $response[] = $phone;
        $response[] = $email;
        $response[] = $image;
        $response[] = $image2;
    }
    
    $response["success"] = true; 
    #echo json_encode($respond);
    echo json_encode($response);
    
?>