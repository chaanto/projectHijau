package com.example.crudapp;

public class konfigurasi {
    public static final String URL_ADD="http://192.168.1.8/Android/pegawai/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.1.8/Android/pegawai/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.1.8/Android/pegawai/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.8/Android/pegawai/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.8/Android/pegawai/hapusPgw.php?id=";
    public static final String URL_GET_JABATAN = "http://192.168.1.8/Android/posisi/posisi_list.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJIH = "salary";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
