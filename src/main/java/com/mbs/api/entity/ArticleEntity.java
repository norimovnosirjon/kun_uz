package com.mbs.api.entity;

import com.mbs.api.entity.base.BaseEntity;
import com.mbs.api.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Entity
@Table(name = "article")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleEntity extends BaseEntity {
    String title;
    @Column(columnDefinition = "text")
    String description;
    @Column(columnDefinition = "text")
    String content;

    @Column(name = "shared_count")
    Long sharedCount;

    @Column(name = "image_id")
    String imageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    AttachEntity image;

    @Column(name = "region_id")
    String regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    RegionEntity region;

    @Column(name = "category_id")
    String categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    CategoryEntity category;

    @Column(name = "moderator_id")
    String moderatorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    ProfileEntity moderator;

    @Column(name = "publisher_id")
    String publisherId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    ProfileEntity publisher;

    @Enumerated(EnumType.STRING)
    ArticleStatus status;

    @Column(name = "published_date")
    LocalDateTime publishedDate;

    @Column(name = "view_count")
    Long viewCount;
}
