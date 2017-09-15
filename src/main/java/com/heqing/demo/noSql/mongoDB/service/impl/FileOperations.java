package com.heqing.demo.noSql.mongoDB.service.impl;

import com.heqing.demo.noSql.mongoDB.service.IFileOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by heqing on 2017/9/14.
 */
public class FileOperations extends DataBaseOperations implements IFileOperations {

    private static final Logger logger = LoggerFactory.getLogger(FileOperations.class);

    GridFS gridFS = null;

    public FileOperations(String dbName) {
        if(gridFS == null) {
            gridFS = new GridFS(getMongoClient().getDB(dbName));
        }
    }


    @Override
    public GridFSDBFile getFileById(Object id) {
        DBObject query  = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

    @Override
    public GridFSDBFile getFileByName(String fileName) {
        DBObject query  = new BasicDBObject("filename", fileName);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

    @Override
    public boolean write(File file) {
        return write(file.getAbsolutePath(), file.getName());
    }

    @Override
    public boolean write(String filePath, String fileName) {
        File file =new File(filePath+"/"+fileName);
        if(!file.exists()) return false;
        try {
            Object id = System.currentTimeMillis();
            DBObject query  = new BasicDBObject("_id", id);
            GridFSDBFile gridFSDBFile = gridFS.findOne(query);
            if(gridFSDBFile == null){
                GridFSInputFile gridFSInputFile = gridFS.createFile(file);
                gridFSInputFile.setId(id);
                gridFSInputFile.setFilename(fileName);
                gridFSInputFile.save();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean read(String filePath, String fileName) {
        GridFSDBFile gridFSDBFile = getFileByName(fileName);
        if(gridFSDBFile == null) {
            return false;
        }
        return read(gridFSDBFile, filePath, fileName);
    }

    @Override
    public boolean read(GridFSDBFile gridFSDBFile, String filePath, String fileName) {
        if(gridFSDBFile != null){
            try {
                gridFSDBFile.writeTo(new FileOutputStream(filePath+"/"+fileName));
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void deleteByFileId(String id) {
        gridFS.remove(id);
    }

    @Override
    public void deleteByFileName(String fileName) {
        gridFS.remove(fileName);
    }
}
