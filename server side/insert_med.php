<?php
$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "pharmacie";
  
  $con = mysqli_connect($hostname,$username,$password,$dbname);
   
$cm=$_GET['cm'];
$nm=$_GET['nm'];
$prv=$_GET['prv'];
$pu=$_GET['pu'];
$ops=$_GET['ops'];
$fm=$_GET['fm'];

   $sql= "insert into medicament(codemedicament,code,libelle,prix,observation,posologie,nomMed) values ('$cm','$fm','0','$prv','$ops','$pu','$nm')";

$query=mysqli_query($con,$sql);


$sql2="SELECT * FROM user WHERE login='aaa' AND password='aaa'";

$query1=mysqli_query($con,$sql2);
while ($row=mysqli_fetch_assoc($query1)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));


?>

