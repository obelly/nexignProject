CREATE TABLE IF NOT EXISTS public.calls
(
    id            BIGSERIAL PRIMARY KEY,
    number_phone  VARCHAR(20) NOT NULL UNIQUE,
    tariff        VARCHAR NOT NULL,
    total_cost    DOUBLE PRECISION NOT NULL,
    monetary_unit VARCHAR(3) NOT NULL
);
CREATE TABLE IF NOT EXISTS public.pay_loads
(
    id          BIGSERIAL PRIMARY KEY,
    call_type   SMALLINT NOT NULL,
    start_time  TIMESTAMP NOT NULL,
    end_time    TIMESTAMP NOT NULL,
    duration    TIME NOT NULL,
    cost        DOUBLE PRECISION NOT NULL ,
    call_id   BIGINT CONSTRAINT fk_call_id_pay_load REFERENCES public.calls
);

INSERT INTO public.calls (number_phone, tariff, total_cost, monetary_unit)
VALUES ('77297341121', 'UNLIMITED', 40,'rub'),
       ('77297341122', 'PER_MINUTE', 40,'rub'),
       ('77297341123', 'REGULAR', 40,'rub'),
       ('77297341124', 'UNLIMITED', 40,'rub'),
       ('77297341125', 'PER_MINUTE', 40,'rub'),
       ('77297341126', 'REGULAR', 40,'rub'),
       ('77297341127', 'UNLIMITED', 40,'rub'),
       ('77297341128', 'PER_MINUTE', 40,'rub'),
       ('77297341129', 'REGULAR', 40,'rub'),
       ('77297341120', 'UNLIMITED', 40,'rub');

INSERT INTO public.pay_loads (call_type, start_time, end_time, duration, cost, call_id)
VALUES ('01','2023-04-01 18:15:20','2023-04-01 18:20:20','00:05:00', 12,1);



