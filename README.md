# Metadata Change Detector

## Why I built this

I came across AutoRABIT and learned that they help companies avoid deployment disasters by comparing metadata before it goes live. That got me thinking – what if I build a mini version of that idea?

So here it is. A small Java tool that compares two metadata files (in JSON format) and tells you exactly what changed.

## What it does

Give it an old metadata file and a new one. It will show you:

- ➕ Fields that were **added**
- ➖ Fields that were **removed**
- 🔄 Fields whose **type changed** (e.g., Text → Number)
- 📦 Objects that were added or removed

Nothing fancy. Just a clear report.

## Sample Output
--- FIELD CHANGES ---
➕ ADDED field: Age (type: Number)
➖ REMOVED field: Email (was type: Email)

--- OBJECT CHANGES ---
➕ ADDED object: Account


## What I used

- **Java 17** – core logic
- **org.json** – to parse JSON files
- **Git** – version control

## How to run

1. Make sure Java 17+ is installed (`java -version`)
2. Clone this repository
3. Download `json-20210307.jar` (already included) or get it from [Maven Central](https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar)
4. Compile:
   ```bash
   javac -cp ".;json-20210307.jar" MetadataChangeDetector.java
   

