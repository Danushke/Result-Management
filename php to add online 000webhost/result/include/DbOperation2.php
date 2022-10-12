<?php
	class DbOperation2 {
		private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';  
			
			$db= new DbConnect();
			
			$this->con = $db->connect();
			
		}
		
		/*CRUD -> C -> Create*/
		
		public function createUser($index_no,$short_name,$reg_no,$mobile,$username,$password){
			if($this->isUserExist($index_no,$username)){
				return 0;
			}else{
				$pass = md5($password); 
				$stmt = $this->con->prepare ("INSERT INTO `user`(`index_no`, `short_name`, `reg_no`, `dep`, `mobile`, `username`, `password`) VALUES (?,?,?,null,?,?,?);");
				
				$stmt->bind_param("ississ",$index_no,$short_name,$reg_no,$mobile,$username,$pass);
				
				if($stmt->execute()){
					return 1;
				}else{
					return 2;
				}
			}
		}
		private function isUserExist($index,$username){
			$stmt=$this->con->prepare ("SELECT index_no FROM user WHERE index_no = ? OR username = ?");
			$stmt->bind_param("is",$index,$username);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows>0;
		}
		public function getUserByUsername($username){
			$stmt = $this->con->prepare("SELECT * FROM user WHERE username = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}	
		
		public function userLogin($username,$passw){
			// the query will execute acording to order of the parameter
			//$pwd=md5($password);
			$passw=md5($passw);
			$stmt=$this->con->prepare("SELECT username FROM user WHERE username = ? AND password = ?" );
			$stmt->bind_param("ss",$username,$passw);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}

		public function userResults($index){
			$stmt = $this->con->prepare("SELECT * FROM `ict1617sem1` WHERE index_no = ?");
			$stmt->bind_param("i",$index);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

	}
?>