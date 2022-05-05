package com.rrpvm.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/*
1. выбор имени и айди запроса по айди банера
select categories.request_id, categories.name from  categories join banners_categories bc on categories.id = bc.categories_id where banner_id = ?
* */
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, length = 255)
    @Size(min = 4, max = 255)
    private String name;
    @Column(name = "request_id", unique = true)
    private String requestId;

    @ManyToMany(targetEntity = Banner.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "banners_categories",
            joinColumns = @JoinColumn(name = "banner_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    @JsonIgnore////break infinity loop
    private List<Banner> banner;

    public Category() {
    }

    public Category(Integer id, String name, String requestId, List<Banner> banner) {
        this.id = id;
        this.name = name;
        this.requestId = requestId;
        this.banner = banner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }
}
