package com.github.americanoicetea.java.elasticsearch.custom;

import java.io.IOException;
import java.util.List;

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
     * search by contact domain
     * 
     * @param contact
     * @return
     * @throws IOException
     * @throws ElasticsearchException
     */
    List<StudentIndex> findByContact(Contact contact) throws ElasticsearchException, IOException {
        var q = SearchRequest.of(sq -> {
            sq.index("student-index")
                    .query(
                            Query.of(query -> {
                                query.bool(BoolQuery.of(bool -> {
                                    bool.filter(filter -> {
                                        return filter.nested(nested -> {
                                            return nested.path("contact")
                                                    .query(nq -> {
                                                        return nq.bool(nqb -> {
                                                            return nqb.must(Query.of(TermQuery -> {
                                                                return TermQuery.term(nqbTerm -> {
                                                                    return nqbTerm
                                                                            .field("contact.province").value("BKK");
                                                                });
                                                            }));
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
        client.search(q, StudentIndex.class);
        return null;
    }

    public static void main(String[] args) {
        var q = SearchRequest.of(sq -> {
            sq.index("student-index")
                    .query(
                            Query.of(query -> {
                                query.bool(BoolQuery.of(bool -> {
                                    bool.filter(filter -> {
                                        return filter.nested(nested -> {
                                            return nested.path("contact")
                                                    .query(nq -> {
                                                        return nq.bool(nqb -> {
                                                            return nqb.must(Query.of(TermQuery -> {
                                                                return TermQuery.term(nqbTerm -> {
                                                                    return nqbTerm
                                                                            .field("contact.province").value("BKK");
                                                                });
                                                            }));
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
        System.out.println(q.toString());
    }
}
