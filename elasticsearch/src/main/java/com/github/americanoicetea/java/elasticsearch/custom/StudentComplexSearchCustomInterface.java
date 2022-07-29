package com.github.americanoicetea.java.elasticsearch.custom;

import java.io.IOException;
import java.util.List;

import com.github.americanoicetea.java.elasticsearch.index.StudentIndex;
import com.github.americanoicetea.java.elasticsearch.index.subindex.Contact;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

public interface StudentComplexSearchCustomInterface {

    List<StudentIndex> findByContact(Contact contact);

}
