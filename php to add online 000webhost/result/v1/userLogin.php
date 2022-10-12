<?php

	require_once '../include/DbOperation2.php';
	//associative array
	$response = array();
	//to storing data we use thhp post method_exists
	//check if there is post method or not
	//"$_SERVER" is predefind variable in php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//here check the feilds are empty or not
		if(isset($_POST['username']) and isset($_POST['password'])){
			//************************** invoke the database from here***********************
			$db=new DbOperation2();
			if($db->userLogin($_POST['username'],$_POST['password'])){
				$user=$db->getUserByUsername($_POST['username']);
				$response['error']=false;
				$response['index']=$user['index_no'];
				$response['short name']=$user['short_name'];
				$response['reg no']=$user['reg_no'];
				$response['dep']=$user['dep'];
				$response['mobile No']=$user['mobile'];
				$response['user name']=$user['username'];
				$response['password']=$user['password'];
			}else{
				$response['error']=true;
				$response['message']="Invalid username or password";
			}
		}else{
			$response['error']=true;
			$response['message']="Required Fields are Missing";
		}
	}
	//the JSON is the data interchange format to communicate with anouther divice
	//display this array in json format & it is automatically encode array to json format & display to inside the browser 
	echo json_encode($response);
?>