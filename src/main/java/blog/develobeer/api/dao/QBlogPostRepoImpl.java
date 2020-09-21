package blog.develobeer.api.dao;

import blog.develobeer.api.domain.BlogPost;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static blog.develobeer.api.domain.QBlogBoard.blogBoard;
import static blog.develobeer.api.domain.QBlogPost.blogPost;

public class QBlogPostRepoImpl implements QBlogPostRepo {
    private final JPAQueryFactory blogQueryFactory;

    private static final int WIDGET_SHOW_COUNT = 3;

    @Autowired
    public QBlogPostRepoImpl(
            @Qualifier("blogQueryFactory") JPAQueryFactory blogQueryFactory) {
        this.blogQueryFactory = blogQueryFactory;
    }

    @Override
    public Page<BlogPost> getAll(Pageable pageable) {
        QueryResults<Tuple> result =  blogQueryFactory
                .select(blogPost, blogBoard.description)
                .from(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardId))
                .where(
                        blogBoard.isOpen.eq(true)
                                .and(blogPost.isDelete.eq(false))
                )
                .orderBy(blogPost.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<BlogPost> postDTOList = result.getResults().stream()
                .map(tuple -> {
                    BlogPost obj = tuple.get(blogPost);
                    obj.setBoardName(tuple.get(blogBoard.description));

                    return obj;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(postDTOList, pageable, result.getTotal());
    }

    @Override
    public List<BlogPost> getRecentPost(Timestamp start, Timestamp end) {
        return blogQueryFactory
                .selectFrom(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardId))
                .where(
                        blogPost.isDelete.eq(false)
                                .and(blogPost.regDate.between(start, end))
                        .and(blogBoard.isOpen.eq(true))
                )
                .limit(WIDGET_SHOW_COUNT)
                .fetch();
    }

    @Override
    public List<BlogPost> getPopularPost() {
        return blogQueryFactory
                .selectFrom(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardId))
                .where(
                        blogPost.isDelete.eq(false)
                                .and(blogBoard.isOpen.eq(true))
                )
                .orderBy(blogPost.hits.desc())
                .limit(WIDGET_SHOW_COUNT)
                .fetch();
    }

    @Override
    public Page<BlogPost> getPostList(int boardId, Pageable pageable) {
        QueryResults<Tuple> result = blogQueryFactory
                .select(blogPost, blogBoard.description)
                .from(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardId))
                .where(
                        blogBoard.boardId.eq(boardId)
                                .and(blogBoard.isOpen.eq(true))
                                .and(blogPost.isDelete.eq(false))
                )
                .orderBy(blogPost.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<BlogPost> postDTOList = result.getResults().stream()
                .map(tuple -> {
                    BlogPost obj = tuple.get(blogPost);
                    obj.setBoardName(tuple.get(blogBoard.description));

                    return obj;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(postDTOList, pageable, result.getTotal());
    }

    @Override
    public BlogPost getPostById(int postId) {
        Tuple tuple = blogQueryFactory
                .select(blogPost, blogBoard.description)
                .from(blogPost)
                .innerJoin(blogBoard)
                .on(blogBoard.boardId.eq(blogPost.boardId))
                .where(
                        blogPost.seq.eq(postId)
                                .and(blogPost.isDelete.eq(false))
                                .and(blogBoard.isOpen.eq(true))
                )
                .fetchOne();

        BlogPost result = tuple.get(blogPost);
        result.setBoardName(tuple.get(blogBoard.description));

        return result;
    }
}
