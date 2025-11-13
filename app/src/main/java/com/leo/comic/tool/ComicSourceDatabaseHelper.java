package com.leo.comic.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.leo.comic.bean.ComicSource;

import java.util.ArrayList;
import java.util.List;

public class ComicSourceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "comic_source_database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMIC_SOURCES = "comic_sources";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOK_DELAY_TIME = "book_delay_time";
    public static final String COLUMN_BOOK_SINGLE_THREAD = "book_single_thread";
    public static final String COLUMN_BOOK_SOURCE_GROUP = "book_source_group";
    public static final String COLUMN_BOOK_SOURCE_NAME = "book_source_name";
    public static final String COLUMN_BOOK_SOURCE_TYPE = "book_source_type";
    public static final String COLUMN_BOOK_SOURCE_URL = "book_source_url";
    public static final String COLUMN_ENABLE = "enable";
    public static final String COLUMN_HTTP_USER_AGENT = "http_user_agent";
    public static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";
    public static final String COLUMN_LOGIN_URL = "login_url";
    public static final String COLUMN_LOGIN_URL_RESULT = "login_url_result";
    public static final String COLUMN_RULE_BOOK_AUTHOR = "rule_book_author";
    public static final String COLUMN_RULE_BOOK_CONTENT = "rule_book_content";
    public static final String COLUMN_RULE_BOOK_CONTENT_DECODER = "rule_book_content_decoder";
    public static final String COLUMN_RULE_BOOK_KIND = "rule_book_kind";
    public static final String COLUMN_RULE_BOOK_LAST_CHAPTER = "rule_book_last_chapter";
    public static final String COLUMN_RULE_BOOK_NAME = "rule_book_name";
    public static final String COLUMN_RULE_BOOK_URL_PATTERN = "rule_book_url_pattern";
    public static final String COLUMN_RULE_CHAPTER_ID = "rule_chapter_id";
    public static final String COLUMN_RULE_CHAPTER_LIST = "rule_chapter_list";
    public static final String COLUMN_RULE_CHAPTER_NAME = "rule_chapter_name";
    public static final String COLUMN_RULE_CHAPTER_PARENT_ID = "rule_chapter_parent_id";
    public static final String COLUMN_RULE_CHAPTER_PARENT_NAME = "rule_chapter_parent_name";
    public static final String COLUMN_RULE_CHAPTER_URL = "rule_chapter_url";
    public static final String COLUMN_RULE_CHAPTER_URL_NEXT = "rule_chapter_url_next";
    public static final String COLUMN_RULE_CONTENT_URL = "rule_content_url";
    public static final String COLUMN_RULE_CONTENT_URL_NEXT = "rule_content_url_next";
    public static final String COLUMN_RULE_COVER_DECODER = "rule_cover_decoder";
    public static final String COLUMN_RULE_COVER_URL = "rule_cover_url";
    public static final String COLUMN_RULE_FIND_URL = "rule_find_url";
    public static final String COLUMN_RULE_INTRODUCE = "rule_introduce";
    public static final String COLUMN_RULE_SEARCH_AUTHOR = "rule_search_author";
    public static final String COLUMN_RULE_SEARCH_COVER_DECODER = "rule_search_cover_decoder";
    public static final String COLUMN_RULE_SEARCH_COVER_URL = "rule_search_cover_url";
    public static final String COLUMN_RULE_SEARCH_KIND = "rule_search_kind";
    public static final String COLUMN_RULE_SEARCH_LAST_CHAPTER = "rule_search_last_chapter";
    public static final String COLUMN_RULE_SEARCH_LIST = "rule_search_list";
    public static final String COLUMN_RULE_SEARCH_NAME = "rule_search_name";
    public static final String COLUMN_RULE_SEARCH_NOTE_URL = "rule_search_note_url";
    public static final String COLUMN_RULE_SEARCH_URL = "rule_search_url";
    public static final String COLUMN_RULE_SEARCH_URL_NEXT = "rule_search_url_next";
    public static final String COLUMN_SERIAL_NUMBER = "serial_number";
    public static final String COLUMN_SOURCE_REMARK = "source_remark";
    public static final String COLUMN_WEIGHT = "weight";

    private static final String CREATE_TABLE_COMIC_SOURCES =
            "CREATE TABLE " + TABLE_COMIC_SOURCES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BOOK_DELAY_TIME + " TEXT, " +
                    COLUMN_BOOK_SINGLE_THREAD + " TEXT, " +
                    COLUMN_BOOK_SOURCE_GROUP + " TEXT, " +
                    COLUMN_BOOK_SOURCE_NAME + " TEXT, " +
                    COLUMN_BOOK_SOURCE_TYPE + " TEXT, " +
                    COLUMN_BOOK_SOURCE_URL + " TEXT, " +
                    COLUMN_ENABLE + " INTEGER, " +
                    COLUMN_HTTP_USER_AGENT + " TEXT, " +
                    COLUMN_LAST_UPDATE_TIME + " INTEGER, " +
                    COLUMN_LOGIN_URL + " TEXT, " +
                    COLUMN_LOGIN_URL_RESULT + " TEXT, " +
                    COLUMN_RULE_BOOK_AUTHOR + " TEXT, " +
                    COLUMN_RULE_BOOK_CONTENT + " TEXT, " +
                    COLUMN_RULE_BOOK_CONTENT_DECODER + " TEXT, " +
                    COLUMN_RULE_BOOK_KIND + " TEXT, " +
                    COLUMN_RULE_BOOK_LAST_CHAPTER + " TEXT, " +
                    COLUMN_RULE_BOOK_NAME + " TEXT, " +
                    COLUMN_RULE_BOOK_URL_PATTERN + " TEXT, " +
                    COLUMN_RULE_CHAPTER_ID + " TEXT, " +
                    COLUMN_RULE_CHAPTER_LIST + " TEXT, " +
                    COLUMN_RULE_CHAPTER_NAME + " TEXT, " +
                    COLUMN_RULE_CHAPTER_PARENT_ID + " TEXT, " +
                    COLUMN_RULE_CHAPTER_PARENT_NAME + " TEXT, " +
                    COLUMN_RULE_CHAPTER_URL + " TEXT, " +
                    COLUMN_RULE_CHAPTER_URL_NEXT + " TEXT, " +
                    COLUMN_RULE_CONTENT_URL + " TEXT, " +
                    COLUMN_RULE_CONTENT_URL_NEXT + " TEXT, " +
                    COLUMN_RULE_COVER_DECODER + " TEXT, " +
                    COLUMN_RULE_COVER_URL + " TEXT, " +
                    COLUMN_RULE_FIND_URL + " TEXT, " +
                    COLUMN_RULE_INTRODUCE + " TEXT, " +
                    COLUMN_RULE_SEARCH_AUTHOR + " TEXT, " +
                    COLUMN_RULE_SEARCH_COVER_DECODER + " TEXT, " +
                    COLUMN_RULE_SEARCH_COVER_URL + " TEXT, " +
                    COLUMN_RULE_SEARCH_KIND + " TEXT, " +
                    COLUMN_RULE_SEARCH_LAST_CHAPTER + " TEXT, " +
                    COLUMN_RULE_SEARCH_LIST + " TEXT, " +
                    COLUMN_RULE_SEARCH_NAME + " TEXT, " +
                    COLUMN_RULE_SEARCH_NOTE_URL + " TEXT, " +
                    COLUMN_RULE_SEARCH_URL + " TEXT, " +
                    COLUMN_RULE_SEARCH_URL_NEXT + " TEXT, " +
                    COLUMN_SERIAL_NUMBER + " INTEGER, " +
                    COLUMN_SOURCE_REMARK + " TEXT, " +
                    COLUMN_WEIGHT + " INTEGER);";

    public ComicSourceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COMIC_SOURCES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMIC_SOURCES);
        onCreate(db);
    }

    public long insertComicSource(ComicSource comicSource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOK_DELAY_TIME, comicSource.getBookDelayTime());
        values.put(COLUMN_BOOK_SINGLE_THREAD, comicSource.getBookSingleThread());
        values.put(COLUMN_BOOK_SOURCE_GROUP, comicSource.getBookSourceGroup());
        values.put(COLUMN_BOOK_SOURCE_NAME, comicSource.getBookSourceName());
        values.put(COLUMN_BOOK_SOURCE_TYPE, comicSource.getBookSourceType());
        values.put(COLUMN_BOOK_SOURCE_URL, comicSource.getBookSourceUrl());
        values.put(COLUMN_ENABLE, comicSource.isEnable() ? 1 : 0);
        values.put(COLUMN_HTTP_USER_AGENT, comicSource.getHttpUserAgent());
        values.put(COLUMN_LAST_UPDATE_TIME, comicSource.getLastUpdateTime());
        values.put(COLUMN_LOGIN_URL, comicSource.getLoginUrl());
        values.put(COLUMN_LOGIN_URL_RESULT, comicSource.getLoginUrlResult());
        values.put(COLUMN_RULE_BOOK_AUTHOR, comicSource.getRuleBookAuthor());
        values.put(COLUMN_RULE_BOOK_CONTENT, comicSource.getRuleBookContent());
        values.put(COLUMN_RULE_BOOK_CONTENT_DECODER, comicSource.getRuleBookContentDecoder());
        values.put(COLUMN_RULE_BOOK_KIND, comicSource.getRuleBookKind());
        values.put(COLUMN_RULE_BOOK_LAST_CHAPTER, comicSource.getRuleBookLastChapter());
        values.put(COLUMN_RULE_BOOK_NAME, comicSource.getRuleBookName());
        values.put(COLUMN_RULE_BOOK_URL_PATTERN, comicSource.getRuleBookUrlPattern());
        values.put(COLUMN_RULE_CHAPTER_ID, comicSource.getRuleChapterId());
        values.put(COLUMN_RULE_CHAPTER_LIST, comicSource.getRuleChapterList());
        values.put(COLUMN_RULE_CHAPTER_NAME, comicSource.getRuleChapterName());
        values.put(COLUMN_RULE_CHAPTER_PARENT_ID, comicSource.getRuleChapterParentId());
        values.put(COLUMN_RULE_CHAPTER_PARENT_NAME, comicSource.getRuleChapterParentName());
        values.put(COLUMN_RULE_CHAPTER_URL, comicSource.getRuleChapterUrl());
        values.put(COLUMN_RULE_CHAPTER_URL_NEXT, comicSource.getRuleChapterUrlNext());
        values.put(COLUMN_RULE_CONTENT_URL, comicSource.getRuleContentUrl());
        values.put(COLUMN_RULE_CONTENT_URL_NEXT, comicSource.getRuleContentUrlNext());
        values.put(COLUMN_RULE_COVER_DECODER, comicSource.getRuleCoverDecoder());
        values.put(COLUMN_RULE_COVER_URL, comicSource.getRuleCoverUrl());
        values.put(COLUMN_RULE_FIND_URL, comicSource.getRuleFindUrl());
        values.put(COLUMN_RULE_INTRODUCE, comicSource.getRuleIntroduce());
        values.put(COLUMN_RULE_SEARCH_AUTHOR, comicSource.getRuleSearchAuthor());
        values.put(COLUMN_RULE_SEARCH_COVER_DECODER, comicSource.getRuleSearchCoverDecoder());
        values.put(COLUMN_RULE_SEARCH_COVER_URL, comicSource.getRuleSearchCoverUrl());
        values.put(COLUMN_RULE_SEARCH_KIND, comicSource.getRuleSearchKind());
        values.put(COLUMN_RULE_SEARCH_LAST_CHAPTER, comicSource.getRuleSearchLastChapter());
        values.put(COLUMN_RULE_SEARCH_LIST, comicSource.getRuleSearchList());
        values.put(COLUMN_RULE_SEARCH_NAME, comicSource.getRuleSearchName());
        values.put(COLUMN_RULE_SEARCH_NOTE_URL, comicSource.getRuleSearchNoteUrl());
        values.put(COLUMN_RULE_SEARCH_URL, comicSource.getRuleSearchUrl());
        values.put(COLUMN_RULE_SEARCH_URL_NEXT, comicSource.getRuleSearchUrlNext());
        values.put(COLUMN_SERIAL_NUMBER, comicSource.getSerialNumber());
        values.put(COLUMN_SOURCE_REMARK, comicSource.getSourceRemark());
        values.put(COLUMN_WEIGHT, comicSource.getWeight());

        long id = db.insert(TABLE_COMIC_SOURCES, null, values);
        db.close();
        return id;
    }

    /**
     * 获取所有漫画源数据
     * @return 漫画源列表
     */
    public List<ComicSource> getAllComicSources() {
        List<ComicSource> comicSources = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_BOOK_DELAY_TIME,
                COLUMN_BOOK_SINGLE_THREAD,
                COLUMN_BOOK_SOURCE_GROUP,
                COLUMN_BOOK_SOURCE_NAME,
                COLUMN_BOOK_SOURCE_TYPE,
                COLUMN_BOOK_SOURCE_URL,
                COLUMN_ENABLE,
                COLUMN_HTTP_USER_AGENT,
                COLUMN_LAST_UPDATE_TIME,
                COLUMN_LOGIN_URL,
                COLUMN_LOGIN_URL_RESULT,
                COLUMN_RULE_BOOK_AUTHOR,
                COLUMN_RULE_BOOK_CONTENT,
                COLUMN_RULE_BOOK_CONTENT_DECODER,
                COLUMN_RULE_BOOK_KIND,
                COLUMN_RULE_BOOK_LAST_CHAPTER,
                COLUMN_RULE_BOOK_NAME,
                COLUMN_RULE_BOOK_URL_PATTERN,
                COLUMN_RULE_CHAPTER_ID,
                COLUMN_RULE_CHAPTER_LIST,
                COLUMN_RULE_CHAPTER_NAME,
                COLUMN_RULE_CHAPTER_PARENT_ID,
                COLUMN_RULE_CHAPTER_PARENT_NAME,
                COLUMN_RULE_CHAPTER_URL,
                COLUMN_RULE_CHAPTER_URL_NEXT,
                COLUMN_RULE_CONTENT_URL,
                COLUMN_RULE_CONTENT_URL_NEXT,
                COLUMN_RULE_COVER_DECODER,
                COLUMN_RULE_COVER_URL,
                COLUMN_RULE_FIND_URL,
                COLUMN_RULE_INTRODUCE,
                COLUMN_RULE_SEARCH_AUTHOR,
                COLUMN_RULE_SEARCH_COVER_DECODER,
                COLUMN_RULE_SEARCH_COVER_URL,
                COLUMN_RULE_SEARCH_KIND,
                COLUMN_RULE_SEARCH_LAST_CHAPTER,
                COLUMN_RULE_SEARCH_LIST,
                COLUMN_RULE_SEARCH_NAME,
                COLUMN_RULE_SEARCH_NOTE_URL,
                COLUMN_RULE_SEARCH_URL,
                COLUMN_RULE_SEARCH_URL_NEXT,
                COLUMN_SERIAL_NUMBER,
                COLUMN_SOURCE_REMARK,
                COLUMN_WEIGHT
        };

        Cursor cursor = db.query(
                TABLE_COMIC_SOURCES,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                ComicSource comicSource = new ComicSource();
                comicSource.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));

                comicSource.setBookDelayTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_DELAY_TIME)));
                comicSource.setBookSingleThread(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_SINGLE_THREAD)));
                comicSource.setBookSourceGroup(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_SOURCE_GROUP)));
                comicSource.setBookSourceName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_SOURCE_NAME)));
                comicSource.setBookSourceType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_SOURCE_TYPE)));
                comicSource.setBookSourceUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_SOURCE_URL)));
                comicSource.setEnable(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ENABLE)) == 1);
                comicSource.setHttpUserAgent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HTTP_USER_AGENT)));
                comicSource.setLastUpdateTime(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_LAST_UPDATE_TIME)));
                comicSource.setLoginUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGIN_URL)));
                comicSource.setLoginUrlResult(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGIN_URL_RESULT)));
                comicSource.setRuleBookAuthor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_AUTHOR)));
                comicSource.setRuleBookContent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_CONTENT)));
                comicSource.setRuleBookContentDecoder(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_CONTENT_DECODER)));
                comicSource.setRuleBookKind(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_KIND)));
                comicSource.setRuleBookLastChapter(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_LAST_CHAPTER)));
                comicSource.setRuleBookName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_NAME)));
                comicSource.setRuleBookUrlPattern(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_BOOK_URL_PATTERN)));
                comicSource.setRuleChapterId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_ID)));
                comicSource.setRuleChapterList(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_LIST)));
                comicSource.setRuleChapterName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_NAME)));
                comicSource.setRuleChapterParentId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_PARENT_ID)));
                comicSource.setRuleChapterParentName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_PARENT_NAME)));
                comicSource.setRuleChapterUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_URL)));
                comicSource.setRuleChapterUrlNext(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CHAPTER_URL_NEXT)));
                comicSource.setRuleContentUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CONTENT_URL)));
                comicSource.setRuleContentUrlNext(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_CONTENT_URL_NEXT)));
                comicSource.setRuleCoverDecoder(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_COVER_DECODER)));
                comicSource.setRuleCoverUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_COVER_URL)));
                comicSource.setRuleFindUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_FIND_URL)));
                comicSource.setRuleIntroduce(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_INTRODUCE)));
                comicSource.setRuleSearchAuthor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_AUTHOR)));
                comicSource.setRuleSearchCoverDecoder(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_COVER_DECODER)));
                comicSource.setRuleSearchCoverUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_COVER_URL)));
                comicSource.setRuleSearchKind(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_KIND)));
                comicSource.setRuleSearchLastChapter(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_LAST_CHAPTER)));
                comicSource.setRuleSearchList(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_LIST)));
                comicSource.setRuleSearchName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_NAME)));
                comicSource.setRuleSearchNoteUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_NOTE_URL)));
                comicSource.setRuleSearchUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_URL)));
                comicSource.setRuleSearchUrlNext(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RULE_SEARCH_URL_NEXT)));
                comicSource.setSerialNumber(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SERIAL_NUMBER)));
                comicSource.setSourceRemark(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOURCE_REMARK)));
                comicSource.setWeight(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT)));

                comicSources.add(comicSource);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return comicSources;
    }

    public int deleteComicSourceById(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_COMIC_SOURCES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }
}
