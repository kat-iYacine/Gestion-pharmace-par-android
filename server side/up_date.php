<?php

$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "pharmacie";
  
  $con = mysqli_connect($hostname,$username,$password,$dbname);
  
    $total=$_GET['total'];
    $idf=$_GET['idf'];

$sql1="UPDATE vente SET montant = '$total'WHERE numvente='$idf'";
$query=mysqli_query($con,$sql1);


?>