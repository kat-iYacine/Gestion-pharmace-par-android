<?php
$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "pharmacie";
  
  $con = mysqli_connect($hostname,$username,$password,$dbname);
   
$cf=$_GET['cf'];
$nom=$_GET['nom'];
$adr=$_GET['adr'];
$ville=$_GET['ville'];
$tel=$_GET['tel'];


   $sql= "insert into fournisseur (codefournisseur,nomfournisseur,adressefournisseur,villefournisseur,telefournisseur) values ('$cf','$nom','$adr','$ville','$tel')";

$query=mysqli_query($con,$sql);


$sql2="SELECT * FROM user WHERE login='aaa' AND password='aaa'";

$query1=mysqli_query($con,$sql2);
while ($row=mysqli_fetch_assoc($query1)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));


?>
