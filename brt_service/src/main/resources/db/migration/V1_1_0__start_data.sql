CREATE TABLE IF NOT EXISTS public.tariffs
(
    id            BIGSERIAL PRIMARY KEY,
    tariff_number VARCHAR(2)       NOT NULL,
    name          VARCHAR(40)      NOT NULL UNIQUE,
    price_rub_min DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS public.abonents
(
    id           BIGSERIAL PRIMARY KEY,
    balance      DOUBLE PRECISION NOT NULL,
    number_phone VARCHAR(20)      NOT NULL UNIQUE,
    is_updated   BOOLEAN,
    tariff_id    BIGINT
        CONSTRAINT fk_tariff_id_abonents REFERENCES public.tariffs
);

INSERT INTO public.tariffs (tariff_number, name, price_rub_min)
VALUES ('06', 'UNLIMITED', 1.0),
       ('03', 'PER_MINUTE', 1.5),
       ('11', 'REGULAR', 0.5);

INSERT INTO public.abonents (number_phone, tariff_id, balance)
VALUES ('4283832220', 2, 883),
       ('5607342156', 3, 277),
       ('1088558037', 3, 1255),
       ('3301704460', 3, 842),
       ('9858884831', 1, 447),
       ('3436916135', 3, 426),
       ('9459161953', 3, 380),
       ('2942324046', 3, 172),
       ('6616278504', 2, 5),
       ('3816928457', 3, 1417),
       ('7006150160', 1, 1037),
       ('2868796526', 1, 1017),
       ('7182799681', 2, 99),
       ('8859727223', 1, 1467),
       ('1299136688', 1, 904),
       ('5715434604', 1, 387),
       ('3084197744', 2, 1095),
       ('5792918037', 2, 487),
       ('3601910463', 1, 764),
       ('2357011142', 3, 1418),
       ('6716647117', 3, 1161),
       ('8922964803', 1, 1491),
       ('6528196230', 1, 271),
       ('6932618904', 3, 287),
       ('5992442611', 1, 978),
       ('2817166480', 1, 1404),
       ('2765506460', 3, 992),
       ('5331069877', 1, 615),
       ('5679179556', 3, 608),
       ('6936093063', 2, 300),
       ('3798909350', 3, 681),
       ('2925356006', 3, 283),
       ('9599895900', 1, 1369),
       ('1198615601', 2, 371),
       ('1404274175', 1, 977),
       ('1668214755', 2, 476),
       ('9133360793', 1, 154),
       ('8108449322', 2, 1391),
       ('2653961152', 1, 1286),
       ('6496216594', 3, 384),
       ('4032341639', 2, 796),
       ('8191309056', 2, 518),
       ('5332434532', 1, 867),
       ('2054365934', 3, 267),
       ('1225373642', 3, 1078),
       ('1249860274', 3, 918),
       ('8343472075', 1, 1114),
       ('5776318429', 2, 580),
       ('9958989320', 1, 466),
       ('9152604901', 3, 265),
       ('3607843941', 2, 449),
       ('7482946128', 1, 208),
       ('4995058752', 3, 1235),
       ('5566503685', 2, 271),
       ('2299147613', 1, 542),
       ('6937778231', 1, 179),
       ('7605718402', 1, 1207),
       ('7898237209', 1, 126),
       ('9836186261', 1, 622),
       ('3299446928', 1, 636),
       ('1331813325', 1, 627),
       ('2079517044', 3, 128),
       ('8453236187', 1, 876),
       ('5038577585', 2, 271),
       ('1925974466', 2, 133),
       ('2048145878', 1, 505),
       ('2172087487', 3, 195),
       ('4809978215', 3, 953),
       ('9577733565', 1, 1135),
       ('9811387869', 1, 1449),
       ('4157774226', 1, 1435),
       ('1822314133', 1, 149),
       ('5052876781', 3, 1339),
       ('4847394130', 2, 1258),
       ('7481651238', 3, 780),
       ('2214964391', 1, 635),
       ('2533874332', 2, 1107),
       ('3439090214', 2, 791),
       ('4131793715', 1, 1133),
       ('5109665773', 1, 1218),
       ('4509754841', 1, 555),
       ('2986430368', 3, 1260),
       ('7325828976', 3, 950),
       ('7721945944', 1, 971),
       ('4518464148', 1, 918),
       ('1719868738', 2, 734),
       ('9671821221', 1, 413),
       ('6083124367', 3, 627),
       ('9532068584', 2, 325),
       ('4262029221', 1, 254),
       ('8441740394', 1, 77),
       ('2818764420', 3, 1045),
       ('8212131757', 2, 1067),
       ('4949816985', 1, 1234),
       ('2192837248', 1, 864),
       ('7378229792', 1, 1500),
       ('1454410923', 3, 413),
       ('4487986442', 3, 847),
       ('7319525139', 1, 1226),
       ('3637032864', 3, 1344),
       ('2619953458', 3, 823),
       ('3095214874', 2, 74),
       ('6684080939', 3, 423),
       ('2176869861', 1, 221),
       ('9037622846', 1, 667),
       ('2822482616', 1, 1180),
       ('8085711400', 3, 588),
       ('5331605512', 1, 413),
       ('2779160989', 1, 1153),
       ('9931941471', 2, 582),
       ('1168568974', 1, 851),
       ('1359506323', 3, 88),
       ('1288486309', 2, 1167),
       ('5573488662', 1, 1355),
       ('6532683735', 2, 1414),
       ('6073357393', 2, 1480),
       ('1341651621', 1, 1363),
       ('7244926099', 1, 824),
       ('2071406166', 3, 1155),
       ('3984631669', 3, 134),
       ('5537232718', 2, 912),
       ('2798460748', 1, 1423),
       ('6912788215', 3, 599),
       ('4428623037', 3, 1061),
       ('5532262739', 2, 1162),
       ('5611336104', 2, 1199),
       ('4862963747', 2, 1332),
       ('2956263213', 2, 72),
       ('8824725106', 1, 1347),
       ('4485203498', 1, 9),
       ('9635953687', 3, 1337),
       ('1179575148', 2, 351),
       ('5051304987', 2, 1364),
       ('4475124008', 1, 1471),
       ('2897537638', 3, 708),
       ('3463021862', 3, 398),
       ('8358269001', 3, 19),
       ('8202912542', 3, 939),
       ('4845949952', 3, 211),
       ('1473313581', 1, 423),
       ('2605374432', 3, 246),
       ('2894305556', 2, 837),
       ('9487031843', 2, 657),
       ('2514195857', 1, 104),
       ('4727867667', 3, 852),
       ('7378876890', 2, 263),
       ('9152623888', 3, 347),
       ('4323582337', 3, 203),
       ('2404062343', 1, 413),
       ('5062869625', 1, 770),
       ('2305683034', 2, 1346),
       ('7951360409', 1, 796),
       ('9013179964', 1, 44),
       ('4182743908', 1, 1172),
       ('3038762170', 1, 1315),
       ('9972367307', 2, 1053),
       ('2726974489', 2, 960),
       ('1896760671', 1, 780),
       ('8207078714', 1, 734),
       ('7748229346', 2, 923),
       ('4437501085', 2, 888),
       ('8305449883', 3, 839),
       ('7197750322', 2, 224),
       ('6132566770', 2, 887),
       ('8473585186', 3, 623),
       ('8253472628', 3, 15),
       ('2096535967', 1, 613),
       ('1322997426', 2, 905),
       ('8106700139', 2, 1313),
       ('5641436303', 2, 1148),
       ('1628078722', 1, 947),
       ('2924299538', 1, 788),
       ('6159976448', 2, 969),
       ('8943916666', 1, 482),
       ('8656716878', 1, 63),
       ('2242080878', 2, 870),
       ('9508743283', 2, 794),
       ('4481656468', 2, 717),
       ('8368493033', 3, 228),
       ('2644702581', 2, 400),
       ('4988279169', 1, 347),
       ('7849074394', 1, 766),
       ('6245537174', 1, 1013),
       ('1434470837', 3, 194),
       ('1905582373', 2, 665),
       ('3729096324', 3, 726),
       ('3392905949', 1, 1287),
       ('9788945560', 1, 1322),
       ('2463890103', 3, 1244),
       ('4864474690', 2, 690),
       ('8446594889', 1, 1231),
       ('2142755289', 1, 272),
       ('3641932052', 2, 339),
       ('3874481076', 2, 385),
       ('8083717005', 1, 1031),
       ('2284134414', 1, 439),
       ('8747701959', 2, 106),
       ('3349945345', 1, 1378),
       ('6846239431', 1, 1341),
       ('1672545720', 1, 436);

