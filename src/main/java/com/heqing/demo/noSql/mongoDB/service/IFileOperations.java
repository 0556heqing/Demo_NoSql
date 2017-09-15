package com.heqing.demo.noSql.mongoDB.service;

import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

/**
 * Created by heqing on 2017/9/14.
 */
public interface IFileOperations extends IDataBaseOperations {

    /**
     * 该方法用于根据文件Id 返回文件，如果有多个则只返回第一个
     * @param id 文件id
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public GridFSDBFile getFileById(Object id);

    /**
     * 该方法用于根据文件名返回文件，如果有多个则只返回第一个
     * @param fileName 文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public GridFSDBFile getFileByName(String fileName);

    /**
     * 该方法用于将文件保存在 MongoDB 中
     * @param file 需要保存的文件
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public boolean write(File file);

    /**
     * 该方法用于将文件保存在 MongoDB 中
     * @param filePath 文件地址
     * @param fileName 文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public boolean write(String filePath, String fileName);

    /**
     * 该方法用于将保存在 MongoDB 中的文件输出到指定地址
     * @param filePath 需要保存的地址
     * @param fileName 保存在 MongoDB 中的文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public boolean read(String filePath, String fileName);

    /**
     * 该方法用于将保存在 MongoDB 中的文件输出到指定地址
     * @param gridFSDBFile 文件地址
     * @param filePath 需要保存的地址
     * @param fileName 保存在 MongoDB 中的文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    public boolean read(GridFSDBFile gridFSDBFile, String filePath, String fileName);

    /**
     * 该方法用于根据文件 Id 删除保存在 MongoDB 中的文件
     * @param id 文件Id
     */
    public void deleteByFileId(String id);

    /**
     * 该方法用于根据文件文件名删除保存在 MongoDB 中的文件
     * @param fileName 文件名
     */
    public void deleteByFileName(String fileName);
}
