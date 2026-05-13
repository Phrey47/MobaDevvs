package com.bibleapp.data

data class BibleBook(
    val id: String,
    val name: String,
    val abbr: String,
    val testament: String, // "OT" or "NT"
    val chapters: Int
)

data class Verse(
    val number: Int,
    val text: String
)

data class SearchResult(
    val bookId: String,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)

data class DailyVerse(
    val bookId: String,
    val chapter: Int,
    val ref: String,
    val text: String
)

val OT_BOOKS = listOf(
    BibleBook("gen","Genesis","Gen","OT",50),
    BibleBook("exo","Exodus","Exo","OT",40),
    BibleBook("lev","Leviticus","Lev","OT",27),
    BibleBook("num","Numbers","Num","OT",36),
    BibleBook("deu","Deuteronomy","Deu","OT",34),
    BibleBook("jos","Joshua","Jos","OT",24),
    BibleBook("jdg","Judges","Jdg","OT",21),
    BibleBook("rut","Ruth","Rut","OT",4),
    BibleBook("1sa","1 Samuel","1Sa","OT",31),
    BibleBook("2sa","2 Samuel","2Sa","OT",24),
    BibleBook("1ki","1 Kings","1Ki","OT",22),
    BibleBook("2ki","2 Kings","2Ki","OT",25),
    BibleBook("1ch","1 Chronicles","1Ch","OT",29),
    BibleBook("2ch","2 Chronicles","2Ch","OT",36),
    BibleBook("ezr","Ezra","Ezr","OT",10),
    BibleBook("neh","Nehemiah","Neh","OT",13),
    BibleBook("est","Esther","Est","OT",10),
    BibleBook("job","Job","Job","OT",42),
    BibleBook("psa","Psalms","Psa","OT",150),
    BibleBook("pro","Proverbs","Pro","OT",31),
    BibleBook("ecc","Ecclesiastes","Ecc","OT",12),
    BibleBook("sng","Song of Solomon","Sng","OT",8),
    BibleBook("isa","Isaiah","Isa","OT",66),
    BibleBook("jer","Jeremiah","Jer","OT",52),
    BibleBook("lam","Lamentations","Lam","OT",5),
    BibleBook("ezk","Ezekiel","Ezk","OT",48),
    BibleBook("dan","Daniel","Dan","OT",12),
    BibleBook("hos","Hosea","Hos","OT",14),
    BibleBook("joe","Joel","Joe","OT",3),
    BibleBook("amo","Amos","Amo","OT",9),
    BibleBook("oba","Obadiah","Oba","OT",1),
    BibleBook("jon","Jonah","Jon","OT",4),
    BibleBook("mic","Micah","Mic","OT",7),
    BibleBook("nah","Nahum","Nah","OT",3),
    BibleBook("hab","Habakkuk","Hab","OT",3),
    BibleBook("zep","Zephaniah","Zep","OT",3),
    BibleBook("hag","Haggai","Hag","OT",2),
    BibleBook("zec","Zechariah","Zec","OT",14),
    BibleBook("mal","Malachi","Mal","OT",4)
)

val NT_BOOKS = listOf(
    BibleBook("mat","Matthew","Mat","NT",28),
    BibleBook("mrk","Mark","Mrk","NT",16),
    BibleBook("luk","Luke","Luk","NT",24),
    BibleBook("jhn","John","Jhn","NT",21),
    BibleBook("act","Acts","Act","NT",28),
    BibleBook("rom","Romans","Rom","NT",16),
    BibleBook("1co","1 Corinthians","1Co","NT",16),
    BibleBook("2co","2 Corinthians","2Co","NT",13),
    BibleBook("gal","Galatians","Gal","NT",6),
    BibleBook("eph","Ephesians","Eph","NT",6),
    BibleBook("php","Philippians","Php","NT",4),
    BibleBook("col","Colossians","Col","NT",4),
    BibleBook("1th","1 Thessalonians","1Th","NT",5),
    BibleBook("2th","2 Thessalonians","2Th","NT",3),
    BibleBook("1ti","1 Timothy","1Ti","NT",6),
    BibleBook("2ti","2 Timothy","2Ti","NT",4),
    BibleBook("tit","Titus","Tit","NT",3),
    BibleBook("phm","Philemon","Phm","NT",1),
    BibleBook("heb","Hebrews","Heb","NT",13),
    BibleBook("jas","James","Jas","NT",5),
    BibleBook("1pe","1 Peter","1Pe","NT",5),
    BibleBook("2pe","2 Peter","2Pe","NT",3),
    BibleBook("1jn","1 John","1Jn","NT",5),
    BibleBook("2jn","2 John","2Jn","NT",1),
    BibleBook("3jn","3 John","3Jn","NT",1),
    BibleBook("jud","Jude","Jud","NT",1),
    BibleBook("rev","Revelation","Rev","NT",22)
)

val ALL_BOOKS = OT_BOOKS + NT_BOOKS

fun getBook(id: String): BibleBook? = ALL_BOOKS.find { it.id == id }

// Sample verse data for key chapters
val VERSE_DATA: Map<String, List<Verse>> = mapOf(
    "gen-1" to listOf(
        Verse(1, "In the beginning God created the heaven and the earth."),
        Verse(2, "And the earth was without form, and void; and darkness was upon the face of the deep. And the Spirit of God moved upon the face of the waters."),
        Verse(3, "And God said, Let there be light: and there was light."),
        Verse(4, "And God saw the light, that it was good: and God divided the light from the darkness."),
        Verse(5, "And God called the light Day, and the darkness he called Night. And the evening and the morning were the first day."),
        Verse(6, "And God said, Let there be a firmament in the midst of the waters, and let it divide the waters from the waters."),
        Verse(7, "And God made the firmament, and divided the waters which were under the firmament from the waters which were above the firmament: and it was so."),
        Verse(8, "And God called the firmament Heaven. And the evening and the morning were the second day."),
        Verse(9, "And God said, Let the waters under the heaven be gathered together unto one place, and let the dry land appear: and it was so."),
        Verse(10, "And God called the dry land Earth; and the gathering together of the waters called he Seas: and God saw that it was good.")
    ),
    "psa-23" to listOf(
        Verse(1, "The LORD is my shepherd; I shall not want."),
        Verse(2, "He maketh me to lie down in green pastures: he leadeth me beside the still waters."),
        Verse(3, "He restoreth my soul: he leadeth me in the paths of righteousness for his name's sake."),
        Verse(4, "Yea, though I walk through the valley of the shadow of death, I will fear no evil: for thou art with me; thy rod and thy staff they comfort me."),
        Verse(5, "Thou preparest a table before me in the presence of mine enemies: thou anointest my head with oil; my cup runneth over."),
        Verse(6, "Surely goodness and mercy shall follow me all the days of my life: and I will dwell in the house of the LORD for ever.")
    ),
    "jhn-3" to listOf(
        Verse(1, "There was a man of the Pharisees, named Nicodemus, a ruler of the Jews:"),
        Verse(2, "The same came to Jesus by night, and said unto him, Rabbi, we know that thou art a teacher come from God: for no man can do these miracles that thou doest, except God be with him."),
        Verse(3, "Jesus answered and said unto him, Verily, verily, I say unto thee, Except a man be born again, he cannot see the kingdom of God."),
        Verse(14, "And as Moses lifted up the serpent in the wilderness, even so must the Son of man be lifted up:"),
        Verse(15, "That whosoever believeth in him should not perish, but have eternal life."),
        Verse(16, "For God so loved the world, that he gave his only begotten Son, that whosoever believeth in him should not perish, but have everlasting life."),
        Verse(17, "For God sent not his Son into the world to condemn the world; but that the world through him might be saved."),
        Verse(36, "He that believeth on the Son hath everlasting life: and he that believeth not the Son shall not see life; but the wrath of God abideth on him.")
    ),
    "pro-3" to listOf(
        Verse(1, "My son, forget not my law; but let thine heart keep my commandments:"),
        Verse(2, "For length of days, and long life, and peace, shall they add to thee."),
        Verse(3, "Let not mercy and truth forsake thee: bind them about thy neck; write them upon the table of thine heart:"),
        Verse(4, "So shalt thou find favour and good understanding in the sight of God and man."),
        Verse(5, "Trust in the LORD with all thine heart; and lean not unto thine own understanding."),
        Verse(6, "In all thy ways acknowledge him, and he shall direct thy paths."),
        Verse(7, "Be not wise in thine own eyes: fear the LORD, and depart from evil.")
    ),
    "rom-8" to listOf(
        Verse(1, "There is therefore now no condemnation to them which are in Christ Jesus, who walk not after the flesh, but after the Spirit."),
        Verse(2, "For the law of the Spirit of life in Christ Jesus hath made me free from the law of sin and death."),
        Verse(28, "And we know that all things work together for good to them that love God, to them who are the called according to his purpose."),
        Verse(31, "What shall we then say to these things? If God be for us, who can be against us?"),
        Verse(38, "For I am persuaded, that neither death, nor life, nor angels, nor principalities, nor powers, nor things present, nor things to come,"),
        Verse(39, "Nor height, nor depth, nor any other creature, shall be able to separate us from the love of God, which is in Christ Jesus our Lord.")
    ),
    "mat-5" to listOf(
        Verse(1, "And seeing the multitudes, he went up into a mountain: and when he was set, his disciples came unto him:"),
        Verse(2, "And he opened his mouth, and taught them, saying,"),
        Verse(3, "Blessed are the poor in spirit: for theirs is the kingdom of heaven."),
        Verse(4, "Blessed are they that mourn: for they shall be comforted."),
        Verse(5, "Blessed are the meek: for they shall inherit the earth."),
        Verse(6, "Blessed are they which do hunger and thirst after righteousness: for they shall be filled."),
        Verse(7, "Blessed are the merciful: for they shall obtain mercy."),
        Verse(8, "Blessed are the pure in heart: for they shall see God."),
        Verse(9, "Blessed are the peacemakers: for they shall be called the children of God.")
    )
)

fun getChapterVerses(bookId: String, chapter: Int): List<Verse> {
    val key = "$bookId-$chapter"
    return VERSE_DATA[key] ?: generatePlaceholderVerses(bookId, chapter)
}

private fun generatePlaceholderVerses(bookId: String, chapter: Int): List<Verse> {
    val book = getBook(bookId) ?: return emptyList()
    val count = when {
        chapter == 1 -> 31
        chapter % 3 == 0 -> 20
        else -> 25
    }
    return (1..count).map { v ->
        Verse(v, "[ ${book.name} $chapter:$v — Full text available in complete Bible edition. ]")
    }
}

private val DAILY_VERSES = listOf(
    DailyVerse("jhn", 3, "John 3:16", "For God so loved the world, that he gave his only begotten Son, that whosoever believeth in him should not perish, but have everlasting life."),
    DailyVerse("psa", 23, "Psalm 23:1", "The LORD is my shepherd; I shall not want."),
    DailyVerse("pro", 3, "Proverbs 3:5", "Trust in the LORD with all thine heart; and lean not unto thine own understanding."),
    DailyVerse("rom", 8, "Romans 8:28", "And we know that all things work together for good to them that love God."),
    DailyVerse("php", 4, "Philippians 4:13", "I can do all things through Christ which strengtheneth me."),
    DailyVerse("isa", 40, "Isaiah 40:31", "But they that wait upon the LORD shall renew their strength; they shall mount up with wings as eagles."),
    DailyVerse("mat", 5, "Matthew 5:3", "Blessed are the poor in spirit: for theirs is the kingdom of heaven.")
)

fun getDailyVerse(): DailyVerse {
    val dayOfYear = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_YEAR)
    return DAILY_VERSES[dayOfYear % DAILY_VERSES.size]
}

fun searchVerses(query: String): List<SearchResult> {
    if (query.length < 2) return emptyList()
    val q = query.lowercase()
    val results = mutableListOf<SearchResult>()
    for ((key, verses) in VERSE_DATA) {
        val parts = key.split("-")
        if (parts.size < 2) continue
        val bookId = parts[0]
        val chapter = parts[1].toIntOrNull() ?: continue
        val book = getBook(bookId) ?: continue
        for (verse in verses) {
            if (verse.text.lowercase().contains(q)) {
                results.add(SearchResult(bookId, book.name, chapter, verse.number, verse.text))
            }
        }
    }
    return results.take(50)
}

val POPULAR_SEARCHES = listOf("love", "faith", "peace", "strength", "grace", "hope", "salvation", "light")

val QUICK_BOOKS = listOf(
    OT_BOOKS.first { it.id == "gen" },
    OT_BOOKS.first { it.id == "psa" },
    OT_BOOKS.first { it.id == "pro" },
    NT_BOOKS.first { it.id == "mat" },
    NT_BOOKS.first { it.id == "jhn" },
    NT_BOOKS.first { it.id == "rom" }
)
