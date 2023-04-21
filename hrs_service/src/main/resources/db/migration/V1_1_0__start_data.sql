CREATE TABLE IF NOT EXISTS public.calls
(
    id            BIGSERIAL PRIMARY KEY,
    number_phone  VARCHAR(20) NOT NULL UNIQUE,
    tariff_id  BIGINT,
    total_cost    DOUBLE PRECISION NOT NULL,
    monetary_unit VARCHAR(3) NOT NULL
);
CREATE TABLE IF NOT EXISTS public.pay_load
(
    id          BIGSERIAL PRIMARY KEY,
    call_type   VARCHAR(2) NOT NULL,
    start_time  TIMESTAMP NOT NULL,
    end_time    TIMESTAMP NOT NULL,
    duration    TIME NOT NULL,
    cost        DOUBLE PRECISION NOT NULL ,
    call_id   BIGINT CONSTRAINT fk_call_id_pay_load REFERENCES public.calls
);

CREATE TABLE IF NOT EXISTS public.tariffs
(
    id            BIGSERIAL PRIMARY KEY,
    tariff_index  VARCHAR(2) NOT NULL,
    name          VARCHAR(40) NOT NULL UNIQUE,
    price_rub_min DOUBLE PRECISION NOT NULL
);


INSERT INTO public.calls (number_phone, tariff_id, total_cost, monetary_unit)
VALUES ('77297341121', 1, 40,'rub'),
       ('77297341122', 2, 40,'rub'),
       ('77297341123', 3, 40,'rub'),
       ('77297341124', 1, 40,'rub'),
       ('77297341125', 2, 40,'rub'),
       ('77297341126', 3, 40,'rub'),
       ('77297341127', 1, 40,'rub'),
       ('77297341128', 2, 40,'rub'),
       ('77297341129', 3, 40,'rub'),
       ('77297341120', 1, 40,'rub');

INSERT INTO public.pay_load (call_type, start_time, end_time, duration, cost, call_id)
VALUES ('01','2023-04-01 18:15:20','2023-04-01 18:20:20','00:05:00', 12,1);

INSERT INTO public.tariffs (tariff_index, name, price_rub_min)
VALUES ('06', 'Безлимитный', 1.0),
       ('03', 'Поминутный', 1.5),
       ('11', 'Стандартный', 0.5);
