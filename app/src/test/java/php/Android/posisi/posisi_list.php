<?php

	require_once('koneksi.php');

	$sql = "SELECT * FROM tb_posisi";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	$result = array();
	$response = array();




	while($row = mysqli_fetch_array($r)){

		$tmp = array();
		$tmp["id"]= $row[0];
	        $tmp["posisi"]= $row[1];
		$tmp["gajih"]= $row[2];
		array_push($response, $tmp);

	}


	echo json_encode(array('result'=>$response));

	mysqli_close($con);
?>
