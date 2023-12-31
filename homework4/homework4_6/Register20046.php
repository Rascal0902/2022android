<?php
    $con=mysqli_connect("localhost","gghs20","rudrhkr20!","gghs20");

    $userID=$_POST["userID"];
    $userPassword=$_POST["userPassword"];
    $userName=$_POST["userName"];
    $userAge=$_POST["userAge"];

    $statement = $con->prepare("INSERT INTO USERCHW VALUES (?,?,?,?)");
    $statement->bind_param('ssss', $userID, $userPassword, $userName, $userAge);
    $statement->execute();

    $response=array();
    $response["success"]=true;
    
    echo json_encode($response);

?>