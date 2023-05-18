-- Table: public.transactions

-- DROP TABLE IF EXISTS public.transactions;

CREATE TABLE IF NOT EXISTS transactions
(
    id bigint NOT NULL,
    amount double precision NOT NULL,
    date date,
    description character varying(255),
    "from" character varying(255),
    name character varying(255),
    "to" character varying(255),
    type character varying(255),
    userid character varying(255),
    CONSTRAINT transactions_pkey PRIMARY KEY (id)
);

create sequence hibernate_sequence start 1 increment 1;