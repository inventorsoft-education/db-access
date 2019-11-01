--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 11.3

-- Started on 2019-11-01 18:43:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "messageDb";
--
-- TOC entry 2156 (class 1262 OID 16729)
-- Name: messageDb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "messageDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';


ALTER DATABASE "messageDb" OWNER TO postgres;

\connect "messageDb"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16732)
-- Name: mail_message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mail_message (
    message_id bigint NOT NULL,
    sent boolean NOT NULL,
    recipient character varying(40) NOT NULL,
    subject character varying(60) NOT NULL,
    text character varying(500) NOT NULL,
    send_date character varying(16) NOT NULL
);


ALTER TABLE public.mail_message OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16730)
-- Name: MySimpleMailMessage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."MySimpleMailMessage_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."MySimpleMailMessage_id_seq" OWNER TO postgres;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 196
-- Name: MySimpleMailMessage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."MySimpleMailMessage_id_seq" OWNED BY public.mail_message.message_id;


--
-- TOC entry 2025 (class 2604 OID 16735)
-- Name: mail_message message_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mail_message ALTER COLUMN message_id SET DEFAULT nextval('public."MySimpleMailMessage_id_seq"'::regclass);


--
-- TOC entry 2150 (class 0 OID 16732)
-- Dependencies: 197
-- Data for Name: mail_message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mail_message (message_id, sent, recipient, subject, text, send_date) FROM stdin;
\.


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 196
-- Name: MySimpleMailMessage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."MySimpleMailMessage_id_seq"', 11, true);


--
-- TOC entry 2027 (class 2606 OID 16740)
-- Name: mail_message MySimpleMailMessage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mail_message
    ADD CONSTRAINT "MySimpleMailMessage_pkey" PRIMARY KEY (message_id);


-- Completed on 2019-11-01 18:43:13

--
-- PostgreSQL database dump complete
--

