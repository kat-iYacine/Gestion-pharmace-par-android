<?php
$type=$_GET['type'];


$con=mysqli_connect("localhost","root","","pharmacie")or die(mysqli_error($con));


if($type=="med"){$sql="SELECT * FROM medicament ";}
if($type=="frnc"){$sql="SELECT codefournisseur FROM               fournisseur";}
if($type=="fm"){$sql="SELECT * FROM typemedicament";}

$query=mysqli_query($con,$sql);
while ($row=mysqli_fetch_assoc($query)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));
?>


