package com.github.americanoicetea.java.elasticsearch.custom;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.americanoicetea.java.elasticsearch.index.StudentIndex;
import com.github.americanoicetea.java.elasticsearch.index.subindex.Contact;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import co.elastic.clients.elasticsearch.core.SearchRequest;

public class StudentComplexSearchCustom implements StudentComplexSearchCustomInterface {

    @Autowired
    private ElasticsearchClient client;

    /**
     * search by contact domain all field not a mandatory, if field is not null then
     * should be in search context
     * 
     * @param contact
     * @return
     * @throws IOException
     * @throws ElasticsearchException
     */
    public List<StudentIndex> findByContact(Contact contact) {
        var q = SearchRequest.of(sq -> {
            sq.index("student-index")
                    .query(Query.of(query -> {
                        query.bool(BoolQuery.of(bool -> {
                            bool.filter(filter -> {
                                return filter.nested(nested -> {
                                    return nested.path("contact")
                                            .query(nq -> {
                                                return nq.bool(nqb -> {
                                                    if (contact.getProvince() != null) {
                                                        nqb.must(Query.of(nqbq -> {
                                                            return nqbq.term(nqbqTerm -> {
                                                                return nqbqTerm
                                                                        .field("contact.province")
                                                                        .value(contact.getProvince());
                                                            });
                                                        }));
                                                    }
                                                    if (contact.getPostalCode() != null) {
                                                        nqb.must(Query.of(nqbq -> {
                                                            return nqbq.term(nqbqTerm -> {
                                                                return nqbqTerm.field("contact.postalCode")
                                                                        .value(contact.getPostalCode());
                                                            });
                                                        }));
                                                    }
                                                    if (contact.getDistrict() != null) {
                                                        nqb.must(Query.of(nqbq -> {
                                                            return nqbq.term(nqbqTerm -> {
                                                                return nqbqTerm.field("contact.district")
                                                                        .value(contact.getDistrict());
                                                            });
                                                        }));
                                                    }
                                                    if (contact.getPhoneNumber() != null) {
                                                        nqb.must(Query.of(nqbq -> {
                                                            return nqbq.queryString(nqbqStrQuery -> {
                                                                return nqbqStrQuery.fields("contact.phoneNumber")
                                                                        .query(contact.getPhoneNumber() + "*");
                                                            });
                                                        }));
                                                    }
                                                    if (contact.getHomeAddress() != null) {
                                                        nqb.must(Query.of(nqbq -> {
                                                            return nqbq.queryString(nqbqStrQuery -> {
                                                                return nqbqStrQuery.fields("contact.homeAddress")
                                                                        .query("*" + contact.getHomeAddress() + "*");
                                                            });
                                                        }));
                                                    }
                                                    return nqb;
                                                });
                                            });
                                });
                            });
                            return bool;
                        }));
                        return query;
                    }));

            return sq;
        });
        try {
            return client.search(q, StudentIndex.class).hits().hits().stream().map(hit -> hit.source())
                    .collect(Collectors.toList());
        } catch (ElasticsearchException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
