<?php
require_once '../include/DbOperation2.php';
	
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$db=new DbOperation2();
		if(isset($_POST['index_no'])and isset($_POST['table_name'])){
			$result=array();
			$result=$db->userResultsRow($_POST['index_no'],$_POST['table_name']);	
			if ($result){
				/* $response['error']=false;
				$response['message']="Success"; */
				$response=$result;
			}else{
				$response['error']=true;
				$response['message']="Error";
			}
		}
	}
	
	echo json_encode($response);
?>