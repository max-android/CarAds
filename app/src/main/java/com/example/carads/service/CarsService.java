package com.example.carads.service;

import com.example.carads.storage.database.entity.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 21.11.2017.
 */

public class CarsService {

    public List<Car> getCars(){

        List<Car> cars = new ArrayList<>();


        cars.add(new Car("Audi S7",
                "https://avatars.mds.yandex.net/get-auto/51923/catalog.20183784.775214863242736773/cattouchret"
        ,"2016","45 000 км","Синий",5800000,5.0,500,"имя1","8 711 456 12 34","name_1@post.ru","Улица 1",53.720976,91.44242300000001));

        cars.add(new Car("Audi Q6",
                "https://avatars.mds.yandex.net/get-auto/761391/catalog.20368784.775214863241723876/cattouchret"
                ,"2016","35 000 км","Синий",4525000,5.0,400,"имя2","8 711 122 34 55","name_2@post.ru","Улица 2",64.539304,40.518735));
        cars.add(new Car("Audi Q6",
                "https://avatars.mds.yandex.net/get-auto/761391/catalog.20368784.775214863241723876/cattouchret"
                ,"2016","35 000 км","Синий",4525000,5.0,400,"имя3","8 712 476 18 39","name_3@post.ru","Улица 3",71.430564,51.128422));


        cars.add(new Car("Audi A8",
                "https://avatars.mds.yandex.net/get-auto/69685/catalog.20071437.775214863242677342/cattouchret"
                ,"2012","64 000 км","Металлик",2425000,6.0,500,"имя4","8 610 908 88 00","name_4@post.ru","Улица 4",51.128422,51.128422));

        cars.add(new Car("Audi A8",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.21040152.775214863155868073/cattouchret"
                ,"2017","15 000 км","Красный",7810000,6.0,550,"имя5","8 615 123 89 10","name_5@post.ru","Улица 5",53.356132,83.749619));


        cars.add(new Car("Audi A8",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.2305479.2355218112614871656/cattouchret"
                ,"2006","180 000 км","Золотой",560000,4.0,350,"имя6","8 620 129 09 00","name_6@post.ru","Улица 6",83.749619,83.749619));


        cars.add(new Car("Audi A8",
                "https://avatars.mds.yandex.net/get-auto/38138/catalog.21040166.775214863155867364/cattouchret"
                ,"2017","10 000 км","Серый",5900000,5.0,450,"имя7","8 670 000 01 00","name_7@post.ru","Улица 7",83.749619,83.749619));


        cars.add(new Car("Audi A6",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.20246013.775214863242650345/cattouchret"
                ,"2015","170 000 км","Синий",1700000,3.0,300,"имя8","8 890 339 09 07","name_8@post.ru","Улица 8",83.749619,83.749619));



        cars.add(new Car("Audi A6",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.2305418.2355218112594639447/cattouchret"
                ,"2008","170 000 км","Металлик",600000,2.0,180,"имя9","8 850 559 78 98","name_9@post.ru","Улица 9",83.749619,127.52717));

        cars.add(new Car("Audi A6",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.4601090.2355218116114058098/cattouchret"
                ,"2005","250 000 км","Белый",350000,2.5,230,"имя10","8 320 449 64 88","name_10@post.ru","Улица 10",56.151382,56.151382));


        cars.add(new Car("Audi A4",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.20637561.775214863238181596/cattouchret"
                ,"2016","18 000 км","Желтый",2550000,2.0,150,"имя11","8 320 546 89 20","name_11@post.ru","Улица 11",53.2434,34.364198));


        cars.add(new Car("Audi A4",
                "https://avatars.mds.yandex.net/get-auto/33384/catalog.20637561.775214863238180857/cattouchret"
                ,"2017","9 000 км","Синий",3200000,2.5,250,"имя12","8 660 729 09 07","name_12@post.ru","Улица 12",58.521475,31.275475));

        cars.add(new Car("Audi A4",
                "https://avatars.mds.yandex.net/get-auto/194833/catalog.20683225.775214863238032646/cattouchret"
                ,"2017","9 000 км","Красный",2700000,1.8,150,"имя13","8 230 769 19 37","name_13@post.ru","Улица 13",43.134019,131.928379));


        cars.add(new Car("Audi A4",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.3480062.2355218115194290613/cattouchret"
                ,"2010","120 000 км","Красный",550000,2.2,220,"имя14","8 600 729 55 99","name_14@post.ru","Улица 14",56.129042,56.129042));


        cars.add(new Car("Audi A4",
                "https://avatars.mds.yandex.net/get-auto/194833/catalog.4759707.2355218115310714986/cattouchret"
                ,"2005","120 000 км","Синий",350000,2.3,200,"имя15","6 800 333 44 99","name_15@post.ru","Улица 15",48.707103,44.516939 ));


        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/33384/catalog.20619323.775214863238030903/cattouchret"
                ,"2016","20 000 км","Черный",5600000,5.0,500,"имя16","8 200 889 55 99","name_16@post.ru","Улица 16",59.220492,59.220492));


        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.20619323.775214863238030906/cattouchret"
                ,"2016","25 000 км","Синий",6400000,6.0,600,"имя17","8 900 009 55 00","name_17@post.ru","Улица 17",51.661535,39.200287));



        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.8277753.2355218116113199713/cattouchret"
                ,"2015","70 000 км","Белый",4500000,5.5,450,"имя18","8 900 669 66 44","name_18@post.ru","Улица 18",39.200287,60.597295));


        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.8277753.2355218116113199736/cattouchret"
                ,"2014","90 000 км","Белый",3500000,4.0,350,"имя19","8 999 000 00 99","name_19@post.ru","Улица 19",60.597295,60.597295));


        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.8277753.2355218116056952408/cattouchret"
                ,"2012","120 000 км","Белый",1900000,3.5,220,"имя20","8 200 222 57 99","name_20@post.ru","Улица 20",60.597295,53.211463 ));



        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.3619801.2355218115337382485/cattouchret"
                ,"2010","140 000 км","Металлик",1500000,4.0,350,"имя21","8 700 768 99 99","name_21@post.ru","Улица 21",52.286387,52.286387));



        cars.add(new Car("BMW 7",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.3619801.2355218116115102516/cattouchret"
                ,"2011","110 000 км","Черный",1600000,3.5,300,"имя22","8 450 555 55 19","name_22@post.ru","Улица 22",55.795793,55.795793));



        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.20856206.775214863214907111/cattouchret"
                ,"2016","10 000 км","Синий",4500000,3.0,280,"имя23","8 408 333 51 96","name_23@post.ru","Улица 23",55.795793,55.795793));

        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/33384/catalog.20856206.775214863214907107/cattouchret"
                ,"2016","15 000 км","Синий",3900000,2.5,250,"имя24","8 300 723 65 89","name_24@post.ru","Улица 24",54.507014,36.252277));

        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/33384/catalog.20856206.775214863214907107/cattouchret"
                ,"2015","19 000 км","Синий",3500000,3.0,280,"имя25","8 300 799 65 99","name_25@post.ru","Улица 25",56.414897,56.414897));


        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.20856206.775214863214907108/cattouchret"
                ,"2016","19 000 км","Синий",4100000,2.5,220,"имя26","8 240 729 55 00","name_26@post.ru","Улица 26",55.359594,86.0877));


        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.20856206.775214863214907108/cattouchret"
                ,"2016","32 000 км","Синий",4500000,3.5,250,"имя27","8 240 729 55 00","name_27@post.ru","Улица 27",54.079033,34.323163));



        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/50005/catalog.20781304.775214863236177885/cattouchret"
                ,"2013","92 000 км","Белый",2800000,3.0,220,"имя28","8 700 000 55 88","name_28@post.ru","Улица 28",50.54986,137.0078));



        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/38138/catalog.2305629.2355218115336577202/cattouchret"
                ,"2008","170 000 км","Синий",600000,3.0,320,"имя29","8 200 339 11 22","name_29@post.ru","Улица 29",55.916229,37.854467));



        cars.add(new Car("BMW 5",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.5139709.2355218115312504304/cattouchret"
                ,"2003","280 000 км","Металлик",400000,2.0,260,"имя30","8 500 50 55 29","name_30@post.ru","Улица 30",57.767683,40.926418));



        cars.add(new Car("BMW X6",
                "https://avatars.mds.yandex.net/get-auto/118771/catalog.20158770.775214863241781755/cattouchret"
                ,"2016","27 000 км","Белый",5600000,4.4,420,"имя31","8 345 777 67 22","name_31@post.ru","Улица 31",45.023877,38.970157));


        cars.add(new Car("BMW X6",
                "https://avatars.mds.yandex.net/get-auto/38138/catalog.20158770.775214863241781750/cattouchret"
                ,"2015","23 000 км","Белый",4500000,5.0,520,"имя32","8 555 766 00 00","name_32@post.ru","Улица 32",45.023877,38.970157));


        cars.add(new Car("BMW X6",
                "https://avatars.mds.yandex.net/get-auto/194833/catalog.20158770.775214863241781751/cattouchret"
                ,"2014","56 000 км","Белый",4200000,5.0,520,"имя33","8 000 333 00 09","name_33@post.ru","Улица 33",45.023877,38.970157));


        cars.add(new Car("BMW X6",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.2433259.2355218115337351733/cattouchret"
                ,"2013","105 000 км","Красный",3500000,5.0,520,"имя34","8 111 222 12 66","name_34@post.ru","Улица 34",45.023877,38.970157));



        cars.add(new Car("BMW X5",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.10382711.775214891550559283/cattouchret"
                ,"2016","10 000 км","Белый",4500000,3.5,400,"имя35","8 222 222 92 34","name_35@post.ru","Улица 35",45.023877,38.970157));


        cars.add(new Car("BMW X5",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.10382711.775214891550559283/cattouchret"
                ,"2016","17 000 км","Белый",4300000,4.5,500,"имя36","8 022 888 02 04","name_36@post.ru","Улица 36",45.023877,38.970157));


        cars.add(new Car("BMW X5",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.2305680.2355218115336579064/cattouchret"
                ,"2010","130 000 км","Металлик",2100000,4.4,300,"имя37","8 112 555 02 14","name_37@post.ru","Улица 37",45.023877,38.970157));



        cars.add(new Car("Mercedes-Benz S",
                "http://wheelsboutique.moscow/wp-content/uploads/2015/08/Кованые-диски-Beneventi-K5.0-на-Mercedes-Benz-S500-4Matic-W222.jpg"
                ,"2016","30 000 км","Черный",6500000,6.0,600,"имя38","8 333 009 00 66","name_38@post.ru","Улица 38",56.008691,92.870529));



        cars.add(new Car("Mercedes-Benz C",
                "http://svtunes.ru/wp-content/uploads/2017/08/Mercedes_C-Class_Sedan_2014-1.jpg"
                ,"2015","55 000 км","Белый",2800000,1.8,150,"имя39","8 000 003 34 78","name_39@post.ru","Улица 39",51.730361,36.192647));




        cars.add(new Car("Mercedes-Benz C",
                "http://3.bp.blogspot.com/-uPcihfl9re4/Uq8rK-c2vsI/AAAAAAAA6vo/8ECs_YacsbI/s1600/2015-Mercedes-C-Class-24%5B3%5D.jpg"
                ,"2014","55 000 км","Черный",2300000,1.8,150,"имя40","8 450 333 44 22","name_40@post.ru","Улица 40",52.6102,39.594719));



        cars.add(new Car("Mercedes-Benz E",
                "http://st.motortrend.com/uploads/sites/10/2015/09/2012-Mercedes-Benz-E350-sedan-front.jpg"
                ,"2010","98 000 км","Серый",1500000,2.0,180,"имя41","8 664 322 44 21","name_41@post.ru","Улица 41",53.411677,58.984415 ));



        cars.add(new Car("Mercedes-Benz C",
                "http://avtovolgograda.ru/wp-content/uploads/2013/12/novy%60j-Mercedes-Benz-C-klass-2015-Foto-41.jpg"
                ,"2016","13 000 км","Белый",3100000,3.0,380,"имя42","8 555 333 44 00","name_42@post.ru","Улица 42",55.755773,37.617761));



        cars.add(new Car("Mercedes-Benz C",
                "https://car4play.ru/uploads/gallery/UuQPvfHA7yqn.jpg"
                ,"2016","9 000 км","Белый",3400000,3.0,350,"имя43","8 111 009 35 66","name_43@post.ru","Улица 43",55.755773,37.617761));



        cars.add(new Car("Mercedes-Benz C",
                "https://img.day.az/clickable/01/5/s-klass_452655_004.jpg"
                ,"2016","12 000 км","Черный",3100000,2.0,150,"имя44","8 777 222 55 11","name_44@post.ru","Улица 44",55.755773,37.617761));





        cars.add(new Car("Mercedes-Benz S",
                "https://motoimg.com/images/mercedes-benz-s-63-amg-w221-2009-29.jpg"
                ,"2012","45 000 км","Черный",4500000,5.0,500,"имя45","8 333 200 59 21","name_45@post.ru","Улица 45",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz GLC",
                "http://cdn.pinthiscars.com/images/mercedes-glc-3.jpg"
                ,"2015","34 000 км","Синий",3300000,4.0,500,"имя46","8 999 770 29 11","name_46@post.ru","Улица 46",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.21016168.775214863157627941/cattouchret"
                ,"2016","23 000 км","Металлик",5400000,6.0,600,"имя47","8 444 440 70 22","name_47@post.ru","Улица 47",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.21016168.775214863157627941/cattouchret"
                ,"2016","12 000 км","Металлик",6700000,6.0,600,"имя48","8 880 999 99 11","name_48@post.ru","Улица 48",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/70747/catalog.10380527.775214891549636569/cattouchret"
                ,"2016","9 000 км","Черный",7200000,6.0,600,"имя49","8 080 900 09 01","name_49@post.ru","Улица 49",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.10380527.775214891549636573/cattouchret"
                ,"2016","9 000 км","Черный",8500000,6.0,600,"имя50","8 111 100 33 91","name_50@post.ru","Улица 50",55.755773,37.617761));




        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/118771/catalog.4865025.2355218115337497033/cattouchret"
                ,"2013","78 000 км","Золотой",3200000,4.0,400,"имя51","8 777 999 11 01","name_51@post.ru","Улица 51",55.755773,37.617761));





        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/873680/catalog.2307753.2355218115337287532/cattouchret"
                ,"2008","145 000 км","Черный",1350000,4.0,400,"имя52","8 888 222 10 33","name_52@post.ru","Улица 52",55.755773,37.617761));


        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/50005/catalog.2307753.2355218115337287476/cattouchret"
                ,"2009","145 000 км","Золотой",1150000,3.0,350,"имя53","8 333 222 10 11","name_53@post.ru","Улица 53",55.755773,37.617761));



        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.6529035.2355218114363934838/cattouchret"
                ,"2005","500 000 км","Черный",600000,6.0,500,"имя54","8 330 002 99 22","name_54@post.ru","Улица 54",55.755773,37.617761));


        cars.add(new Car("Mercedes-Benz S",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.6528906.2355218114363934652/cattouchret"
                ,"1998","500 000 км","Черный",200000,6.0,500,"имя55","8 654 890 07 00","name_55@post.ru","Улица 55",55.755773,37.617761));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.20743538.775214863237135259/cattouchret"
                ,"2016","50 000 км","Синий",3800000,3.0,240,"имя56","8 888 089 67 90","name_56@post.ru","Улица 56",55.755773,37.617761));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/776173/catalog.20743538.775214863237135255/cattouchret"
                ,"2016","44 000 км","Металлик",4500000,3.0,250,"имя57","8 123 567 57 90","name_57@post.ru","Улица 57",55.755773,37.617761));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.20743538.775214863237135256/cattouchret"
                ,"2016","36 000 км","Синий",3600000,3.0,240,"имя58","8 123 077 00 00","name_58@post.ru","Улица 58",68.9695629,33.07454));



        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.20743538.775214863237135258/cattouchret"
                ,"2016","23 000 км","Синий",3700000,2.0,180,"имя59","8 890 076 34 56","name_59@post.ru","Улица 59",55.743553,52.39582 ));



        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.9259589.2355218116943322053/cattouchret"
                ,"2013","72 000 км","Коричневый",2600000,3.5,350,"имя60","8 456 678 12 66","name_60@post.ru","Улица 60",56.323902,44.002267));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/118771/catalog.9259589.2355218116943322084/cattouchret"
                ,"2013","98 000 км","Коричневый ",2500000,3.5,350,"имя61","8 567 123 10 60","name_61@post.ru","Улица 61",57.910144,59.98132 ));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/28591/catalog.4593262.2355218115337411350/cattouchret"
                ,"2011","114 000 км","Металлик",1500000,2.0,250,"имя62","8 789 456 23 78","name_62@post.ru","Улица 62",53.786502,87.155205));




        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/51923/catalog.4593262.2355218115337411313/cattouchret"
                ,"2010","167 000 км","Металлик",1200000,3.0,250,"имя63","8 453 451 00 12","name_63@post.ru","Улица 63",44.723489,37.76866));


        cars.add(new Car("Mercedes-Benz E",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.4593262.2355218115337411317/cattouchret"
                ,"2009","167 000 км","Металлик",950000,3.0,320,"имя64","8 122 000 33 18","name_64@post.ru","Улица 64",55.028739,82.90692));


        cars.add(new Car("Mercedes-Benz G",
                "https://avatars.mds.yandex.net/get-auto/34134/catalog.20632346.775214863238030814/cattouchret"
                ,"2015","42 000 км","Черный",10500000,5.5,550,"имя65","8 456 789 39 12","name_65@post.ru","Улица 65",54.989342,73.368212 ));


        cars.add(new Car("Mercedes-Benz GLE",
                "https://avatars.mds.yandex.net/get-auto/118771/catalog.20529984.775214863239997919/cattouchret"
                ,"2015","42 000 км","Металлик",3500000,5.5,550,"имя66","8 123 890 45 23","name_66@post.ru","Улица 66",52.970306,36.063514));


        cars.add(new Car("Mercedes-Benz GL",
                "https://avatars.mds.yandex.net/get-auto/50005/catalog.8225668.775214863243692930/cattouchret"
                ,"2015","34 000 км","Коричневый",4500000,5.5,550,"имя67","8 123 890 45 23","name_67@post.ru","Улица 67",53.194546,45.019529 ));


        cars.add(new Car("Mercedes-Benz GL",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.8225668.2355218116052248316/cattouchret"
                ,"2013","34 000 км","Коричневый",3500000,5.5,550,"имя68","8 113 234 55 00","name_68@post.ru","Улица 68",58.004785,56.237654));



        cars.add(new Car("Mercedes-Benz GL",
                "https://avatars.mds.yandex.net/get-auto/28591/catalog.4986815.2355218115337500813/cattouchret"
                ,"2010","34 000 км","Металлик",2400000,5.5,550,"имя69","8 348 122 89 43","name_69@post.ru","Улица 69",57.819365,28.331786));




        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.7955105.775214863243630500/cattouchret"
                ,"2013","125 000 км","Синий",1800000,3.7,450,"имя70","8 666 111 89 00","name_70@post.ru","Улица 70",47.227151,39.744972));


        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.7955105.2355218116114028117/cattouchret"
                ,"2013","145 000 км","Синий",1600000,3.7,450,"имя71","8 678 000 19 50","name_71@post.ru","Улица 71",54.619886,39.744954));


        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/198007/catalog.7955105.2355218116114028091/cattouchret"
                ,"2013","112 000 км","Синий",1400000,3.7,450,"имя72","8 000 000 11 70","name_72@post.ru","Улица 72",53.195533,50.101801));


        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.7955105.2355218116114028090/cattouchret"
                ,"2012","78 000 км","Синий",1300000,3.7,450,"имя73","8 321 444 99 78","name_73@post.ru","Улица 73",59.938806,30.314278));

        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/118771/catalog.7955105.2355218116114028092/cattouchret"
                ,"2013","45 000 км","Серый", 2500000,5.0,550,"имя74","8 567 123 98 32","name_74@post.ru","Улица 74",51.531528,46.03582 ));



        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/397534/catalog.2662172.2355218112587191822/cattouchret"
                ,"2011","156 000 км","Оранжевый",1200000,3.7,350,"имя75","8 098 654 21 76","name_75@post.ru","Улица 75",43.581509,39.722882));



        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/51923/catalog.2662172.2355218112589039975/cattouchret"
                ,"2010","175 000 км","Оранжевый",900000,3.7,350,"имя76","8 000 000 32 11","name_76@post.ru","Улица 76",56.859611,35.911896));

        cars.add(new Car("Infinity FX",
                "https://avatars.mds.yandex.net/get-auto/175972/catalog.2662172.2355218112589039972/cattouchret"
                ,"2010","120 000 км","Оранжевый",1500000,5.0,350,"имя77","8 000 000 00 77","name_77@post.ru","Улица 77",62.027833,129.704151));




        cars.add(new Car("Porsche Cayenne 958",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.6136700.775214863243625725/cattouchret"
                ,"2012","80 000 км","Серый",2500000,5.0,550,"имя78","8 567 000 87 23","name_78@post.ru","Улица 78",59.938806,59.938806));



        cars.add(new Car("Porsche Cayenne 958",
                "https://avatars.mds.yandex.net/get-auto/26126/catalog.6136700.775214863207676604/cattouchret"
                ,"2011","95 000 км","Синий",2100000,5.0,550,"имя79","8 094 789 12 45","name_79@post.ru","Улица 79",59.938801,59.938805));





        cars.add(new Car("Porsche Cayenne 958",
                "https://avatars.mds.yandex.net/get-auto/193297/catalog.21080954.775214863151428255/cattouchret"
                ,"2017","7 000 км","Серый",7500000,5.0,550,"имя80","8 000 777 77 77","name_80@post.ru","Улица 80",59.938802,59.938804));

        cars.add(new Car("Mercedes-Benz GL",
                "https://avatars.mds.yandex.net/get-auto/515988/catalog.4986815.2355218115337500811/cattouchret"
                ,"2009","34 000 км","Металлик",2100000,5.5,550,"имя81","8 378 892 89 43","name_81@post.ru","Улица 81",59.938803,59.938800));


        return cars;
    }







}
