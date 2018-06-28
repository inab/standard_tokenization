drop table document cascade;
drop table document_sentence cascade;
drop table chemicalcompound_sentence cascade;
drop table hepatotoxicityterm_sentence cascade;
drop table cytochrome_sentence cascade;
drop table chemicalcompound_cytochrome_sentence cascade;
drop table sentence cascade;
drop table section cascade;

-- Table: document

CREATE TABLE document
(
  id SERIAL PRIMARY KEY,
  type varchar(31) NOT NULL,
  sourceId varchar(255) NOT NULL
);


-- Table: section

CREATE TABLE section (
  id SERIAL PRIMARY KEY,
  name varchar(255) NOT NULL,
  internalname varchar(255) NOT NULL
);


-- Table: sentence

CREATE TABLE sentence
(
  id SERIAL PRIMARY KEY,
  text varchar,
  document_id integer,
  section_id integer,
  CONSTRAINT fkc9dnaecd26krbtc89lnfplc8l FOREIGN KEY (section_id)
      REFERENCES section (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fklt57de34kwq60icdvyapi1o8q FOREIGN KEY (document_id)
      REFERENCES document (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: chemicalcompound_sentence

CREATE TABLE chemicalcompound_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  chemicalcompound_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (chemicalcompound_id)
      REFERENCES compounddict (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: hepatoxicityterm_sentence

CREATE TABLE hepatotoxicityterm_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  hepatotoxicityterm_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (hepatotoxicityterm_id)
      REFERENCES hepatotoxkeyword (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: cytochrome_sentence

CREATE TABLE cytochrome_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  cytochrome_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (cytochrome_id)
      REFERENCES cytochrome (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: cytochrome_sentence

CREATE TABLE chemicalcompound_cytochrome_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  chemicalcompound_id integer,
  cytochrome_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (cytochrome_id)
      REFERENCES cytochrome (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mx333o8qra3g FOREIGN KEY (chemicalcompound_id)
      REFERENCES compounddict (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);





-- Table: document_sentence

CREATE TABLE document_sentence
(
  document_id integer NOT NULL,
  sentences_id integer NOT NULL,
  CONSTRAINT fkduu441mvk9ihl8wvtnyc0yqbl FOREIGN KEY (document_id)
      REFERENCES document (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkt3xdamjok26uu8tclp1te1lmn FOREIGN KEY (sentences_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




