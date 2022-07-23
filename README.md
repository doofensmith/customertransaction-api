# API Customer Transaction
##### By : Wahyu Wijanarko

### Installation Guide :
1. **Buat database pada MySQL :**  
  SQL : ```create database db_custtransaction;```
2. **Konfigurasi file** [application.properties](./src/main/resources/application.properties)  
  Ubah field berikut sesuai username dan password pada MySQL yang terinstall:  
   ``spring.datasource.username=[username] default root``  
   ``spring.datasource.password=[password] default empty``
3. **Jalankan Springboot :**  
  Command : ``mvn spring-boot:run``
4. **Gunakan Postman atau Swagger untuk menggunakan API.**  
:warning:**(Pastikan Springboot telah berjalan di local)**  
[Swagger UI](http://localhost:8080/swagger-ui.html#/)
  

### Testing API :  
1. **Menambahkan data akun :**  
- **Akun 1**  
![img.png](screenshots/img.png)  
- **Akun 2**  
![img2.png](screenshots/img2.png)  
2. **Menampilkan seluruh data akun :**  
![img3.png](screenshots/img3.png)  
3. **Input data transaksi :**  
- **Transaksi 1**  
![img4.png](screenshots/img4.png)  
- **Transaksi 2**  
![img5.png](screenshots/img5.png)  
- **Transaksi 3**  
![img6.png](screenshots/img6.png)  
4. **Menampilkan jumlah poin tiap akun :**  
![img7.png](screenshots/img7.png)  
5. **Cetak report transaksi :**  
- **Report transaksi account_id 1 :**  
![img8.png](screenshots/img8.png)  
- **Report transaksi account_id 2 :**  
![img9.png](screenshots/img9.png)  