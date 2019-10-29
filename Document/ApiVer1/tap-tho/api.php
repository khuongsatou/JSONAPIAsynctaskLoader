<?php
include('tap-tho.php');

$ket_qua = [
	"success"	=> true,
	"tap_tho"	=> $tap_tho
];

if(isset($_GET['id'])) {
	$id = $_GET['id'];
	$ket_qua = [
		"success"	=> true,
		"bai_tho"	=> $tap_tho[$id]
	];
}
echo "<pre>";
print_r(json_encode($ket_qua));
echo "</pre>";