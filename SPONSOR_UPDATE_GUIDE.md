# Sponsor - Konferans İlişkisi Güncellemesi

## Yapılan Değişiklikler

### 1. Backend Değişiklikleri

#### ✅ Sponsor Entity (Sponsor.java)
- `Conference` ilişkisi eklendi (ManyToOne)
- Her sponsor artık bir konferansa bağlı

#### ✅ CreateConferenceRequest (CreateConferenceRequest.java)
- `List<SponsorDto> sponsors` alanı eklendi
- Artık konferans oluştururken sponsorlar da gönderebilirsiniz

#### ✅ ConferenceServiceImpl (ConferenceServiceImpl.java)
- **create()**: Konferans oluştururken sponsorları da kaydeder
- **update()**: Konferans güncellerken sponsorları da günceller
- **delete()**: Konferans silinirken sponsorlar da silinir

#### ✅ SponsorServiceImpl (SponsorServiceImpl.java)
- Debug log'ları eklendi
- `conferenceId` kontrolü yapılıyor
- `getByConferenceId()` metodu ile konferansa ait sponsorlar çekilebilir

## Frontend'den Kullanım

### Yeni Konferans Oluştururken

```javascript
const conferenceData = {
  conferenceName: "Modern Konferans",
  shortSubtitle: "2024 Bilim",
  description: "...",
  // ...diğer alanlar
  sponsors: [
    {
      name: "Finansal Sponsor 1",
      type: "Financial",
      logoUrl: "https://example.com/logo1.png"
    },
    {
      name: "Eğitim Sponsoru",
      type: "Educational",
      logoUrl: "https://example.com/logo2.png"
    }
  ]
};

// POST /api/conferences
fetch('http://localhost:8080/api/conferences', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + token
  },
  body: JSON.stringify(conferenceData)
});
```

### Konferans Güncellerken

```javascript
const updateData = {
  conferenceName: "Güncellenmiş İsim",
  sponsors: [
    {
      name: "Yeni Sponsor",
      type: "General",
      logoUrl: "https://example.com/new-logo.png"
    }
  ]
};

// PUT /api/conferences/{id}
fetch(`http://localhost:8080/api/conferences/${conferenceId}`, {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + token
  },
  body: JSON.stringify(updateData)
});
```

### Konferansa Ait Sponsorları Çekme

```javascript
// GET /api/sponsors/conference/{conferenceId}
fetch(`http://localhost:8080/api/sponsors/conference/${conferenceId}`)
  .then(res => res.json())
  .then(sponsors => {
    console.log('Sponsorlar:', sponsors);
    // Artık localStorage'a gerek yok!
  });
```

### Tek Sponsor Oluşturma (Manuel)

```javascript
const sponsorData = {
  name: "Sponsor Adı",
  type: "Financial", // Financial, Educational, General
  logoUrl: "https://example.com/logo.png",
  conferenceId: 1 // ÖNEMLİ: conferenceId mutlaka gönderin!
};

// POST /api/sponsors
fetch('http://localhost:8080/api/sponsors', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(sponsorData)
});
```

## Mevcut Veritabanı Düzeltme

Eğer veritabanınızda zaten `conference_id = NULL` olan sponsorlar varsa:

### 1. MySQL/MariaDB ile Düzeltme

```sql
-- Önce konferans ID'nizi bulun
SELECT conference_id, conference_name FROM conference;

-- Tüm NULL sponsorları bir konferansa bağlayın (örnek: conference_id = 1)
UPDATE sponsor 
SET conference_id = 1 
WHERE conference_id IS NULL;

-- Kontrol edin
SELECT id, name, type, conference_id FROM sponsor;
```

### 2. Backend API ile Düzeltme

Her sponsor için PUT isteği gönderin:

```javascript
// Her NULL conference_id'li sponsoru güncelleme
const updateSponsor = async (sponsorId, conferenceId) => {
  await fetch(`http://localhost:8080/api/sponsors/${sponsorId}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      conferenceId: conferenceId
    })
  });
};

// Örnek kullanım
updateSponsor(38, 1); // Sponsor ID 38'i Konferans ID 1'e bağla
```

## Sorun Giderme

### "Conference not found" Hatası
- `conferenceId`'nin doğru olduğundan emin olun
- Konferansın veritabanında mevcut olduğunu kontrol edin

### conference_id Hala NULL Geliyorsa
1. Backend log'larını kontrol edin (console'da yazdırılan mesajlar)
2. Frontend'den `conferenceId` gönderildiğinden emin olun
3. SponsorDto'da `conferenceId` alanının olduğunu kontrol edin

### Sponsor Oluşturulmuyor
- Browser console'da network sekmesini açın
- POST isteğinin body'sinde `conferenceId` olduğunu kontrol edin
- Backend console'da log'ları takip edin

## Test Etme

1. Yeni bir konferans oluşturun (sponsors dizisi ile)
2. Backend console'da log'ları kontrol edin
3. Veritabanında `SELECT * FROM sponsor` çalıştırın
4. `conference_id` sütununda artık NULL olmamalı

## Önemli Notlar

⚠️ **Konferans Silme**: Konferans silindiğinde, ona bağlı tüm sponsorlar da otomatik silinir.

✅ **Null Safety**: Update işlemlerinde null kontrolü yapılıyor, mevcut veriler korunuyor.

✅ **Güvenlik**: Sadece konferans sahibi konferansı ve sponsorlarını güncelleyebilir.

✅ **Debug**: Backend console'da sponsor işlemleri için log mesajları görünür.

