<?php

# define is constat in php to use store simple values it can't be change durind the script
#syntax is constant
#define(name,value,case-sensitive)

	//require"login.php";
	$user_namee="Danushka";
	define('DB_HOST','localhost');
	define('DB_USER','id13441540_root');
	define('DB_PASS','Root@123');
	define('DB_NAME','id13441540_result_m_s');
	
	$con=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	if(mysqli_connect_errno()){
		die('Unable to connect database'.mysqli_connect_errno()); // "dei" for stop further execution
	}
	//$stmt=$con->prepare("select index_no,short_name,reg_no,dep,mobile from user order by index_no;");
	
	/* $stmt = $this->con->prepare("SELECT * FROM ict1617sem1 "); // WHERE index_no = ?"
	$stmt->bind_param("s",$index_no);
	$stmt->execute();
	return $stmt->get_result()->fetch_assoc();
	 */
	 
	 
	 
	/* $stmt = $con->prepare("SELECT * FROM ict1617sem1 ");
	$stmt->execute();
	$stmt->bind_result(); */

	
	
///	$id=("select index_no from user where username like '$user_namee';");
///	$re=mysql_query($id);
///	$value=mysql_fetch_object($re);
//	$_SESSION['myindex']=$value;

	$index=$_POST["index_no"];

	$stmt = $con->prepare("select ICT1301,ICT1302,ICT1303,CMT1301,CMT1303,CMT1005 from ict1617sem1 where index_no like '$index';");
	$stmt->execute();
	$stmt->bind_result($ICT1301,$ICT1302,$ICT1303,$CMT1301,$CMT1303,$CMT1005); 
	
	$data=array(); //fetch the data from database
	while($stmt->fetch()){
		$temp=array();
		/* $data['indexno']=$index_no;
		$data['shortname']=$short_name;
		$data['reg_no']=$reg_no;
		$data['mobile']=$mobile;
		$data['username']=$username;
		$data['password']=$password; */
		
		
		$temp['ICT1301']=$ICT1301;
		$temp['ICT1302']=$ICT1302;
		$temp['ICT1303']=$ICT1303;
		$temp['CMT1301']=$CMT1301;
		$temp['CMT1303']=$CMT1303;
		$temp['CMT1005']=$CMT1005;
		
		/* $temp['ICT1301']=$index_no;
		$temp['ICT1302']=$short_name;
		$temp['ICT1303']=$reg_no;
		$temp['CMT1301']=$dep;
		$temp['CMT1303']=$mobile; */
		
		
		array_push($data,$temp);
	}
	echo json_encode($data);
?>