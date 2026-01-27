# Conference Management System - Proje Dokümantasyonu

## 📋 İçindekiler
1. [Proje Genel Bakış](#proje-genel-bakış)
2. [Teknoloji Stack](#teknoloji-stack)
3. [Veritabanı Yapısı](#veritabanı-yapısı)
4. [Güvenlik ve Authentication](#güvenlik-ve-authentication)
5. [API Endpoints](#api-endpoints)
6. [DTO Yapısı](#dto-yapısı)
7. [Servis Katmanı](#servis-katmanı)
8. [Pagination Yapısı](#pagination-yapısı)
9. [CORS Konfigürasyonu](#cors-konfigürasyonu)
10. [Yapılan Önemli Değişiklikler](#yapılan-önemli-değişiklikler)

---

## 🎯 Proje Genel Bakış

**Conference Management System**, akademik ve profesyonel konferansların yönetilmesi için geliştirilmiş full-stack bir web uygulamasıdır. Sistem, konferans organizatörlerinin etkinliklerini oluşturmasına, yönetmesine ve katılımcılarla etkileşim kurmasına olanak tanır.

### Temel Özellikler:
- ✅ JWT tabanlı kullanıcı kimlik doğrulama ve yetkilendirme
- ✅ Konferans oluşturma, düzenleme ve yönetme
- ✅ Etkinlik (Event) yönetimi
- ✅ Önemli tarihler (Important Dates) takibi
- ✅ Duyuru (Announcement) sistemi
- ✅ Komite (Committee) yönetimi
- ✅ Program yönetimi
- ✅ Sponsor yönetimi
- ✅ Görsel (Picture) yönetimi
- ✅ Kullanıcı profil yönetimi
- ✅ Pagination desteği
- ✅ CORS desteği ile frontend entegrasyonu

---

## 🛠 Teknoloji Stack

### Backend
- **Framework**: Spring Boot 3.5.7
- **Java Version**: 19
- **Build Tool**: Maven
- **Database**: MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Security**: Spring Security 6
- **Authentication**: JWT (JSON Web Token)
  - Library: JJWT 0.12.3
  - Token Süre: 86400000ms (24 saat)
- **Validation**: Spring Boot Validation
- **Template Engine**: Thymeleaf

### Dependencies
```xml
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-web
- spring-boot-starter-validation
- spring-boot-starter-thymeleaf
- mysql-connector-j
- lombok
- jjwt-api (0.12.3)
- jjwt-impl (0.12.3)
- jjwt-jackson (0.12.3)
```

---

## 🗄 Veritabanı Yapısı

### Entity Sınıfları

#### 1. **Person (Kullanıcı)**
```java
- id (Long, Primary Key)
- name (String, 100)
- surname (String, 100)
- email (String, 150, Unique)
- passwordHash (String, 255)
- title (String, 100)
```

#### 2. **Conference (Konferans)**
```java
- id (Long, Primary Key)
- conferenceName (String, 200)
- shortSubtitle (String, 255)
- startDate (LocalDate)
- endDate (LocalDate)
- description (TEXT)
- location (String, 255)
- logoPath (String, 255)
- coverPath (String, 255)
- owner (Person, ManyToOne)
- Footer Bilgileri:
  - footerOrganizationTitle
  - footerAddress
  - footerCityCountry
  - footerEmail
  - footerPhone
  - footerFacebookUrl
  - footerTwitterUrl
  - footerLinkedinUrl
  - footerInstagramUrl
  - footerYearText
```

#### 3. **Event (Etkinlik)**
```java
- id (Long, Primary Key)
- title (String, 200)
- description (TEXT)
- startTime (LocalDateTime)
- endTime (LocalDateTime)
- location (String, 255)
- conference (Conference, ManyToOne)
```

#### 4. **ImportantDate (Önemli Tarihler)**
```java
- id (Long, Primary Key)
- eventName (String, 200)
- eventDate (LocalDate)
- description (TEXT)
- conference (Conference, ManyToOne)
```

#### 5. **Announcement (Duyurular)**
```java
- id (Long, Primary Key)
- title (String, 200)
- content (TEXT)
- publishDate (LocalDateTime)
- conference (Conference, ManyToOne)
```

#### 6. **Committee (Komite)**
```java
- id (Long, Primary Key)
- committeeName (String, 150)
- role (String, 100)
- personName (String, 100)
- personSurname (String, 100)
- email (String, 150)
- affiliation (String, 200)
- conference (Conference, ManyToOne)
```

#### 7. **Program**
```java
- id (Long, Primary Key)
- day (String, 50)
- startTime (LocalTime)
- endTime (LocalTime)
- sessionTitle (String, 255)
- speaker (String, 200)
- location (String, 200)
- conference (Conference, ManyToOne)
```

#### 8. **Sponsor**
```java
- id (Long, Primary Key)
- sponsorName (String, 200)
- level (String, 50) - GOLD, SILVER, BRONZE
- logoUrl (String, 500)
- websiteUrl (String, 500)
- conference (Conference, ManyToOne)
```

#### 9. **Picture (Görseller)**
```java
- id (Long, Primary Key)
- imagePath (String, 500)
- caption (String, 255)
- uploadDate (LocalDateTime)
- conference (Conference, ManyToOne)
```

#### 10. **PersonConference**
```java
- id (Long, Primary Key)
- person (Person, ManyToOne)
- conference (Conference, ManyToOne)
- role (String, 50) - ADMIN, ORGANIZER, REVIEWER, ATTENDEE
- joinedDate (LocalDateTime)
```

---

## 🔐 Güvenlik ve Authentication

### JWT (JSON Web Token) Implementasyonu

#### JWT Konfigürasyonu
```properties
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
jwt.expiration=86400000 (24 saat)
```

#### Security Bileşenleri

**1. JwtService**
- Token oluşturma
- Token doğrulama
- Claims çıkarma
- Token süresi kontrolü

**2. JwtAuthenticationFilter**
- Her HTTP isteğinde JWT token kontrolü
- Authorization header'dan token çıkarma
- Token doğrulama ve kullanıcı kimliğini context'e ekleme

**3. PersonDetailsService**
- UserDetailsService implementasyonu
- Email ile kullanıcı yükleme
- Spring Security entegrasyonu

**4. SecurityConfig**
- Security filter chain konfigürasyonu
- CORS ayarları
- Public ve protected endpoint tanımları
- Password encoder (BCrypt)

#### Korunan ve Açık Endpointler

**Public Endpoints (Kimlik doğrulama gerektirmez):**
```
POST /api/auth/register
POST /api/auth/login
```

**Protected Endpoints (JWT token gerektirir):**
```
GET  /api/auth/profile
PUT  /api/auth/profile
PUT  /api/auth/update-password
Tüm diğer /api/** endpointleri
```

### Authentication Flow

1. **Kayıt (Register)**
   - Kullanıcı bilgileri alınır
   - Şifre BCrypt ile hashlenir
   - Veritabanına kaydedilir
   - JWT token oluşturulur ve döndürülür

2. **Giriş (Login)**
   - Email ve şifre doğrulanır
   - Başarılıysa JWT token oluşturulur
   - Token ve kullanıcı bilgileri response'da döndürülür

3. **Profil İşlemleri**
   - JWT token'dan email çıkarılır
   - Kullanıcı bilgileri getirilir/güncellenir
   - Şifre güncellemesi ayrı endpoint ile yapılır

---

## 🌐 API Endpoints

### 1. Authentication Endpoints

#### POST /api/auth/register
**Yeni kullanıcı kaydı**
```json
Request:
{
  "name": "John",
  "surname": "Doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "title": "Prof. Dr."
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "john@example.com",
  "name": "John",
  "surname": "Doe"
}
```

#### POST /api/auth/login
**Kullanıcı girişi**
```json
Request:
{
  "email": "john@example.com",
  "password": "securePassword123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "john@example.com",
  "name": "John",
  "surname": "Doe"
}
```

#### GET /api/auth/profile
**Profil bilgilerini getir**
```json
Headers:
Authorization: Bearer {token}

Response:
{
  "id": 1,
  "name": "John",
  "surname": "Doe",
  "email": "john@example.com",
  "title": "Prof. Dr."
}
```

#### PUT /api/auth/profile
**Profil güncelleme**
```json
Headers:
Authorization: Bearer {token}

Request:
{
  "name": "John",
  "surname": "Doe",
  "title": "Prof. Dr."
}

Response:
{
  "id": 1,
  "name": "John",
  "surname": "Doe",
  "email": "john@example.com",
  "title": "Prof. Dr."
}
```

#### PUT /api/auth/update-password
**Şifre güncelleme**
```json
Headers:
Authorization: Bearer {token}

Request:
{
  "currentPassword": "oldPassword123",
  "newPassword": "newSecurePassword456"
}

Response:
{
  "message": "Password updated successfully"
}
```

### 2. Conference Endpoints

#### GET /api/conferences?page=0&size=10
**Tüm konferansları listele (Paginated)**

#### GET /api/conferences/{id}
**Belirli bir konferansı getir**

#### POST /api/conferences
**Yeni konferans oluştur**
```json
Request:
{
  "conferenceName": "International AI Conference 2025",
  "shortSubtitle": "Advancing AI Research",
  "startDate": "2025-06-15",
  "endDate": "2025-06-17",
  "description": "A premier conference on AI",
  "location": "Istanbul, Turkey",
  "logoPath": "/logos/ai-conf-2025.png",
  "coverPath": "/covers/ai-conf-cover.jpg",
  "footerOrganizationTitle": "AI Research Foundation",
  "footerAddress": "Tech Street 123",
  "footerCityCountry": "Istanbul, Turkey",
  "footerEmail": "info@aiconf2025.com",
  "footerPhone": "+90 212 123 4567",
  "footerFacebookUrl": "https://facebook.com/aiconf",
  "footerTwitterUrl": "https://twitter.com/aiconf",
  "footerLinkedinUrl": "https://linkedin.com/company/aiconf",
  "footerInstagramUrl": "https://instagram.com/aiconf",
  "footerYearText": "© 2025 AI Conference"
}
```

#### PUT /api/conferences/{id}
**Konferans güncelle**

#### DELETE /api/conferences/{id}
**Konferans sil**

### 3. Event Endpoints

#### GET /api/events?page=0&size=10
**Tüm etkinlikleri listele**

#### GET /api/conferences/{conferenceId}/events?page=0&size=10
**Belirli konferansa ait etkinlikleri listele**

#### POST /api/events
**Yeni etkinlik oluştur**
```json
{
  "title": "Keynote Speech",
  "description": "Opening keynote by Dr. Smith",
  "startTime": "2025-06-15T09:00:00",
  "endTime": "2025-06-15T10:30:00",
  "location": "Main Hall",
  "conferenceId": 1
}
```

#### PUT /api/events/{id}
**Etkinlik güncelle**

#### DELETE /api/events/{id}
**Etkinlik sil**

### 4. Important Date Endpoints

#### GET /api/important-dates?page=0&size=10
**Tüm önemli tarihleri listele**

#### GET /api/conferences/{conferenceId}/important-dates?page=0&size=10
**Konferansa ait önemli tarihleri listele**

#### POST /api/important-dates
**Yeni önemli tarih ekle**

#### PUT /api/important-dates/{id}
**Önemli tarih güncelle**

#### DELETE /api/important-dates/{id}
**Önemli tarih sil**

### 5. Announcement Endpoints

#### GET /api/announcements?page=0&size=10
**Tüm duyuruları listele**

#### GET /api/conferences/{conferenceId}/announcements?page=0&size=10
**Konferansa ait duyuruları listele**

#### POST /api/announcements
**Yeni duyuru oluştur**

#### PUT /api/announcements/{id}
**Duyuru güncelle**

#### DELETE /api/announcements/{id}
**Duyuru sil**

### 6. Committee Endpoints

#### GET /api/committees?page=0&size=10
**Tüm komiteleri listele**

#### GET /api/conferences/{conferenceId}/committees?page=0&size=10
**Konferansa ait komiteleri listele**

#### POST /api/committees
**Yeni komite üyesi ekle**

#### PUT /api/committees/{id}
**Komite üyesi güncelle**

#### DELETE /api/committees/{id}
**Komite üyesi sil**

### 7. Program, Sponsor, Picture, PersonConference Endpoints
Benzer CRUD ve pagination yapısına sahip

---

## 📦 DTO Yapısı

### Request DTOs
1. **CreateConferenceRequest** - Konferans oluşturma
2. **CreateEventRequest** - Etkinlik oluşturma
3. **CreateImportantDateRequest** - Önemli tarih ekleme
4. **CreateAnnouncementRequest** - Duyuru oluşturma
5. **CreateCommitteeRequest** - Komite üyesi ekleme
6. **CreateProgramRequest** - Program ekleme
7. **CreatePersonRequest** - Kullanıcı oluşturma
8. **CreatePictureRequest** - Görsel ekleme
9. **CreatePersonConferenceRequest** - Kullanıcı-konferans ilişkisi
10. **RegisterRequest** - Kayıt
11. **LoginRequest** - Giriş
12. **UpdateProfileRequest** - Profil güncelleme
13. **UpdatePasswordRequest** - Şifre güncelleme

### Response DTOs
1. **ConferenceDto** - Konferans bilgileri
2. **EventDto** - Etkinlik bilgileri
3. **ImportantDateDto** - Önemli tarih bilgileri
4. **AnnouncementDto** - Duyuru bilgileri
5. **CommitteeDto** - Komite bilgileri
6. **ProgramDto** - Program bilgileri
7. **PersonDto** - Kullanıcı bilgileri
8. **PictureDto** - Görsel bilgileri
9. **PersonConferenceDto** - İlişki bilgileri
10. **SponsorDto** - Sponsor bilgileri
11. **AuthResponse** - Authentication yanıtı
12. **ProfileResponse** - Profil bilgileri

---

## 🔄 Servis Katmanı

### Interface ve Implementation Yapısı

Her entity için:
- **Interface** (Service Layer)
- **Implementation** (ServiceImpl)

**Örnek: ConferenceService**
```java
Interface:
- Page<ConferenceDto> getAllConferences(int page, int size)
- ConferenceDto getConferenceById(Long id)
- ConferenceDto createConference(CreateConferenceRequest request)
- ConferenceDto updateConference(Long id, CreateConferenceRequest request)
- void deleteConference(Long id)

Implementation:
- Repository injection
- Entity ↔ DTO dönüşümü (manuel mapping)
- Business logic
- Exception handling
```

### Tüm Service Sınıfları
1. **ConferenceService / ConferenceServiceImpl**
2. **EventService / EventServiceImpl**
3. **ImportantDateService / ImportantDateServiceImpl**
4. **AnnouncementService / AnnouncementServiceImpl**
5. **CommitteeService / CommitteeServiceImpl**
6. **ProgramService / ProgramServiceImpl**
7. **SponsorService / SponsorServiceImpl**
8. **PersonService / PersonServiceImpl**
9. **PictureService / PictureServiceImpl**
10. **PersonConferenceService / PersonConferenceServiceImpl**
11. **AuthService** (Authentication işlemleri)

---

## 📄 Pagination Yapısı

### Spring Data JPA Pagination

Tüm servislerde pagination desteği eklendi:

```java
// Repository
Page<Entity> findAll(Pageable pageable);
Page<Entity> findByConferenceId(Long conferenceId, Pageable pageable);

// Service
Page<EntityDto> getAll(int page, int size);
Page<EntityDto> getByConferenceId(Long conferenceId, int page, int size);

// Controller
@GetMapping
public ResponseEntity<Page<EntityDto>> getAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
)
```

### Pagination Response Yapısı
```json
{
  "content": [...],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalPages": 5,
  "totalElements": 48,
  "last": false,
  "first": true,
  "numberOfElements": 10
}
```

---

## 🌍 CORS Konfigürasyonu

### Frontend Entegrasyonu

**Base URL**: `http://localhost:5173`

**CORS Ayarları:**
```java
@CrossOrigin(
    origins = {"http://localhost:5173", "http://localhost:3000"}, 
    allowCredentials = "true"
)
```

**SecurityConfig'de Global CORS:**
```java
.cors(cors -> cors.configurationSource(request -> {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList(
        "http://localhost:5173", 
        "http://localhost:3000"
    ));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(Arrays.asList("*"));
    config.setAllowCredentials(true);
    return config;
}))
```

---

## 🔧 Yapılan Önemli Değişiklikler

### 1. **JWT Authentication Sistemi Eklendi**
- JwtService oluşturuldu
- JwtAuthenticationFilter implementasyonu
- PersonDetailsService ile Spring Security entegrasyonu
- Token tabanlı kimlik doğrulama

### 2. **Pagination Desteği Eklendi**
- Tüm repositorylerde Page<Entity> döndüren metodlar
- Service katmanında pagination parametreleri
- Controller'larda page ve size parametreleri
- List döndüren metodlar kaldırıldı

### 3. **DTO Katmanı Genişletildi**
- Create ve Read işlemleri için ayrı DTOlar
- ProfileResponse ve AuthResponse eklendi
- UpdateProfileRequest ve UpdatePasswordRequest eklendi

### 4. **Auth Controller ve Service Eklendi**
- Register endpoint
- Login endpoint
- Profile endpoint (GET)
- Profile update endpoint (PUT)
- Password update endpoint (PUT)

### 5. **CORS Konfigürasyonu**
- Frontend URL'leri whitelist'e eklendi
- Credentials desteği
- Tüm HTTP metodları için izin

### 6. **Security Config Güncellemeleri**
- Public ve protected endpoint ayrımı
- JWT filter chain'e eklendi
- BCrypt password encoder

### 7. **Repository Değişiklikleri**
- findAll() → findAll(Pageable pageable)
- Conference bazlı filtreleme için pagination desteği
- Gereksiz List döndüren metodlar kaldırıldı

### 8. **Entity İyileştirmeleri**
- Conference nullable alanlar güncellendi
- Footer bilgileri eklendi
- İlişkiler (ManyToOne, OneToMany) düzenlendi

### 9. **Exception Handling**
- RuntimeException ile hata yönetimi
- Try-catch blokları
- Anlamlı hata mesajları

### 10. **Database Configuration**
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
MySQL8Dialect kullanımı
```

---

## 📊 Veritabanı Bağlantısı

```properties
URL: jdbc:mysql://localhost:3306/conference_management_system
Username: root
Password: Mihra2021.
Driver: com.mysql.cj.jdbc.Driver
```

---

## 🎯 Postman Test Collection

Projede iki Postman dosyası mevcut:
1. `Conference_Management_Complete.postman_collection.json`
2. `Conference_Management_Local.postman_environment.json`

---

## 📝 Notlar

### Token Süresi
JWT token süresi **24 saat** (86400000 ms) olarak ayarlanmıştır.

### Şifre Güvenliği
Tüm şifreler BCrypt algoritması ile hashlenerek saklanmaktadır.

### Manuel Mapping
Projede entity ↔ DTO dönüşümü için MapStruct veya ModelMapper kullanılmamış, manuel mapping tercih edilmiştir.

### Dosya Yapısı
- **controllers**: REST API endpoints
- **services**: Business logic (interface)
- **services/impl**: Business logic implementation
- **repositories**: Database access layer
- **domain**: Entity classes
- **models/dto**: Data Transfer Objects
- **security**: JWT ve authentication
- **config**: Configuration classes

---

## 🚀 Projeyi Çalıştırma

1. MySQL veritabanını başlat
2. Database'i oluştur: `conference_management_system`
3. `application.properties` dosyasını kontrol et
4. Maven dependency'leri indir: `mvn clean install`
5. Uygulamayı başlat: `mvn spring-boot:run`
6. API: `http://localhost:8080`
7. Frontend: `http://localhost:5173`

---

**Son Güncelleme**: 2026 M01 6
**Versiyon**: 0.0.1-SNAPSHOT
**Java**: 19
**Spring Boot**: 3.5.7

