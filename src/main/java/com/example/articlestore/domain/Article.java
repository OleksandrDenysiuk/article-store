package com.example.articlestore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Article")
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Byte[] image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private Date date;

    @ManyToMany
    @JoinTable(name = "article_userId",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likedUsers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(author, article.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author);
    }

    public String getFormattedDate(){
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy", new Locale("en"));
        return df.format(getDate());
    }

    public boolean isLikedUser(User user){
        for(User likedUser : likedUsers){
            if(likedUser.equals(user)){
                return true;
            }
        }
        return false;
    }
}
