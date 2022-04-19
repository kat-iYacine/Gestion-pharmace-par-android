<?php
$med=$_GET['med'];
$con=mysqli_connect("localhost","root","","pharmacie")or die(mysqli_error($con));

$sql="SELECT libelle,prix FROM medicament WHERE codemedicament='$med'";

$query=mysqli_query($con,$sql);
while ($row=mysqli_fetch_assoc($query)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));

?>