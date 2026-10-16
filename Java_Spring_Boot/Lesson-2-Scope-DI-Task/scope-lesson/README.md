--- Task-1: Singleton ---

Yeni user yaradildi : <br>

<img width="1152" height="537" alt="create-user" src="https://github.com/user-attachments/assets/fda04e4b-f4c1-41b6-95c9-9d80418b872c" />


Yaradilan userin id-si get olundu, eyni user qaytarildi : <br>

<img width="1160" height="517" alt="get-user" src="https://github.com/user-attachments/assets/d0af288f-a4f0-4fe7-8555-9388cb69cb9f" />


Butun userleri görmek ücün sorğu atildi, yeni user elave olunub: <br>

<img width="1162" height="892" alt="all-user" src="https://github.com/user-attachments/assets/03262de3-1105-451f-b75f-931a3356bbc8" />


10 defeden çox sorğu gönderildi ve ".. yaradildi" logları yalnız 1 defe çıxdı : <br>

<img width="1162" height="545" alt="topic-1-log" src="https://github.com/user-attachments/assets/78d33ab6-aa19-4a3e-9ab8-1f8366caa817" />

Tapşırıqdaki 8 ci sualın cavabı : <br>

1. Adi HashMap və long counter burda niyə təhlükəlidir?
 Çünki thread-safe deyillər. 
 Eger eyni anda bir neçe istifadeçi sorğu atıb yeni user yaratmaga çalışsa,
 fərqli threadlər eyni anda map-ə data yazmağa və counteri artırmağa çalışacaq.
 Race condition yaranacaq və datalar üst-üstə yazılacaq.
 
2. Singleton bean-i eyni anda neçə thread istifadə edir?
  Eger eyni anda 100 istifadəçi sorğu göndererse 
  1 Singleton obyekt eyni anda 100 fərqli thread tərəfindən istifadə edilə bilər.
  Məsələn proyektdə istifadə etdiyimiz User Repository.

===================================================================================

--- Task-2: Prototype ---

11-ci sualın nəticəsi:

Səhv variant (Hər dəfə eyni obyekt create olur) <br>

<img width="967" height="567" alt="wrong-user-create" src="https://github.com/user-attachments/assets/1dc3adbc-2df7-409c-aa1b-77c9a5fb601e" />

=====

12-ci sualın nəticəsi:

Düzgün variant (ObjectProvider ilə hər dəfə yeni obyekt yaranır) <br>

<img width="867" height="492" alt="correct-user-create" src="https://github.com/user-attachments/assets/fa49242f-2ee1-4d6e-a4a0-1132769a43ab" />

===== <br>

13-cü sualın nəticəsi:

Prototype bean avtomatik predestroy olunmur. Çünki əgər Spring Container yaradılan hər bir prototype bean-i
destroy etmək üçün izləsəydi, yəni reference saxlasaydı,həmin bean-lər proqram işlədiyi müddətcə yaddaşdan silinməyəcəkdi.
GC onları təmizləməyəcəkdi və OutofMemory error olacaqdı. <br>

<img width="911" height="690" alt="bean-destroy" src="https://github.com/user-attachments/assets/a88de255-86f9-4e87-a49e-51b6357d7f6a" />

===================================================================================

--- Task-3: Request ---

18-ci sualın nəticəsi :

@Scope("request") annotation yazanda ScopeNotActiveException alırıq. 
Çünki proyekt qalxanda Spring Singleton bean-ləri (Controller, Service) yaradır, 
amma o anda heç bir aktiv HTTP sorgusu olmadigi ucun Request scope Bean-i yarada bilmir.
<br>

<img width="1257" height="1007" alt="error-request-scope" src="https://github.com/user-attachments/assets/5524d56c-d948-46ab-8af8-f720ca5c46a5" />

==

Xətanın düzeldilmiş halı : <br>

<img width="1292" height="1050" alt="correct-reuqest-mode" src="https://github.com/user-attachments/assets/904f2ebb-88a9-4988-8e34-0f985c36485f" />

===========

19-cu sualın nəticəsi: <br>

Class adı olaraq RequestContext$$SpringCGLIB$$0 çıxır.

<img width="1290" height="885" alt="class-name" src="https://github.com/user-attachments/assets/258c92cd-d6b3-4285-8b45-7a1e38ff08b9" />

<br>
Singleton-a request bean necə inject oluna bilir?

Çünki Spring bizim Singleton obyektlərimizin içinə əsl RequestContext obyektini yox, onun Proxy versiyasını inject edir.
Ne vaxtki kenardan bir HTTP sorğusu gelir ve biz hemin proxy-nin metodunu çağırırıq, 
proxy arxa planda o anki HTTP sorğusuna aid olan esl obyekti tapıb işi ona yönlendirir.


===================================================================================

--- Task-4: Session ---

24-cü sualın nəticəsi : 

Chrome-da id requestlər göndərdikdə recentdə görünür və userlərin tam məlumatı gəlir.
Amma Postman-də recent request göndərdikdə boş gəlir. 
Çünki Brauzerdə JSESSIONID adında cookie var və sessiya orda saxlanır. İncognito windowda yeni istifadəçi statusu yarandığı üçün sessiya boş qayıdır.
Postman-də isə istəsək settingsdən cookie-ni aktivləşdirə bilərik.
<br>
Chrome : <br>
<img width="815" height="611" alt="recent-success" src="https://github.com/user-attachments/assets/b3e4cb5b-d7d8-4bfd-89ac-c0485315be1b" />

=== <br>
Postman : <br>

<img width="1256" height="672" alt="recent-empty" src="https://github.com/user-attachments/assets/1ef469c6-5ffe-4730-8726-db545c89f49e" />

====== <br>

25-ci sualın cavabı:

Bu siyahını request scope-da saxlasaydıq nə olardı?

Əgər Request scope istifadə etsəydik recent məntiqi işləməyəcəkdi.Çünki biz 1-ci istifadəçiyə baxanda siyahı yaranacaqdı, içinə "1"i qoyub silinəcəkdi. Sonra /recent linkinə girəndə, həmin URL yeni bir HTTP sorğusu olduğu üçün təzə boş bir obyekt yaranacaqdı.





