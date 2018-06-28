SELECT d.sourceid, s.id, s.text, hk.*
FROM document d, sentence s, hepatotoxicityterm_sentence hs, hepatotoxkeyword hk
where d.id=s.document_id and hs.sentence_id=s.id and hk.id=hs.hepatotoxicityterm_id


SELECT d.sourceid, s.id, s.text, cc.*
FROM document d, sentence s, chemicalcompound_sentence cs, compounddict cc
where d.id=s.document_id and cs.sentence_id=s.id and cs.chemicalcompound_id=cc.id


SELECT d.sourceid, s.id, s.text, cc.*
FROM document d, sentence s, chemicalcompound_sentence cs, compounddict cc
where d.id=s.document_id and cs.sentence_id=s.id and cs.chemicalcompound_id=cc.id



SELECT d.sourceid, s.id, s.text, cc.*
FROM document d, sentence s, cytochrome_sentence cs, cytochrome cc
where d.id=s.document_id and cs.sentence_id=s.id and cs.cytochrome_id=cc.id



SELECT d.sourceid, s.id, s.text, c.*, cd.*
FROM document d, sentence s, chemicalcompound_cytochrome_sentence cs, cytochrome c, compounddict cd
where d.id=s.document_id and cs.sentence_id=s.id and cs.cytochrome_id=c.id and cd.id=cs.chemicalcompound_id