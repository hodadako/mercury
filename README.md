# Mercury

_also available in :_ [한국어](./docs/ko/README.md) / [日本語](./docs/ja/README.md)

[![codecov](https://codecov.io/gh/hodadako/mercury/branch/main/graph/badge.svg)](https://codecov.io/gh/hodadako/mercury)
![CI](https://github.com/hodadako/mercury/actions/workflows/test-coverage.yml/badge.svg)
![Last Commit](https://img.shields.io/github/last-commit/hodadako/mercury)

Mercury is an open source translator designed to transform human-readable natural language to DNA Sequences. The project aims for a unique bridge between complex genomic information and accessible linguistic representations, making biological data more intuitive for researchers, developers, and enthusiasts.

 > The core functionality of mercury is to analyze DNA sequences and convert them into meaningful natural language sentences or designated binary sequences. This allows for DNA-embedded messages to be translated into human-readable format.

## _Disclaimer_
The current version of mercury does not guarantee the stability of DNA sequences when used in any types of real life forms, in that many of the problems including _GC ratio_, _pre-designated sequences for ribosomes_, etc. are yet to be solved. Version 2 may include features to handle limitations mentioned above, but the version update cannot be promised at the moment.

## Features
- **Bidirectional Conversion**:
    - DNA → Natural Language / Binary
    - Natural Language / Binary → DNA

- **Codon-Aware Encoding**:
    - All DNA sequences include start/stop codons and are padded to maintain 3-base alignment


## DNA Encoding Background

DNA is composed of four nucleotide bases: **A**, **C**, **G**, and **T**. Mercury leverages this limited but information-rich alphabet to encode binary or textual data, aligning with biological conventions such as:

- **Start Codon**: `ATG` (initiation signal)
- **Stop Codons**: `TAA`, `TAG`, `TGA` (termination signal)
- **Codon Alignment**: DNA is interpreted in **triplets (3 bases)**

Mercury ensures all generated DNA sequences follow this codon structure for biological validity and decoding accuracy.


## Conversion Process

###  Conversion Table

Mercury uses a 2-bit representation for each nucleotide base, allowing DNA sequences to encode binary data and vice versa. Below is the default mapping:

| Nucleotide | Binary |
|------------|--------|
| A          | 00     |
| C          | 01     |
| G          | 10     |
| T          | 11     |

> This table is used when converting binary sequences to DNA and decoding DNA back to binary data.

### Padding

DNA sequences must be aligned in **triplets (codons)** to preserve biological structure.  
Since Mercury uses **2-bit per base encoding** (4 bases per byte), the resulting DNA sequence length may not always be divisible by 3.

To ensure correct codon alignment, Mercury performs padding based on the following rules:

#### 1. Padding Bases (`A`)

If the payload (converted to DNA) is not a multiple of 3 in length, Mercury appends padding bases (`A`, representing `00`) at the end of the binary-encoded DNA sequence.  
These padding bases ensure the total DNA length **before adding the stop codon** is divisible by 3.

#### 2. Padding Length Determination & Stop Codon Selection

The number of padding bases added determines which **stop codon** is used.  
This mechanism allows the decoder to infer the original payload length by examining the stop codon, effectively eliminating the need for an explicit length header.

| Stop Codon | Padding Length (bases) |
|------------|-----------------------|
| `TAA`      | 0 (no padding)        |
| `TAG`      | 1 base padding        |
| `TGA`      | 2 bases padding       |

---

### Example

#### **Input**
- Text: `"A"`
- Encoding: UTF-8 → `0x41` → Binary: `01000001` (8 bits)

#### **Process**

1. **Binary → DNA Payload (2 bits per base):**

| Bits | DNA base |
|-------|----------|
| 01    | C        |
| 00    | A        |
| 00    | A        |
| 01    | C        |

_Resulting DNA Payload: `CAAC` (4 bases)_

2. **Determine Padding:**
Payload length = 4 bases  
`4 % 3 = 1` → length is `3n + 1`
According to the padding rule, a remainder of 1 requires **2 bases of padding**.  

3. **Apply Padding:**
Append 2 padding bases (`A`) to DNA payload:  
`CAAC` + `AA` = `CAACAA`  
Now length = 6 bases (divisible by 3)

4. **Select Stop Codon:**
Since 2 padding bases were added, select stop codon: `TGA`

---

> **Final DNA sequences:** `ATGCAACAATGA`

## How to Use
```java
MercuryTranslator translator = new SimpleMercuryTranslator();

String dna = translator.encode("I will be always attached to you");
// ATGCAGCAGAACTCTCGGCCGTACGTAAGAACGAGCGCCAGAACGACCGTACTCTCGACCTGCCTATAGAACGACCTCACTCACGACCGATCGGACGCCCGCAAGAACTCACGTTAGAACTGCCGTTCTCCATAG
String text = translator.decode(dna);
// I will be always attached to you
```

## How to Contribute
`TBD`

## Issues & Bugs

If you encounter any issues or bugs while using Mercury, please feel free to open an issue on the [GitHub Issues page](https://github.com/your-repo/mercury/issues).

When reporting a bug, kindly include the following information to help us diagnose and resolve the problem more efficiently:

- Steps to reproduce the issue
- Expected vs. actual behavior
- Your environment details (OS, Java version, Mercury version)
- Any relevant error messages or stack traces

We appreciate your feedback and contributions to improve Mercury!

## Inspired By

This project was mainly inspired by the great passion of the [original author](https://github.com/hodadako) to the [G-Witch series](https://en.gundam.info/about-gundam/series-pages/witch/).

<details>
<summary>Spoiler Alert: Key Scene Description</summary>

In a memorable scene, the protagonist Miorine Rembran discovers a hidden message encoded in the DNA of a tomato left by her mother.
<br><br>
The anime beautifully depicts this moment where a security system is unlocked using a Java-based system designed to decode the DNA message—mirroring the core idea behind this project.

</details>
<br>

[Blessing](https://youtu.be/3eytpBOkOFA?si=lWngRVD31NY4kCwh).
