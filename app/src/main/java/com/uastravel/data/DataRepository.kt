package com.uastravel.repository

import com.uastravel.R
import com.uastravel.data.entity.UserEntityDetails

object DataRepository {

    fun getRecentsData(): List<UserEntityDetails> {
        return listOf(
            UserEntityDetails(placeName = "Aogashima Volcano", countryName = "Jepang", price = "From $200 - $500", totalPrice = "$250", date = "23 November 2023", imageUrl = R.drawable.aogashima, aboutPlace = "Gunung ini memang pantas di juluki sebagai surga tersembunyi karena Aogashima Volcano ini terletak di Pulau Augashima yang menjadi salah satu bagian Pulau Izu, untuk sampai ke Pulau Aogashima ini anda bisa menggunakan kapal \n" +
                    "feri dari Tokyo jaraknya sekitar 300 meter.Pulau Aogashima ini memang masih hijau, bersih, dan memiliki kawah gunung berapi yang masih aktif, meskipun masih aktif tempat ini juga memiliki penduduk sekitar 200 penduduk.\n  \n"),
            UserEntityDetails(placeName = "Bromo", countryName = "Indonesia", price = "From $150 - $300", totalPrice = "$200", date = "23 Juli 2023", imageUrl = R.drawable.bromo, aboutPlace = "Gunung Bromo atau dalam bahasa Tengger dieja \"Brama\", juga disebut Kaldera Tengger, adalah sebuah gunung berapi aktif di Jawa Timur, Indonesia. \n" +
                    "Gunung ini memiliki ketinggian 2.329 meter di atas permukaan laut dan berada dalam empat wilayah kabupaten, yakni Kabupaten Probolinggo, Kabupaten Pasuruan, \n" +
                    "Kabupaten Lumajang, dan Kabupaten Malang. Gunung Bromo terkenal sebagai objek wisata utama di Jawa Timur \n"),
            UserEntityDetails(placeName = "Bern", countryName = "Swiss", price = "From $250 - $400", totalPrice = "$300", date = "11 Juli 2023", imageUrl = R.drawable.bern, aboutPlace = "kota Bern merupakan kota terintah yang menjadi ibukota negara Swiss .Di Kota Bern anda bisa melihat cantiknya bangunan kuno yang di bagun sejak tahun 1100an.\n \n" +
                    "jika anda berada di Kota Bern maka wajib banget melihat indahnya Katedral Bern yang di bangun pada tahun 1400an.Pada tahun 1530 Kota Bern ini diberi menara yang memiliki Jam besar dan memiliki fitur lonceng, lonceng ini akan berbunyi setiap satu jam. \n  \n"),
            UserEntityDetails(placeName = "Raja Ampat", countryName = "Indonesia", price = "From $150 - $300", totalPrice = "$270", date = "30 Febuari 2023", imageUrl = R.drawable.raja, aboutPlace = "epulauan Raja Ampat menjadi salah satu tujuan wisata yang sangat menarik di Papua. Bahkan sekarang banyak wisatawan asing yang selalu kembali ke sana untuk berlibur.\n" +
                    "pemandangan pulau-pulau yang indah terlihat sangat eksotis dan menawan. Gugusan pulau ini terdiri dari Pulau Waigeo, Pulau Salawati, Pulau Batanta dan Pulau Misool.\n" +
                    "Dari atas bukit maka gugusan pulau ini menjadi ikon Papua yang khas. Tempat ini cocok untuk Anda yang suka menyelam karena terdapat lebih dari 1200 jenis ikan dan 540 bagian koral dan terumbu karang yang sangat indah.\n  \n"),
        )
    }

    fun getTopPlacesData(): List<UserEntityDetails> {
        return listOf(
            UserEntityDetails(placeName = "Machu Pichu", countryName = "Peru", price = "From $250 - $500", totalPrice = "$400", date = "14 Maret 2023", imageUrl = R.drawable.machu_pichu, aboutPlace = " Machu Picchu adalah situs arkeologis yang terletak di pegunungan Andes di Peru. Tempat ini merupakan salah satu dari tujuan wisata paling ikonik di Amerika Selatan dan salah satu warisan sejarah terbesar dari Kekaisaran Inca. Machu Picchu menarik minat wisatawan dari seluruh dunia karena keindahan alamnya yang menakjubkan, arsitektur kuno yang megah, dan lokasinya yang spektakuler di puncak gunung.\n \n"),
            UserEntityDetails(placeName = "Pyramid of Giza", countryName = "Mesir", price = "From $200 - $450", totalPrice = "$300", date = "31 December 2022", imageUrl = R.drawable.pyramid_giza, aboutPlace = "Piramida Giza adalah salah satu dari tujuh keajaiban dunia kuno yang terletak di dataran Giza, dekat Kairo, Mesir. Piramida ini merupakan bagian dari kompleks pemakaman yang dibangun untuk para raja Firaun Khufu (juga dikenal sebagai Cheops), Khafre, dan Menkaure pada masa Dinasti Keempat Mesir Kuno.\n \n"),
            UserEntityDetails(placeName = "Great Wall Of China", countryName = "China", price = "From $250 - $450", totalPrice = "$380", date = "16 Juni 2023", imageUrl = R.drawable.great_wall_china, aboutPlace = "Great Wall of China adalah sebuah struktur pertahanan yang sangat besar dan megah yang terletak di Cina. Merupakan salah satu keajaiban dunia dan salah satu ikon terkenal dari Cina, Great Wall dibangun selama berabad-abad untuk melindungi wilayah dari serangan dan invasi dari suku-suku nomaden di utara.Pembangunan Great Wall dimulai sejak abad ke-7 SM hingga abad ke-17 Masehi, dengan beberapa bagian dibangun dan diperluas oleh berbagai dinasti Cina, terutama pada masa Dinasti Qin, Han, Ming, dan lainnya. Struktur ini terdiri dari tembok-tembok batu, menara pengawas, dan pertahanan lainnya yang membentang di sepanjang wilayah Cina.\n\n"),
            UserEntityDetails(placeName = "Taj Mahal", countryName = "India", price = "From $350 - $700", totalPrice = "$400", date = "08 Januari 2023", imageUrl = R.drawable.taj_mahal, aboutPlace = "Taj Mahal adalah sebuah monumen megah yang terletak di Agra, India. Monumen ini dianggap sebagai salah satu keajaiban dunia dan merupakan simbol cinta abadi. Dibangun antara tahun 1631 dan 1648 oleh Kaisar Mughal Shah Jahan sebagai makam bagi istrinya yang tercinta, Mumtaz Mahal, yang meninggal saat melahirkan anak ke-14 mereka.\n\n \n"),
        )
    }
}