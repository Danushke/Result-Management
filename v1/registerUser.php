<?php

	require_once '../include/DbOperation.php';
	//associative array
	$response = array();
	//to storing data we use thhp post method_exists
	//check if there is post method or not
	//"$_SERVER" is predefind variable in php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//check if user has given all required values
		//no need order only thing is match with tabble head
		if(isset($_POST['username'])and
			isset($_POST['password'])and
			isset($_POST['email'])/* and
			isset($_POST['dep'])and
			isset($_POST['mobile'])and
			isset($_POST['username'])and
			isset($_POST['password']) */)
			{
			//operate the data further 
			$db=new DbOperation();
			if($db->createUser (
				$_POST['username'],
				$_POST['email'],
				$_POST['password']/* ,
				$_POST['dep'],
				$_POST['mobile'],
				$_POST['username'],
				$_POST['password'] */
			)){
				$response['error']=false;
				$response['message']="User registered successfully";
			}else{
				$response['error']=true;
				$response['message']="Some error occured try again";
			}
		}else{ // if user not provided all the required values
			$response['error']=true;
			$response['message']="Required Fields are Missing";
		}
	}else{
		$response['error']=true;
		$response['message']="Invalid Requst";
	}
	
	echo json_encode($response);
?> 