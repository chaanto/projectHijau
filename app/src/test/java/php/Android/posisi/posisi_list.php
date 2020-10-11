<?php

	require_once('koneksi.php');

	$sql = "SELECT * FROM tb_posisi";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	$result = array();
	$response = array();
    	$response["posisi"] = array();	
	
 

	while($row = mysqli_fetch_array($r)){

		$tmp = array();
	        $tmp["posisi"]= $row[1];
		array_push($response["posisi"], $tmp);

	}

	echo json_encode($response);

	mysqli_close($con);
?>
