<?php
$con=mysqli_connect("localhost","root","","pharmacie")or die(mysqli_error($con));

$sql="SELECT * FROM vente";
$query=mysqli_query($con,$sql);
while ($row=mysqli_fetch_assoc($query)) {
                    	
                $output[]=$row;
                                 }
print(json_encode( $output));


/*$date=$time="20".date("y")."-".date("m")."-".date("d")." ".date("H").":".date("i").":".date("s").'</br>';
echo $date;
$i=date("y").date("m").date("d").date("H").date("i").date("s");
echo $i+0;*/
?>
