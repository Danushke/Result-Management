<?php

	require_once '../include/DbOperation2.php';
	//associative array
	$response = array();
	//to storing data we use thhp post method_exists
	//check if there is post method or not
	//"$_SERVER" is predefind variable in php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//check if user has given all required values
		//no need order only thing is match with tabble head
		if(isset($_POST['index_no'])and
			isset($_POST['short_name'])and
			isset($_POST['reg_no']) and
			//isset($_POST['dep'])and
			isset($_POST['mobile'])and
			isset($_POST['username'])and
			isset($_POST['password']) )
			{
			//operate the data further 
			$db=new DbOperation2();
			$result=$db->createUser($_POST['index_no'],$_POST['short_name'],$_POST['reg_no'] ,
									/*$_POST['dep'],*/
									$_POST['mobile'],$_POST['username'],$_POST['password']
								);
			if($result==1){
				$response['error']=false;
				$response['message']="User registered successfully";
			}else if($result==2){
				$response['error']=true;
				$response['message']="Some error occured try again";
			}else if($result==0){
				$response['error']=true;
				$response['message']="index or user name already exists";
			}
		}else{ // if user not provided all the required values
			$response['error']=true;
			$response['message']="Required Fields are Missing";
		}
	}else{
		$response['error']=true;
		$response['message']="Invalid Requset";
	}
	
	echo json_encode($response);
?> 