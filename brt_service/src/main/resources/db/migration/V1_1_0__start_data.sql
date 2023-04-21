-- CREATE TABLE IF NOT EXISTS public.tariffs
-- (
--     id            BIGSERIAL PRIMARY KEY,
--     tariff_index  VARCHAR(2) NOT NULL,
--     name          VARCHAR(40) NOT NULL UNIQUE,
--     price_rub_min DOUBLE PRECISION NOT NULL
-- );

CREATE TABLE IF NOT EXISTS public.abonents
(
    id        BIGSERIAL PRIMARY KEY,
    balance   DOUBLE PRECISION NOT NULL,
    phone     VARCHAR(20) NOT NULL UNIQUE
--     tariff_id BIGINT CONSTRAINT fk_tariff_id_abonents REFERENCES public.tariffs
);

-- INSERT INTO public.tariffs (tariff_index, name, price_rub_min)
-- VALUES ('06', 'Безлимитный', 1.0),
--        ('03', 'Поминутный', 1.5),
--        ('11', 'Стандартный', 0.5);

INSERT INTO public.abonents (balance, phone)
VALUES (1000, '77297341121'),
       (2000, '77297341122'),
       (5000, '77297341123'),
       (500, '77297341124'),
       (400, '77297341125'),
       (7000, '77297341126'),
       (2300, '77297341127'),
       (5600, '77297341128'),
       (1400, '77297341129'),
       (1200, '77297341120');