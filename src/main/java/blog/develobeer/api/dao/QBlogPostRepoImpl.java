package blog.develobeer.api.dao;

import blog.develobeer.api.domain.BlogPost;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

import static blog.develobeer.api.domain.QBlogBoard.blogBoard;
import static blog.develobeer.api.domain.QBlogPost.blogPost;

public class QBlogPostRepoImpl implements QBlogPostRepo {
    private final JPAQueryFactory blogQueryFactory;

    @Autowired
    public QBlogPostRepoImpl(
            @Qualifier("blogQueryFactory") JPAQueryFactory blogQueryFactory) {
        this.blogQueryFactory = blogQueryFactory;
    }

    @Override
    public Page<BlogPost> getAll(Pageable pageable) {
        QueryResults<BlogPost> result = blogQueryFactory
                .select(blogPost)
                .from(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardID))
                .where(
                        blogBoard.isOpen.eq(true)
                                .and(blogPost.isDelete.eq(false))
                )
                .orderBy(blogPost.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public List<BlogPost> getRecentPost(Timestamp start, Timestamp end) {
        return blogQueryFactory
                .selectFrom(blogPost)
                .where(
                        blogPost.isDelete.eq(false)
                                .and(blogPost.regDate.between(start, end))
                )
                .limit(3)
                .fetch();
    }

    @Override
    public List<BlogPost> getPopularPost() {
        return blogQueryFactory
                .selectFrom(blogPost)
                .where(blogPost.isDelete.eq(false))
                .orderBy(blogPost.hits.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public Page<BlogPost> getPost(Pageable pageable) {
        QueryResults<BlogPost> result = blogQueryFactory
                .selectFrom(blogPost)
                .where(blogPost.isDelete.eq(false))
                .orderBy(blogPost.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<BlogPost> getPostList(int boardId, Pageable pageable) {
        QueryResults<BlogPost> result = blogQueryFactory
                .select(blogPost)
                .from(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardID))
                .where(
                        blogBoard.boardId.eq(boardId)
                                .and(blogBoard.isOpen.eq(true))
                                .and(blogPost.isDelete.eq(false))
                )
                .orderBy(blogPost.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
