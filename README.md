# eyo2

**eyo2**, Windows'ta mikrofon sesini gerçek zamanlı işlemek için geliştirilen uygulamadır. Görünen uygulama adı eyo2'dir; GitHub deposunun adresi şimdilik `Rynie067/eya2` olarak kalır.

## Hedef özellikler

- Mikrofon sesini yükseltme (gain).
- Sesi kalınlaştırma (pitch shift).
- Bass / sıcaklık kontrolü.
- Kompresör ve gürültü azaltma.
- Gerçek zamanlı, düşük gecikmeli çalışma.
- Discord'un doğrudan gerçek mikrofon aygıtından işlenmiş sesi alması.
- Ses işleme yerel bilgisayarda; buluta ses gönderilmez.
- Kullanıcıya VB-CABLE, VoiceMeeter veya başka bir sanal ses kablosu kurdurmamak.

## Mimari

Python tarafı ayrı modüllere bölünecek:

```text
eyo2/
  main.py
  ui/
  audio/
    devices.py
    engine.py
    effects.py
    noise_reduction.py
  settings/
    config.py
  windows/
    apo_bridge.py
native/
  eyo2_apo/
installer/
```

Python arayüz, ayarlar, aygıt algılama ve DSP kontrolünü üstlenir. Windows mikrofon girişine Discord'dan önce efekt uygulamak için gereken yerel ses bileşeni ayrı bir Windows APO olarak geliştirilecektir. Bu bileşen, VB-CABLE gibi ikinci bir sanal kablo uygulaması değildir; eyo2'nin kendi Windows ses entegrasyonudur.

## Önemli teknik sınır

Sadece Python ses yakalama/oynatma kütüphaneleri, Windows'a yeni mikrofon aygıtı ekleyemez ve Discord'un seçtiği gerçek mikrofonun ses zincirine kendiliğinden müdahale edemez. Bunun için Windows Audio Processing Object (APO) bileşeni gerekir. Microsoft APO'ları Windows ses motorunda çalışan COM DLL bileşenleri olarak tanımlar; gerçek zamanlı işlem arayüzü ve Windows tarafından tanınan kurulum/kayıt yapısı gerekir.

Bu nedenle yalnızca arayüzün açılması veya mikrofonun hoparlöre aktarılması, Discord entegrasyonunun tamamlandığı anlamına gelmez. Discord'a gerçek entegrasyon doğrulanmadan sürüm "hazır" olarak işaretlenmeyecektir.

## Derleme ve yayın

Hedef, Python tarafı ve eyo2'nin kendi Windows ses bileşenini tek bir Windows x64 kurulum dosyasında paketlemektir. Sürücü/DLL imzalama, Windows güvenlik onayları ve gerçek mikrofon/Discord testi tamamlanmadan indirilebilir kurulum yayımlanmış sayılmaz.

## Gizlilik

Mikrofon verisi yerel olarak işlenecek; kayıt dosyası oluşturulmayacak ve ses herhangi bir sunucuya gönderilmeyecektir.
