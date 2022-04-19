<?php

$hostname = "localhost";
//Define your database username here.
$username = "root";
//Define your database password here.
$password = "";
//Define your database name here.
$dbname = "pharmacie";
  
  $con = mysqli_connect($hostname,$username,$password,$dbname);
  
  $sm =$_GET['sm'];
  $qv =$_GET['qv'];
  $idf=$_GET['idf'];

$Sql_Query = "insert into ventemedicament (codemedicament,numvente,qtevente) values ('$sm','$idf','$qv')";
//mysqli_query($con,$Sql_Query)or die(mysqli_error($con));



if(mysqli_query($con,$Sql_Query)){
	  
   // echo 'Data Inserted Successfully';

$sql="SELECT libelle FROM medicament WHERE codemedicament='$sm'";

$query=mysqli_query($con,$sql)or die(mysqli_error($con));


while ($row=mysqli_fetch_array($query)) {
                    	
                $q=$row['libelle'];

                                }

//echo"libelle=".$q."  qnt=".$qv; 

$a=$q-$qv;
//echo"somme=".$a;

$sql1="UPDATE medicament SET libelle = '$a'WHERE codemedicament='$sm'";
$query=mysqli_query($con,$sql1);

 


$sql2="SELECT * FROM user WHERE login='aaa' AND password='aaa'";

$query1=mysqli_query($con,$sql2);
while ($row=mysqli_fetch_assoc($query1)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));


	
  }
  else{
	  
   // echo 'Try Again';
	
  }
  mysqli_close($con);



?>