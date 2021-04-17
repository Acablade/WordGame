# WordGame

VERSION: 1.16.4
(didnt test other versions but will most likely work because i havent used any packets)


Word Game for Minecraft (currently only supports Turkish and English)

Game where you try to find a new word that starts with the last character of the last word, for example: tire->elephant->tail->last...


**ITS STILL UNDER DEVELOPMENT AND NOT YET RELEASED**


**DOES NOT SUPPORT PLACEHOLDER API YET**

TODO:
- [ ] PlaceholderAPI support

FEATURES:

- Word checking via API of Dictionary sites
- Permissions
- Commands
- API
- Config

LANGUAGES:
- Turkish
- English

**How does the word recognition work?**
It takes the word written in chat and sends a GET request to a dictionary api and returns the value in boolean

**Example file isnt included in project build**

Discord: DaOz#4795 if you want to talk to me about this project.

## Maven Repository

**Repository**:
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

**Dependency (type latest version in for version)**
```
<dependency>
    <groupId>com.github.Acablade</groupId>
    <artifactId>WordGame</artifactId>
    <version></version>
</dependency>
```
[![](https://jitpack.io/v/Acablade/WordGame.svg)](https://jitpack.io/#Acablade/WordGame)

See [JitPack](https://jitpack.io/#Acablade/WordGame)