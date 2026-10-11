--- Task-1: Singleton ---

Yeni user yaradildi :

<img width="1152" height="537" alt="create-user" src="https://github.com/user-attachments/assets/fda04e4b-f4c1-41b6-95c9-9d80418b872c" />


Yaradilan userin id-si get olundu, eyni user qaytarildi :

<img width="1160" height="517" alt="get-user" src="https://github.com/user-attachments/assets/d0af288f-a4f0-4fe7-8555-9388cb69cb9f" />


Butun userleri görmek ücün sorğu atildi, yeni user elave olunub:

<img width="1162" height="892" alt="all-user" src="https://github.com/user-attachments/assets/03262de3-1105-451f-b75f-931a3356bbc8" />


10 defeden çox sorğu gönderildi ve ".. yaradildi" logları yalnız 1 defe çıxdı :

<img width="1162" height="545" alt="topic-1-log" src="https://github.com/user-attachments/assets/78d33ab6-aa19-4a3e-9ab8-1f8366caa817" />

Tapşırıqdaki 8 ci sualın cavabı :

1. Adi HashMap və long counter burda niyə təhlükəlidir?
 Çünki thread-safe deyillər. 
 Eger eyni anda bir neçe istifadeçi sorğu atıb yeni user yaratmaga çalışsa,
 fərqli threadlər eyni anda map-ə data yazmağa və counteri artırmağa çalışacaq.
 Race condition yaranacaq və datalar üst-üstə yazılacaq.
 
2. Singleton bean-i eyni anda neçə thread istifadə edir?
  Eger eyni anda 100 istifadəçi sorğu göndererse 
  1 Singleton obyekt eyni anda 100 fərqli thread tərəfindən istifadə edilə bilər.
  Məsələn proyektdə istifadə etdiyimiz User Repository.


 
