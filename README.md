# JpNHolidayK [![CI](https://github.com/fumiya-kume/JpNHolidayK/actions/workflows/ci.yml/badge.svg)](https://github.com/fumiya-kume/JpNHolidayK/actions/workflows/ci.yml) 

# Version information

|lib|version|
|---|---|
|JpNHolidayK|![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fservice%2Flocal%2Frepo_groups%2Fpublic%2Fcontent%2Fsystems%2Fkuu%2FJpNHolidayK%2Fmaven-metadata.xml)|
|JpNHolidayK-kotlinx-datetime|![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fservice%2Flocal%2Frepo_groups%2Fpublic%2Fcontent%2Fsystems%2Fkuu%2FJpNHolidayK-kotlinx-datetime%2Fmaven-metadata.xml)|

# How to Install

## Gradle

```groove
implementation 'systems.kuu:JpNHolidayK:1.0.1'
implementation 'systems.kuu:JpNHolidayK-kotlinx-datetime:1.0.0'
```

## Gradle(Kotlin)

```kt
implementation("systems.kuu:JpNHolidayK:1.0.1")
implementation("systems.kuu:JpNHolidayK-kotlinx-datetime:1.0.0")
```

# Usages

```kt
JpNHoliday().getNationalHolidayName(1955, 1, 1)
```
