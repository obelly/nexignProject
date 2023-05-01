CREATE TABLE IF NOT EXISTS public.calls
(
    id            BIGSERIAL PRIMARY KEY,
    number_phone  VARCHAR(20) NOT NULL UNIQUE,
    tariff        VARCHAR NOT NULL,
    total_cost    DOUBLE PRECISION,
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



