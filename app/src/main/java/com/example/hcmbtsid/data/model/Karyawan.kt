package com.example.hcmbtsid.data.model

data class Karyawan(
    val nip: Int,
    var nama: String,
    var email:String,
    val tempat_lahir: String,
    val tanggal_lahir: String,
    val alamat: String,
    val npwp: String,
    val bpjs_tenagakerja:String,
    val bpj_kesehatan:String,
    val create_at: String,
    val update_at: String,
    val delete: String,
    val foto: String,
    val id_divisi:Int,
    val id_departement: Int,
    val id_jabatan: Int,
    val alamat_kerja: String,
    val telp_kerja: Long
){

}