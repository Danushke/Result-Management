<?php
	
	require_once '../include/DbOperation2.php';
	//associative array
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$dbo=new DbOperation2();
		$result=$dbo->column2();
		$response['error']=false;
		
		
		$response['ict1301']=$result[''];
		$response['ict1302']=$result['ICT1302'];
		$response['ict1303']=$result['ICT1303'];
		$response['cmt1301']=$result['CMT1301'];
		$response['cmt1303']=$result['CMT1303'];
		//$response['cml1201']=$result['CML1201'];
		$response['cmt1005']=$result[''];
			
	}else{
		$response['error']=true;
		$response['message']="Invalid Requst";
	}
	echo json_encode($response);
?>

