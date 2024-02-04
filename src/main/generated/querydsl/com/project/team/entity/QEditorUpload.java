package com.project.team.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEditorUpload is a Querydsl query type for EditorUpload
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEditorUpload extends EntityPathBase<EditorUpload> {

    private static final long serialVersionUID = 2029720842L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEditorUpload editorUpload = new QEditorUpload("editorUpload");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> editUploadSid = createNumber("editUploadSid", Long.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath pathUrl = createString("pathUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QEditorUpload(String variable) {
        this(EditorUpload.class, forVariable(variable), INITS);
    }

    public QEditorUpload(Path<? extends EditorUpload> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEditorUpload(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEditorUpload(PathMetadata metadata, PathInits inits) {
        this(EditorUpload.class, metadata, inits);
    }

    public QEditorUpload(Class<? extends EditorUpload> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

