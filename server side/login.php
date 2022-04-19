<?php
$username=$_GET['username'];
$pss=$_GET['password'];


 $con=mysqli_connect("localhost","root","","pharmacie")or die(mysqli_error($con));


$sql="SELECT * FROM user WHERE login='$username' AND password='$pss'";

$query=mysqli_query($con,$sql);
while ($row=mysqli_fetch_assoc($query)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));
?>

