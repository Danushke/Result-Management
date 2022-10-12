<?php
	
	/* require_once '../include/DbOperation2.php';
	//associative array
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
	}else{
		$response['error']=true;
		$response['message']="Invalid Requst";
	} */
	
	
	
	
	
	
	
	
	
	
	
	
	
	$DB_HOST="localhost";
	$DB_USER="root";
	$DB_PASS="Root123";
	$DB_NAME="result_m_s";
	
	$con=mysqli_connect($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME);
	
	$index=$_POST["index"];
	
	$sql="select * from `ict1617sem1` WHERE index_no like '$index'"; //
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result))
	{
		array_push($response,array("ICT1301"=>$row[1],
		"ICT1302"=>$row[2],
		"ICT1303"=>$row[3],
		"CMT1301"=>$row[4],
		"CMT1303"=>$row[5],"CML1201"=>$row[6],"CMT1005"=>$row[7]));
		
	}
	echo json_encode(array("server_response"=>$response));