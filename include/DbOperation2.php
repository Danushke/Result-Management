
<?php
	class DbOperation2 {
		//private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';  
			
			$db= new DbConnect();
			
			$this->con = $db->connect();
			
		}
		
		/*CRUD -> C -> Create*/
		
		public function createUser($index_no,$short_name,$reg_no,$dep,$mobile,$username,$password){
			if($this->isUserExist($index_no,$username)){
				return 0;
			}else{
				$pass = md5($password); 
				$stmt = $this->con->prepare ("INSERT INTO `user` (`index_no`, `short_name`, `reg_no`, `dep`, `email`, `username`, `password`) VALUES (?,?,?,?,?,?,?);");
				
				$stmt->bind_param("issssss",$index_no,$short_name,$reg_no,$dep,$mobile,$username,$pass);
				
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
			return $stmt->num_rows;
		}
		public function getUserByUsername($username){
			$stmt = $this->con->prepare("SELECT * FROM user WHERE username = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getUserByUsername2($username){
			$stmt = $this->con->prepare("select short_name, index_no, dep, concat(dep, mid(reg_no, 4,4)) as table_name from user WHERE username = ?");
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
		
		public function userResults2($index,$tableName){
			$table="ict1617sem1";
			$stmt = $this->con->prepare("SELECT * FROM `$tableName` WHERE index_no = ?");
			$stmt->bind_param("i",$index);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function userResults3($index,$tableName){
			$qry=("SELECT * FROM `ict1617sem1` ");
			$stmt = mysqli_query($conn,$qry);
			return $stmt;
		}
		public function userResults4($index,$tableName){
			$stmt = $this->con->prepare("SELECT * FROM `$tableName`");
			$stmt->execute();
			$stmt->bind_result();
			return $stmt->get_result()->fetch_assoc();
			//return $stmt->get_result()->fetch_assoc();
		}

		public function column(){
			//$qry=("SELECT * FROM `$table` WHERE index_no = $index");
			$qry=("SHOW COLUMNS FROM `$tableName`;");
			$stmt = mysqli_query($conn,$qry);
			return $stmt;
		}
		
		public function column2(){
			//$qry=("SELECT * FROM `$table` WHERE index_no = $index");
			$qry=("SELECT * FROM `ict1617sem1` WHERE index_no = 436");
			$stmt = mysqli_query($conn,$qry);
			return $stmt;
		}
		
		public function column3(){
			
			$qry=("SELECT column_name FROM `ict1617sem1`");
			$stmt = mysqli_query($conn,$qry);
			return $stmt;
		}
		
		public function userResultsRow($index,$tableName){
			$sqlQuery="select * from $tableName  ";
			//WHERE index_no = $index
			// using fetch_row
			$test=array();
			if ($result=$this->con->query($sqlQuery)){
				//while($row=$result->fetch_row()){
				while($row=$result->fetch_assoc()){
				//while($row=$result->fetch_array()){
					//print_r($row);
					//printf("%s (%s)\n",$row[0],$row[2]);
					//echo ($row[0].$row[2]) ;
					//echo json_encode($row);
					//$arr=array($row array($row));
					//$test=array_combine($test,$row);
					echo json_encode($row) ;
					//return $row;
				}
				echo json_encode($test) ;
				$result->free_result();
			}else
				return false;
		}
		
		
		
		
		public function userResultsObject($index,$tableName){
			$sqlQuery="select * from $tableName  WHERE index_no = $index";
			// using fetch_row
			
			if ($result=$this->con->query($sqlQuery)){
				//while($row=$result->fetch_row()){
				while($row=$result->fetch_assoc()){
				//while($row=$result->fetch_array()){
					//print_r($row);
					//printf("%s (%s)\n",$row[0],$row[2]);
					//echo ($row[0].$row[2]) ;
					//echo json_encode($row);
					//$arr=array($row);
					$m[]=$row;
					return $m;
					//echo json_encode($arr) ;
				}
				$result->free_result();
			}else
				return false;
		}
	}
	
	//select mid(reg_no, 4,4) as t_name from user;
	//select concat(dep mid(reg_no, 4,4)) as t_name from user;
	//select index_no, dep, concat(dep, mid(reg_no, 4,4)) as t_name from user;
	//describe user;
	//show columns from user;
?>