<?php


$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "pharmacie";
  
  $con = mysqli_connect($hostname,$username,$password,$dbname);
  
  $id =$_GET['id'];
  
  $date="20".date("y")."-".date("m")."-".date("d");
 

   $sql= "insert into vente (numvente,datevente,montant) values ('$id','$date','0')";

$query=mysqli_query($con,$sql);


$sql2="SELECT * FROM user WHERE login='aaa' AND password='aaa'";

$query1=mysqli_query($con,$sql2);
while ($row=mysqli_fetch_assoc($query1)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));

?>