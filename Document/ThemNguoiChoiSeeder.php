<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Str;

class ThemNguoiChoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $count = 1;
        while($count < 1001) {
			echo "Them nguoi choi thu " . $count . "\n";
        	$tenDangNhap = Str::random(8);
        	App\NguoiChoi::create([
        		'ten_dang_nhap' => $tenDangNhap,
        		'mat_khau'		=> Hash::make(Str::random(6)),
        		'email'			=> $tenDangNhap . '@gmail.com',
        		'hinh_dai_dien'	=> $tenDangNhap . '.jpg',
        		'diem_cao_nhat'	=> rand(1000, 5000),
        		'credit'		=> rand(10, 500)
        	]);
        	$count++;
        }
    }
}
