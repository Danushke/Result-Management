<?php
//############################please check these php code later about the order of passing data for my further knowladge
	class DbOperation {
		private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';  //import the file
			
			//after import the file we create object of that class (class we import)
			$db= new DbConnect();
			//from this object we can get the connection link
			
			//using the $db object we call the method connect in DbConnect class
			$this->con = $db->connect();
		
			
			// when ever we create the object of this DbOperation class the constructor will invoke & initialise the con variable 
		}
		
		/*CRUD -> C -> Create*/
		//when ever we call this function will need to pass these parameters to create a new user
		function createUser($username,$pass,$email){
			if(isUserExist()){
				
			}else{
			
				//***********************,$dep,$mobile,$username,$pwd
				$password=md5($pass); // encrypt password
				
				$stmt = $this->con->prepare("INSERT INTO `userr` (`username`, `password`, `email`) VALUES (?, ?, ?);");//("INSERT INTO userr('index_no','shortname','reg_no') VALUES(?,?,?);"); //data insert statment
				//***********************,?,?,?,?
				// we bind the actual parameters
				$stmt->bind_param("sss",$username,$pass,$email);
				//echo $username."  ".$pass."  ".$email;
				//**********************,$dep,$mobile,$username,$pwd
				//as soon as call "$stmt-.execute()" this our data will insert to database
				// some times may have an error when put the data to datbse so that we use if 
				if($stmt->execute()){
					return true;
				}else{
					return false;
					//$username.$pass.$email
				}
			}
		}
		
		private function isUserExist($index,$username){
			$stmt=$this->con->prepare ("select index_no from user where index_no=? or username=?");
			$stmt->bind_param("is",$index_no,$username);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows>0;
		}
		
	}
?>